
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private static Connection conexionn;
    private static Conexion instancia;

    private static final String URL = "jdbc:mysql://localhost:3306/comida";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // Constructor privado para evitar instanciación directa
    private Conexion() {

    }

    // Método para conectar a la base de datos
    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexionn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return conexionn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return conexionn;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() throws SQLException {
        try {
            if (conexionn != null) {
                conexionn.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e);
        }
    }

    // Método estático para obtener la instancia única
    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }
}
