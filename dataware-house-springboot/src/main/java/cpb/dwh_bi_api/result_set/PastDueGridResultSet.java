package cpb.dwh_bi_api.result_set;

import cpb.dwh_bi_api.models.PastDueGridModel;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class PastDueGridResultSet {

    public List<PastDueGridModel> getAll(ResultSet result){
        List<PastDueGridModel> pastDueGrids = new ArrayList<>();
        try {

            while (result.next()){
                PastDueGridModel pastDueGrid = new PastDueGridModel();
                pastDueGrid.setArrangement_id(result.getString("ARRANGEMENT_ID"));
                pastDueGrid.setReportdate(result.getString("REPORTDATE"));
                pastDueGrid.setBranch(result.getString("BRANCH"));
                pastDueGrid.setContractld(result.getString("CONTRACTLD"));
                pastDueGrid.setShort_name_en(result.getString("SHORT_NAME_EN"));
                pastDueGrid.setGender(result.getString("GENDER"));
                pastDueGrid.setCreditofficer(result.getString("CREDITOFFICER"));
                pastDueGrid.setCreditofficer_name(result.getString("CREDITOFFICER_NAME"));
                pastDueGrid.setDisbursedate(result.getString("DISBURSEDATE"));
                pastDueGrid.setLoansize(result.getString("LOANSIZE"));
                pastDueGrid.setRate(result.getString("RATE"));
                pastDueGrid.setCurrency(result.getString("CURRENCY"));
                pastDueGrid.setLoanterm(result.getString("LOANTERM"));
                pastDueGrid.setBalance(result.getString("BALANCE"));
                pastDueGrid.setPrincipal_due(result.getString("PRINCIPAL_DUE"));
                pastDueGrid.setInterest_due(result.getString("INTEREST_DUE"));
                pastDueGrid.setDaydue(result.getString("DAYDUE"));
                pastDueGrid.setProvince(result.getString("PROVINCE"));
                pastDueGrid.setDistrict(result.getString("DISTRICT"));
                pastDueGrid.setCommunce(result.getString("COMMUNCE"));
                pastDueGrid.setVillage(result.getString("VILLAGE"));
                pastDueGrid.setCall_dpd(result.getString("CALL_DPD"));
                pastDueGrid.setMax_dpd(result.getString("MAX_DPD"));

                pastDueGrids.add(pastDueGrid);

            }

        }catch (Exception e){

        }
        return pastDueGrids;
    }

    public PastDueGridModel getOne(ResultSet result){
        PastDueGridModel pastDueGrid = new PastDueGridModel();
        try {

            while (result.next()){

                pastDueGrid.setArrangement_id(result.getString("ARRANGEMENT_ID"));
                pastDueGrid.setReportdate(result.getString("REPORTDATE"));
                pastDueGrid.setBranch(result.getString("BRANCH"));
                pastDueGrid.setContractld(result.getString("CONTRACTLD"));
                pastDueGrid.setShort_name_en(result.getString("SHORT_NAME_EN"));
                pastDueGrid.setGender(result.getString("GENDER"));
                pastDueGrid.setCreditofficer(result.getString("CREDITOFFICER"));
                pastDueGrid.setCreditofficer_name(result.getString("CREDITOFFICER_NAME"));
                pastDueGrid.setDisbursedate(result.getString("DISBURSEDATE"));
                pastDueGrid.setLoansize(result.getString("LOANSIZE"));
                pastDueGrid.setRate(result.getString("RATE"));
                pastDueGrid.setCurrency(result.getString("CURRENCY"));
                pastDueGrid.setLoanterm(result.getString("LOANTERM"));
                pastDueGrid.setBalance(result.getString("BALANCE"));
                pastDueGrid.setPrincipal_due(result.getString("PRINCIPAL_DUE"));
                pastDueGrid.setInterest_due(result.getString("INTEREST_DUE"));
                pastDueGrid.setDaydue(result.getString("DAYDUE"));
                pastDueGrid.setProvince(result.getString("PROVINCE"));
                pastDueGrid.setDistrict(result.getString("DISTRICT"));
                pastDueGrid.setCommunce(result.getString("COMMUNCE"));
                pastDueGrid.setVillage(result.getString("VILLAGE"));
                pastDueGrid.setCall_dpd(result.getString("CALL_DPD"));
                pastDueGrid.setMax_dpd(result.getString("MAX_DPD"));

            }

        }catch (Exception e){

        }
        return pastDueGrid;
    }
}
