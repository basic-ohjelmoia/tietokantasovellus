package Mechlab.Servlets;

import Mechlab.Models.Kayttaja;
import Mechlab.Models.Komponentti;
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
 *
 * @author mikromafia
 */
public class KomponenttiEditoi extends HttpServlet {

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
        
        request.setAttribute("navimechselaa", "");
        request.setAttribute("naviyllapito", "active");
        request.setAttribute("navilogin", "");
        request.setAttribute("naviloginmoodi", "POISTU");
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
                         
                          if (null != request.getParameter("reactor")) {   // oli: "poista"
                              
                               Reaktori reactor = Reaktori.getReaktori(komponentti_id);   // "komponentti_id" on Reaktori-luokan kannalta sama asia kuin reactor_id
                        
                                 sivu.setAttribute("reaktori", reactor);  
                        
                                     naytaJSP("reaktorieditoi.jsp", sivu, response);
                              session.setAttribute("kirjautunut", kayttaja);
                              
                
                                    
                           
                          } else  if (null != request.getParameter("equipment")) {   // oli: "poista"
                              
                               Komponentti equipment = Komponentti.getKomponentti(komponentti_id, true);
                        
                                 sivu.setAttribute("komponentti", equipment);  
                        
                                     naytaJSP("varusteeditoi.jsp", sivu, response);
                              session.setAttribute("kirjautunut", kayttaja);
                              
                        
                           
                          } 
                          
                          
                          else {
                            
                        Komponentti komponentti = Komponentti.getKomponentti(komponentti_id);
                        
                        sivu.setAttribute("komponentti", komponentti);  
                        
                        naytaJSP("komponenttieditoi.jsp", sivu, response);
                        session.setAttribute("kirjautunut", kayttaja);
                          }
                    } 
                    
            
                else {response.encodeRedirectURL("KomponenttiSelaa");
                    session.setAttribute("kirjautunut", kayttaja);
                    }
            }   else {response.encodeRedirectURL("KomponenttiSelaa");
                    session.setAttribute("kirjautunut", kayttaja);
                    }
            }
              else {response.encodeRedirectURL("KomponenttiSelaa");
                    session.setAttribute("kirjautunut", kayttaja);}
            
            if (poistettiinKomponentti) {
                         //response.encodeRedirectURL("KomponenttiSelaa");
                         response.sendRedirect("KomponenttiSelaa");
                    session.setAttribute("kirjautunut", kayttaja);
                  //  String ilmoitus = ""+komponentti.
                    session.setAttribute("ilmoitus", ilmoitusSisalto);
          
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
             String ilmoitus="";
        HttpSession session = request.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
//        try {
//            processRequest(request, response);
//        } catch (NamingException ex) {
//            Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        request.setCharacterEncoding("UTF-8"); 
//          
////         if (!onKirjautunut(request,response)) { // tarkistetaan, että on kirjautunut adminina 
////             return; 
////         } 
//         String weaponid  = request.getParameter("weaponid"); 
//        String weaponname = request.getParameter("weaponname"); 
//         String weapontype = request.getParameter("weapontype"); 
//        try {
//            Komponentti.paivitaKomponentti(weaponid, weaponname,weapontype);
//   //          
//   //         if ((kuvaus.length() > 0 && kuvaus.length() <= 300) && (ohjeet.length() > 0 && ohjeet.length() <= 300)) { 
//   //             kuvaus = estaCrossSiteScripting(kuvaus); 
//   //             ohjeet = estaCrossSiteScripting(ohjeet); 
//   //             drinkId = estaCrossSiteScripting(drinkId); 
//   //              
//   //             long drinkinId = Long.parseLong(drinkId); 
//   //              
//   //             rekisteri.paivitaDrinkki(drinkinId, kuvaus, ohjeet); 
//   //             response.sendRedirect(request.getContextPath()+"/DrinkinTiedot?id="+drinkinId); 
//   //         } else { 
//   //             request.setAttribute("virhe", "Et syöttänyt kaikkia tietoja!"); 
//   //             processRequest(request, response, drinkId); // jos tietoja puuttuu, ohjataan samalle sivulle 
//   //         } 
   //         }
boolean olikoAse=false;
boolean olikoVaruste=false;


 if (null !=      request.getParameter("reactorid")) {
           
     String reactorname = "";
         String reactorid  = request.getParameter("reactorid"); 
         reactorname = request.getParameter("equipmentname"); 
         String cooling = request.getParameter("cooling");
         String power = request.getParameter("power");
         String weight = request.getParameter("weight");
         
         
         
         ilmoitus = reactorname +" has been saved to the database!";
         if (reactorname.length()==0) {ilmoitus="Reactor #"+reactorid+" has been saved to the database and given a new common name!";}
                    try {
                        Reaktori.paivitaReaktori(reactorid, reactorname, cooling, power, weight);
                    } catch (SQLException ex) {
                        Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NamingException ex) {
                        Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }


  if (null !=      request.getParameter("equipmentid")) {
           
     
         String equipmentid  = request.getParameter("equipmentid"); 
        String equipmentname = request.getParameter("equipmentname"); 
         String equipmenttype = request.getParameter("equipmenttype"); 
         String weight = request.getParameter("weight");
         String equipmenttier = request.getParameter("equipmenttier");
         String equipmentactivity = request.getParameter("equipmentactivity");
         String heat = request.getParameter("heat");
         String volume = request.getParameter("volume");
         String location = request.getParameter("location");
         
         
         ilmoitus = equipmentname +" has been saved to the database!";
         if (equipmentname.length()==0) {ilmoitus="Equipment #"+equipmentid+" has been saved to the database and given a new common name!";}
                    try {
                        Komponentti.paivitaVarusteKomponentti(equipmentid, equipmentname,equipmenttype, weight, equipmenttier, equipmentactivity, heat, volume, location);
                    } catch (SQLException ex) {
                        Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NamingException ex) {
                        Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }

        
            if (null !=      request.getParameter("weaponid")) {
           
     
         String weaponid  = request.getParameter("weaponid"); 
        String weaponname = request.getParameter("weaponname"); 
         String weapontype = request.getParameter("weapontype"); 
         String weight = request.getParameter("weight");
         String damage = request.getParameter("damage");
         String heat = request.getParameter("heat");
         String range = request.getParameter("range");
         String ammo = request.getParameter("ammo");
         String volume = request.getParameter("volume");
         String location = request.getParameter("location");
         
         ilmoitus = weaponname +" has been saved to the database!";
                    try {
                        Komponentti.paivitaKomponentti(weaponid, weaponname,weapontype, weight, damage,heat,range, ammo, volume, location);
                    } catch (SQLException ex) {
                        Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NamingException ex) {
                        Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            
           //response.encodeRedirectURL("komponenttiselaa");
            response.sendRedirect("komponenttiselaa");
            session.setAttribute("kirjautunut", kayttaja);
            session.setAttribute("ilmoitus", ilmoitus);
//        try {
//            naytaJSP("komponenttieditoi.jsp", request, response);
//                 session.setAttribute("kirjautunut", kayttaja);
//   //response.encodeRedirectURL("MechSelaa");
//        } catch (NamingException ex) {
//            Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(KomponenttiEditoi.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
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
