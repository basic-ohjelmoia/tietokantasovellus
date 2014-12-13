package Mechlab.Servlets;

import Mechlab.Models.Kayttaja;
import Mechlab.Models.Komponentisto;
import Mechlab.Models.Komponentti;
import Mechlab.Models.Mech;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servletti jolla luodaan uusi Mechi.
 * 
 * @author Tuomas Honkala
 */
public class MechLuoUusi extends HttpServlet {

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


        response.sendRedirect(response.encodeRedirectURL("mechselaa"));
        //response.sendRedirect("mechedit");
        // HttpServletResponse tamaResponse = response;
        response.setContentType("text/html;charset=UTF-8");
        Istunto istunto = new Istunto();
        String ilmoitus = "";

        if (istunto.onkoKirjautunut(request, response)) {
            HttpSession session = request.getSession();
            Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");

            if (null != request.getParameter("poista")) {
                int poistettava_mech_id = Integer.parseInt(request.getParameter("poista"));


                String palaute = "";
                palaute = Mech.poistaMech(poistettava_mech_id, kayttaja.getID(), kayttaja.getOikeustaso());
                session.setAttribute("ilmoitus", palaute);
            } else if (null != request.getParameter("kopioi")) {
                
                int kopioitava_mech_id = Integer.parseInt(request.getParameter("kopioi"));
                Mech kopioitava = Mech.getMech(kopioitava_mech_id);
              //  int kopion_mech_id = Mech.createNewMech(kayttaja.getID());
                
                               
                //Mech kopio = Mech.getMech(kopion_mech_id);
                //Mech kopio
                        int kopion_id= Mech.copyMech(kopioitava, kayttaja.getID());
                Mech kopio = Mech.getMech(kopion_id);
                
                //kopio.muutaPainoluokka(kopioitava.getPaino());
                //kopio.muutaCollectionID(kopioitava.getMech_id());
                //Mech.vaihdaNimi(""+kopion_mech_id, "BLOCK "+kopio.getMech_id());
                
                ArrayList<Komponentti> kopioitavatKomponentit = new ArrayList<Komponentti>();
                kopioitavatKomponentit = kopioitava.getMechinKomponentit("ALL");
                
                for (Komponentti osa : kopioitavatKomponentit) {
                    kopio.asennaKomponentti(osa.getKomponentti_id(), osa.getSijainti());
                }
                
//                for (Komponentisto osa : Komponentisto.getKomponentisto()) {
//                    if (osa.getBattlemech_id()==kopioitava.getMech_id()) {
//                        kopio.asennaKomponentti(osa.getComponent_id(), osa.getSijainti());
//                    }
//                }
//                
//                for (Komponentti asennettava : kopioitavatKomponentit) {
//                    kopio.asennaKomponentti(asennettava.getKomponentti_id(), asennettava.getSijainti());
//                }
                session.setAttribute("ilmoitus", "A copy of " + kopioitava.getNimi() + " has been added to your collection of mechs! Click <a href=\"mechedit?id=" + kopio.getMech_id() + "\">here</a> to edit the copied design!");
                
            }  else        {
                if (Mech.voikoKayttajaLuodaUudenMechin(kayttaja.getID())) {
                    Mech mech = null;
                    Komponentti komponentti = null;
                    int mech_id = 0;
                    mech_id = Mech.createNewMech(kayttaja.getID());
                    session.setAttribute("ilmoitus", "Pre-production model #" + mech_id + " has been added to the assembly line. Click <a href=\"mechedit?id=" + mech_id + "\">here</a> to edit the mech design!");
                } else {
                    session.setAttribute("ilmoitus", "Construction denied! You must proceed with your previous pre-production model or DELETE it from the assembly line!");
                }
                // mech = Mech.getMech(mech_id);


//                        if (session.getAttribute("mech") != null) {
//                            mech = (Mech) session.getAttribute("mech");
//                        }
                //   tamaResponse.sendRedirect(tamaResponse.encodeRedirectURL("mechedit?id="+mech_id));       
                //session.setAttribute("newmechid", mech_id);
            }
            
          //  session = request.getSession();
            session.setAttribute("ilmoitus", ilmoitus);
            session.setAttribute("kirjautunut", kayttaja);
            
            
        } else {
            response.encodeRedirectURL("login");
            //session.setAttribute("kirjautunut", kayttaja);
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(MechLuoUusi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MechLuoUusi.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MechLuoUusi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MechLuoUusi.class.getName()).log(Level.SEVERE, null, ex);
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
