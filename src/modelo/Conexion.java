package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author rvelasquez
 */
public class Conexion {

    private Connection miConexion;
    private static final String url = "jdbc:mysql://localhost/bd_proyecto";
    private static final String user = "root";
    private static final String password = "root";

    public Connection getMiConexion() {
        return miConexion;
    }

    public void setMiConexion(Connection miConexion) {
        this.miConexion = miConexion;
    }

    public void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            miConexion = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            if (miConexion != null) {
                if (miConexion.isClosed() == false) {
                    miConexion.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
