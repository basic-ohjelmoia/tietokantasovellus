package Mallit;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Tietokanta.Yhteys;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
/**
 *
 * @author mikromafia
 */
public class Kayttaja extends HttpServlet {

     private int id;
    private String tunnus;
    private String salasana;
    
    public Kayttaja() {
        
    }
    
    public Kayttaja(int id, String tunnus, String salasana) {
        this.id=id;
        this.tunnus=tunnus;
        this.salasana=salasana;
    }
    
    public static List<Kayttaja> getKayttajat() throws NamingException, SQLException {
  String sql = "SELECT kayttaja_id, nimi, salasana from kayttaja";
  
  Connection yhteys = new Yhteys().getConnection();
  PreparedStatement kysely = yhteys.prepareStatement(sql);
  ResultSet tulokset = kysely.executeQuery();

  ArrayList<Kayttaja> kayttajat = new ArrayList<Kayttaja>();
  while (tulokset.next()) {
    //Luodaan tuloksia vastaava olio ja palautetaan olio:
    Kayttaja k = new Kayttaja();
    k.setID(tulokset.getInt("id"));
    k.setTunnus(tulokset.getString("tunnus"));
    k.setSalasana(tulokset.getString("salasana"));

    kayttajat.add(k);
    
  }   
  //Suljetaan kaikki resutuloksetsit:
  try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}



  
  return kayttajat;
}
    
    
    public void setID(int id) {
        this.id=id;
    }
    public void setTunnus (String tunnus) {
        this.tunnus=tunnus;
    }
    
    public void setSalasana (String sala) {
        this.salasana=sala;
    }
    
    public String getSalasana() {
        return this.salasana;
    }
    
    public String getTunnus() {
        return this.tunnus;
    }
    
    public int getID() {
        return this.id;
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Kayttaja</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Kayttaja at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
      
        } finally {            
            out.close();
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
