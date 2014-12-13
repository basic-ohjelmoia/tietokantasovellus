package Mechlab.Servlets;

import Mechlab.Models.Kayttaja;
import Mechlab.Models.Komponentti;
import Mechlab.Models.Reaktori;
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
 * Komponenttien selaamiseen käytettävä servletti
 *
 * @author Tuomas Honkala
 */
public class KomponenttiSelaa extends HttpServlet {

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
        String ilmoitus = (String) session.getAttribute("ilmoitus");

        HttpServletRequest sivu = request;

        request.setAttribute("navimechselaa", "");
        request.setAttribute("naviyllapito", "active");
        request.setAttribute("navilogin", "");
        request.setAttribute("naviloginmoodi", "POISTU");
        request.setAttribute("naviloginosoite", "Uloskirjaudu");
        // request.setAttribute("kirjautuneenNimi", "Et ole kirjautunut sisään!");

//          if (null != request.getAttribute("poistettukomponentti")) {
//                         String poisto = (String) request.getAttribute("poistettukomponentti");
//                         sivu.setAttribute("poistettukomponentti", poisto);
//          }
        response.setContentType("text/html;charset=UTF-8");

//         List<Komponentti> komponentit = new ArrayList<Komponentti>();
//         komponentit = Komponentti.getKomponentit();
//                        sivu.setAttribute("komponentit", komponentit);  
//                        
//                        naytaJSP("komponenttiselaa.jsp", sivu, response);



        if (istunto.onkoKirjautunut(request, response)) {
            // HttpSession session = sivu.getSession();
            //Kayttaja kayttaja = (Kayttaja) session..getAttribute("kayttaja");
            //sivu.setAttribute("kirjautuneenNimi", session.getAttribute("nimi"));


            if (null != ilmoitus) {
                // Samalla kun viesti haetaan, se poistetaan istunnosta,
                // ettei se näkyisi myöhemmin jollain toisella sivulla uudestaan.
                session.removeAttribute("ilmoitus");

                sivu.setAttribute("ilmoitus", ilmoitus);
            }


            if (kayttaja.getOikeustaso() >= 0) {


                //sivu.setAttribute("naviadmin", "true");
                List<Komponentti> komponentit = Komponentti.getAseKomponentit();
                sivu.setAttribute("asekomponentit", komponentit);

                List<Komponentti> varustekomponentit = Komponentti.getVarusteKomponentit();
                sivu.setAttribute("varustekomponentit", varustekomponentit);

                List<Reaktori> reaktorit = Reaktori.getReaktorit();
                sivu.setAttribute("reaktorit", reaktorit);

                naytaJSP("komponenttiselaa.jsp", sivu, response);
                session.setAttribute("kirjautunut", kayttaja);

            } else {
                response.encodeRedirectURL("MechSelaa");
                session.setAttribute("kirjautunut", kayttaja);
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
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(KomponenttiSelaa.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException ex) {
            Logger.getLogger(KomponenttiSelaa.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(KomponenttiSelaa.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException ex) {
            Logger.getLogger(KomponenttiSelaa.class.getName()).log(Level.SEVERE, null, ex);
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
