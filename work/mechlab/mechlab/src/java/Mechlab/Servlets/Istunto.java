package Mechlab.Servlets;

import Mechlab.Models.Kayttaja;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mikromafia
 */
public class Istunto extends HttpServlet {

    HttpServletRequest nykyinenSivu;
   // HttpSession session;
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
    
    public Istunto() {
        
    }
    
    public Istunto(HttpSession session) {
        //this.session = session;
    }
    
    public Istunto (HttpServletRequest request) {
        this.nykyinenSivu = request;
    }
    
    protected boolean onkoKirjautunut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {
      
        HttpSession session = request.getSession(true);
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        
        if (kayttaja != null) {
            request.setAttribute("kirjautunut", kayttaja); // oli request.
              request.setAttribute("kirjautuneenNimi", kayttaja.getNimi());  // oli request.
             request.setAttribute("vierailukerta", kayttaja.getVierailukerta());
                
            if (kayttaja.getOikeustaso()>0) {
                
                request.setAttribute("admin", true);    // oli request.
                request.setAttribute("naviadmin", "true");
            } else {
                request.setAttribute("admin", "false");
            }
            
            return true;
        }
        
        //request.setAttribute("virheViesti", "Mechlabin käyttö edellyttää kirjautumista!");
        response.sendRedirect("login?istunnoton=kylla");
        return false;

    }
    
    protected boolean onkoAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {
        
        HttpSession session = request.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        
        if (kayttaja != null) {
            session.setAttribute("kirjautunut", this);  // oli request.
            if (kayttaja.onkoAdmin()) {
                return true;
            } else {
            return false;
        }
        
        //request.setAttribute("virheViesti", "Mechlabin käyttö edellyttää kirjautumista!");
        //response.sendRedirect("login?istunnoton=kylla");
        }
        return false;

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

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
