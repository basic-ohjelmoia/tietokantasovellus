package Mechlab.Servlets;

import Mechlab.Models.Kayttaja;
import Mechlab.Models.Komponentti;
import Mechlab.Models.Mech;
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
 * Servletti, joka asentaa mechiin uuden komponentin
 *
 * @author Tuomas Honkala
 */
public class MechAsennaKomponentti extends HttpServlet {

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
        String ilmoitus = "";
        HttpSession session = request.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");

        Mech mech = null;
        Komponentti komponentti = null;
        int mech_id = 0;
        if (null != request.getParameter("mechid")) {
            mech_id = Integer.parseInt(request.getParameter("mechid"));
            mech = Mech.getMech(mech_id);
        }

//                        if (session.getAttribute("mech") != null) {
//                            mech = (Mech) session.getAttribute("mech");
//                        }
        response.sendRedirect(response.encodeRedirectURL("mechedit?id=" + mech.getMech_id()));
        boolean laiton = false;

        if (kayttaja.getID() != mech.getUser_id() && kayttaja.getOikeustaso() == 0) {
            laiton = true;
            session.setAttribute("ilmoitus", "Not possible! You can only make changes to your own mechs!");
        }

        boolean olikoAse = false;
        boolean olikoVaruste = false;

        String minne = "";
        String location = "";



        if (null != request.getParameter("minne") && !laiton) {
            minne = request.getParameter("minne").toUpperCase();
            if (minne.contains("LA")) {
                location = "LEFT ARM";
            }
            if (minne.contains("HD")) {
                location = "HEAD";
            }
            if (minne.contains("RA")) {
                location = "RIGHT ARM";
            }
            if (minne.contains("CT")) {
                location = "CENTER TORSO";
            }
            if (minne.contains("LT")) {
                location = "LEFT TORSO";
            }
            if (minne.contains("RT")) {
                location = "RIGHT TORSO";
            }

            if (minne.contains("LL")) {
                location = "LEFT LEG";
            }
            if (minne.contains("RL")) {
                location = "RIGHT LEG";
            }


        }

        if (null != request.getParameter("vaihdareaktori") && !laiton) {
            int reaktori_id = Integer.parseInt(request.getParameter("vaihdareaktori"));


            mech.vaihdaReaktori(reaktori_id);
        }

        if (null != request.getParameter("painoluokka") && !laiton) {
            int painoluokka = Integer.parseInt(request.getParameter("painoluokka"));


            mech.muutaPainoluokka(painoluokka);
        }

        if (null != request.getParameter("lisaa") && !laiton) {
            int komponentti_id = Integer.parseInt(request.getParameter("lisaa"));


            mech.asennaKomponentti(komponentti_id, location);
        }
        if (null != request.getParameter("poista") && !laiton) {
            int komponentti_id = Integer.parseInt(request.getParameter("poista"));

            mech.poistaKomponentti(komponentti_id);
            //mech.poistaKomponentti(komponentti_id, location);
        }

        session.setAttribute("kirjautunut", kayttaja);
        //session.setAttribute("ilmoitus", ilmoitus);
        // session.setAttribute("mech", mech);
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
            Logger.getLogger(MechAsennaKomponentti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MechAsennaKomponentti.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MechAsennaKomponentti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MechAsennaKomponentti.class.getName()).log(Level.SEVERE, null, ex);
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
