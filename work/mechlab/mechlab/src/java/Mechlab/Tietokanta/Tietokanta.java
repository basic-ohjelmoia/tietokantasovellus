
package Mechlab.Tietokanta;



import Mechlab.Models.Kayttaja;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author mikromafia
 */
public class Tietokanta {

    
    //  InitialContext cxt;
  //  DataSource yhteysVarasto;
    
    public Tietokanta() throws NamingException {
            
    }
    
     public static Connection getYhteys() throws NamingException, SQLException {
        InitialContext cxt = new InitialContext();
        DataSource yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tuho");
     Connection yhteys  = yhteysVarasto.getConnection(); 
    //try { yhteys.close(); } catch (Exception e) {  }
     return yhteys;
    }

    public Connection getConnection() throws NamingException, SQLException {
     return getYhteys();
    }
}