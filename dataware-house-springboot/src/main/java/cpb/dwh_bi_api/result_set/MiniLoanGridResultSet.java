package cpb.dwh_bi_api.result_set;

import cpb.dwh_bi_api.models.MiniLoanGridModel;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class MiniLoanGridResultSet {

    public List<MiniLoanGridModel> getAll(ResultSet result){
        List<MiniLoanGridModel> miniLoanGrids = new ArrayList<>();

        try {

            while (result.next()){
                MiniLoanGridModel miniLoanGrid = new MiniLoanGridModel();
                miniLoanGrid.setCustomerid(result.getString("CUSTOMERID"));
                miniLoanGrid.setBranch(result.getString("BRANCH"));
                miniLoanGrid.setArrangement_id(result.getString("ARRANGEMENT_ID"));
                miniLoanGrid.setContractld(result.getString("CONTRACTLD"));
                miniLoanGrid.setShort_name_en(result.getString("SHORT_NAME_EN"));
                miniLoanGrid.setShort_name_kh(result.getString("SHORT_NAME_KH"));
                miniLoanGrid.setGender(result.getString("GENDER"));
                miniLoanGrid.setDisbursedate(result.getString("DISBURSEDATE"));
                miniLoanGrid.setLoansize(result.getString("LOANSIZE"));
                miniLoanGrid.setMaturity_date(result.getString("MATURITY_DATE"));
                miniLoanGrid.setOutstanding(result.getString("OUTSTANDING"));
                miniLoanGrid.setRate(result.getString("RATE"));
                miniLoanGrid.setDue_date(result.getString("DUE_DATE"));
                miniLoanGrid.setPrincipal_due(result.getString("PRINCIPAL_DUE"));
                miniLoanGrid.setInterest_due(result.getString("INTEREST_DUE"));
                miniLoanGrid.setPenalty_due(result.getString("PENALTY_DUE"));
                miniLoanGrid.setDaydue(result.getString("DAYDUE"));
                miniLoanGrid.setCreditofficer(result.getString("CREDITOFFICER"));
                miniLoanGrid.setCreditofficer_name(result.getString("CREDITOFFICER_NAME"));
                miniLoanGrid.setDate_of_birth(result.getString("DATE_OF_BIRTH"));
                miniLoanGrid.setAge(result.getString("AGE"));
                miniLoanGrid.setCurrency(result.getString("CURRENCY"));
                miniLoanGrid.setReportdate(result.getString("REPORTDATE"));
                miniLoanGrid.setProvince(result.getString("PROVINCE"));
                miniLoanGrid.setDistrict(result.getString("DISTRICT"));
                miniLoanGrid.setCommunce(result.getString("COMMUNCE"));
                miniLoanGrid.setColl_issue_date(result.getString("COLL_ISSUE_DATE"));
                miniLoanGrid.setCollateral_type(result.getString("COLLATERAL_TYPE"));
                miniLoanGrid.setColl_type(result.getString("COLL_TYPE"));
                miniLoanGrid.setColl_remark(result.getString("COLL_REMARK"));

                miniLoanGrids.add(miniLoanGrid);

            }

        }catch (Exception e){

        }

        return miniLoanGrids;
    }

    public MiniLoanGridModel getOne(ResultSet result){
        MiniLoanGridModel miniLoanGrid = new MiniLoanGridModel();
        try {

            while (result.next()){

                miniLoanGrid.setCustomerid(result.getString("CUSTOMERID"));
                miniLoanGrid.setBranch(result.getString("BRANCH"));
                miniLoanGrid.setArrangement_id(result.getString("ARRANGEMENT_ID"));
                miniLoanGrid.setContractld(result.getString("CONTRACTLD"));
                miniLoanGrid.setShort_name_en(result.getString("SHORT_NAME_EN"));
                miniLoanGrid.setShort_name_kh(result.getString("SHORT_NAME_KH"));
                miniLoanGrid.setGender(result.getString("GENDER"));
                miniLoanGrid.setDisbursedate(result.getString("DISBURSEDATE"));
                miniLoanGrid.setLoansize(result.getString("LOANSIZE"));
                miniLoanGrid.setMaturity_date(result.getString("MATURITY_DATE"));
                miniLoanGrid.setOutstanding(result.getString("OUTSTANDING"));
                miniLoanGrid.setRate(result.getString("RATE"));
                miniLoanGrid.setDue_date(result.getString("DUE_DATE"));
                miniLoanGrid.setPrincipal_due(result.getString("PRINCIPAL_DUE"));
                miniLoanGrid.setInterest_due(result.getString("INTEREST_DUE"));
                miniLoanGrid.setPenalty_due(result.getString("PENALTY_DUE"));
                miniLoanGrid.setDaydue(result.getString("DAYDUE"));
                miniLoanGrid.setCreditofficer(result.getString("CREDITOFFICER"));
                miniLoanGrid.setCreditofficer_name(result.getString("CREDITOFFICER_NAME"));
                miniLoanGrid.setDate_of_birth(result.getString("DATE_OF_BIRTH"));
                miniLoanGrid.setAge(result.getString("AGE"));
                miniLoanGrid.setCurrency(result.getString("CURRENCY"));
                miniLoanGrid.setReportdate(result.getString("REPORTDATE"));
                miniLoanGrid.setProvince(result.getString("PROVINCE"));
                miniLoanGrid.setDistrict(result.getString("DISTRICT"));
                miniLoanGrid.setCommunce(result.getString("COMMUNCE"));
                miniLoanGrid.setColl_issue_date(result.getString("COLL_ISSUE_DATE"));
                miniLoanGrid.setCollateral_type(result.getString("COLLATERAL_TYPE"));
                miniLoanGrid.setColl_type(result.getString("COLL_TYPE"));
                miniLoanGrid.setColl_remark(result.getString("COLL_REMARK"));
            }

        }catch (Exception e){

        }

        return miniLoanGrid;
    }
}
