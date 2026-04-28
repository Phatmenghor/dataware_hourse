package cpb.dwh_bi_api.repositories;

public class MiniLoanGridRepository {

    public static String getAll(){
        return "SELECT * FROM V_RPT_MINI_GRID";
    }
    public static String getDefault(int limit, String branch){
        if(branch !=""){
            return "SELECT * FROM V_RPT_MINI_GRID  WHERE BRANCH='"+branch+"' AND REPORTDATE = (SELECT MAX(REPORTDATE) FROM V_RPT_MINI_GRID) FETCH NEXT "+limit+" ROWS ONLY";
        } else if(limit == 0){
            return "SELECT * FROM V_RPT_MINI_GRID  WHERE BRANCH='"+branch+"' AND REPORTDATE = (SELECT MAX(REPORTDATE) FROM V_RPT_MINI_GRID)";
        }
        return "SELECT * FROM V_RPT_MINI_GRID  WHERE REPORTDATE = (SELECT MAX(REPORTDATE) FROM V_RPT_MINI_GRID) FETCH NEXT "+limit+" ROWS ONLY";
    }


    public static String getExport(int limit, String branch, String reportDate, String currency){
        if(branch ==""){
            return "SELECT * FROM V_RPT_MINI_GRID  WHERE CURRENCY IN("+currency+") AND REPORTDATE ='"+reportDate+"'";
        }
        if(reportDate !=""){
            return "SELECT * FROM V_RPT_MINI_GRID  WHERE BRANCH='"+branch+"' AND CURRENCY IN("+currency+") AND REPORTDATE ='"+reportDate+"'";
        }
        return "SELECT * FROM V_RPT_MINI_GRID  WHERE BRANCH='"+branch+"' AND CURRENCY IN("+currency+") AND REPORTDATE = (SELECT MAX(REPORTDATE) FROM V_RPT_MINI_GRID)";

    }

    public static String getByAAID(String id){
        return "SELECT * FROM V_RPT_MINI_GRID WHERE ARRANGEMENT_ID='"+id+"'";
    }

    public static String getByFilter(int show, String branch, String cid, String reportDate, String coid){
        String q="";

        if(branch!="" && reportDate==""){
            q="SELECT * FROM V_RPT_MINI_GRID WHERE BRANCH='"+branch+"' FETCH NEXT "+show+" ROWS ONLY";
        }
        if(branch!="" && reportDate!=""){
            q="SELECT * FROM V_RPT_MINI_GRID WHERE BRANCH='"+branch+"' AND REPORTDATE='"+reportDate+"' FETCH NEXT "+show+" ROWS ONLY";
        }

        if(cid!="" && branch!=""){
            q="SELECT * FROM V_RPT_MINI_GRID WHERE BRANCH='"+branch+"' AND REPORTDATE='"+reportDate+"' AND CUSTOMERID='"+cid+"'";
        }

        if(coid!="" && branch!=""){
            q="SELECT * FROM V_RPT_MINI_GRID WHERE BRANCH='"+branch+"' AND REPORTDATE='"+reportDate+"' AND CREDITOFFICER='"+coid+"' FETCH NEXT "+show+" ROWS ONLY";
        }

        if(coid!="" && branch!="" && reportDate==""){
            q="SELECT * FROM V_RPT_MINI_GRID WHERE BRANCH='"+branch+"'  AND CREDITOFFICER='"+coid+"' FETCH NEXT "+show+" ROWS ONLY";
        }

        return q;
    }
}
