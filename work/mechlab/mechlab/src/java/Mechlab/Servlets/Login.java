package Mechlab.Servlets;

import Mechlab.Models.Kayttaja;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * Sisäänkirjautumis-servletti
 * @author Tuomas Honkala
 */
public class Login extends HttpServlet {

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
        
        request.setAttribute("navimechselaa", "");
        request.setAttribute("navilogin", "active");
        request.setAttribute("naviloginmoodi", "KIRJAUDU");
        request.setAttribute("naviloginosoite", "login");
        //request.setAttribute("kirjautuneenNimi", "Et ole kirjautunut sisään!");
        
     //   String istunnoton = "";
     String salasana = request.getParameter("password");
  String kayttaja = request.getParameter("username");
   //istunnoton = request.getParameter("istunnoton");
  //if (!istunnoton.equals("") || istunnoton != null) {
      
      //if( istunnoton != null || istunnoton.trim().length() > 0 ) {
   //if (istunnoton()>0) {
  if (null != request.getParameter("istunnoton")) {
      //istunnoton = request.getParameter("istunnoton");
      if (request.getParameter("istunnoton").equals("kylla")) {
      asetaVirhe("MechLabin käyttö edellyttää kirjautumista!", request);} else {
      asetaVirhe("Olet kirjautunut ulos Mechlabista!", request);    
      }
  //}
  }
//  Kayttaja kukalie = null;
//        kukalie = Kayttaja.etsiKayttajaTunnuksilla(kayttaja, salasana);
  /* Välitetään näkymille tieto siitä, mikä tunnus yritti kirjautumista */
  request.setAttribute("kayttaja", kayttaja);  
  
  /* Jos kummatkin parametrit ovat null, käyttäjä ei ole edes yrittänyt vielä kirjautua. 
   * Näytetään pelkkä lomake */
  if (kayttaja == null || salasana == null) {
    naytaJSP("login.jsp", request, response);
    return;
  }

  //Tarkistetaan että vaaditut kentät on täytetty:
  if (kayttaja == null || kayttaja.equals("")) {
    asetaVirhe("Kirjautuminen epäonnistui! Et antanut käyttäjätunnusta.", request);
    naytaJSP("login.jsp", request, response);
    
    return;
  }

//  /* Välitetään näkymille tieto siitä, mikä tunnus yritti kirjautumista */
//  request.setAttribute("kayttaja", kayttaja);  

  if (salasana == null || salasana.equals("")) {
    asetaVirhe("Kirjautuminen epäonnistui! Et antanut salasanaa.", request);
    naytaJSP("login.jsp", request, response);
    return;
  }
  
//  if (kayttaja.contains("Admin") && salasana.contains("1")) {//.
      
      if (Kayttaja.etsiKayttajaTunnuksilla(kayttaja, salasana)!=null) { // oli null
  //kayttaja.e
          Kayttaja kirjautuja = Kayttaja.etsiKayttajaTunnuksilla(kayttaja, salasana);
  /* Tarkistetaan onko parametrina saatu oikeat tunnukset */
  //if (kayttaja.equals("svinhufvud") && salasana.equals("kissakartano")) {
    /* Jos tunnus on oikea, ohjataan käyttäjä HTTP-ohjauksella kissalistaan. */
    //response.sendRedirect("selaaMech");
    //naytaJSP("sivu.jsp", request, response);
    //response.sendRedirect("MechSelaa");
//    RequestDispatcher rd = request.getRequestDispatcher("/MechSelaa");
//   rd.forward(request, response);
    // naytaJSP("login.jsp", request, response);
    //    asetaVirhe("Kirjautuminen olisi nyt onnistunut.", request);
    //naytaJSP("sivu.jsp", request, response);
    
      
//      asetaVirhe("Kirjautuminen onnistui!", request);
//          naytaJSP("login.jsp", request, response);
//    return;
          response.sendRedirect("MechSelaa");
          HttpSession session = request.getSession();
        

               // if (kayttaja != null) {
                     //Tallennetaan istuntoon käyttäjäolio
                    //HttpServletResponse tallennaResponse = response;
                    
                    //response = tallennaResponse;
                    session.setAttribute("kirjautunut", kirjautuja);
                 
              //  }
          
          //response.encodeRedirectURL("MechSelaa");
      
      Kayttaja.lisaaVierailuTauluun(kirjautuja);
  }
  
  else // (Kayttaja.etsiKayttajaTunnuksilla(kayttaja, salasana)==null)
//  else if (Kayttaja.getKayttajat().size()>0)
  {
  //if (Kayttaja.etsiKayttajaTunnuksilla(kayttaja, salasana)==null) {
   
    /* Väärän tunnuksen syöttänyt saa eteensä lomakkeen ja virheen.
     * Tässä käytetään omalta yläluokalta perittyjä yleiskäyttöisiä metodeja.
     */
    asetaVirhe("Kirjautuminen epäonnistui! Antamasi tunnus tai salasana on väärä.", request);
    naytaJSP("login.jsp", request, response);
    
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
    
    public void asetaVirhe(String virheteksti, HttpServletRequest request) {
        request.setAttribute("virheViesti", virheteksti);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
