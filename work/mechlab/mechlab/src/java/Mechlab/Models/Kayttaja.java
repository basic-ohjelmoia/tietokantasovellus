
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
    
    public Kayttaja() {
        
    }
    
    public Kayttaja(int id, String tunnus, String salasana) {
        this.id=id;
        this.nimi=tunnus;
        this.salasana=salasana;
    }
    
    public static List<Kayttaja> getKayttajat() throws NamingException, SQLException {
  String sql = "SELECT kayttaja_id, nimi, salasana from kayttaja";
  
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

    kayttajat.add(k);
    
  }   
//  kayttajat.add(new Kayttaja(999, "none", "nopw"));
  
  //Suljetaan kaikki resutuloksetsit:
  try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}



  
  return kayttajat;
}
    
    
    public static Kayttaja etsiKayttajaTunnuksilla(String nimi, String salasana) throws NamingException, SQLException {
  String sql = "SELECT kayttaja_id,nimi, salasana from kayttaja where nimi = ? AND salasana = ?";
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
  }

  //Jos kysely ei tuottanut tuloksia käyttäjä on nyt vielä null.

  //Suljetaan kaikki resurssit:
  try { rs.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
  //Käyttäjä palautetaan vasta täällä, kun resurssit on suljettu onnistuneesti.
  return kirjautunut;
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
}