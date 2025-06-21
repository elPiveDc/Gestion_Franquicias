package conexion;

import config.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnector {
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                DBConfig.get("postgres.url"),
                DBConfig.get("postgres.user"),
                DBConfig.get("postgres.password")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
