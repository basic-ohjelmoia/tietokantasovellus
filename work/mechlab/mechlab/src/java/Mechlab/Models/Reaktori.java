
package Mechlab.Models;

import Mechlab.Tietokanta.Tarkistaja;
import Mechlab.Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;


/**
 * 	reaktori_id INTEGER NOT NULL,
	nimi	VARCHAR(40)	NOT NULL,
	cooling	INTEGER		NOT NULL,
	teho	INTEGER		NOT NULL,
	massa	INTEGER		NOT NULL,
	PRIMARY KEY (reaktori_id)
 */
public class Reaktori extends Komponentti {

    private int reaktori_id;
    private String nimi;
    private int cooling;
    private int teho;
    private int massa;
    
    
    public Reaktori() {
        
    }
    
    public Reaktori(int newID, String nimi, int cooling, int teho, int massa) {
        this.reaktori_id=newID;
        this.nimi=nimi;
        this.cooling=cooling;
        this.teho=teho;
        this.massa=massa;
        
    }
    
    
    
    @Override
    public int getCost () {
        //int hinta = 1;
        return (this.massa*1000)+(this.cooling*this.teho*10);

    }
                
                                                
                                                    //equipmentid, equipmentname,equipmenttype, weight, equipmenttier, equipmentactivity, heat, volume, location);
   public static void paivitaReaktori(String idstring, String nimi, String strcooling, String strteho, String strmassa) throws SQLException, NamingException {
       boolean uusiReaktori = false;
       int id = Integer.parseInt(idstring);
       int massa = 1;
       int cooling = 10;
       int teho = 100;
     
       
       Reaktori reaktori = getReaktori(id); //, true);   // = true tarkoittaa, että haetaan equipment-tyyppistä komponenttia
       if (reaktori == null) {reaktori = getReaktori(0); id = getUusiID(); uusiReaktori = true;}
       
       if (nimi.equalsIgnoreCase("keep")) {nimi = reaktori.getNimi();}
       
       if (nimi.length()>39) {nimi=nimi.substring(0, 38);}
       if (strcooling.equalsIgnoreCase("keep")) {cooling = reaktori.getCooling();} else {cooling = Integer.parseInt(strcooling);}
       if (strmassa.equalsIgnoreCase("keep")) {massa = reaktori.getMassa();} else {massa = Integer.parseInt(strmassa);}
       if (strteho.equalsIgnoreCase("keep")) {teho = reaktori.getTeho();} else {teho = Integer.parseInt(strteho);}
       
         //if (komponentti.getKategoria() == null) {
         

       
               // TARKISTETAAN SYÖTTEIDEN LAILLISUUUS:
         if (cooling<2 || cooling>30) {cooling=10;}
         if (teho<40 || teho>500) {teho=150;}
         if (massa<1 || massa>25) {massa=5;}
       
         if (nimi.length()==0 || nimi == null) {
          Reaktori k = new Reaktori();  // tämä komponentti luodaan pelkästään lyhenteen "laskemiseksi"
                        k.setID(id);
                    k.setNimi(nimi);
                    //k.setKategoria("VARUSTE");
                    k.setMassa(massa);
                    k.setCooling(cooling);
                    k.setTeho(teho);
                    
                    
                    
                  nimi = k.getReaktoriCommonName();}
         
          String sql = "UPDATE reaktori SET nimi = '"+nimi+"', cooling = "+cooling+", teho = "+teho+", massa = "+massa+
                  " WHERE reaktori_id = "+id;
          if (uusiReaktori) {
              
if (nimi.equalsIgnoreCase("Unnamed Reactor") || nimi.length()==0 || nimi == null || nimi.equalsIgnoreCase("Unnamed Equipment") || nimi.equalsIgnoreCase("NEW-000A")) {
                  
                    Reaktori k = new Reaktori();  // tämä komponentti luodaan pelkästään lyhenteen "laskemiseksi"
                        k.setID(id);
                    k.setNimi(nimi);
                     k.setMassa(massa);
                    k.setCooling(cooling);
                    k.setTeho(teho);
                    
                    
                  nimi = k.getReaktoriCommonName();}//.getLyhenne();}
              
         sql = "INSERT INTO reaktori (reaktori_id, nimi, cooling, teho, massa) "
                +"VALUES ("+id+", '"+nimi+"', "+cooling+", "+teho+", "+massa+")";
          }

        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             //kysely.setString(1, Integer.toString(vierailija.getID()));
             kysely.executeUpdate();
           //  vierailija.lisaaVierailukerta();
             
      
  
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
 
   }
            
  
   
