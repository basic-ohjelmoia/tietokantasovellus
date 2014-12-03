
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
    private int komponentisto_id;
    private String sijainti;
    

    
    public Komponentti() {
        this.sijainti ="Unknown";
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
        this.komponentisto_id=0;
        this.sijainti ="Unknown";
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
    
    public void setSijainti(String sijainti) {
        this.sijainti=sijainti;
    }
    
    public String getSijainti() {
        return this.sijainti;
    }
    
    public String getSijaintilyhyt() {
        return Tarkistaja.lyhentaja(this.sijainti);
    }
    
    
    public int getCost () {
        int hinta = 1;
          if (this.kategoria == null) {
           return 100;
       }
        
        if (this.kategoria.equalsIgnoreCase("ASE")) {
         hinta = 500 * this.weaponDamage;
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
        if (this.weaponAmmo==0) {hinta=(hinta*2)+500;} else {hinta-=(hinta/(2+this.weaponAmmo));}}
        else {
            hinta = 100 * (25-this.massa);
            hinta = hinta + ((this.varusteType.length()*50)/(this.varusteTier*this.varusteTier));
            hinta = hinta + ((21-this.heat)*25);
            if (this.varusteType.equalsIgnoreCase("COCKPIT")) {hinta = hinta * 25;}
            if (this.varusteType.equalsIgnoreCase("GYROSCOPE")) {hinta = hinta * 10;}
            if (this.varusteType.equalsIgnoreCase("SENSORS")) {hinta = hinta * 5;}
            if (this.varusteType.equalsIgnoreCase("ACTIVE CAMO")) {hinta = hinta * 75;}
            if (this.varusteType.equalsIgnoreCase("TARGETTING COMPUTER")) {hinta = hinta * 15;}
            if (this.varusteTier==1) {hinta += 5000;}
            if (this.varusteTier==2) {hinta += 1500;}
            if (this.varusteTier==3) {hinta = hinta/2;}
            if (this.massa==1) {hinta += 2500;}
            if (this.heat<=1) {hinta += 1000;}
        }
        return hinta;
    }
                
                                                
                                                    //equipmentid, equipmentname,equipmenttype, weight, equipmenttier, equipmentactivity, heat, volume, location);
   public static void paivitaVarusteKomponentti(String idstring, String nimi, String tyyppi, String weight, String strtier, String activity, String strheat, String volume, String location) throws SQLException, NamingException {
       boolean uusiKomponentti = false;
       int id = Integer.parseInt(idstring);
       int massa = 1;
       int tier = 3;
       int heat = 0;
     
       
       Komponentti komponentti = getKomponentti(id, true);   // = true tarkoittaa, että haetaan equipment-tyyppistä komponenttia
       if (komponentti == null) {komponentti = getKomponentti(0, true); id = getUusiID(); uusiKomponentti = true;}
       
       if (nimi.equalsIgnoreCase("keep")) {nimi = komponentti.getNimi();}
       
       if (nimi.length()>39) {nimi=nimi.substring(0, 38); if (!Tarkistaja.onkoAlfanumeerinen(nimi)) {nimi=null; nimi="";}}
       if (tyyppi.equalsIgnoreCase("keep")) {tyyppi = komponentti.getVarustetype();}
       if (weight.equalsIgnoreCase("keep")) {massa = komponentti.getMassa();} else {massa = Integer.parseInt(weight);}
       if (strtier.equalsIgnoreCase("keep")) {tier = komponentti.getVarustetier();} else {tier = Integer.parseInt(strtier);}
       if (strheat.equalsIgnoreCase("keep")) {heat = komponentti.getHeat();} else {heat = Integer.parseInt(strheat);}
       if (activity.equalsIgnoreCase("keep")) {activity = komponentti.getVarusteactivity();} 
         if (volume.equalsIgnoreCase("keep")) {volume = komponentti.getKokoluokka();}
         if (location.equalsIgnoreCase("keep")) {location = komponentti.getSijoituspaikka();}
         //if (komponentti.getKategoria() == null) {
         
//            String sql = "UPDATE komponentti SET kategoria = 'ASE', nimi = '"+nimi+"', massa = "+massa+", weapon_type = '"+tyyppi.toUpperCase()+
//                  "', heat = "+heat+", weapon_damage = "+damage+", weapon_maxrange = "+maxrange+", weapon_minrange = "+minrange+
//                  ", weapon_ammo = "+ammo+", kokoluokka = '"+volume.toUpperCase()+"', sijoituspaikka = '"+location+"'"+
//                  " WHERE komponentti_id = "+id;
         
         // TARKISTETAAN SYÖTTEIDEN LAILLISUUUS:
         if (activity.contentEquals("PASSIVE") || activity.contentEquals("ACTIVE")) {} else {activity="PASSIVE";}
         volume = Tarkistaja.tarkistaVolume(volume);
         location = Tarkistaja.tarkistaLocation(location);
         tyyppi = Tarkistaja.tarkistaVarusteTyyppi(tyyppi);
         if (massa<1 || massa>25) {massa=1;}
         if (heat<0 || heat>30) {heat=0;}
         if (tier<1 || tier>3) {tier=3;}
         
         
         if (nimi.length()==0) {
          Komponentti k = new Komponentti();  // tämä komponentti luodaan pelkästään lyhenteen "laskemiseksi"
                        k.setID(id);
                    k.setNimi(nimi);
                    k.setKategoria("VARUSTE");
                    k.setMassa(massa);
                    k.setKokoluokka(volume.toUpperCase());
                    k.setHeat(heat);
                    k.setVarustetier(tier);
                    k.setVarustetype(tyyppi.toUpperCase());
                    k.setVarusteactivity(activity.toUpperCase());
                    
                    
                  nimi = k.getVarusteCommonName();}
         
          String sql = "UPDATE komponentti SET kategoria = 'VARUSTE', nimi = '"+nimi+"', massa = "+massa+", varuste_type = '"+tyyppi.toUpperCase()+
                  "', heat = "+heat+", varuste_tier = "+tier+", varuste_activity = '"+activity+"'"+
                  ", kokoluokka = '"+volume.toUpperCase()+"', sijoituspaikka = '"+location+"'"+
                  " WHERE komponentti_id = "+id;
          if (uusiKomponentti) {
              
if (nimi.equalsIgnoreCase("Unnamed Component") || nimi.length()==0 || nimi.equalsIgnoreCase("Unnamed Equipment") || nimi.equalsIgnoreCase("NEW-000A")) {
                  
                    Komponentti k = new Komponentti();  // tämä komponentti luodaan pelkästään lyhenteen "laskemiseksi"
                        k.setID(id);
                    k.setNimi(nimi);
                    k.setKategoria("VARUSTE");
                    k.setMassa(massa);
                    k.setKokoluokka(volume.toUpperCase());
                    k.setHeat(heat);
                    k.setVarustetier(tier);
                    k.setVarustetype(tyyppi.toUpperCase());
                    k.setVarusteactivity(activity.toUpperCase());
                    
                    
                  nimi = k.getVarusteCommonName();}//.getLyhenne();}
              
         sql = "INSERT INTO KOMPONENTTI (komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, varuste_type, varuste_tier, varuste_activity) "
                +"VALUES ("+id+", '"+nimi+"', "+massa+", '"+volume.toUpperCase()+"', "+heat+", 'VARUSTE', '"+location.toUpperCase()+"', '"+tyyppi.toUpperCase()+"', "+tier+", '"+activity.toUpperCase()+"')";
          }
//           sql = "INSERT INTO KOMPONENTTI (komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo) "+  
//                 "VALUES ("+id+", '"+nimi+"', "+massa+", '"+volume.toUpperCase()+"', "+heat+", 'ASE', '"+location.toUpperCase()+"', "+damage+", "+maxrange+", "+minrange+", '"+tyyppi.toUpperCase()+"', "+ammo+")";
//          }
          
       //String sql ="UPDATE komponentti SET weapon_type = '12345678901234567890'";// WHERE komponentti_id = 2";
        //String sql = "UPDATE komponentti SET weapon_type = '"+tyyppi.toUpperCase()+"' WHERE komponentti_id = "+id;
                
                //+ "REPLACE (weapon_type,  = '"+tyyppi+"' WHERE komponentti_id = 2";//+id;
        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             //kysely.setString(1, Integer.toString(vierailija.getID()));
             kysely.executeUpdate();
           //  vierailija.lisaaVierailukerta();
             
      
  
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
 
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
       if (komponentti == null) {komponentti = getKomponentti(0); id = getUusiID(); uusiKomponentti = true;}
       
       if (nimi.equalsIgnoreCase("keep")) {nimi = komponentti.getNimi();}
       
       if (nimi.length()>39) {nimi=nimi.substring(0, 38); if (!Tarkistaja.onkoAlfanumeerinen(nimi)) {nimi=null; nimi="";}}
       if (tyyppi.equalsIgnoreCase("keep")) {tyyppi = komponentti.getWeapontype();}
       if (weight.equalsIgnoreCase("keep")) {massa = komponentti.getMassa();} else {massa = Integer.parseInt(weight);}
       if (strdamage.equalsIgnoreCase("keep")) {damage = komponentti.getWeapondamage();} else {damage = Integer.parseInt(strdamage);}
       if (strheat.equalsIgnoreCase("keep")) {heat = komponentti.getHeat();} else {heat = Integer.parseInt(strheat);}
       if (strrange.equalsIgnoreCase("keep")) {maxrange = komponentti.getWeaponmaxrange(); minrange = komponentti.getWeaponminrange();} else {
           int newMax = 0;
           int newMin = 0;
           if (strrange.equalsIgnoreCase("cqb")) {newMax =1; newMin=1;}
           else if (strrange.equalsIgnoreCase("short")) {newMax =3; newMin=1;}
           //else if (strrange.equalsIgnoreCase("med")) {newMax =5; newMin=1;}
           else if (strrange.equalsIgnoreCase("long")) {newMax =9; newMin=2;}
           else if (strrange.equalsIgnoreCase("vlng")) {newMax =12; newMin=3;}
           else if (strrange.equalsIgnoreCase("arty")) {newMax =15; newMin=5;}
           else {newMax =5; newMin=1;}  // DEFAULTOIDAAN MEDIUMIIN
           maxrange=newMax;
           minrange=newMin;
       }
         if (strammo.equalsIgnoreCase("keep")) {ammo = komponentti.getWeaponammo();} else {
            ammo = 0; // DEFAULT 0 = INFINITE AMMO 
           
           
           if (strammo.equalsIgnoreCase("single")) {ammo=1;}
           else if (strammo.equalsIgnoreCase("limited")) {ammo=5;}
           else if (strammo.equalsIgnoreCase("standard")) {ammo=10;}
           else if (strammo.equalsIgnoreCase("extended")) {ammo=15;}
           else if (strammo.equalsIgnoreCase("plentiful")) {ammo=25;}
           else {ammo=0;}
        }
         if (volume.equalsIgnoreCase("keep")) {volume = komponentti.getKokoluokka();}
         if (location.equalsIgnoreCase("keep")) {location = komponentti.getSijoituspaikka();}
         //if (komponentti.getKategoria() == null) {
         
         // TARKISTETAAN SYÖTTEIDEN LAILLISUUUS:
         
         volume = Tarkistaja.tarkistaVolume(volume);
         location = Tarkistaja.tarkistaLocation(location);
         tyyppi = Tarkistaja.tarkistaAseTyyppi(tyyppi);
         if (massa<1 || massa>25) {massa=1;}
         if (minrange<1 || minrange>5) {minrange=1;}
         if (maxrange<1 || maxrange>15) {maxrange=5;}
         if (heat<0 || heat>30) {heat=1;}
         if (damage<1 || damage>100) {damage=1;}
         
         
          if (nimi.equalsIgnoreCase("Unnamed Component") || nimi.length()<1 || nimi.equalsIgnoreCase("Unnamed Equipment") || nimi.equalsIgnoreCase("NEW-000A")) {
                  
                    Komponentti k = new Komponentti();
             k.setID(id);
                    k.setNimi(nimi);
                    k.setMassa(massa);
                    k.setKokoluokka(volume.toUpperCase());
                    k.setWeapontype(tyyppi.toUpperCase());
                    k.setHeat(heat);
                    k.setKategoria("ASE");
                    k.setWeapondamage(damage);
                    k.setWeaponmaxrange(maxrange);
                    k.setWeaponminrange(minrange);
                    k.setWeaponammo(ammo);
                    
                  nimi = k.getAseCommonName();}
         
          String sql = "UPDATE komponentti SET kategoria = 'ASE', nimi = '"+nimi+"', massa = "+massa+", weapon_type = '"+tyyppi.toUpperCase()+
                  "', heat = "+heat+", weapon_damage = "+damage+", weapon_maxrange = "+maxrange+", weapon_minrange = "+minrange+
                  ", weapon_ammo = "+ammo+", kokoluokka = '"+volume.toUpperCase()+"', sijoituspaikka = '"+location+"'"+
                  " WHERE komponentti_id = "+id;
          if (uusiKomponentti) {
              
              if (nimi.equalsIgnoreCase("Unnamed Component") || nimi.length()<1 || nimi.equalsIgnoreCase("Unnamed Equipment") || nimi.equalsIgnoreCase("NEW-000A")) {
                  
                    Komponentti k = new Komponentti();  // tämä komponentti luodaan pelkästään lyhenteen "laskemiseksi"
                        k.setID(id);
                    k.setNimi(nimi);
                    k.setMassa(massa);
                    k.setKokoluokka(volume.toUpperCase());
                    k.setWeapontype(tyyppi.toUpperCase());
                    k.setHeat(heat);
                    k.setKategoria("ASE");
                    k.setWeapondamage(damage);
                    k.setWeaponmaxrange(maxrange);
                    k.setWeaponminrange(minrange);
                    k.setWeaponammo(ammo);
                    
                  nimi = k.getAseCommonName();}
              
         sql = "INSERT INTO KOMPONENTTI (komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo) "+  
                 "VALUES ("+id+", '"+nimi+"', "+massa+", '"+volume.toUpperCase()+"', "+heat+", 'ASE', '"+location.toUpperCase()+"', "+damage+", "+maxrange+", "+minrange+", '"+tyyppi.toUpperCase()+"', "+ammo+")";
          }
          
       //String sql ="UPDATE komponentti SET weapon_type = '12345678901234567890'";// WHERE komponentti_id = 2";
        //String sql = "UPDATE komponentti SET weapon_type = '"+tyyppi.toUpperCase()+"' WHERE komponentti_id = "+id;
                
                //+ "REPLACE (weapon_type,  = '"+tyyppi+"' WHERE komponentti_id = 2";//+id;
        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             //kysely.setString(1, Integer.toString(vierailija.getID()));
             kysely.executeUpdate();
           //  vierailija.lisaaVierailukerta();
             
      
  
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
 
   }
   
   public static void poistaKomponentti(int komponentti_id) throws NamingException, SQLException {
       String sql = "DELETE FROM komponentti WHERE komponentti_id = "+ komponentti_id;
         Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             //kysely.executeQuery();
             //ResultSet tulokset = kysely.executeQuery();
             kysely.executeUpdate();
      //try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
   }
   
   public String getAseCommonName() {
         String commonName ="";
       boolean hightech = false;
       String etuliite = "";
       if (getWeapontype().equalsIgnoreCase("MISSILE")) {
       commonName = "Missile";
       if (this.weaponAmmo==1 && this.weaponDamage>=10) {commonName="Stand-Off Missile";}
       
           if (this.weaponMaxRange>=12) {etuliite="Long Range";}
       else if (this.weaponMaxRange>=7) {etuliite="Medium Range";}
       else {etuliite="Short Range";}
       
       }
       
              if (getWeapontype().equalsIgnoreCase("Kinetic")) {
       commonName = "Auto Cannon";
       if (this.massa>=12 && this.weaponMaxRange >= 12) {commonName= "Gauss Rifle";}
       if (this.massa>=10 && this.weaponMaxRange >= 12 && this.weaponAmmo==0) {commonName= "Rail Gun";}
              
       
          //if (this.weaponDamage<=5) {etuliite="";}
       
       
       }
       
          if (getWeapontype().equalsIgnoreCase("AUTO")) {
       commonName = "Machine Gun";
       if (this.massa>=10) {commonName= "Chain Gun";}
       if (this.weaponAmmo==1) {commonName= "Bullet Storm";}
       
       if (this.weaponAmmo==0) {commonName="Pulse Laser";}
       if (this.weaponAmmo==0 && this.massa>=12) {commonName= "Laser Array";}
       
          if (this.weaponDamage>=8) {etuliite="Heavy";}
       else if (this.weaponDamage>=5) {etuliite="Medium";}
       else {etuliite="Light";}
       
       }
          
          
       if (getWeapontype().equalsIgnoreCase("MELEE")) {
           commonName = "Hatchet";
       
       if (this.massa>=8) {etuliite="Heavy";}
       else if (this.massa>=5) {etuliite="Medium";}
       else {etuliite="Light";}
           
           
       }
       
       if (getWeapontype().equalsIgnoreCase("ENERGY")) {
           commonName = "Laser";
       
            if (this.massa>=10 && this.weaponDamage>=10) {
                     commonName="Particle Projection Cannon"; 
                    if (this.massa>=15) {etuliite="Heavy";} else {etuliite="";}
           } else {
           
       if (this.weaponDamage>=8) {etuliite="Large";}
       else if (this.weaponDamage>=5) {etuliite="Medium";}
       else {etuliite="Small";}
            }
          
       }
       
       if (etuliite.length()>0) {
       commonName=etuliite+" "+commonName;}
       
       
       return commonName+" "+this.weaponDamage;
       
   }
   
   public String getVarusteCommonName() {
       String commonName ="";
       boolean hightech = false;
       
       String tierIndikaattori = "III";
       if (this.varusteTier==2) {tierIndikaattori = "II";}
       if (this.varusteTier==1) {tierIndikaattori = "I";}
              
     
       
         if (this.varusteType.contains("HEAT SINK")) {commonName="Heat Sink";}
           if (this.varusteType.contains("JUMP JET")) {commonName="Jump Jet";}
           if (this.varusteType.contains("ANTI MISSILE SYSTEM")) {commonName="Anti-Missile System"; hightech=true;}
           if (this.varusteType.contains("ARMOR PLATING")) {commonName="Armor Plating";}
           if (this.varusteType.contains("GYROSCOPE")) {commonName="Gyroscope"; hightech=true;}
           if (this.varusteType.contains("COCKPIT")) {commonName="Cockpit"; hightech=true;}
           if (this.varusteType.contains("TARGETTING COMPUTER")) {commonName="Targetting Computer"; hightech=true;}
           if (this.varusteType.contains("ACTIVE CAMO")) {commonName="Active Camo"; hightech=true;}
           if (this.varusteType.contains("SENSORS")) {commonName="Sensor Package"; hightech=true;}
           if (this.varusteType.contains("ACTUATORS")) {commonName="Actuators";}
       
           String etuliite = "Light"; if (hightech) {etuliite="Minor"; if (this.varusteTier<3) {etuliite="Miniaturized";}}
       if (this.varusteType.contains("ACTUATORS")) {
           if (this.kokoluokka.equalsIgnoreCase("SMALL")) {etuliite="Light";}
           if (this.kokoluokka.equalsIgnoreCase("MEDIUM")) {etuliite="Medium";}
           if (this.kokoluokka.equalsIgnoreCase("HEAVY")) {etuliite="Heavy";}
           if (this.kokoluokka.equalsIgnoreCase("XL")) {etuliite="XL";}
       } else {
           if (this.massa==2) {etuliite="Medium"; if (hightech) {etuliite="Standard"; if (this.varusteTier<3) {etuliite="Hardened";}}}
       if (this.massa>=3) {etuliite="Heavy"; if (hightech) {etuliite="Major"; if (this.varusteTier<3) {etuliite="Military-Grade";}}}
       }
           if (commonName.length()<1) {return getLyhenne();}
           
           return etuliite+" "+commonName+" "+tierIndikaattori;
           
   }
   
   public String getLyhenne() {
       String lyhenne = "";
       if (this.kategoria == null) {
           return "NEW-000A";
       }
       //Komponentti komponentti = getKomponentti(komponentti_id);
       if (this.kategoria.equalsIgnoreCase("ASE")) {
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
           else if (getHeat()==0) {lyhenne+="C";}
           else if (getWeapondamage()>=12) {lyhenne+="X";}
           else if (getWeapondamage()<5) {lyhenne+="P";}
           else if (getWeaponmaxrange()>=12) {lyhenne+="E";}
           else {lyhenne+="A";}
       }
       lyhenne=lyhenne+this.komponentti_id;
       } else {
           if (this.varusteType.contains("HEAT SINK")) {lyhenne="HS";}
           if (this.varusteType.contains("JUMP JET")) {lyhenne="JET";}
           if (this.varusteType.contains("ANTI MISSILE SYSTEM")) {lyhenne="AMS";}
           if (this.varusteType.contains("ARMOR PLATING")) {lyhenne="AP";}
           if (this.varusteType.contains("GYROSCOPE")) {lyhenne="GYRO";}
           if (this.varusteType.contains("COCKPIT")) {lyhenne="PIT";}
           if (this.varusteType.contains("TARGETTING COMPUTER")) {lyhenne="TAC";}
           if (this.varusteType.contains("ACTIVE CAMO")) {lyhenne="A.CAM";}
           if (this.varusteType.contains("SENSORS")) {lyhenne="SE";}
           if (this.varusteType.contains("ACTUATORS")) {lyhenne="ACTR";}
           if (lyhenne.length()<1) {lyhenne += this.varusteType.charAt(0);}
           
           int mallinumero = (this.varusteType.length()*(100/(this.massa))*(this.heat+1));
           
           if (this.varusteTier==1) {lyhenne = lyhenne+"-"+mallinumero;}
           if (this.varusteTier==2) {lyhenne = lyhenne+"-"+(mallinumero/3);}
           if (this.varusteTier==3) {lyhenne = lyhenne+"-"+(mallinumero/7);}
        
             if (getMassa()>=15) {lyhenne=lyhenne+"E";}
       else if (getMassa()>=8 || getKokoluokka().contains("LARGE") || getKokoluokka().contains("XL")) {lyhenne=lyhenne+"D";}
       else if (getMassa()>=5 || getKokoluokka().contains("MEDIUM")) {lyhenne=lyhenne+"C";}
       else if (this.varusteActivity.equalsIgnoreCase("ACTIVE")) {lyhenne=lyhenne+"B";}
       else {lyhenne=lyhenne+"A";}
       
             
             if (this.varusteTier==1) {lyhenne = lyhenne+this.komponentti_id+"-SR";}
           if (this.varusteTier==2) {lyhenne = lyhenne+this.komponentti_id+"-R";}
           if (this.varusteTier==3) {lyhenne = lyhenne+this.komponentti_id;}
             
       //lyhenne=lyhenne+this.komponentti_id;    
       }
       
       
       
       return lyhenne;
   }
    
   public static Komponentti getKomponentti(int komponentti_id) throws NamingException, SQLException {
       return getKomponentti(komponentti_id, false);
   }
   
   /**
    * Hakee tietokannasta Komponentin. Jos haetaan id-numeroa 0, metodi palauttaa pohjan uudelle komponentille. id=0:lla Boolean equipment=false tuottaa aseen, equipment=true varusteen. Jos id-numero on muu kuin nolla, boolean-valinta sivuutetaan.
    * @param komponentti_id Komponentin id-numero.
    * @param equipment True=varuste, False=ase.
    * @return palauttaa tietokannasta löytyneen komponentin
    * @throws NamingException
    * @throws SQLException 
    */
    public static Komponentti getKomponentti(int komponentti_id, boolean equipment) throws NamingException, SQLException {
  Komponentti k = null;
  
  if (komponentti_id>0) {
  
        String sql = "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo, varuste_type, varuste_tier, varuste_activity FROM komponentti WHERE komponentti_id="+komponentti_id;
//  String sql = "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo FROM komponentti WHERE komponentti_id="+komponentti_id;
 // if (equipment) {
    //  sql = "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, varuste_type, varuste_tier, varuste_activity FROM komponentti WHERE komponentti_id="+komponentti_id;
  //}
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
    if (k.getKategoria().equalsIgnoreCase("ASE")) {
    k.setWeapondamage(tulokset.getInt("weapon_damage"));
    k.setWeaponmaxrange(tulokset.getInt("weapon_maxrange"));
    k.setWeaponminrange(tulokset.getInt("weapon_minrange"));
    k.setWeapontype(tulokset.getString("weapon_type"));
    k.setWeaponammo(tulokset.getInt("weapon_ammo"));
    } else {
         k.setVarustetype(tulokset.getString("varuste_type"));
         k.setVarustetier(tulokset.getInt("varuste_tier"));
         k.setVarusteactivity(tulokset.getString("varuste_activity"));
    }
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
    if (equipment) {
        k.setNimi("Unnamed Equipment");
        k.setSijoituspaikka("ALL");
        k.setVarustetype("HEAT SINK");
        k.setVarustetier(3);
        k.setVarusteactivity("PASSIVE");
    } else {
    k.setSijoituspaikka("ARMS_TORSO");
    k.setWeapondamage(1);
    k.setWeaponmaxrange(1);
    k.setWeaponminrange(1);
    k.setWeapontype("ENERGY");
    k.setWeaponammo(0);}
  }
  //Jos kysely ei tuottanut tuloksia käyttäjä on nyt vielä null.

  //Suljetaan kaikki resurssit:

  
  //Käyttäjä palautetaan vasta täällä, kun resurssit on suljettu onnistuneesti.
  return k;
}
    
    public static List<Komponentti> getKaikkiKomponentit() throws NamingException, SQLException {
        ArrayList<Komponentti> komponentit = new ArrayList<Komponentti>();
        komponentit.addAll(getVarusteKomponentit());
        komponentit.addAll(getAseKomponentit());
        return komponentit;
    }
    
    
       public static List<Komponentti> getVarusteKomponentit() throws NamingException, SQLException {
           
  String sql = "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, varuste_type, varuste_tier, varuste_activity FROM komponentti WHERE kategoria='VARUSTE'"
  //        "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo FROM komponentti WHERE kategoria='ASE'"
          +" ORDER BY varuste_type ASC, varuste_tier ASC, massa ASC";
  
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
         k.setVarustetype(tulokset.getString("varuste_type"));
         k.setVarustetier(tulokset.getInt("varuste_tier"));
         k.setVarusteactivity(tulokset.getString("varuste_activity"));
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
    
       public static List<Komponentti> getAseKomponentit() throws NamingException, SQLException {
           return getAseKomponentit("ALL");
       }
    
    public static List<Komponentti> getAseKomponentit(String weapon_type) throws NamingException, SQLException {
        String sqltype = "";
        if (weapon_type.equalsIgnoreCase("ALL")) {} else {
            sqltype = " AND weapon_type='"+weapon_type+"' ";
        }
        
  String sql = "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo FROM komponentti WHERE kategoria='ASE'"+sqltype
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
        public void setVarustetier(int tier) {
            this.varusteTier=tier;
        }
        public int getVarustetier() {
            return this.varusteTier;
        }
        
        public void setVarustetype(String tyyppi) {
            this.varusteType=tyyppi;
        }
        public String getVarustetype() {
            return this.varusteType;
        }
        
        public void setVarusteactivity(String activity) {
            this.varusteActivity=activity;
        }
        
        public String getVarusteactivity() {
            return this.varusteActivity;
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
     
     public String getKokoluokkalyhyt() {
         return Tarkistaja.lyhentaja(this.kokoluokka);
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
 
          public int getKomponentisto_id() {
              return this.komponentisto_id;
          }
          public void setKomponentistoID(int id) {
              this.komponentisto_id=id;
          }
}

