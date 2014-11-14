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
public class Tietokanta_1 extends HttpServlet {

    
    //  InitialContext cxt;
  //  DataSource yhteysVarasto;
    
    public Tietokanta_1() throws NamingException {
            
    }

    public Connection getConnection() throws NamingException, SQLException {
        InitialContext cxt = new InitialContext();
        DataSource yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tuho");
     Connection yhteys  = yhteysVarasto.getConnection(); 
    //try { yhteys.close(); } catch (Exception e) {  }
     return yhteys;
    }
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {
        
       // InitialContext cxt = new InitialContext();
        
        
        Connection yhteys = getConnection(); 
        
         PreparedStatement kysely = null;
  ResultSet tulokset = null;
  PrintWriter out = response.getWriter(); 
  response.setContentType("text/plain;charset=UTF-8");
   out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Connection</title>");            
            out.println("</head>");
            out.println("<body>");
  try {
    //Alustetaan muuttuja jossa on Select-kysely, joka palauttaa lukuarvon:
    String sqlkysely = "SELECT 1+1 as two";
    //String sqlkysely = "SELECT nimi FROM kayttaja";
    kysely = yhteys.prepareStatement(sqlkysely);
    tulokset = kysely.executeQuery();
    if(tulokset.next()) {
      //Tuloksen arvoksi pitäisi tulla numero kaksi.
      int tulos = tulokset.getInt("two");
      out.println("Tulos: "+tulos); 
    } else {
      out.println("Virhe!"); 
    }
    
      sqlkysely = "SELECT kayttaja_id, nimi, salasana from kayttaja";
  
  kysely = yhteys.prepareStatement(sqlkysely);
kysely = yhteys.prepareStatement(sqlkysely);
    tulokset = kysely.executeQuery();

  ArrayList<Kayttaja> kayttajat = new ArrayList<Kayttaja>();
  while (tulokset.next()) {
    //Luodaan tuloksia vastaava olio ja palautetaan olio:
    Kayttaja k = new Kayttaja();
    k.setID(tulokset.getInt("kayttaja_id"));
    k.setNimi(tulokset.getString("nimi"));
    k.setSalasana(tulokset.getString("salasana"));

    kayttajat.add(k);
    
  }   
  kayttajat.add(new Kayttaja(999, "none", "nopw")); 
    
   for (Kayttaja kayttaja : kayttajat) {
                out.println(kayttaja.getNimi()+"<br>");
            }
  
    sqlkysely = "SELECT nimi FROM kayttaja";
    kysely = yhteys.prepareStatement(sqlkysely);
    tulokset = kysely.executeQuery();
    if(tulokset.next()) {
        out.println("Kayttajat-taulu: "+tulokset); 
    }
  } catch (Exception e) {
    out.println("Virhe: "+e.getMessage()); 
      out.println("hmmm...</body>");
            out.println("</html>");
  }

        tulokset.close(); kysely.close();
}
        
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Connection</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Connection at " + request.getContextPath() + "</h1>");
//            out.println("hmmm...</body>");
//            out.println("</html>");
//        } finally {            
//            out.close();
        
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(Tietokanta_2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(Tietokanta_2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
