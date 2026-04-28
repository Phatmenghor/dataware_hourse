package cpb.dwh_bi_api.generator;

import cpb.dwh_bi_api.models.CustomerModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class CustomerExcelGenerator {

    private List<CustomerModel> customerModelList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public CustomerExcelGenerator(List < CustomerModel > customerModelList) {
        this.customerModelList = customerModelList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("Student");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row,0,"CUSTOMERID", style);
        createCell(row,1,"MNEMONIC", style);
        createCell(row,2,"SHORTNAMEEN", style);
        createCell(row,3,"SHORTNAMEKH", style);
        createCell(row,4,"NAMEEN", style);
        createCell(row,5,"NAMEKH", style);
        createCell(row,6,"STREET", style);
        createCell(row,7,"TOWNCOUNTRY", style);
        createCell(row,8,"COUNTRY", style);
        createCell(row,9,"SECTOR", style);
        createCell(row,10,"ACCOUNTOFFICER", style);
        createCell(row,11,"INDUSTRY", style);
        createCell(row,12,"TARGET", style);
        createCell(row,13,"NATIONALITY", style);
        createCell(row,14,"CUSTOMERSTATUS", style);
        createCell(row,15,"RESIDENCE", style);
        createCell(row,16,"CONTACTDATE", style);
        createCell(row,17,"LEGALID", style);
        createCell(row,18,"LEGALDOCNAME", style);
        createCell(row,19,"LEGALHOLDERNAME", style);
        createCell(row,20,"LEGALISSDATE", style);
        createCell(row,21,"BIRTHINCORPDATE", style);
        createCell(row,22,"LANGUAGE", style);
        createCell(row,23,"COMPANYBOOK", style);
        createCell(row,24,"TITLE", style);
        createCell(row,25,"GIVENNAMES", style);
        createCell(row,26,"FAMILYNAME", style);
        createCell(row,27,"GENDER", style);
        createCell(row,28,"DATEOFBIRTH", style);
        createCell(row,29,"MARITALSTATUS", style);
        createCell(row,30,"PHONE1", style);
        createCell(row,31,"PHONE2", style);
        createCell(row,32,"OCCUPATION", style);
        createCell(row,33,"AMLCHECK", style);
        createCell(row,34,"AMLRESULT", style);
        createCell(row,35,"LOCATION", style);
        createCell(row,36,"CUSTLEGACYNO", style);
        createCell(row,37,"CUSTOWNERSHIP", style);
        createCell(row,38,"COCODE", style);
        createCell(row,39,"EXPIRYDATE", style);
        createCell(row,40,"RELMANAGER", style);
        createCell(row,41,"STAFF", style);
        createCell(row,42,"SMS1", style);
        createCell(row,43,"EMAIL1", style);
        createCell(row,44,"ASSETCLASS", style);
        createCell(row,45,"REFERRALBY", style);
        createCell(row,46,"CUSTPLOB", style);
        createCell(row,47,"ATMFLAG", style);
        createCell(row,48,"ATMSECUREWORD", style);
        createCell(row,49,"ATMCARDSNAME", style);
        createCell(row,50,"ROLE", style);
        createCell(row,51,"REPORTDATE", style);

    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (CustomerModel record: customerModelList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getCustomerid(), style);
            createCell(row, columnCount++, record.getShort_name_en(), style);
            createCell(row, columnCount++, record.getShort_name_kh(), style);
            createCell(row, columnCount++, record.getName_en(), style);
            createCell(row, columnCount++, record.getName_kh(), style);
            createCell(row, columnCount++, record.getStreet(), style);
            createCell(row, columnCount++, record.getTown_country(), style);
            createCell(row, columnCount++, record.getCountry(), style);
            createCell(row, columnCount++, record.getSector(), style);
            createCell(row, columnCount++, record.getAccount_officer(), style);
            createCell(row, columnCount++, record.getIndustry(), style);
            createCell(row, columnCount++, record.getTarget(), style);
            createCell(row, columnCount++, record.getNationality(), style);
            createCell(row, columnCount++, record.getCustomer_status(), style);
            createCell(row, columnCount++, record.getResidence(), style);
            createCell(row, columnCount++, record.getContact_date(), style);
            createCell(row, columnCount++, record.getLegal_id(), style);
            createCell(row, columnCount++, record.getLegal_doc_name(), style);
            createCell(row, columnCount++, record.getLegal_holder_name(), style);
            createCell(row, columnCount++, record.getLegal_iss_date(), style);
            createCell(row, columnCount++, record.getBirth_incorp_date(), style);
            createCell(row, columnCount++, record.getLanguage(), style);
            createCell(row, columnCount++, record.getCompany_book(), style);
            createCell(row, columnCount++, record.getTitle(), style);
            createCell(row, columnCount++, record.getGiven_names(), style);
            createCell(row, columnCount++, record.getFamily_name(), style);
            createCell(row, columnCount++, record.getGender(), style);
            createCell(row, columnCount++, record.getDate_of_birth(), style);
            createCell(row, columnCount++, record.getMarital_status(), style);
            createCell(row, columnCount++, record.getPhone_1(), style);
            createCell(row, columnCount++, record.getPhone_2(), style);
            createCell(row, columnCount++, record.getOccupation(), style);
            createCell(row, columnCount++, record.getAml_check(), style);
            createCell(row, columnCount++, record.getAml_result(), style);
            createCell(row, columnCount++, record.getLocation(), style);
            createCell(row, columnCount++, record.getCust_legacy_no(), style);
            createCell(row, columnCount++, record.getCust_ownership(), style);
            createCell(row, columnCount++, record.getCo_code(), style);
            createCell(row, columnCount++, record.getExpiry_date(), style);
            createCell(row, columnCount++, record.getRel_manager(), style);
            createCell(row, columnCount++, record.getStaff(), style);
            createCell(row, columnCount++, record.getSms_1(), style);
            createCell(row, columnCount++, record.getEmail_1(), style);
            createCell(row, columnCount++, record.getAsset_class(), style);
            createCell(row, columnCount++, record.getReferral_by(), style);
            createCell(row, columnCount++, record.getCust_plob(), style);
            createCell(row, columnCount++, record.getAtm_flag(), style);
            createCell(row, columnCount++, record.getAtm_cards_name(), style);
            createCell(row, columnCount++, record.getRole(), style);
            createCell(row, columnCount++, record.getReportdate(), style);

        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


}
