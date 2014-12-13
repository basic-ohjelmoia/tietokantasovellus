package Mechlab.Servlets;

import Mechlab.Models.Kayttaja;
import Mechlab.Models.Komponentti;
import Mechlab.Models.Mech;
import Mechlab.Models.Reaktori;
import Mechlab.Models.Simulator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servletti, joka tuottaa taistelusimulaation tuloksen käyttäjän nähtäväksi.
 * @author Tuomas Honkala
 */
public class MechSimuloi extends HttpServlet {

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
        boolean poistettiinKomponentti = false;
          Istunto istunto = new Istunto();
        HttpSession session = request.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        
        HttpServletRequest sivu = request;
        
        request.setAttribute("navimechselaa", "active");
        request.setAttribute("navimechedit", "");
        request.setAttribute("naviyllapito", "");
        request.setAttribute("navilogin", "");
        request.setAttribute("naviloginmoodi", "LOGOUT");
        request.setAttribute("naviloginosoite", "Uloskirjaudu");
       // request.setAttribute("kirjautuneenNimi", "Et ole kirjautunut sisään!");
        boolean ilmoitus = false;
        String ilmoitusSisalto = "";
        response.setContentType("text/html;charset=UTF-8");
        
//       if (null !=      request.getParameter("weaponid")) {
//         String weaponid  = request.getParameter("weaponid"); 
//        String weaponname = request.getParameter("weaponname"); 
//         String weapontype = request.getParameter("weapontype"); 
//        
//            Komponentti.paivitaKomponentti(weaponid, weaponname,weapontype);
//        
//       } else {
//       Komponentti.paivitaKomponentti("2", "sama","blah");}
//         List<Komponentti> komponentit = new ArrayList<Komponentti>();
//         komponentit = Komponentti.getKomponentit();
//                        sivu.setAttribute("komponentit", komponentit);  
//                        
//                        naytaJSP("komponenttiselaa.jsp", sivu, response);
        
        
        
            if (istunto.onkoKirjautunut(request, response)) {
                   // HttpSession session = sivu.getSession();
                    //Kayttaja kayttaja = (Kayttaja) session..getAttribute("kayttaja");
                    //sivu.setAttribute("kirjautuneenNimi", session.getAttribute("nimi"));
                    int mech_id = 0;
                    if (kayttaja.getOikeustaso()>=0) {
                        int attacker_id = 0;
                        int defender_id = 0;
                        
                          if (null != request.getParameter("attacker")) {
                              String attacker_id_string = request.getParameter("attacker");
                            
                                try {
                                  attacker_id = Integer.parseInt(attacker_id_string);
                            } catch(Exception e) {
                                // Id-numero nolla ei käytännössä koskaan löydy kannasta, 
                                // joten koodin suoritus päätyy
                            // alla olevan if-lauseen else-haaraan
                            attacker_id = 0;
                            }
                          }
                          
                          if (null != request.getParameter("defender")) {
                              String defender_id_string = request.getParameter("defender");
                            
                                try {
                                  defender_id = Integer.parseInt(defender_id_string);
                            } catch(Exception e) {
                                // Id-numero nolla ei käytännössä koskaan löydy kannasta, 
                                // joten koodin suoritus päätyy
                            // alla olevan if-lauseen else-haaraan
                            defender_id = 0;
                            }
                          }
  
                       Mech attacker = Mech.getMech(attacker_id);
                       Mech defender = Mech.getMech(defender_id);
                          
                       ArrayList<String> results = Simulator.getResults(attacker, defender);
                       
                      
                        
                        sivu.setAttribute("userid", kayttaja.getID());  
                        sivu.setAttribute("results", results);
                        sivu.setAttribute("attacker", attacker);
                        sivu.setAttribute("defender", defender);
                        
                      
                        
                        naytaJSP("mechsimuloi.jsp", sivu, response);
                        session.setAttribute("kirjautunut", kayttaja);
                         // }
                    } 
                    
            
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
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(MechSimuloi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MechSimuloi.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MechSimuloi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MechSimuloi.class.getName()).log(Level.SEVERE, null, ex);
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
