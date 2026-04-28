package cpb.dwh_bi_api.repositories;

public class PastDueGridRepository {
    public static String getAll(int limit, String branch){
        if(branch !=""){
            return "SELECT * FROM V_RPT_PASTDUE_GRID WHERE BRANCH='"+branch+"' AND REPORTDATE = (SELECT MAX(REPORTDATE) FROM V_RPT_PASTDUE_GRID) FETCH NEXT "+limit+" ROWS ONLY";
        }
        return "SELECT * FROM V_RPT_PASTDUE_GRID WHERE REPORTDATE = (SELECT MAX(REPORTDATE) FROM V_RPT_PASTDUE_GRID) FETCH NEXT "+limit+" ROWS ONLY";
    }

    public static String getByFilter(int show, String branch, String aaid, String reportDate){
        String q="";

        if(branch!="" && reportDate==""){
            q="SELECT * FROM V_RPT_PASTDUE_GRID WHERE BRANCH='"+branch+"' AND REPORTDATE = (SELECT MAX(REPORTDATE) FROM V_RPT_PASTDUE_GRID) FETCH NEXT "+show+" ROWS ONLY";
        }
        if(branch!="" && reportDate!=""){
            q="SELECT * FROM V_RPT_PASTDUE_GRID WHERE BRANCH='"+branch+"' AND REPORTDATE='"+reportDate+"' FETCH NEXT "+show+" ROWS ONLY";
        }

        if(aaid!="" && branch!=""){
            q="SELECT * FROM V_RPT_PASTDUE_GRID WHERE BRANCH='"+branch+"' AND REPORTDATE='"+reportDate+"' AND ARRANGEMENT_ID='"+aaid+"'";
        }
        return q;
    }

    public static String getById(String id){
        return "SELECT * FROM V_RPT_PASTDUE_GRID WHERE ARRANGEMENT_ID ='"+id+"'";
    }

    public static String getExport(int limit, String branch, String reportDate, String currency){
        if(branch ==""){
            return "SELECT * FROM V_RPT_PASTDUE_GRID  WHERE CURRENCY IN("+currency+") AND REPORTDATE ='"+reportDate+"'";
        }
        if(reportDate !=""){
            return "SELECT * FROM V_RPT_PASTDUE_GRID  WHERE BRANCH='"+branch+"' AND CURRENCY IN("+currency+") AND REPORTDATE ='"+reportDate+"'";
        }
        return "SELECT * FROM V_RPT_PASTDUE_GRID  WHERE BRANCH='"+branch+"' AND CURRENCY IN("+currency+") AND REPORTDATE = (SELECT MAX(REPORTDATE) FROM V_RPT_PASTDUE_GRID)";

    }
}
