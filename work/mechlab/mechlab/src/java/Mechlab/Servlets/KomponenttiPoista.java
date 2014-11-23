package Mechlab.Servlets;

import Mechlab.Models.Kayttaja;
import Mechlab.Models.Komponentti;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class KomponenttiPoista extends HttpServlet {

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
        response.sendRedirect(response.encodeRedirectURL("komponenttiselaa"));
          boolean poistettiinKomponentti = false;
          String poistettu = "";
          Istunto istunto = new Istunto();
        HttpSession session = request.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        
        HttpServletRequest sivu = request;
        HttpServletResponse alkuperainenResponse = response;
//        request.setAttribute("navimechselaa", "");
//        request.setAttribute("naviyllapito", "active");
//        request.setAttribute("navilogin", "");
//        request.setAttribute("naviloginmoodi", "POISTU");
//        request.setAttribute("naviloginosoite", "Uloskirjaudu");
       // request.setAttribute("kirjautuneenNimi", "Et ole kirjautunut sisään!");
        
        response.setContentType("text/html;charset=UTF-8");
          boolean poistettiinkoJotain = false;
       String poistettiin = "";
        
       if (istunto.onkoKirjautunut(request, response)) {
                   // HttpSession session = sivu.getSession();
                    //Kayttaja kayttaja = (Kayttaja) session..getAttribute("kayttaja");
                    //sivu.setAttribute("kirjautuneenNimi", session.getAttribute("nimi"));
                    int komponentti_id = 0;
                    if (kayttaja.getOikeustaso()>0) {
                        
                          if (null != request.getParameter("id")) {
                              String idParam = request.getParameter("id");
                            int id;
                                try {
                                  komponentti_id = Integer.parseInt(idParam);
                            } catch(Exception e) {
                                // Id-numero nolla ei käytännössä koskaan löydy kannasta, 
                                // joten koodin suoritus päätyy
                            // alla olevan if-lauseen else-haaraan
                            komponentti_id = 0;
                            }
                         
                      //    if (null != request.getParameter("poista")) {
      
                              //  if (request.getParameter("poista").equals("kylla")) {
//                                Komponentti komponentti = Komponentti.getKomponentti(komponentti_id);
//                                request.setAttribute("poistettukomponentti", komponentti.getNimi());  
                                //if (komponentti != null) {poistettu=komponentti.getNimi();}
                                poistettiin = ""+komponentti_id;// Komponentti.getKomponentti(komponentti_id).getNimi();
                                poistettiinkoJotain = true;
                                
                                String ilmoitusSisalto = ""+Komponentti.getKomponentti(komponentti_id).getNimi()+" has been deleted from database!";
                              session.setAttribute("ilmoitus", ilmoitusSisalto);
                                
                                    Komponentti.poistaKomponentti(komponentti_id);

                                    
                                    //       }
                            }
                    }
                    
       }
     
       //response.encodeRedirectURL(response.encodeRedirectURL(
                         //response.encodeRedirectURL("KomponenttiSelaa");
                         //response.sendRedirect("komponenttiselaa");
                         //alkuperainenResponse.sendRedirect("KomponenttiSelaa");
//                        if (poistettiinkoJotain) {session.setAttribute("ilmoitus", "Component number "+poistettiin+" has been deleted from the database!");}  
//                    session.setAttribute("kirjautunut", kayttaja);
                 
          
          
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
            Logger.getLogger(KomponenttiPoista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KomponenttiPoista.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(KomponenttiPoista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KomponenttiPoista.class.getName()).log(Level.SEVERE, null, ex);
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
