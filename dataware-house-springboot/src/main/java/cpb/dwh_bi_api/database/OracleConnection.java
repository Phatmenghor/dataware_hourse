package cpb.dwh_bi_api.database;

import javax.transaction.Transactional;
import java.sql.*;

public class OracleConnection {

    private String ORACLE_CLASS = "oracle.jdbc.driver.OracleDriver";
    //    Production
    private static final String ORACLE_DB_URL = "jdbc:oracle:thin:@192.168.102.5:1521:dwh";
    private static final String ORACLE_USER = "dwh";
    private static final String ORACLE_PASS = "Bnk$$444";

    //    UAT
//    private static final String ORACLE_DB_URL = "jdbc:oracle:thin:@//192.168.127.88:1521/cdwh";
//    private static final String ORACLE_USER = "dwh";
//    private static final String ORACLE_PASS = "Bnk$$444";

    Connection connection = null;

    @Transactional
    public ResultSet selectAll(String query) {
        ResultSet result = null;
        try {
            // Remove trailing semicolon — JDBC throws ORA-00911 if present
            query = query.trim().replaceAll(";\\s*$", "");
            Class.forName(ORACLE_CLASS);
            connection = DriverManager.getConnection(ORACLE_DB_URL, ORACLE_USER, ORACLE_PASS);
            PreparedStatement statement = connection.prepareStatement(query);
            result = statement.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;

    }

}
