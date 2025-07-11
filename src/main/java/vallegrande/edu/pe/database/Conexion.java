package vallegrande.edu.pe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://poo4.ctnqnq18zreq.us-east-1.rds.amazonaws.com:3306/gestion_materiales";
    private static final String USER = "admin";
    private static final String PASS = "diego123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
} 