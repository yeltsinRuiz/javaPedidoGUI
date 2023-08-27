
package negocio;


import javax.swing.JOptionPane;
import data.Conexion;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.*;


public class PedidoService {
    private Conexion cnx;

    public PedidoService() {
        cnx = Conexion.getInstance();
    }

    public void registrarPedido(String nombre, String precio, String plato) {
        try (Connection conexion = cnx.conectar()) {
            PreparedStatement registrar = conexion.prepareStatement("INSERT INTO pedido VALUES (?,?,?,?)");
            registrar.setString(1, null);
            registrar.setString(2, nombre);
            registrar.setString(3, precio);
            registrar.setString(4, plato);
            registrar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro exitoso");
            cnx.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el pedido: " + e.getMessage());
        }
    }

    public DefaultTableModel consultarPedidos() {
        try {
            Connection conexion = cnx.conectar();
            PreparedStatement seleccion = conexion.prepareStatement("SELECT * FROM pedido");
            ResultSet consulta = seleccion.executeQuery();
            

            ResultSetMetaData datos = consulta.getMetaData();
            DefaultTableModel modelo = new DefaultTableModel();
            int cantidadColumna = datos.getColumnCount();
            for (int i = 1; i <= cantidadColumna; i++) {
                modelo.addColumn(datos.getColumnName(i));
            }

            while (consulta.next()) {
                Object arreglo[] = new Object[cantidadColumna];
                for (int i = 0; i < cantidadColumna; i++) {
                    arreglo[i] = consulta.getObject(i + 1);
                }
                modelo.addRow(arreglo);
            }
            consulta.close();
            cnx.cerrarConexion();
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al consultar los pedidos: " + e.getMessage());
            return null;
        }
    }

    public void eliminarPedido(String id) {
        try (Connection conexion = cnx.conectar()) {
            PreparedStatement eliminar = conexion.prepareStatement("DELETE FROM pedido WHERE id=?");
            eliminar.setString(1, id);
            eliminar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pedido eliminado");
            cnx.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el pedido: " + e.getMessage());
        }
    }

    public void modificarPedido(String id, String nombre, String precio, String plato) {
        try (Connection conexion = cnx.conectar()) {
            PreparedStatement verificar = conexion.prepareStatement("SELECT COUNT(*) FROM pedido WHERE id = ?");
            verificar.setString(1, id);
            ResultSet resultadoVerificacion = verificar.executeQuery();
            resultadoVerificacion.next();
            resultadoVerificacion.close();

            int count = resultadoVerificacion.getInt(1);

            if (count > 0) {
                PreparedStatement modificar = conexion.prepareStatement(
                        "UPDATE pedido SET nombre=?, precio=?, plato=? WHERE id=?");
                modificar.setString(1, nombre);
                modificar.setString(2, precio);
                modificar.setString(3, plato);
                modificar.setString(4, id);
                modificar.executeUpdate();

                JOptionPane.showMessageDialog(null, "Pedido modificado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No existe un pedido con el ID especificado");
            }

            cnx.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el pedido: " + e.getMessage());
        }
    }

    public DefaultTableModel buscarPedido(String id) {
        try {
            Connection conexion = cnx.conectar();
            DefaultTableModel modelo = new DefaultTableModel();
            PreparedStatement buscar = conexion.prepareStatement("SELECT * FROM pedido WHERE id=?");
            buscar.setString(1, id);
            ResultSet consultar = buscar.executeQuery();

            ResultSetMetaData datos = consultar.getMetaData();
            int cantidadColumna = datos.getColumnCount();
            for (int i = 1; i <= cantidadColumna; i++) {
                modelo.addColumn(datos.getColumnName(i));
            }

            if (consultar.next()) {
                Object arreglo[] = new Object[cantidadColumna];
                for (int i = 0; i < cantidadColumna; i++) {
                    arreglo[i] = consultar.getObject(i + 1);
                }
                modelo.addRow(arreglo);
                JOptionPane.showMessageDialog(null, "Pedido encontrado");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el pedido con el ID especificado");
            }

            cnx.cerrarConexion();
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el pedido: " + e.getMessage());
            return null;
        }
    }
    

}
