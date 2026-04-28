package cpb.dwh_bi_api.result_set;

import cpb.dwh_bi_api.models.CustomerModel;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerResultSet {

    public List<CustomerModel> getAll(ResultSet result){

        List<CustomerModel> customers = new ArrayList<>();

        try{

            while (result.next()) {
                CustomerModel customer = new CustomerModel();
                customer.setCustomerid(result.getString("CUSTOMERID"));
                customer.setMnemonic(result.getString("MNEMONIC"));
                customer.setShort_name_en(result.getString("SHORT_NAME_EN"));
                customer.setShort_name_kh(result.getString("SHORT_NAME_KH"));
                customer.setName_en(result.getString("NAME_EN"));
                customer.setName_kh(result.getString("NAME_KH"));
                customer.setStreet(result.getString("STREET"));
                customer.setTown_country(result.getString("TOWN_COUNTRY"));
                customer.setCountry(result.getString("COUNTRY"));
                customer.setSector(result.getString("SECTOR"));
                customer.setAccount_officer(result.getString("ACCOUNT_OFFICER"));
                customer.setIndustry(result.getString("INDUSTRY"));
                customer.setTarget(result.getString("TARGET"));
                customer.setNationality(result.getString("NATIONALITY"));
                customer.setCustomer_status(result.getString("CUSTOMER_STATUS"));
                customer.setResidence(result.getString("RESIDENCE"));
                customer.setContact_date(result.getString("CONTACT_DATE"));
                customer.setLegal_id(result.getString("LEGAL_ID"));
                customer.setLegal_doc_name(result.getString("LEGAL_DOC_NAME"));
                customer.setLegal_holder_name(result.getString("LEGAL_HOLDER_NAME"));
                customer.setLegal_iss_date(result.getString("LEGAL_ISS_DATE"));
                customer.setBirth_incorp_date(result.getString("BIRTH_INCORP_DATE"));
                customer.setLanguage(result.getString("LANGUAGE"));
                customer.setCompany_book(result.getString("COMPANY_BOOK"));
                customer.setTitle(result.getString("TITLE"));
                customer.setGiven_names(result.getString("GIVEN_NAMES"));
                customer.setFamily_name(result.getString("FAMILY_NAME"));
                customer.setGender(result.getString("GENDER"));
                customer.setDate_of_birth(result.getString("DATE_OF_BIRTH"));
                customer.setMarital_status(result.getString("MARITAL_STATUS"));
                customer.setPhone_1(result.getString("PHONE_1"));
                customer.setPhone_2(result.getString("PHONE_2"));
                customer.setOccupation(result.getString("OCCUPATION"));
                customer.setAml_check(result.getString("AML_CHECK"));
                customer.setAml_result(result.getString("AML_RESULT"));
                customer.setLocation(result.getString("LOCATION"));
                customer.setCust_legacy_no(result.getString("CUST_LEGACY_NO"));
                customer.setCust_ownership(result.getString("CUST_OWNERSHIP"));
                customer.setCo_code(result.getString("CO_CODE"));
                customer.setExpiry_date(result.getString("EXPIRY_DATE"));
                customer.setRel_manager(result.getString("REL_MANAGER"));
                customer.setStaff(result.getString("STAFF"));
                customer.setSms_1(result.getString("SMS_1"));
                customer.setEmail_1(result.getString("EMAIL_1"));
                customer.setAsset_class(result.getString("ASSET_CLASS"));
                customer.setReferral_by(result.getString("REFERRAL_BY"));
                customer.setCust_plob(result.getString("CUST_PLOB"));
                customer.setAtm_flag(result.getString("ATM_FLAG"));
                customer.setAtm_secure_word(result.getString("ATM_SECURE_WORD"));
                customer.setAtm_cards_name(result.getString("ATM_CARDS_NAME"));
                customer.setRole(result.getString("ROLE"));
                customer.setReportdate(result.getString("REPORTDATE"));

                customers.add(customer);
            }

        }catch (Exception e){

        }

        return  customers;
    }


    public CustomerModel getOne(ResultSet result){

        CustomerModel customer = new CustomerModel();

        try{

            while (result.next()) {
                customer.setCustomerid(result.getString("CUSTOMERID"));
                customer.setMnemonic(result.getString("MNEMONIC"));
                customer.setShort_name_en(result.getString("SHORT_NAME_EN"));
                customer.setShort_name_kh(result.getString("SHORT_NAME_KH"));
                customer.setName_en(result.getString("NAME_EN"));
                customer.setName_kh(result.getString("NAME_KH"));
                customer.setStreet(result.getString("STREET"));
                customer.setTown_country(result.getString("TOWN_COUNTRY"));
                customer.setCountry(result.getString("COUNTRY"));
                customer.setSector(result.getString("SECTOR"));
                customer.setAccount_officer(result.getString("ACCOUNT_OFFICER"));
                customer.setIndustry(result.getString("INDUSTRY"));
                customer.setTarget(result.getString("TARGET"));
                customer.setNationality(result.getString("NATIONALITY"));
                customer.setCustomer_status(result.getString("CUSTOMER_STATUS"));
                customer.setResidence(result.getString("RESIDENCE"));
                customer.setContact_date(result.getString("CONTACT_DATE"));
                customer.setLegal_id(result.getString("LEGAL_ID"));
                customer.setLegal_doc_name(result.getString("LEGAL_DOC_NAME"));
                customer.setLegal_holder_name(result.getString("LEGAL_HOLDER_NAME"));
                customer.setLegal_iss_date(result.getString("LEGAL_ISS_DATE"));
                customer.setBirth_incorp_date(result.getString("BIRTH_INCORP_DATE"));
                customer.setLanguage(result.getString("LANGUAGE"));
                customer.setCompany_book(result.getString("COMPANY_BOOK"));
                customer.setTitle(result.getString("TITLE"));
                customer.setGiven_names(result.getString("GIVEN_NAMES"));
                customer.setFamily_name(result.getString("FAMILY_NAME"));
                customer.setGender(result.getString("GENDER"));
                customer.setDate_of_birth(result.getString("DATE_OF_BIRTH"));
                customer.setMarital_status(result.getString("MARITAL_STATUS"));
                customer.setPhone_1(result.getString("PHONE_1"));
                customer.setPhone_2(result.getString("PHONE_2"));
                customer.setOccupation(result.getString("OCCUPATION"));
                customer.setAml_check(result.getString("AML_CHECK"));
                customer.setAml_result(result.getString("AML_RESULT"));
                customer.setLocation(result.getString("LOCATION"));
                customer.setCust_legacy_no(result.getString("CUST_LEGACY_NO"));
                customer.setCust_ownership(result.getString("CUST_OWNERSHIP"));
                customer.setCo_code(result.getString("CO_CODE"));
                customer.setExpiry_date(result.getString("EXPIRY_DATE"));
                customer.setRel_manager(result.getString("REL_MANAGER"));
                customer.setStaff(result.getString("STAFF"));
                customer.setSms_1(result.getString("SMS_1"));
                customer.setEmail_1(result.getString("EMAIL_1"));
                customer.setAsset_class(result.getString("ASSET_CLASS"));
                customer.setReferral_by(result.getString("REFERRAL_BY"));
                customer.setCust_plob(result.getString("CUST_PLOB"));
                customer.setAtm_flag(result.getString("ATM_FLAG"));
                customer.setAtm_secure_word(result.getString("ATM_SECURE_WORD"));
                customer.setAtm_cards_name(result.getString("ATM_CARDS_NAME"));
                customer.setRole(result.getString("ROLE"));
                customer.setReportdate(result.getString("REPORTDATE"));


            }

        }catch (Exception e){

        }

        return  customer;
    }
}
