package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionJDBC {
    Connection conexion = null;
    String db = "prusuario";
    String host = "localhost";
    String port = "5432";
    String user = "postgres";  
    String pass = "postgres";
    
    String driverDB = "org.postgresql.Driver";
    String url = "jdbc:postgresql://" + host + ":" + port + "/" + db;
    
    public Connection conectar(){
        try {
            Class.forName(driverDB);
            conexion = DriverManager.getConnection(url, user,pass);
            if (!conexion.isClosed()){
                System.out.println("Conexión exitosa a la base de datos @" + db);         
            }
            return conexion;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Connection getConexion(){
        return conexion;
    }
}
