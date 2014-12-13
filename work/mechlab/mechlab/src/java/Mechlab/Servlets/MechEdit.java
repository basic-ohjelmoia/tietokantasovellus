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
 * Keskeinen servletti jota käytetään mechien editointisivun kontrollointiin
 *
 * @author Tuomas Honkala
 */
public class MechEdit extends HttpServlet {

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
        //boolean ilmoitus = false;
        //String ilmoitusSisalto = "";

        String ilmoitus = null;
        if (session.getAttribute("ilmoitus") != null) {
            ilmoitus = (String) session.getAttribute("ilmoitus");
            session.removeAttribute(ilmoitus);
        }

        response.setContentType("text/html;charset=UTF-8");




        if (istunto.onkoKirjautunut(request, response)) {

            int mech_id = 0;
            if (kayttaja.getOikeustaso() >= 0) {

                if (null != request.getParameter("id")) {
                    String idParam = request.getParameter("id");
                    int id;
                    try {
                        mech_id = Integer.parseInt(idParam);
                    } catch (Exception e) {

                        mech_id = 0;
                    }

                    if (mech_id == 0) {
                        mech_id = (Integer) session.getAttribute("newmechid");
                    }

                    Mech mech = Mech.getMech(mech_id);


                    List<Mech> mechit = Mech.getMechit();
                    sivu.setAttribute("mechit", mechit);
                    sivu.setAttribute("ilmoitus", ilmoitus);
                    sivu.setAttribute("mech", mech);
                    sivu.setAttribute("userid", kayttaja.getID());
                    session.setAttribute("mech", mech);
                    sivu.setAttribute("aseetauto", Komponentti.getAseKomponentit("AUTO"));
                    sivu.setAttribute("aseetenergy", Komponentti.getAseKomponentit("ENERGY"));
                    sivu.setAttribute("aseetmissile", Komponentti.getAseKomponentit("MISSILE"));
                    sivu.setAttribute("aseetmelee", Komponentti.getAseKomponentit("MELEE"));
                    sivu.setAttribute("aseetkinetic", Komponentti.getAseKomponentit("KINETIC"));
                    sivu.setAttribute("reaktorit", Reaktori.getReaktorit());
                    sivu.setAttribute("reaktori", mech.getReaktori());
                    sivu.setAttribute("varusteet", Komponentti.getVarusteKomponentit());

                    sivu.setAttribute("leftarm", mech.getMechinKomponentit("LEFT ARM"));
                    sivu.setAttribute("head", mech.getMechinKomponentit("HEAD"));
                    sivu.setAttribute("rightarm", mech.getMechinKomponentit("RIGHT ARM"));

                    sivu.setAttribute("lefttorso", mech.getMechinKomponentit("LEFT TORSO"));
                    sivu.setAttribute("centertorso", mech.getMechinKomponentit("CENTER TORSO"));
                    sivu.setAttribute("righttorso", mech.getMechinKomponentit("RIGHT TORSO"));

                    sivu.setAttribute("leftleg", mech.getMechinKomponentit("LEFT LEG"));
                    sivu.setAttribute("rightleg", mech.getMechinKomponentit("RIGHT LEG"));
                    sivu.setAttribute("allcomponents", mech.getMechinKomponentit("ALL"));
                    sivu.setAttribute("armorvalues", mech.getArmorratingList(false));

                    sivu.setAttribute("varoitukset", mech.getVaroitukset(1));
                    sivu.setAttribute("huomautukset", mech.getVaroitukset(2));
                    sivu.setAttribute("saavutukset", mech.getVaroitukset(3));

                    sivu.setAttribute("armor_hd", mech.getArmorvalue("h", true));
                    sivu.setAttribute("armor_ct", mech.getArmorvalue("ct", true));
                    sivu.setAttribute("armor_lt", mech.getArmorvalue("lt", true));
                    sivu.setAttribute("armor_rt", mech.getArmorvalue("rt", true));
                    sivu.setAttribute("armor_la", mech.getArmorvalue("la", true));
                    sivu.setAttribute("armor_ra", mech.getArmorvalue("ra", true));
                    sivu.setAttribute("armor_ll", mech.getArmorvalue("ll", true));
                    sivu.setAttribute("armor_rl", mech.getArmorvalue("rl", true));

                    sivu.setAttribute("is_hd", mech.getArmorvalue("h", false));
                    sivu.setAttribute("is_ct", mech.getArmorvalue("ct", false));
                    sivu.setAttribute("is_lt", mech.getArmorvalue("lt", false));
                    sivu.setAttribute("is_rt", mech.getArmorvalue("rt", false));
                    sivu.setAttribute("is_la", mech.getArmorvalue("la", false));
                    sivu.setAttribute("is_ra", mech.getArmorvalue("ra", false));
                    sivu.setAttribute("is_ll", mech.getArmorvalue("ll", false));
                    sivu.setAttribute("is_rl", mech.getArmorvalue("rl", false));

                    naytaJSP("mecheditoi.jsp", sivu, response);
                    session.setAttribute("kirjautunut", kayttaja);
                    // }
                } else {
                    response.encodeRedirectURL("KomponenttiSelaa");
                    session.setAttribute("kirjautunut", kayttaja);
                }
            } else {
                response.encodeRedirectURL("KomponenttiSelaa");
                session.setAttribute("kirjautunut", kayttaja);
            }
        } else {
            response.encodeRedirectURL("KomponenttiSelaa");
            session.setAttribute("kirjautunut", kayttaja);
        }

        if (poistettiinKomponentti) {
            //response.encodeRedirectURL("KomponenttiSelaa");
            response.sendRedirect("KomponenttiSelaa");
            session.setAttribute("kirjautunut", kayttaja);
            //ilmoitus = ""+komponentti+" remove.
            session.setAttribute("ilmoitus", ilmoitus);


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
            Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
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
        Istunto istunto = new Istunto();
        response.sendRedirect(response.encodeRedirectURL("mechedit?id=" + request.getParameter("mechid")));
        String ilmoitus = "";

        HttpSession session = request.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");

        boolean olikoAse = false;
        boolean olikoVaruste = false;

        String mechid = "";
        String mechnimi = "";

        if (null != request.getParameter("mechid")) {


            mechid = request.getParameter("mechid");
            mechnimi = request.getParameter("mechnimi");

            ilmoitus = "The mech has been renamed as " + mechnimi + "!";
            //if (reactorname.length()==0) {ilmoitus="Reactor #"+reactorid+" has been saved to the database and given a new common name!";}
            try {
                Mech.vaihdaNimi(mechid, mechnimi);
            } catch (SQLException ex) {
                Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //response.encodeRedirectURL("komponenttiselaa");

        session.setAttribute("kirjautunut", kayttaja);
        session.setAttribute("ilmoitus", ilmoitus);


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
