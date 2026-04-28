package cpb.dwh_bi_api.database;

import java.sql.*;

public class PostgrestConnection {

    private String PG_CLASS = "org.postgresql.Driver";

    // ---------LOCAL-------------
    // private String PG_DB_URL = "jdbc:postgresql://localhost:5432/dwh_cpb_bi";
    // private String PG_USER = "postgres";
    // private String PG_PASS = "samno106";

    // ---------UAT-------------
    private String PG_DB_URL = "jdbc:postgresql://192.168.103.106:5432/dwh_cpb_bi";
    private String PG_USER = "postgres";
    private String PG_PASS = "123cp!@#";

    // ---------PROD-------------
//     private String PG_DB_URL = "jdbc:postgresql://192.168.101.5:5432/dwh_cpb_bi";
//     private String PG_USER = "postgres";
//     private String PG_PASS = "123cp!@#";

    Connection sqsv_conn = null;

    public ResultSet selectAll(String query) {
        ResultSet result = null;
        System.out.println(query);
        try {
            Class.forName(PG_CLASS);
            sqsv_conn = DriverManager.getConnection(PG_DB_URL, PG_USER, PG_PASS);
            PreparedStatement sqsv_stat = sqsv_conn.prepareStatement(query);
            result = sqsv_stat.executeQuery();
            sqsv_conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public ResultSet create(String query) {
        ResultSet result = null;
        System.out.println(query);
        try {
            Class.forName(PG_CLASS);
            sqsv_conn = DriverManager.getConnection(PG_DB_URL, PG_USER, PG_PASS);
            PreparedStatement sqsv_stat = sqsv_conn.prepareStatement(query);
            result = sqsv_stat.executeQuery();
            sqsv_conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public ResultSet delete(String query) {
        ResultSet result = null;
        System.out.println(query);
        try {
            Class.forName(PG_CLASS);
            sqsv_conn = DriverManager.getConnection(PG_DB_URL, PG_USER, PG_PASS);
            PreparedStatement sqsv_stat = sqsv_conn.prepareStatement(query);
            result = sqsv_stat.executeQuery();
            sqsv_conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public ResultSet query(String query) {
        ResultSet result = null;
        System.out.println(query);
        try {
            Class.forName(PG_CLASS);
            sqsv_conn = DriverManager.getConnection(PG_DB_URL, PG_USER, PG_PASS);
            PreparedStatement sqsv_stat = sqsv_conn.prepareStatement(query);
            result = sqsv_stat.executeQuery();
            sqsv_conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

}
