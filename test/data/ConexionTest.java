
package data;

import static com.mysql.cj.conf.PropertyKey.PASSWORD;
import java.sql.Connection;
import javax.print.DocFlavor.URL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;

/**
 *
 * @author yelts
 */
public class ConexionTest {

    public ConexionTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of conectar method, of class Conexion.
     */
    @Test
    public void testConectar() {
        System.out.println("conectar");
        Conexion instance = null;
        Connection expResult = null;
        Connection result = instance.conectar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cerrarConexion method, of class Conexion.
     */
    @Test
    public void testCerrarConexion() throws Exception {
        System.out.println("cerrarConexion");
        Conexion instance = null;
        instance.cerrarConexion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class Conexion.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Conexion expResult = null;
        Conexion result = Conexion.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


}
