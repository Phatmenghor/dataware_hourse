package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.database.OracleConnection;
import cpb.dwh_bi_api.database.OracleConnectionJDBC;
import cpb.dwh_bi_api.dto.ReportRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportingService {

    @Autowired
    private OracleConnectionJDBC oracleConnection;

    @SneakyThrows
    public List<Map<String, Object>> getAll(String query) {
        OracleConnection oracleConn = new OracleConnection();

        ResultSet result = oracleConn.selectAll(query);
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = result.getMetaData();
            while (result.next()) {
                int numColumns = rsmd.getColumnCount();
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= numColumns; i++) {
                    String columnName = rsmd.getColumnName(i);
                    row.put(columnName, result.getObject(columnName));
                }

                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            result.close();
        }
        return list;
    }

    public int calculateTotalRecords(String query) throws Exception {
        String countQuery = "SELECT COUNT(*) AS total FROM (" + query + ")";
        OracleConnection oracleConn = new OracleConnection();

        ResultSet result = null;
        int totalRecords = 0;
        try {
            result = oracleConn.selectAll(countQuery);
            if (result.next()) {
                totalRecords = result.getInt("total");
            }
        } finally {
            if (result != null) {
                result.close();
            }
        }
        return totalRecords;
    }

    public byte[] generateReport(ReportRequest request) throws Exception {
        File tempFile = File.createTempFile("report", ".xlsx");
        SXSSFWorkbook workbook = null;
        FileOutputStream tempFileOutputStream = null;


        try {
            tempFileOutputStream = new FileOutputStream(tempFile);// Use SXSSFWorkbook for large datasets
            workbook = new SXSSFWorkbook(100);
            // Create reusable styles
            CellStyle labelStyle = createLabelCellStyle(workbook);
            CellStyle valueStyle = createValueCellStyle(workbook);
            CellStyle headerStyle = createHeaderCellStyle(workbook);
            CellStyle dataStyle = createDataCellStyle(workbook);

            // Create a sheet
            Sheet sheet = createSheetWithUniqueName(workbook);
            addLogoToSheet(sheet);

            // Add report information rows
            addReportInfoRow(sheet, "A4:B4", "Report Name:", request.getReportName(), labelStyle, valueStyle);
            addReportInfoRow(sheet, "A5:B5", "Date:", request.getReportDate(), labelStyle, valueStyle);
            addReportInfoRow(sheet, "A6:B6", "Branch:", request.getBranch(), labelStyle, valueStyle);

            // Add blank rows between report info and headers
            int currentRow = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < 3; i++) {
                sheet.createRow(currentRow + i); // Create 3 blank rows
            }

            // Add Header Row
            addHeaders(sheet, request.getColumns(), headerStyle);

            try (Connection connection = oracleConnection.getConnection();
                 Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {

                statement.setFetchSize(1600);  // Fetch in batches of 1000 rows
                ResultSet resultSet = statement.executeQuery(request.getQuery());

                int rowCount = 7;  // Starting row index after header
                int sheetNumber = 1;  // Row number for data
                int maxRowsPerSheet = 1000000;
                int index = 1;
                while (resultSet.next()) {
                    // Map the row to a Map<String, Object> to handle dynamic columns
                    if (rowCount > maxRowsPerSheet) {
                        // Create a new sheet when row limit is exceeded
                        sheetNumber++;
                        System.out.println("###==sheetNumber" + sheetNumber);
                        sheet = createSheetWithUniqueName(workbook);
                        addLogoToSheet(sheet);
                        // Add report information rows
                        addReportInfoRow(sheet, "A4:B4", "Report Name:", request.getReportName(), labelStyle, valueStyle);
                        addReportInfoRow(sheet, "A5:B5", "Date:", request.getReportDate(), labelStyle, valueStyle);
                        addReportInfoRow(sheet, "A6:B6", "Branch:", request.getBranch(), labelStyle, valueStyle);

                        for (int i = 0; i < 3; i++) {
                            sheet.createRow(currentRow + i); // Create 3 blank rows
                        }
                        // Add Header Row
                        addHeaders(sheet, request.getColumns(), headerStyle); // Add headers again for the new sheet
                        rowCount = 7;  // Reset row count after creating a new sheet
                    }

                    // Map data from result set and add it to the sheet
                    Map<String, Object> row = new HashMap<>();
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
                    }

                    addDataRows(sheet, row, request.getColumns(), dataStyle, rowCount++, index++);

                    if (rowCount % 100 == 0) {
                        ((SXSSFSheet) sheet).flushRows(100); // Keep only the last 100 rows in memory
                    }
                }

                // Set column widths after adding all data
                setColumnWidths(sheet, request.getColumns());
                workbook.write(tempFileOutputStream);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Error while generating report", e);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Unexpected error occurred while generating the report", e);
        } finally {

            // Make sure to close the workbook properly to release any resources
            if (workbook != null) {
                workbook.dispose();  // Dispose the workbook to clear resources (important for SXSSFWorkbook)
            }


            if (tempFileOutputStream != null) {
                tempFileOutputStream.close();
            }

        }

        byte[] result;
        try (FileInputStream fileInputStream = new FileInputStream(tempFile);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            result = byteArrayOutputStream.toByteArray();
        } finally {
            // Ensure the temporary file is deleted after reading its contents
            if (tempFile.exists()) {
                tempFile.delete();
            }

            if (tempFile.exists() && !tempFile.delete()) {
                System.err.println("Failed to delete temporary file: " + tempFile.getAbsolutePath());
            }


        }
        return result;
    }

    private void addLogoToSheet(Sheet sheet) throws IOException {
        try (InputStream logoStream = getClass().getClassLoader().getResourceAsStream("cpb/dwh_bi_api/image/logo.png")) {
            byte[] imageBytes = IOUtils.toByteArray(logoStream);
            int pictureIdx = sheet.getWorkbook().addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
            Drawing<?> drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = sheet.getWorkbook().getCreationHelper().createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            anchor.setCol2(1);
            anchor.setRow2(1);

            Picture picture = drawing.createPicture(anchor, pictureIdx);
            picture.resize(4, 1); // Resize the picture
            sheet.setColumnWidth(0, 256 * 4);
            Row row = sheet.getRow(0) == null ? sheet.createRow(0) : sheet.getRow(0);
            row.setHeightInPoints(50);
        }
    }


    private void addReportInfoRow(Sheet sheet, String cellRange, String label, String value,
                                  CellStyle labelStyle, CellStyle valueStyle) {
        int labelRowNum = sheet.getPhysicalNumberOfRows();
        Row labelRow = sheet.createRow(labelRowNum);
        sheet.addMergedRegion(new CellRangeAddress(labelRowNum, labelRowNum, 0, 1));
        Cell labelCell = labelRow.createCell(0);
        labelCell.setCellValue(label);
        labelCell.setCellStyle(labelStyle);

        sheet.addMergedRegion(new CellRangeAddress(labelRowNum, labelRowNum, 2, 3));
        Cell valueCell = labelRow.createCell(2);
        valueCell.setCellValue(value);
        valueCell.setCellStyle(valueStyle);
    }

    private CellStyle createLabelCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);
        return style;
    }

    private CellStyle createValueCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        return style;
    }

    private String getPaginatedQuery(String query, int maxRows) {
        return "SELECT * FROM (" + query + ") WHERE ROWNUM <= " + maxRows;
    }

    private void addHeaders(Sheet sheet, List<Map<String, String>> columns, CellStyle headerStyle) {
        Row headerRow = sheet.createRow(6);  // Create header row
        headerRow.setHeightInPoints(20);  // Set row height for visibility

        // Add "No" column as the first header
        Cell noHeaderCell = headerRow.createCell(0);
        noHeaderCell.setCellValue("No   ");
        noHeaderCell.setCellStyle(headerStyle);

        // Add header cells from columns list
        for (int i = 0; i < columns.size(); i++) {
            String header = columns.get(i).get("header");
            Cell cell = headerRow.createCell(i + 1);
            cell.setCellValue(header);
            cell.setCellStyle(headerStyle);
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);

        style.setVerticalAlignment(VerticalAlignment.CENTER);
        if (workbook instanceof XSSFWorkbook || workbook instanceof SXSSFWorkbook) {
            // For SXSSFWorkbook, we need to use indexed color for the background fill
            style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());  // Using predefined IndexedColors
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  // Solid fill
        }
        return style;
    }

    private void addDataRows(Sheet sheet, Map<String, Object> rowData, List<Map<String, String>> columns,
                             CellStyle dataStyle, int rowIndex, int index) {
        Row row = sheet.createRow(rowIndex);
        int indexCell = 0;  // Index column for data rows
        // Add index number as the first column (e.g., "1", "2", "3", etc.)
        Cell indexCellObj = row.createCell(indexCell++);
        indexCellObj.setCellValue(index++); // Use rowIndex as a row number
        indexCellObj.setCellStyle(dataStyle);

        // Add the actual data cells
        for (int i = 0; i < columns.size(); i++) {
            String key = columns.get(i).get("key");
            Object value = rowData.get(key);
            Cell cell = row.createCell(i + 1);
            if (value != null) {
                cell.setCellValue(value.toString());
            } else {
                cell.setCellValue("");  // Handle null values
            }
            cell.setCellStyle(dataStyle);
        }
    }

    private CellStyle createDataCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);  // Set font size to 10
        style.setFont(font);
        return style;
    }

    private Sheet createSheetWithUniqueName(SXSSFWorkbook workbook) {
        return workbook.createSheet();
    }

    private void setColumnWidths(Sheet sheet, List<Map<String, String>> columns) {
        // Iterate over each column to determine the maximum header content length
        for (int i = 0; i <= columns.size(); i++) {

            if (i == 0) {
                sheet.setColumnWidth(i, 2000);
            } else {
                sheet.setColumnWidth(i, 5000);  // Use i directly since it’s 0-indexed
            }
        }
    }

}
