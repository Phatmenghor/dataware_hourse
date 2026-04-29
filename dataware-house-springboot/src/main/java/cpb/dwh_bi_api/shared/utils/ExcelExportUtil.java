package cpb.dwh_bi_api.shared.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Excel export utility for converting database results and data to Excel files.
 * Supports converting ResultSet and List<Map> data to Excel format.
 */
@Component
@Slf4j
public class ExcelExportUtil {

    @Value("${excel.export.enabled:true}")
    private boolean exportEnabled;

    @Value("${excel.export.temp-directory:./temp/excel}")
    private String tempDirectory;

    @Value("${excel.export.max-rows-per-sheet:10000}")
    private int maxRowsPerSheet;

    /**
     * Export ResultSet to Excel file
     * @param resultSet SQL ResultSet
     * @param filename Output filename
     * @return File path of exported Excel
     */
    public String exportResultSetToExcel(ResultSet resultSet, String filename) throws SQLException, IOException {
        if (!exportEnabled) {
            throw new IllegalStateException("Excel export is disabled");
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(metaData.getColumnName(i));
                cell.setCellStyle(getHeaderCellStyle(workbook));
            }

            // Write data rows
            int rowNum = 1;
            while (resultSet.next()) {
                if (rowNum > maxRowsPerSheet) {
                    log.warn("Maximum rows per sheet ({}) reached, stopping export", maxRowsPerSheet);
                    break;
                }

                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    Cell cell = row.createCell(i - 1);
                    setCellValue(cell, value);
                }
            }

            // Auto-size columns
            for (int i = 0; i < columnCount; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            String filePath = createOutputFile(workbook, filename);
            log.info("Excel file exported successfully: {}", filePath);
            return filePath;

        } finally {
            workbook.close();
        }
    }

    /**
     * Export List of Maps to Excel file
     * @param dataList List of row data (each map is a row)
     * @param headers Column headers
     * @param filename Output filename
     * @return File path of exported Excel
     */
    public String exportMapListToExcel(List<Map<String, Object>> dataList, List<String> headers, String filename) throws IOException {
        if (!exportEnabled) {
            throw new IllegalStateException("Excel export is disabled");
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        try {
            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers.get(i));
                cell.setCellStyle(getHeaderCellStyle(workbook));
            }

            // Write data rows
            int rowNum = 1;
            for (Map<String, Object> rowData : dataList) {
                if (rowNum > maxRowsPerSheet) {
                    log.warn("Maximum rows per sheet ({}) reached, stopping export", maxRowsPerSheet);
                    break;
                }

                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < headers.size(); i++) {
                    String header = headers.get(i);
                    Object value = rowData.get(header);
                    Cell cell = row.createCell(i);
                    setCellValue(cell, value);
                }
            }

            // Auto-size columns
            for (int i = 0; i < headers.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            String filePath = createOutputFile(workbook, filename);
            log.info("Excel file exported successfully: {}", filePath);
            return filePath;

        } finally {
            workbook.close();
        }
    }

    /**
     * Set cell value based on object type
     */
    private void setCellValue(Cell cell, Object value) {
        if (value == null) {
            cell.setCellValue("");
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue(value.toString());
        }
    }

    /**
     * Get header cell style (bold, filled background)
     */
    private CellStyle getHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /**
     * Create output file and return file path
     */
    private String createOutputFile(Workbook workbook, String filename) throws IOException {
        File dir = new File(tempDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filenameParts[] = filename.split("\\.");
        String finalFilename = filenameParts[0] + "_" + timestamp + ".xlsx";
        String filePath = tempDirectory + File.separator + finalFilename;

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }

        return filePath;
    }
}
