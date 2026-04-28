package cpb.dwh_bi_api.models;

import lombok.Data;

@Data
public class MiniLoanGridModel {
    public String customerid;
    public String branch;
    public String arrangement_id;
    public String contractld;
    public String short_name_en;
    public String short_name_kh;
    public String gender;
    public String disbursedate;
    public String loansize;
    public String maturity_date;
    public String outstanding;
    public String rate;
    public String due_date;
    public String principal_due;
    public String interest_due;
    public String penalty_due;
    public String daydue;
    public String creditofficer;
    public String creditofficer_name;
    public String date_of_birth;
    public String age;
    public String currency;
    public String reportdate;
    public String province;
    public String district;
    public String communce;
    public String coll_issue_date;
    public String collateral_type;
    public String coll_type;
    public String coll_remark;

}
