package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.database.OracleConnection;
import cpb.dwh_bi_api.models.CustomerModel;
import cpb.dwh_bi_api.repositories.CustomerRepository;
import cpb.dwh_bi_api.result_set.CustomerResultSet;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.ResultSet;
import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    private CustomerResultSet customerResultSet;

    public List<CustomerModel> getAll(int show){

        OracleConnection oracleConn = new OracleConnection();

        String query = "";

        if(show ==0){
            query = customerRepository.getDefault();
        }else {
            query = customerRepository.getByPage(show);
        }

        ResultSet result = oracleConn.selectAll(query);

        return customerResultSet.getAll(result);

    }

    public CustomerModel getAllFilter(String cid, String branch){

        OracleConnection oracleConn = new OracleConnection();

        String query = "";

        if (cid !=""){
            query = customerRepository.getById(cid);
        } else if (branch !="") {
            query = customerRepository.getByBranch(branch);
        } else{
            query = customerRepository.getDefault();
        }

        ResultSet result = oracleConn.selectAll(query);

        return customerResultSet.getOne(result);

    }

    public void writeCustomerToCsv(List<CustomerModel> customers, Writer writer) {
        try {

            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            printer.printRecord("CUSTOMERID",
                    "MNEMONIC",
                    "SHORT NAME EN",
                    "SHORT NAME KH",
                    "NAME EN",
                    "NAME KH",
                    "STREET",
                    "TOWN COUNTRY",
                    "COUNTRY",
                    "SECTOR",
                    "ACCOUNT OFFICER",
                    "INDUSTRY",
                    "TARGET",
                    "NATIONALITY",
                    "CUSTOMER STATUS",
                    "RESIDENCE",
                    "CONTACT DATE",
                    "LEGAL ID",
                    "LEGAL DOC NAME",
                    "LEGAL HOLDER NAME",
                    "LEGAL ISS DATE",
                    "BIRTH INCORP DATE",
                    "LANGUAGE",
                    "COMPANY BOOK",
                    "TITLE",
                    "GIVEN NAMES",
                    "FAMILY NAME",
                    "GENDER",
                    "DATE OF BIRTH",
                    "MARITAL STATUS",
                    "PHONE 1",
                    "PHONE 2",
                    "OCCUPATION",
                    "AML CHECK",
                    "AML RESULT",
                    "LOCATION",
                    "CUST LEGACY NO",
                    "CUST OWNERSHIP",
                    "CO CODE",
                    "EXPIRY DATE",
                    "REL MANAGER",
                    "STAFF",
                    "SMS 1",
                    "EMAIL 1",
                    "ASSET CLASS",
                    "REFERRAL BY",
                    "CUST PLOB",
                    "ATM FLAG",
                    "ATM SECURE WORD",
                    "ATM CARDS NAME",
                    "ROLE",
                    "REPORTDATE"
                    );
            for (CustomerModel customer : customers) {
                printer.printRecord(
                        customer.getCustomerid(),
                        customer.getShort_name_en(),
                        customer.getShort_name_kh(),
                        customer.getName_en(),
                        customer.getName_kh(),
                        customer.getStreet(),
                        customer.getTown_country(),
                        customer.getCountry(),
                        customer.getSector(),
                        customer.getAccount_officer(),
                        customer.getIndustry(),
                        customer.getTarget(),
                        customer.getNationality(),
                        customer.getCustomer_status(),
                        customer.getResidence(),
                        customer.getContact_date(),
                        customer.getLegal_id(),
                        customer.getLegal_doc_name(),
                        customer.getLegal_holder_name(),
                        customer.getLegal_iss_date(),
                        customer.getBirth_incorp_date(),
                        customer.getLanguage(),
                        customer.getCompany_book(),
                        customer.getTitle(),
                        customer.getGiven_names(),
                        customer.getFamily_name(),
                        customer.getGender(),
                        customer.getDate_of_birth(),
                        customer.getMarital_status(),
                        customer.getPhone_1(),
                        customer.getPhone_2(),
                        customer.getOccupation(),
                        customer.getAml_check(),
                        customer.getAml_result(),
                        customer.getLocation(),
                        customer.getCust_legacy_no(),
                        customer.getCust_ownership(),
                        customer.getCo_code(),
                        customer.getExpiry_date(),
                        customer.getRel_manager(),
                        customer.getStaff(),
                        customer.getSms_1(),
                        customer.getEmail_1(),
                        customer.getAsset_class(),
                        customer.getReferral_by(),
                        customer.getCust_plob(),
                        customer.getAtm_flag(),
                        customer.getAtm_cards_name(),
                        customer.getRole(),
                        customer.getReportdate()
                        );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
