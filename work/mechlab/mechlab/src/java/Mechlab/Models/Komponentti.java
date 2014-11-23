
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
public class Komponentti {

    private int komponentti_id;
    private String numero;
    private String nimi;
    private int massa;
    private String kokoluokka;
    private int heat;
    private String kategoria;
    private String sijoituspaikka;
    private int weaponDamage;
    private int weaponMaxRange;
    private int weaponMinRange;
    private String weapontype;
    private int weaponAmmo;
    private String varusteType;
    private int varusteTier;
    private String varusteActivity;
    
    private int oikeustaso;

    
    public Komponentti() {
        
    }
    
    public Komponentti(int newID, String nimi, String kategoria) {
        this.komponentti_id=newID;
        this.numero=String.valueOf(komponentti_id);
        this.nimi=nimi;
        this.kategoria=kategoria;
        this.massa=1;
        this.kokoluokka="small";
        this.heat=0;
        this.sijoituspaikka="all";
        this.weaponDamage=0;
        this.weaponMaxRange=0;
        this.weaponMinRange=0;
        this.weapontype="none";
        this.weaponAmmo=0;
        this.varusteType="none";
        this.varusteTier=0;
        this.varusteActivity="none";
    }
    
    
//    komponentti_id	INTEGER NOT NULL,
//	nimi	CHAR(40)	NOT NULL,
//	massa	INTEGER		NOT NULL,
//	kokoluokka	CHAR(20)	NOT NULL,
//	heat	INTEGER		NOT NULL,
//	kategoria	CHAR(20)	NOT NULL,
//	sijoituspaikka 	CHAR(20)	NOT NULL,
//	weapon_damage	INTEGER,
//	weapon_maxrange	INTEGER,
//	weapon_minrange	INTEGER,
//	weapon_type	CHAR(20),
//	weapon_ammo	INTEGER,
//	varuste_type	CHAR(20),
//	varuste_tier	INTEGER,
//	varuste_activity CHAR(20),
    
