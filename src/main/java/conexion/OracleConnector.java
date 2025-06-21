package conexion;

import config.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnector {
    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(
                DBConfig.get("oracle.url"),
                DBConfig.get("oracle.user"),
                DBConfig.get("oracle.password")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
