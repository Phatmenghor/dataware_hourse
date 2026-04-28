package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.database.OracleConnection;
import cpb.dwh_bi_api.models.MiniLoanGridModel;
import cpb.dwh_bi_api.models.PastDueGridModel;
import cpb.dwh_bi_api.repositories.PastDueGridRepository;
import cpb.dwh_bi_api.result_set.PastDueGridResultSet;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Writer;
import java.sql.ResultSet;
import java.util.List;

@Service
public class PastDueGridService {

    private PastDueGridRepository pastDueGridRepository;

    @Autowired
    private PastDueGridResultSet pastDueGridResultSet;


    public List<PastDueGridModel> getAll(int limit, String branch){

        OracleConnection oracleConn = new OracleConnection();

        String query = pastDueGridRepository.getAll(limit, branch);
        ResultSet result = oracleConn.selectAll(query);

        return pastDueGridResultSet.getAll(result);

    }

    public List<PastDueGridModel> getByBranch(String branch, String aaid, String reportDate, int show){

        OracleConnection oracleConn = new OracleConnection();

        String query = "";

        query = pastDueGridRepository.getByFilter(show, branch, aaid, reportDate);

        ResultSet result = oracleConn.selectAll(query);

        return pastDueGridResultSet.getAll(result);

    }

    public PastDueGridModel getById(String aaid){

        OracleConnection oracleConn = new OracleConnection();

        String query = "";

        query = pastDueGridRepository.getById(aaid);

        ResultSet result = oracleConn.selectAll(query);

        return pastDueGridResultSet.getOne(result);

    }

    public List<PastDueGridModel> getExport(int show, String branch,String reportDate, String currency){
        OracleConnection oracleConn = new OracleConnection();

        String query = "";

        query = pastDueGridRepository.getExport(show, branch, reportDate, currency);

        ResultSet result = oracleConn.selectAll(query);

        return pastDueGridResultSet.getAll(result);
    }

    public void writeToCsv(List<PastDueGridModel> pastDueGridModels, Writer writer) {

        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            printer.printRecord(
                    "ARRANGEMENT_ID",
                    "REPORTDATE",
                    "BRANCH",
                    "CONTRACTLD",
                    "SHORT_NAME_EN",
                    "GENDER",
                    "CREDITOFFICER",
                    "CREDITOFFICER_NAME",
                    "DISBURSEDATE",
                    "LOANSIZE",
                    "RATE",
                    "CURRENCY",
                    "LOANTERM",
                    "BALANCE",
                    "PRINCIPAL_DUE",
                    "INTEREST_DUE",
                    "DAYDUE",
                    "PROVINCE",
                    "DISTRICT",
                    "COMMUNCE",
                    "VILLAGE",
                    "CALL_DPD",
                    "MAX_DPD");

            for (PastDueGridModel pastDueGridModel:pastDueGridModels){
                printer.printRecord(
                        pastDueGridModel.getArrangement_id(),
                        pastDueGridModel.getReportdate(),
                        pastDueGridModel.getBranch(),
                        pastDueGridModel.getContractld(),
                        pastDueGridModel.getShort_name_en(),
                        pastDueGridModel.getGender(),
                        pastDueGridModel.getCreditofficer(),
                        pastDueGridModel.getCreditofficer_name(),
                        pastDueGridModel.getDisbursedate(),
                        pastDueGridModel.getLoansize(),
                        pastDueGridModel.getRate(),
                        pastDueGridModel.getCurrency(),
                        pastDueGridModel.getLoanterm(),
                        pastDueGridModel.getBalance(),
                        pastDueGridModel.getPrincipal_due(),
                        pastDueGridModel.getInterest_due(),
                        pastDueGridModel.getDaydue(),
                        pastDueGridModel.getProvince(),
                        pastDueGridModel.getDistrict(),
                        pastDueGridModel.getCommunce(),
                        pastDueGridModel.getVillage(),
                        pastDueGridModel.getCall_dpd(),
                        pastDueGridModel.getMax_dpd()

                        );
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
