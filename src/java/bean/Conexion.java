package bean;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection conectar() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Dentista;encrypt=false";
            String usuario = "sa";
            String contraseña = "sasa";

            con = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa a SQL Server.");
        } catch (Exception e) {
            System.out.println("Error al conectar a SQL Server: " + e.getMessage());
        }
        return con;
    }
}