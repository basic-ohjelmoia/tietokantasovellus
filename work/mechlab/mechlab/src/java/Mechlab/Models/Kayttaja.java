
package Mechlab.Models;

import Mechlab.Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;


/**
 */
public class Kayttaja {

     private int id;
    private String nimi;
    private String salasana;
    private int oikeustaso;
    private int vierailukerta;
    private int collection_id;
    
    public Kayttaja() {
        
    }
    
    public Kayttaja(int id, String tunnus, String salasana) {
        this.id=id;
        this.nimi=tunnus;
        this.salasana=salasana;
        this.oikeustaso=0;
        this.vierailukerta=0;
        this.collection_id=id;
    }
    
    public static List<Kayttaja> getKayttajat() throws NamingException, SQLException {
  String sql = "SELECT kayttaja_id, nimi, salasana, oikeustaso, vierailukerta from kayttaja";
  
  //Tietokanta yhteys0 = new Tietokanta();
  Connection yhteys = Tietokanta.getYhteys();
  PreparedStatement kysely = yhteys.prepareStatement(sql);
  ResultSet tulokset = kysely.executeQuery();

  ArrayList<Kayttaja> kayttajat = new ArrayList<Kayttaja>();
  while (tulokset.next()) {
    //Luodaan tuloksia vastaava olio ja palautetaan olio:
    Kayttaja k = new Kayttaja();
    k.setID(tulokset.getInt("kayttaja_id"));
    k.setNimi(tulokset.getString("nimi"));
    k.setSalasana(tulokset.getString("salasana"));
    if (tulokset.getInt("oikeustaso")>0) {k.setOikeustaso(tulokset.getInt("oikeustaso"));}
    k.setVierailukerta(tulokset.getInt("vierailukerta"));
    kayttajat.add(k);
    
  }   
//  kayttajat.add(new Kayttaja(999, "none", "nopw"));
  
  //Suljetaan kaikki resutuloksetsit:
  try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}



  
  return kayttajat;
}
    /**
     * Lisää vierialun SQL-tauluun.
     */
    public static void lisaaVierailuTauluun(Kayttaja vierailija) throws NamingException, SQLException {
  String sql = "UPDATE kayttaja SET vierailukerta = vierailukerta + 1 WHERE kayttaja_id = "+vierailija.getID();
        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             //kysely.setString(1, Integer.toString(vierailija.getID()));
             //kysely.executeQuery();
             kysely.executeUpdate();
             vierailija.lisaaVierailukerta();
             
      
  
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
  Komponentisto.siivoaKomponentisto();  // siivotaan ennen kirjautumista
  //return vierailija;
  lisaaMechKokoelma(vierailija.getID());
}
    
     public static void lisaaMechKokoelma(int id) throws NamingException, SQLException {
         
  String sql = "INSERT INTO MECHKOKOELMA (mechkokoelma_id) VALUES ("+id+")";
        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             //kysely.setString(1, Integer.toString(vierailija.getID()));
             kysely.executeUpdate();
             
             
      
  
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
         
   sql = "UPDATE kayttaja SET collection_id = "+id+" WHERE kayttaja_id = "+id;
         yhteys = Tietokanta.getYhteys();
              kysely = yhteys.prepareStatement(sql);
             //kysely.setString(1, Integer.toString(vierailija.getID()));
             kysely.executeUpdate();
             
             
      
  
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
  
  //return vierailija;
}
    
    
    public static Kayttaja etsiKayttajaTunnuksilla(String nimi, String salasana) throws NamingException, SQLException {
  String sql = "SELECT kayttaja_id,nimi,salasana,oikeustaso,vierailukerta from kayttaja where nimi = ? AND salasana = ?";
        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             kysely.setString(1, nimi);
            kysely.setString(2, salasana);
             ResultSet rs = kysely.executeQuery();
  
  //Alustetaan muuttuja, joka sisältää löydetyn käyttäjän
  Kayttaja kirjautunut = null;

  //next-metodia on kutsuttava aina, kun käsitellään 
  //vasta kannasta saatuja ResultSet-olioita.
  //ResultSet on oletuksena ensimmäistä edeltävällä -1:llä rivillä.
  //Kun sitä kutsuu ensimmäisen kerran siirtyy se ensimmäiselle riville 0.
  //Samalla metodi myös palauttaa tiedon siitä onko seuraavaa riviä olemassa.
  if (rs.next()) { 
    //Kutsutaan sopivat tiedot vastaanottavaa konstruktoria 
    //ja asetetaan palautettava olio:
    kirjautunut = new Kayttaja();
    kirjautunut.setID(rs.getInt("kayttaja_id"));
    kirjautunut.setNimi(rs.getString("nimi"));
    kirjautunut.setSalasana(rs.getString("salasana"));
    if (rs.getInt("oikeustaso")>0) {kirjautunut.setOikeustaso(rs.getInt("oikeustaso"));}
    kirjautunut.setVierailukerta(rs.getInt("vierailukerta"));
  }

  //Jos kysely ei tuottanut tuloksia käyttäjä on nyt vielä null.

  //Suljetaan kaikki resurssit:
  try { rs.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
  //Käyttäjä palautetaan vasta täällä, kun resurssit on suljettu onnistuneesti.
  return kirjautunut;
}
    
    
    
    
    
    
    public static Kayttaja getKayttaja(int kayttaja_id) throws NamingException, SQLException {
  Kayttaja k = null;
  
  String sql = "SELECT kayttaja_id, nimi from kayttaja where kayttaja_id = "+kayttaja_id;
  
      

        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
    
             ResultSet tulokset = kysely.executeQuery();
  
  
  

 
  if (tulokset.next()) { 

    k = new Kayttaja();
    k.setID(tulokset.getInt("kayttaja_id"));
    k.setNimi(tulokset.getString("nimi"));

  }
     try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
  if (k == null) 
   { 
    k = new Kayttaja();
    k.setID(-1);
    k.setNimi("User Not Found!");
    
    
  }
  //Jos kysely ei tuottanut tuloksia käyttäjä on nyt vielä null.

  //Suljetaan kaikki resurssit:

  
  //Käyttäjä palautetaan vasta täällä, kun resurssit on suljettu onnistuneesti.
  return k;
}
    
    
    public boolean onkoAdmin() {
        if (this.oikeustaso>0) {return true;}
        return false;
    }
    
    public void setOikeustaso(int oikeustaso) {
        this.oikeustaso=oikeustaso;
    }
    
    public int getOikeustaso() {
        return this.oikeustaso;
    }
    
    public void setID(int id) {
        this.id=id;
    }
    public void setNimi (String tunnus) {
        this.nimi=tunnus;
    }
    
    public void setSalasana (String sala) {
        this.salasana=sala;
    }
    
    public String getSalasana() {
        return this.salasana;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public int getID() {
        return this.id;
    }
    
    public void lisaaVierailukerta() {
        this.vierailukerta++;
    }
    
    public void setVierailukerta(int montako) {
        this.vierailukerta=montako;
    }
    
    public int getVierailukerta() {
        return this.vierailukerta;
    }
    public int getCollection_id() {
        return this.id;     // Collection_id ja userin oma id on käytännössä sama asia
    }
}