    public int getCost () {
        int hinta = 500 * this.weaponDamage;
        if (this.massa*1.5 > this.weaponDamage) {hinta=hinta/2;}
        if (this.massa*1.25 > this.weaponDamage) {hinta=hinta/2;}
        if (this.massa < this.weaponDamage) {hinta=hinta*2;}
        if (this.massa*1.5 < this.weaponDamage) {hinta=hinta*2;}
        if (this.massa*2 < this.weaponDamage) {hinta=hinta*2;}
        hinta = hinta + ((21-this.heat)*50);
        hinta = hinta + ((25-this.weaponMaxRange)*25);
        if (this.weapontype.equalsIgnoreCase("KINETIC")) {hinta=hinta/3; hinta+=(hinta/6)*this.weaponAmmo;}
        if (this.weapontype.equalsIgnoreCase("MISSILE")) {hinta=(hinta/2)+1000; hinta+=(hinta/5)*this.weaponAmmo;}
        if (this.weapontype.equalsIgnoreCase("MELEE")) {hinta=(hinta*2)+2000;}
        if (this.weaponAmmo==0) {hinta=(hinta*2)+500;} else {hinta-=(hinta/(2+this.weaponAmmo));}
        return hinta;
    }
        
            
   public static void paivitaKomponentti(String idstring, String nimi, String tyyppi, String weight, String strdamage, String strheat, String strrange, String strammo, String volume, String location) throws SQLException, NamingException {
       boolean uusiKomponentti = false;
       int id = Integer.parseInt(idstring);
       int massa = 0;
       int damage = 0;
       int heat = 0;
       int maxrange = 0;
       int minrange = 0;
       int ammo = 0;
       
       Komponentti komponentti = getKomponentti(id);
       if (komponentti == null) {komponentti = getKomponentti(0); uusiKomponentti = true;}
       
       if (nimi.equalsIgnoreCase("keep") || nimi.length()<1) {nimi = komponentti.getNimi();}
       
       if (nimi.length()>39) {nimi=nimi.substring(0, 38);}
       if (tyyppi.equalsIgnoreCase("keep")) {tyyppi = komponentti.getWeapontype();}
       if (weight.equalsIgnoreCase("keep")) {massa = komponentti.getMassa();} else {massa = Integer.parseInt(weight);}
       if (strdamage.equalsIgnoreCase("keep")) {damage = komponentti.getWeapondamage();} else {damage = Integer.parseInt(strdamage);}
       if (strheat.equalsIgnoreCase("keep")) {heat = komponentti.getHeat();} else {heat = Integer.parseInt(strheat);}
       if (strrange.equalsIgnoreCase("keep")) {maxrange = komponentti.getWeaponmaxrange(); minrange = komponentti.getWeaponminrange();} else {
           int newMax = 0;
           int newMin = 0;
           if (strrange.equalsIgnoreCase("cqb")) {newMax =1; newMin=1;}
           if (strrange.equalsIgnoreCase("short")) {newMax =3; newMin=1;}
           if (strrange.equalsIgnoreCase("medium")) {newMax =5; newMin=1;}
           if (strrange.equalsIgnoreCase("long")) {newMax =9; newMin=2;}
           if (strrange.equalsIgnoreCase("vlng")) {newMax =12; newMin=3;}
           if (strrange.equalsIgnoreCase("arty")) {newMax =15; newMin=5;}
           maxrange=newMax;
           minrange=newMin;
       }
         if (strammo.equalsIgnoreCase("keep")) {ammo = komponentti.getWeaponammo();} else {
            ammo = 0; // DEFAULT 0 = INFINITE AMMO 
           
           
           if (strammo.equalsIgnoreCase("single")) {ammo=1;}
           if (strammo.equalsIgnoreCase("limited")) {ammo=5;}
           if (strammo.equalsIgnoreCase("standard")) {ammo=10;}
           if (strammo.equalsIgnoreCase("extended")) {ammo=15;}
           if (strammo.equalsIgnoreCase("plentiful")) {ammo=25;}
        }
         if (volume.equalsIgnoreCase("keep")) {volume = komponentti.getKokoluokka();}
         if (location.equalsIgnoreCase("keep")) {location = komponentti.getSijoituspaikka();}
         //if (komponentti.getKategoria() == null) {
         
         
         
          String sql = "UPDATE komponentti SET kategoria = 'ASE', nimi = '"+nimi+"', massa = "+massa+", weapon_type = '"+tyyppi.toUpperCase()+
                  "', heat = "+heat+", weapon_damage = "+damage+", weapon_maxrange = "+maxrange+", weapon_minrange = "+minrange+
                  ", weapon_ammo = "+ammo+", kokoluokka = '"+volume.toUpperCase()+"', sijoituspaikka = '"+location+"'"+
                  " WHERE komponentti_id = "+id;
          if (uusiKomponentti) {
              
              if (nimi.equalsIgnoreCase("Unnamed Component")) {
                  
                    Komponentti k = new Komponentti();  // tämä komponentti luodaan pelkästään lyhenteen "laskemiseksi"
                        k.setID(id);
                    k.setNimi(nimi);
                    k.setMassa(massa);
                    k.setKokoluokka(volume.toUpperCase());
                    k.setWeapontype(tyyppi.toUpperCase());
                    k.setHeat(heat);
                    k.setWeapondamage(damage);
                    k.setWeaponmaxrange(maxrange);
                    k.setWeaponminrange(minrange);
                    k.setWeaponammo(ammo);
                    
                  nimi = k.getLyhenne();}
              
         sql = "INSERT INTO KOMPONENTTI (komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo) "+  
                 "VALUES ("+id+", '"+nimi+"', "+massa+", '"+volume.toUpperCase()+"', "+heat+", 'ASE', '"+location.toUpperCase()+"', "+damage+", "+maxrange+", "+minrange+", '"+tyyppi.toUpperCase()+"', "+ammo+")";
          }
          
       //String sql ="UPDATE komponentti SET weapon_type = '12345678901234567890'";// WHERE komponentti_id = 2";
        //String sql = "UPDATE komponentti SET weapon_type = '"+tyyppi.toUpperCase()+"' WHERE komponentti_id = "+id;
                
                //+ "REPLACE (weapon_type,  = '"+tyyppi+"' WHERE komponentti_id = 2";//+id;
        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             //kysely.setString(1, Integer.toString(vierailija.getID()));
             ResultSet rs = kysely.executeQuery();
           //  vierailija.lisaaVierailukerta();
             
      
  try { rs.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
  //return vierailija;
//        if (uusiKomponentti) {
//            String lyhennenimi = nimi;
//            if (nimi.equalsIgnoreCase("Unnamed Component")) {lyhennenimi = Komponentti.getKomponentti(id).getLyhenne();
//         sql = "UPDATE komponentti SET nimi = '"+lyhennenimi+"' WHERE komponentti_id = \"+id";
//           yhteys = Tietokanta.getYhteys();
//              kysely = yhteys.prepareStatement(sql);
//             //kysely.setString(1, Integer.toString(vierailija.getID()));
//              kysely.executeQuery();
//           //  vierailija.lisaaVierailukerta();
//             
//      
//  try { rs.close(); } catch (Exception e) {}
//  try { kysely.close(); } catch (Exception e) {}
//  try { yhteys.close(); } catch (Exception e) {}
//                }
//        }
   }
   
   public static void poistaKomponentti(int komponentti_id) throws NamingException, SQLException {
       String sql = "DELETE FROM komponentti WHERE komponentti_id = "+ komponentti_id;
         Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             kysely.executeQuery();
             //ResultSet tulokset = kysely.executeQuery();
   }
   
   public String getLyhenne() {
       String lyhenne = "";
       //Komponentti komponentti = getKomponentti(komponentti_id);
       if (getMassa()>=15) {lyhenne="X";}
       else if (getMassa()>=8 || getKokoluokka().contains("LARGE") || getKokoluokka().contains("XL")) {lyhenne="H";}
       else if (getMassa()>=5 || getKokoluokka().contains("MEDIUM")) {lyhenne="M";}
       else if (getMassa()<=3) {lyhenne="S";}
       
       if (getWeapontype().equalsIgnoreCase("ENERGY")) {
           if (getMassa()<9) {lyhenne+="LAS";} else {lyhenne+="PPC";}
           if (getHeat()==0) {lyhenne="F.VAC";}
            }
       if (getWeapontype().equalsIgnoreCase("AUTO")) {
           if (getMassa()<6) {lyhenne+="MG";} else {lyhenne+="GAU";}}
       if (getWeapontype().equalsIgnoreCase("KINETIC")) {
           if (getMassa()<=10 || getWeapondamage()<=20) {lyhenne+="AC";} else {lyhenne="RAIL";}}
       if (getWeapontype().equalsIgnoreCase("MISSILE")) {
           lyhenne="RM-"+getWeapondamage();
           
           if (getWeaponmaxrange()<=6) {lyhenne="S"+lyhenne;}
           else if (getWeaponmaxrange()<=9) {lyhenne="M"+lyhenne;}
           else {lyhenne="L"+lyhenne;}
           
           if (getWeapondamage()>20) {lyhenne="SO-MSL-";}
       }
       if (getWeapontype().equalsIgnoreCase("MELEE")) {
           lyhenne="BLU-"+(getMassa()*getWeapondamage())+"00";}
       else {
           lyhenne+="-"+(getWeapondamage()+getMassa()*getHeat());
           if (getHeat()>=10) {lyhenne+="H";}
           if (getHeat()==0) {lyhenne+="S";}
           if (getWeapondamage()>=12) {lyhenne+="X";}
           if (getWeapondamage()<5) {lyhenne+="P";}
           if (getWeaponmaxrange()>=12) {lyhenne+="L";}
       }
           
       
       
       return lyhenne;
   }
    
    public static Komponentti getKomponentti(int komponentti_id) throws NamingException, SQLException {
  Komponentti k = null;
  
  if (komponentti_id>0) {
        
  String sql = "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo FROM komponentti WHERE komponentti_id="+komponentti_id;
        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
    
             ResultSet tulokset = kysely.executeQuery();
  
  
  

 
  if (tulokset.next()) { 

    k = new Komponentti();
    k.setID(tulokset.getInt("komponentti_id"));
    k.setNimi(tulokset.getString("nimi"));
    k.setMassa(tulokset.getInt("massa"));
    k.setKokoluokka(tulokset.getString("kokoluokka"));
    k.setHeat(tulokset.getInt("heat"));
    k.setKategoria(tulokset.getString("kategoria"));
    k.setSijoituspaikka(tulokset.getString("sijoituspaikka"));
    k.setWeapondamage(tulokset.getInt("weapon_damage"));
    k.setWeaponmaxrange(tulokset.getInt("weapon_maxrange"));
    k.setWeaponminrange(tulokset.getInt("weapon_minrange"));
    k.setWeapontype(tulokset.getString("weapon_type"));
    k.setWeaponammo(tulokset.getInt("weapon_ammo"));
    //if (tulokset.getInt("oikeustaso")>0) {k.setOikeustaso(tulokset.getInt("oikeustaso"));}
   // komponentit.add(k);

  }
     try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  }
  else { //if (komponentti_id==0) {
    k = new Komponentti();
    k.setID(getUusiID());
    k.setNimi("Unnamed Component");
    k.setMassa(1);
    k.setKokoluokka("Small");
    k.setHeat(1);
 //   k.setKategoria("ASE"); // jätetään nulliksi, jotta uusi vastaluotu komponentti tunnistetaan uudeksi
    k.setSijoituspaikka("ALL");
    k.setWeapondamage(1);
    k.setWeaponmaxrange(1);
    k.setWeaponminrange(1);
    k.setWeapontype("ENERGY");
    k.setWeaponammo(0);
  }
  //Jos kysely ei tuottanut tuloksia käyttäjä on nyt vielä null.

  //Suljetaan kaikki resurssit:

  
  //Käyttäjä palautetaan vasta täällä, kun resurssit on suljettu onnistuneesti.
  return k;
}
    
    
    public static List<Komponentti> getAseKomponentit() throws NamingException, SQLException {
  String sql = "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo FROM komponentti WHERE kategoria='ASE'"
          +" ORDER BY weapon_type ASC, massa DESC, weapon_damage DESC";
  
  //Tietokanta yhteys0 = new Tietokanta();
  Connection yhteys = Tietokanta.getYhteys();
  PreparedStatement kysely = yhteys.prepareStatement(sql);
  ResultSet tulokset = kysely.executeQuery();

  ArrayList<Komponentti> komponentit = new ArrayList<Komponentti>();
  while (tulokset.next()) {
    //Luodaan tuloksia vastaava olio ja palautetaan olio:
    Komponentti k = new Komponentti();
    k.setID(tulokset.getInt("komponentti_id"));
    k.setNimi(tulokset.getString("nimi"));
    k.setMassa(tulokset.getInt("massa"));
    k.setKokoluokka(tulokset.getString("kokoluokka"));
    k.setHeat(tulokset.getInt("heat"));
    k.setKategoria(tulokset.getString("kategoria"));
    k.setSijoituspaikka(tulokset.getString("sijoituspaikka"));
    k.setWeapondamage(tulokset.getInt("weapon_damage"));
    k.setWeaponmaxrange(tulokset.getInt("weapon_maxrange"));
    k.setWeaponminrange(tulokset.getInt("weapon_minrange"));
    k.setWeapontype(tulokset.getString("weapon_type"));
    k.setWeaponammo(tulokset.getInt("weapon_ammo"));
    //if (tulokset.getInt("oikeustaso")>0) {k.setOikeustaso(tulokset.getInt("oikeustaso"));}
    komponentit.add(k);
    
  }   
//  kayttajat.add(new Kayttaja(999, "none", "nopw"));
  
  //Suljetaan kaikki resutuloksetsit:
  try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}



  
  return komponentit;
}
    
