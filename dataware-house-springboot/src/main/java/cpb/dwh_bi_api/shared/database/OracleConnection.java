package cpb.dwh_bi_api.shared.database;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Oracle database connection utility for reporting and data fetch operations.
 * Used for fetching data from Oracle for conversion to Excel and reports.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OracleConnection {

    @Value("${datasource.oracle.url:}")
    private String oracleUrl;

    @Value("${datasource.oracle.username:}")
    private String oracleUsername;

    @Value("${datasource.oracle.password:}")
    private String oraclePassword;

    @Value("${datasource.oracle.driver-class-name:oracle.jdbc.OracleDriver}")
    private String driverClassName;

    /**
     * Get Oracle database connection
     * @return Database connection
     * @throws SQLException if connection fails
     */
    public Connection getConnection() throws SQLException {
        if (oracleUrl == null || oracleUrl.isEmpty()) {
            throw new SQLException("Oracle database URL not configured. Set DB_ORACLE_URL environment variable.");
        }

        try {
            Class.forName(driverClassName);
            log.info("Connecting to Oracle database: {}", oracleUrl);
            return DriverManager.getConnection(oracleUrl, oracleUsername, oraclePassword);
        } catch (ClassNotFoundException e) {
            log.error("Oracle JDBC driver not found", e);
            throw new SQLException("Oracle JDBC driver not found", e);
        } catch (SQLException e) {
            log.error("Failed to connect to Oracle database", e);
            throw e;
        }
    }

    /**
     * Test Oracle database connection
     * @return true if connection successful, false otherwise
     */
    public boolean testConnection() {
        try {
            Connection conn = getConnection();
            conn.close();
            log.info("Oracle connection test successful");
            return true;
        } catch (SQLException e) {
            log.error("Oracle connection test failed", e);
            return false;
        }
    }
}