   public static void poistaReaktori(int reaktori_id) throws NamingException, SQLException {
       String sql = "DELETE FROM reaktori WHERE reaktori_id = "+ reaktori_id;
         Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             //kysely.executeQuery();
             //ResultSet tulokset = kysely.executeQuery();
      kysely.executeUpdate();
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
   }
   
   public String getReaktoriCommonName() {
         String commonName ="";
       boolean hightech = false;
       String etuliite = "";
       if (this.teho>300) {commonName="Fusion Reactor";}
       else if (this.teho>=200) {commonName="Fission Reactor";}
       else {commonName="ICE Reactor";}
       
       if (massa>=9) {etuliite="Large ";}
       else if (massa<=4) {etuliite="Small ";}
       
       if (cooling>=12) {etuliite=etuliite+"Cold ";}
       else if (cooling<8) {etuliite=etuliite+"Red ";}
       
       
       return etuliite+commonName+" "+this.teho;
       
   }
   
   
   
   public String getLyhenne() {
       
         String lyhenne ="";
       String takaliite="";
       int kerroin = 100;
       if (this.teho>300) {lyhenne="FXR";}
       else if (this.massa>200) {lyhenne="FR";}
       else {lyhenne="ICE";}
       
       if (massa>=10) {kerroin = 1000;}
       else if (massa<=5) {kerroin = 10;}
       
       if (cooling>=15) {takaliite="X";}
       else if (cooling>=12) {takaliite="C";}
       else if (cooling<8) {takaliite="H";}
       else if (cooling==10 && this.teho>250) {takaliite="B";}
       else {takaliite="A";}
       
       int sarjanumero = kerroin*this.massa + ((this.teho*this.cooling)/(this.massa));
               
       return lyhenne+"-"+sarjanumero+takaliite+this.reaktori_id;
       
      
   }
    

