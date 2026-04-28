package cpb.dwh_bi_api.models;

import lombok.Data;

@Data
public class PastDueGridModel {

    public String arrangement_id;
    public String reportdate;
    public String branch;
    public String contractld;
    public String short_name_en;
    public String gender;
    public String creditofficer;
    public String creditofficer_name;
    public String disbursedate;
    public String loansize;
    public String rate;
    public String currency;
    public String loanterm;
    public String balance;
    public String principal_due;
    public String interest_due;
    public String daydue;
    public String province;
    public String district;
    public String communce;
    public String village;
    public String call_dpd;
    public String max_dpd;

}
