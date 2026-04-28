package cpb.dwh_bi_api.database;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class OracleConnectionJDBC {
    private static final String ORACLE_CLASS = "oracle.jdbc.driver.OracleDriver";

//    Production
    private static final String ORACLE_DB_URL = "jdbc:oracle:thin:@192.168.102.5:1521:dwh";
    private static final String ORACLE_USER = "dwh";
    private static final String ORACLE_PASS = "Bnk$$444";

//    UAT

//    private static final String ORACLE_DB_URL = "jdbc:oracle:thin:@//192.168.127.88:1521/cdwh";
//    private static final String ORACLE_USER = "dwh";
//    private static final String ORACLE_PASS = "Bnk$$444";

    private Connection connection = null;

    // Open the connection
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(ORACLE_CLASS);
                connection = DriverManager.getConnection(ORACLE_DB_URL, ORACLE_USER, ORACLE_PASS);
            } catch (Exception e) {
                e.printStackTrace();
                throw new SQLException("Failed to connect to the database");
            }
        }
        return connection;
    }

    // Close the connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