    /**
     * Super-räyhäkkäästi koodattu metodi, joka etsii ensimmäisen vapaan id-numeron...
     * @return vpaaa id
     * @throws NamingException
     * @throws SQLException 
     */
    public static int getUusiID() throws NamingException, SQLException {
        List<Komponentti> vanhat = getKomponentit();
        int suurinID = 1;
        
//        for (int i = 1; i<10000; i++) {
//            boolean olikoVapaa=true;
        
        for (Komponentti komponentti : vanhat) {
            if (komponentti.getKomponentti_id()>=suurinID) {
                suurinID=komponentti.getKomponentti_id();
            }
        }
//        if (olikoVapaa) {return laskuri;} else {laskuri++;}
//        
//        }
//        return laskuri;
        return suurinID+1;
    }
    
    public static List<Komponentti> getKomponentit() throws NamingException, SQLException {
  String sql = "SELECT komponentti_id, nimi, kategoria FROM komponentti";
  
  //Tietokanta yhteys0 = new Tietokanta();
  Connection yhteys = Tietokanta.getYhteys();
  PreparedStatement kysely = yhteys.prepareStatement(sql);
  ResultSet tulokset = kysely.executeQuery();

  ArrayList<Komponentti> komponentit = new ArrayList<Komponentti>();
  while (tulokset.next()) {
    //Luodaan tuloksia vastaava olio ja palautetaan olio:
    Komponentti k = new Komponentti();
    k.setID(tulokset.getInt("komponentti_id"));
    k.setNimi(tulokset.getString("nimi"));
    k.setKategoria(tulokset.getString("kategoria"));
    //if (tulokset.getInt("oikeustaso")>0) {k.setOikeustaso(tulokset.getInt("oikeustaso"));}
    komponentit.add(k);
    
  }   
//  kayttajat.add(new Kayttaja(999, "none", "nopw"));
  
  //Suljetaan kaikki resutuloksetsit:
  try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}



  
  return komponentit;
}
    /**
     * Lisää vierialun SQL-tauluun.
     */
    public static void lisaaVierailuTauluun(Komponentti vierailija) throws NamingException, SQLException {
  String sql = "UPDATE kayttaja SET vierailukerta = vierailukerta + 1 WHERE kayttaja_id = "+vierailija.getKomponentti_id();
        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             //kysely.setString(1, Integer.toString(vierailija.getID()));
             ResultSet rs = kysely.executeQuery();
           //  vierailija.lisaaVierailukerta();
             
      
  try { rs.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
  //return vierailija;
}
    
    
    public static Komponentti etsiKayttajaTunnuksilla(String nimi, String salasana) throws NamingException, SQLException {
  String sql = "SELECT kayttaja_id,nimi,salasana,oikeustaso,vierailukerta from kayttaja where nimi = ? AND salasana = ?";
        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             kysely.setString(1, nimi);
            kysely.setString(2, salasana);
             ResultSet rs = kysely.executeQuery();
  
  //Alustetaan muuttuja, joka sisältää löydetyn käyttäjän
  Komponentti kirjautunut = null;

  //next-metodia on kutsuttava aina, kun käsitellään 
  //vasta kannasta saatuja ResultSet-olioita.
  //ResultSet on oletuksena ensimmäistä edeltävällä -1:llä rivillä.
  //Kun sitä kutsuu ensimmäisen kerran siirtyy se ensimmäiselle riville 0.
  //Samalla metodi myös palauttaa tiedon siitä onko seuraavaa riviä olemassa.
  if (rs.next()) { 
    //Kutsutaan sopivat tiedot vastaanottavaa konstruktoria 
    //ja asetetaan palautettava olio:
    kirjautunut = new Komponentti();
    kirjautunut.setID(rs.getInt("kayttaja_id"));
    kirjautunut.setNimi(rs.getString("nimi"));
    kirjautunut.setKategoria(rs.getString("salasana"));
   // if (rs.getInt("oikeustaso")>0) {kirjautunut.setOikeustaso(rs.getInt("oikeustaso"));}
   // kirjautunut.setVierailukerta(rs.getInt("vierailukerta"));
  }

  //Jos kysely ei tuottanut tuloksia käyttäjä on nyt vielä null.

  //Suljetaan kaikki resurssit:
  try { rs.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
  //Käyttäjä palautetaan vasta täällä, kun resurssit on suljettu onnistuneesti.
  return kirjautunut;
}
    

    

    
    public void setID(int newID) {
        this.komponentti_id=newID;
        
    }
    public void setNimi (String nimi) {
        this.nimi=nimi;
    }
    
    public void setKategoria (String kategoria) {
        this.kategoria=kategoria;
    }
    
    public String getKategoria() {
        return this.kategoria;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public int getKomponentti_id() {
        return this.komponentti_id;
    }
    
 public void setMassa (int massa) {
        this.massa=massa;
    } 
    public int getMassa () {
        return this.massa;
    }
    
    public void setHeat (int heat) {
        this.heat=heat;
    }
        public int getHeat () {
        return this.heat;
    }

     public void setWeapondamage (int dmg) {
        this.weaponDamage=dmg;
    }
            public int getWeapondamage () {
        return this.weaponDamage;
    }
     
     public void setWeaponminrange (int rng) {
        this.weaponMinRange=rng;
    }
            public int getWeaponminrange () {
        return this.weaponMinRange;
    }
     
     public void setWeaponmaxrange (int rng) {
        this.weaponMaxRange=rng;
    }
         public int getWeaponmaxrange () {
        return this.weaponMaxRange;
    }
     
     public void setWeaponammo (int ammo) {
        this.weaponAmmo=ammo;
    } 
          public int getWeaponammo () {
        return this.weaponAmmo;
    }
     
     public void setKokoluokka(String nimike) {
         this.kokoluokka=nimike;
     }
     public String getKokoluokka() {
         return this.kokoluokka;
     }
     
     public void setSijoituspaikka(String nimike) {
         this.sijoituspaikka=nimike;
     }
     public String getSijoituspaikka() {
         return this.sijoituspaikka;
     }
     
          public void setWeapontype(String nimike) {
         this.weapontype=nimike;
     }
          public String getWeapontype() {
              return this.weapontype;
          }
    
}
