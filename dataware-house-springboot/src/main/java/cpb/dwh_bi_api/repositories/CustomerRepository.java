package cpb.dwh_bi_api.repositories;

public class CustomerRepository {

    public static String getDefault(){
        return "SELECT * FROM D_CBS_CUSTOMERS FETCH NEXT 10 ROWS ONLY";
    }

    public static String getAll(){
        return "SELECT * FROM D_CBS_CUSTOMERS";
    }

    public static String getByPage(int page){
        return "SELECT * FROM D_CBS_CUSTOMERS FETCH NEXT "+page+" ROWS ONLY";
    }

    public static String getById(String id){
        return "SELECT * FROM D_CBS_CUSTOMERS WHERE CUSTOMERID="+id;
    }

    public static String getByBranch(String branch){
        return "SELECT * FROM D_CBS_CUSTOMERS WHERE CO_CODE="+branch+" FETCH NEXT 10 ROWS ONLY";
    }
}
