package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.database.OracleConnection;
import cpb.dwh_bi_api.models.CustomerModel;
import cpb.dwh_bi_api.models.MiniLoanGridModel;
import cpb.dwh_bi_api.repositories.MiniLoanGridRepository;
import cpb.dwh_bi_api.result_set.MiniLoanGridResultSet;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.util.List;

@Service
public class MiniLoanGridService {

    private MiniLoanGridRepository miniLoanGridRepository;

    @Autowired
    private MiniLoanGridResultSet miniLoanGridResultSet;

    public List<MiniLoanGridModel> getAll(int show, String branch){

        OracleConnection oracleConn = new OracleConnection();

        String query = "";

        query = miniLoanGridRepository.getDefault(show, branch);

        ResultSet result = oracleConn.selectAll(query);

        return miniLoanGridResultSet.getAll(result);

    }

    public List<MiniLoanGridModel> getExport(int show, String branch,String reportDate, String currency){

        OracleConnection oracleConn = new OracleConnection();

        String query = "";

        query = miniLoanGridRepository.getExport(show, branch, reportDate, currency);

        ResultSet result = oracleConn.selectAll(query);

        return miniLoanGridResultSet.getAll(result);

    }

    public List<MiniLoanGridModel> getByBranch(String branch, String cid,String reportDate,int show, String coid){

        OracleConnection oracleConn = new OracleConnection();

        String query = "";

        query = miniLoanGridRepository.getByFilter(show, branch, cid, reportDate, coid);

        ResultSet result = oracleConn.selectAll(query);

        return miniLoanGridResultSet.getAll(result);

    }

    public MiniLoanGridModel getByAAID(String id){
        OracleConnection oracleConn = new OracleConnection();

        String query = "";

        query = miniLoanGridRepository.getByAAID(id);

        ResultSet result = oracleConn.selectAll(query);

        return miniLoanGridResultSet.getOne(result);
    }

    public void writeToCsv(List<MiniLoanGridModel> miniLoanGrids, Writer writer) {
        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            printer.printRecord(
                    "CUSTOMERID",
                    "BRANCH",
                    "ARRANGEMENT_ID",
                    "CONTRACTLD",
                    "SHORT_NAME_EN",
                    "SHORT_NAME_KH",
                    "GENDER",
                    "DISBURSEDATE",
                    "LOANSIZE",
                    "MATURITY_DATE",
                    "OUTSTANDING",
                    "RATE",
                    "DUE_DATE",
                    "PRINCIPAL_DUE",
                    "INTEREST_DUE",
                    "PENALTY_DUE",
                    "DAYDUE",
                    "CREDITOFFICER",
                    "CREDITOFFICER_NAME",
                    "DATE_OF_BIRTH",
                    "AGE",
                    "CURRENCY",
                    "REPORTDATE",
                    "PROVINCE",
                    "DISTRICT",
                    "COMMUNCE",
                    "COLL_ISSUE_DATE",
                    "COLLATERAL_TYPE",
                    "COLL_TYPE",
                    "COLL_REMARK"
                    );
            for (MiniLoanGridModel miniLoanGrid:miniLoanGrids){
                printer.printRecord(
                        miniLoanGrid.getCustomerid(),
                        miniLoanGrid.getBranch(),
                        miniLoanGrid.getArrangement_id(),
                        miniLoanGrid.getContractld(),
                        miniLoanGrid.getShort_name_en(),
                        miniLoanGrid.getShort_name_kh(),
                        miniLoanGrid.getGender(),
                        miniLoanGrid.getDisbursedate(),
                        miniLoanGrid.getLoansize(),
                        miniLoanGrid.getMaturity_date(),
                        miniLoanGrid.getOutstanding(),
                        miniLoanGrid.getRate(),
                        miniLoanGrid.getDue_date(),
                        miniLoanGrid.getPrincipal_due(),
                        miniLoanGrid.getInterest_due(),
                        miniLoanGrid.getPenalty_due(),
                        miniLoanGrid.getDaydue(),
                        miniLoanGrid.getCreditofficer(),
                        miniLoanGrid.getCreditofficer_name(),
                        miniLoanGrid.getDate_of_birth(),
                        miniLoanGrid.getAge(),
                        miniLoanGrid.getCurrency(),
                        miniLoanGrid.getReportdate(),
                        miniLoanGrid.getProvince(),
                        miniLoanGrid.getDistrict(),
                        miniLoanGrid.getCommunce(),
                        miniLoanGrid.getColl_issue_date(),
                        miniLoanGrid.getColl_type(),
                        miniLoanGrid.getColl_remark()
                );
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