    public static Reaktori getReaktori(int reaktori_id) throws NamingException, SQLException {
  Reaktori k = null;
  
  if (reaktori_id>0) {
  
        String sql = "SELECT reaktori_id, nimi, cooling, teho, massa FROM reaktori WHERE reaktori_id="+reaktori_id;
//  String sql = "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo FROM komponentti WHERE komponentti_id="+komponentti_id;
 // if (equipment) {
    //  sql = "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, varuste_type, varuste_tier, varuste_activity FROM komponentti WHERE komponentti_id="+komponentti_id;
  //}
        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
    
             ResultSet tulokset = kysely.executeQuery();
  
  
  

 
  if (tulokset.next()) { 

    k = new Reaktori();
    k.setID(tulokset.getInt("reaktori_id"));
    k.setNimi(tulokset.getString("nimi"));
    k.setCooling(tulokset.getInt("cooling"));
    k.setTeho(tulokset.getInt("teho"));
    k.setMassa(tulokset.getInt("massa"));
    
    //if (tulokset.getInt("oikeustaso")>0) {k.setOikeustaso(tulokset.getInt("oikeustaso"));}
   // komponentit.add(k);

  }
     try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  }
  else { //if (komponentti_id==0) {
    k = new Reaktori();
    k.setID(getUusiID());
    k.setNimi("Unnamed Reactor");
    k.setMassa(1);
    k.setCooling(10);
    k.setTeho(150);
    
  }
  //Jos kysely ei tuottanut tuloksia käyttäjä on nyt vielä null.

  //Suljetaan kaikki resurssit:

  
  //Käyttäjä palautetaan vasta täällä, kun resurssit on suljettu onnistuneesti.
  return k;
}
    

    
       public static List<Reaktori> getReaktorit() throws NamingException, SQLException {
           
  String sql = "SELECT reaktori_id, nimi, cooling, teho, massa FROM reaktori" // WHERE kategoria='VARUSTE'"
  //        "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo FROM komponentti WHERE kategoria='ASE'"
          +" ORDER BY teho ASC, massa ASC, cooling DESC";
  
  //Tietokanta yhteys0 = new Tietokanta();
  Connection yhteys = Tietokanta.getYhteys();
  PreparedStatement kysely = yhteys.prepareStatement(sql);
  ResultSet tulokset = kysely.executeQuery();

  ArrayList<Reaktori> reaktorit = new ArrayList<Reaktori>();
  while (tulokset.next()) {
    //Luodaan tuloksia vastaava olio ja palautetaan olio:
    Reaktori k = new Reaktori();
    k.setID(tulokset.getInt("reaktori_id"));
      k.setNimi(tulokset.getString("nimi"));
    k.setCooling(tulokset.getInt("cooling"));
    k.setTeho(tulokset.getInt("teho"));
    k.setMassa(tulokset.getInt("massa"));
    
   
    reaktorit.add(k);
    
  }   
//  kayttajat.add(new Kayttaja(999, "none", "nopw"));
  
  //Suljetaan kaikki resutuloksetsit:
  try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}



  
  return reaktorit;
}
    
    
  
    
    /**
     * Super-räyhäkkäästi koodattu metodi, joka etsii ensimmäisen vapaan id-numeron...
     * @return vpaaa id
     * @throws NamingException
     * @throws SQLException 
     */
    public static int getUusiID() throws NamingException, SQLException {
        List<Reaktori> vanhat = getReaktorit();
        int suurinID = 1;
        
//        for (int i = 1; i<10000; i++) {
//            boolean olikoVapaa=true;
        
        for (Reaktori reaktori : vanhat) {
            if (reaktori.getReaktori_id()>=suurinID) {
                suurinID=reaktori.getReaktori_id();
            }
        }
//        if (olikoVapaa) {return laskuri;} else {laskuri++;}
//        
//        }
//        return laskuri;
        return suurinID+1;
    }
    
    

    
    @Override
    public void setID(int newID) {
        this.reaktori_id=newID;
        
    }
    @Override
    public void setNimi (String nimi) {
        this.nimi=nimi;
    }
    

    
    @Override
    public String getKategoria() {
        return "REAKTORI";
    }
    
    @Override
    public String getNimi() {
        return this.nimi;
    }
    
    public int getReaktori_id() {
        return this.reaktori_id;
    }
    
    @Override
 public void setMassa (int massa) {
        this.massa=massa;
    } 
    @Override
    public int getMassa () {
        return this.massa;
    }
    
   
    @Override
        public int getHeat () {
        return 0;
    }
        
    @Override
        public int getVarustetier() {
            return 0;
        }
        
      
    @Override
        public String getVarustetype() {
            return "REACTOR";
        }
        
      
        
    @Override
        public String getVarusteactivity() {
            return "REACTOR";
        }


    @Override
            public int getWeapondamage () {
        return 0;
    }
     

    @Override
            public int getWeaponminrange () {
        return 0;
    }
     

    @Override
         public int getWeaponmaxrange () {
return 0;
    }
     

    @Override
          public int getWeaponammo () {
 return 0;
    }
     

    @Override
     public String getKokoluokka() {
         if (this.massa>=10) {return "XL";}
         else if (this.massa>=7) {return "LARGE";}
         else if (this.massa>=3) {return "MEDIUM";}
         return "SMALL";
     }
     
  
    @Override
     public String getSijoituspaikka() {
         return "ANY_TORSO";
     }
     
      
    @Override
          public String getWeapontype() {
              return "REACTOR";
          }
 
    
    public void setCooling(int cooling) {
        this.cooling=cooling;
    }
    
    public int getCooling() {
        return this.cooling;
    }
    
    public void setTeho(int teho) {
        this.teho=teho;
    }
    
    public int getTeho() {
        return this.teho;
    }
    
}



