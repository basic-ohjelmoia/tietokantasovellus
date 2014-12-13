package Mechlab.Servlets;

import Mechlab.Models.Kayttaja;
import Mechlab.Models.Komponentti;
import Mechlab.Models.Mech;
import Mechlab.Models.Reaktori;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servletti mechien selailuun 
 * @author Tuomas Honkala
 */
public class MechSelaa extends HttpServlet {

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
        
        
        Istunto istunto = new Istunto();
        HttpSession session = request.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        
        String ilmoitus = null;
        if (session.getAttribute("ilmoitus") != null) {
        ilmoitus = (String) session.getAttribute("ilmoitus");
        session.removeAttribute(ilmoitus);
        }
        
        HttpServletRequest sivu = request;
        
        request.setAttribute("navimechselaa", "active");
       // request.setAttribute("naviadmin", "");
        request.setAttribute("navilogin", "");
        request.setAttribute("naviloginmoodi", "POISTU");
        request.setAttribute("naviloginosoite", "Uloskirjaudu");
        request.setAttribute("ilmoitus", ilmoitus);
       // request.setAttribute("kirjautuneenNimi", "Et ole kirjautunut sisään!");
        
        response.setContentType("text/html;charset=UTF-8");
        if (null != request.getParameter("view")) {
            String view = request.getParameter("view");
            
            if (view.equalsIgnoreCase("owned")) {
                request.setAttribute("view", "owned");
            }
            if (view.equalsIgnoreCase("all")) {
                request.setAttribute("view", "all");
            }
            if (view.equalsIgnoreCase("operational")) {
                request.setAttribute("view", "operational");
            }
                
        } else {   request.setAttribute("view", "all");}
        
        if (null != request.getParameter("weight")) {
            String maxweight = request.getParameter("weight");
            
             if (maxweight.equalsIgnoreCase("0")) {
                request.setAttribute("minweight", 20);
                request.setAttribute("maxweight", 100);
            }
            
            if (maxweight.equalsIgnoreCase("35")) {
                request.setAttribute("minweight", 20);
                request.setAttribute("maxweight", 35);
            }
            
            if (maxweight.equalsIgnoreCase("55")) {
                request.setAttribute("minweight", 40);
                request.setAttribute("maxweight", 55);
            }
            if (maxweight.equalsIgnoreCase("75")) {
                request.setAttribute("minweight", 60);
                request.setAttribute("maxweight", 75);
            }
            if (maxweight.equalsIgnoreCase("100")) {
                request.setAttribute("minweight", 80);
                request.setAttribute("maxweight", 100);
            }
                
        } else {  request.setAttribute("minweight", 20); request.setAttribute("maxweight", 100);}
        
        
            if (istunto.onkoKirjautunut(request, response)) {
                    //HttpSession session = request.getSession();
                    //Kayttaja kayttaja = (Kayttaja) session..getAttribute("kayttaja");
                    //sivu.setAttribute("kirjautuneenNimi", session.getAttribute("nimi"));
                
                  //  if (istunto.onkoAdmin(request, response)) {
                        //sivu.setAttribute("naviadmin", "true");
                  //  }
                   if (null != ilmoitus) {
                     // Samalla kun viesti haetaan, se poistetaan istunnosta,
                        // ettei se näkyisi myöhemmin jollain toisella sivulla uudestaan.
                        session.removeAttribute("ilmoitus");

                         sivu.setAttribute("ilmoitus", ilmoitus);
                     }
                
                if (kayttaja.getOikeustaso()>=0) {
                        
                      List<Mech> mechit = Mech.getMechit();
                        sivu.setAttribute("mechit", mechit);  
                        
//                        List<Komponentti> varustekomponentit = Komponentti.getVarusteKomponentit();
//                        sivu.setAttribute("varustekomponentit", varustekomponentit);  
//                        
//                        List<Reaktori> reaktorit = Reaktori.getReaktorit();
//                        sivu.setAttribute("reaktorit", reaktorit);  
                       
                    
                }
                
                 
                
                
                    naytaJSP("mechselaa.jsp", request, response);
                    session.setAttribute("kirjautunut", kayttaja);
            }
    
    }
    
      public void naytaJSP(String sivu, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
          
  /* Luodaan RequestDispatcher-olio, joka osaa näyttää lista.jsp:n */
      RequestDispatcher dispatcher = request.getRequestDispatcher(sivu);
      
    

  /* Pyydetään dispatcher-oliota näyttämään JSP-sivunsa */
      dispatcher.forward(request, response);
      
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
        try {
                    try {
                        processRequest(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(MechSelaa.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (NamingException ex) {
            Logger.getLogger(MechSelaa.class.getName()).log(Level.SEVERE, null, ex);
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
                    try {
                        processRequest(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(MechSelaa.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (NamingException ex) {
            Logger.getLogger(MechSelaa.class.getName()).log(Level.SEVERE, null, ex);
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
