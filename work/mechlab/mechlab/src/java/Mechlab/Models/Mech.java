
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
 * mech_id	INTEGER NOT NULL ,
	nimi	VARCHAR(40)	NOT NULL,
	collection_id INTEGER REFERENCES mechkokoelma(mechkokoelma_id) ON DELETE CASCADE,
	user_id INTEGER REFERENCES kayttaja(kayttaja_id) ON DELETE CASCADE,
	reactor_id INTEGER REFERENCES reaktori(reaktori_id) ON DELETE CASCADE,
	paino	INTEGER		NOT NULL,
	panssariarvo	INTEGER	,
	CHECK (paino > 19),
	CHECK (paino < 101),
	PRIMARY KEY (mech_id)
 */
public class Mech {

    private int mech_id;
    private String nimi;
    private int collection_id;
    private int user_id;
    private int reactor_id;
    private int paino;
    private int panssariarvo;
    
    private Reaktori reaktori;
    
    private ArrayList<Komponentti> head;
    private ArrayList<Komponentti> centertorso;
    private ArrayList<Komponentti> lefttorso;
    private ArrayList<Komponentti> righttorso;
    private ArrayList<Komponentti> leftarm;
    private ArrayList<Komponentti> rightarm;
    private ArrayList<Komponentti> leftleg;
    private ArrayList<Komponentti> rightleg;
    


    public Mech() {
        
    }

    public Mech(int mech_id, String nimi, int collection_id, int user_id, int reactor_id, int paino) {
        
    }
    /**
     * Luo uuden mechin ja palauttaa uuden mechin uuden mech_id:n
     * @param user_id Mechin omistajan user_id
     * @return palauttaa uuden mech_id:n
     * @throws NamingException
     * @throws SQLException 
     */
    public static int createNewMech(int user_id) throws NamingException, SQLException {
       int mech_id = getUusiID();
       
    String sql = "INSERT INTO MECH (mech_id, nimi, collection_id, user_id, paino) VALUES" // jätetään reactor_id pois aluksi
   //+"("+mech_id+", 'New Unnamed Mech', "+user_id+", "+user_id+", 20)";//
    +"("+mech_id+", 'New Unnamed Mech', (SELECT mechkokoelma_id from MECHKOKOELMA where mechkokoelma_id="+user_id+"), (SELECT kayttaja_id from kayttaja where kayttaja_id="+user_id+"), 20)";
    //+" (SELECT mechkokoelma_id from MECHKOKOELMA where mechkokoelma_id="+user_id+"), (SELECT kayttaja_id from kayttaja where kayttaja_id="+user_id+"), (SELECT reaktori_id from REAKTORI where reaktori_id=2), 30)";
 Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
    
             kysely.executeQuery();
               try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
  return mech_id;
    }
    
 public static Mech getMech(int mech_id) throws NamingException, SQLException {
            Mech k = null;
  
         if (mech_id>0) {
  
        String sql = "SELECT mech_id, nimi, collection_id, user_id, reactor_id, paino FROM mech WHERE mech_id="+mech_id;

        Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
    
             ResultSet tulokset = kysely.executeQuery();
  
  
  

 
  if (tulokset.next()) { 

    k = new Mech();
    k.setID(tulokset.getInt("mech_id"));
    k.setNimi(tulokset.getString("nimi"));
    k.setCollectionID(tulokset.getInt("collection_id"));
    k.setUserID(tulokset.getInt("user_id"));
    k.setReactorID(tulokset.getInt("reactor_id"));
    k.setPaino(tulokset.getInt("paino"));


  }
     try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  }
  else { //if (komponentti_id==0) {
    k = new Mech();
    k.setID(getUusiID());
    k.setNimi("Unnamed Mech");
    //k.setCollectionID(-1);  // annetaan näiden palauttaa null?
    //k.setUserID(-1);
    //k.setReactorID(-1);
    k.setPaino(20);
    
  }
  //Jos kysely ei tuottanut tuloksia käyttäjä on nyt vielä null.

  //Suljetaan kaikki resurssit:

  
  //Käyttäjä palautetaan vasta täällä, kun resurssit on suljettu onnistuneesti.
  return k;
}
    
 
 public static List<Mech> getMechit() throws NamingException, SQLException {
           
  String sql = "SELECT mech_id, nimi, collection_id, user_id, reactor_id, paino FROM mech" // WHERE kategoria='VARUSTE'"
  //        "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo FROM komponentti WHERE kategoria='ASE'"
          +" ORDER BY paino ASC";
  
  //Tietokanta yhteys0 = new Tietokanta();
  Connection yhteys = Tietokanta.getYhteys();
  PreparedStatement kysely = yhteys.prepareStatement(sql);
  ResultSet tulokset = kysely.executeQuery();

  ArrayList<Mech> mechit = new ArrayList<Mech>();
  while (tulokset.next()) {
    //Luodaan tuloksia vastaava olio ja palautetaan olio:
    Mech k = new Mech();
 k.setID(tulokset.getInt("mech_id"));
    k.setNimi(tulokset.getString("nimi"));
    k.setCollectionID(tulokset.getInt("collection_id"));
    k.setUserID(tulokset.getInt("user_id"));
    k.setReactorID(tulokset.getInt("reactor_id"));
    k.setPaino(tulokset.getInt("paino"));

    
   
    mechit.add(k);
    
  }   

  try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}



  
  return mechit;
}
 
    
   public void setID(int newID) {
        this.mech_id=newID;
    }
   
     public int getMech_id() {
         
        return this.mech_id;
    }
     
        public void setUserID(int newID) {
        this.user_id=newID;
    }
   
     public int getUser_id() {
        return this.user_id;
    }
 
     public void setCollectionID(int newID) {
        this.collection_id=newID;
    }
   
     public int getCollection_id() {
        return this.collection_id;
    }
     
          public void setReactorID(int newID) {
        this.reactor_id=newID;
    }
   
     public int getReactor_id() {
        return this.reactor_id;
    }
     
    public void setNimi(String nimi) {
        this.nimi=nimi;
    }
    public String getNimi() {
        return this.nimi;
    }
    
    
     public void setPaino (int massa) {
        this.paino=massa;
    } 
    public int getPaino () {
        return this.paino;
    }
    
    public int getTotalweapondamage() throws NamingException, SQLException {
        return MechData.getComponentValues(this, "totalweapondamage");
    }
    public int getTotalweaponheat() throws NamingException, SQLException {
        return MechData.getComponentValues(this, "totalweaponheat");
    }
    public int getMaxweaponrange() throws NamingException, SQLException {
        return MechData.getComponentValues(this, "maxweaponrange");
    }
    
     public int getHeatsinks() throws NamingException, SQLException {
        int heatsinks = 0;
        Reaktori omareaktori = getReaktori();
        if (omareaktori != null) {
            heatsinks += omareaktori.getCooling();
        }
         
        for (Komponentti komponentti : getMechinKomponentit("ALL")) {
            if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                if (komponentti.getVarustetype().contentEquals("HEAT SINK")) {
                    heatsinks += (4-komponentti.getVarustetier());
                }
            }
            
        }
        return heatsinks;
    }
    
     public int getInnerStructure() throws NamingException, SQLException {
         int innerStructure = 0;
         for (Komponentti komponentti : getMechinKomponentit("ALL")) {
             int lisays = 1;
             if (komponentti.getKokoluokka().contentEquals("LARGE") || komponentti.getKokoluokka().contentEquals("XL")) {
                 lisays =2;
             }
             if (komponentti.getMassa()>=8) {
                 lisays +=(getMassa()/8);
             }
             if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                if (komponentti.getVarustetype().contentEquals("ARMOR PLATING")) {
                    lisays = 0;   // ARMOR PLATING ei kasvata inner-structurea
                }
             }
             innerStructure+=lisays;
         }
         return innerStructure;
     }
     
     public int getArmorrating() throws NamingException, SQLException {
         ArrayList<String>lista = getArmorratingList(true);
         
         return Integer.parseInt(lista.get(0));
     }
     
     /**
      * Jos parametriksi annetaan false, stringiin ei liitetä armorrating-arvoa ensimmäiseksi riviksi. 
      * @param armorratingRivinKanssa 
      * @return
      * @throws NamingException
      * @throws SQLException 
      */
     public ArrayList<String> getArmorratingList(boolean armorratingRivinKanssa) throws NamingException, SQLException {
         return MechData.getArmorratingList(this, armorratingRivinKanssa);
     }
      
     
     public int getWalkingspeed() throws NamingException, SQLException {
         boolean olikoGyroscope = false;
         boolean olikoVasemmanJalanActuator = false;
         boolean olikoOikeanJalanActuator = false;
         
          for (Komponentti komponentti : getMechinKomponentit("ALL")) {
            if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                if (komponentti.getVarustetype().contentEquals("GYROSCOPE")) {
                    olikoGyroscope = true;
                }
                
            }
        }
        for (Komponentti komponentti : getMechinKomponentit("LEFT LEG")) {
            if (komponentti.getVarustetype().contentEquals("ACTUATORS")) {
                    if (Tarkistaja.tarkistaLegActuatorinRiittavyys(komponentti.getKokoluokka(), this.paino))
                    {olikoVasemmanJalanActuator=true;}
                }
        }
        for (Komponentti komponentti : getMechinKomponentit("RIGHT LEG")) {
            if (komponentti.getVarustetype().contentEquals("ACTUATORS")) {
                    if (Tarkistaja.tarkistaLegActuatorinRiittavyys(komponentti.getKokoluokka(), this.paino))
                    {olikoOikeanJalanActuator=true;}
                }
        }
         
          
         
         int nopeus = 0;
         Reaktori omareaktori = getReaktori();
         if (omareaktori != null) {
                nopeus = (int) ((omareaktori.getTeho()/this.paino)*9.5);
                int puolitus = nopeus/2;
                if (!olikoVasemmanJalanActuator) {nopeus -= puolitus;}
                if (!olikoOikeanJalanActuator) {nopeus -= puolitus;}
                if (!olikoGyroscope) {nopeus = (nopeus / 9);}
         }
         if (nopeus<0) {nopeus=0;}
         return nopeus;
     }
     
     public int getRunningspeed() throws SQLException, NamingException {
         int kerroin = 165-(this.paino/2);
         boolean tier1 = false;
         boolean tier2 = false;
         boolean tier3 = false;
       
         for (Komponentti komponentti : getMechinKomponentit("LEFT LEG")) {
            if (komponentti.getVarustetype().contentEquals("ACTUATORS")) {
                    if (komponentti.getVarustetier()==3) {tier3=true;}
                    if (komponentti.getVarustetier()==2) {tier2=true;}
                    if (komponentti.getVarustetier()==1) {tier1=true;}
                }
        }
        
        for (Komponentti komponentti : getMechinKomponentit("RIGHT LEG")) {
            if (komponentti.getVarustetype().contentEquals("ACTUATORS")) {
                    if (komponentti.getVarustetier()==3) {tier3=true;}
                    if (komponentti.getVarustetier()==2) {tier2=true;}
                    if (komponentti.getVarustetier()==1) {tier1=true;}
                }
        }
        
        if (tier3) {}
        else if (tier2) {kerroin +=5;}
        else if (tier1) {kerroin +=10;}
        else {kerroin = 0;}
        
        return (int) ((getWalkingspeed()*kerroin)/100);
         //return (int) (getWalkingspeed()*1.4);
     }
     
     public int getArmorvalue(String lokaatio, boolean pyydetaanArmorValue) throws NamingException, SQLException {
         return (MechData.getArmorvalue(this, lokaatio, pyydetaanArmorValue));
     }
     
     public Kayttaja getOwner() throws NamingException, SQLException {
         Kayttaja omistaja = Kayttaja.getKayttaja(user_id);
         return omistaja;
     }
     
     public String getOwnername() throws NamingException, SQLException {
         Kayttaja omistaja = getOwner();
         return omistaja.getNimi();
     }
     
     public int getJumprating() throws NamingException, SQLException {
         int hyppymatka = 0;
         int effiency = 100;
         if (this.paino<40) {effiency+=10;}
         else if (this.paino<60) {}
         else if (this.paino>=60) {effiency-=10;}
         else if (this.paino>=80) {effiency-=20;}
         
          for (Komponentti komponentti : getMechinKomponentit("ALL")) {
            if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                if (komponentti.getVarustetype().contentEquals("JUMP JET")) {
                    hyppymatka += (((120-this.paino)/komponentti.getVarustetier()));
                    if (Tarkistaja.tarkistaKokoluokanRiittavyys(komponentti.getKokoluokka(), this.paino)) {
                        effiency -=6;
                    }
                    else {effiency -=9;}
                    if (effiency<1) {effiency=1;}
                }
            }
        }
        hyppymatka = (hyppymatka * effiency)/100;
        return hyppymatka;
     }
     
     
     
     
      public int getWeaponrating() throws NamingException, SQLException {
        int rating = 0;
        int sensorLevel = 5;
        int targettingComputerLevel = 5;
        int damageTotal = 0;
        int heatTotal = 0;
         
        for (Komponentti komponentti : getMechinKomponentit("ALL")) {
            if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                    if (komponentti.getVarustetype().contentEquals("SENSORS")) {
                        if (komponentti.getVarustetier()<sensorLevel) {
                            sensorLevel=komponentti.getVarustetier();
                        }
                    }
            }
            if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                if (komponentti.getVarustetype().contentEquals("TARGETTING COMPUTER")) {
                    if (komponentti.getVarustetier()<targettingComputerLevel) {
                        targettingComputerLevel=komponentti.getVarustetier();
                    }
                }
            }
            
            if (komponentti.getKategoria().contentEquals("ASE")) {
                heatTotal += komponentti.getHeat();
                damageTotal += komponentti.getWeapondamage();
                
                int lisays = (komponentti.getWeapondamage()/2);
                if (komponentti.getWeapontype().contentEquals("MELEE")) {
                    lisays = lisays/2;
                }
                if (komponentti.getWeapontype().contentEquals("MISSILE")) {
                    lisays += (komponentti.getWeaponmaxrange()/2);
                }
                if (komponentti.getWeaponmaxrange()>=15) {
                    lisays +=3;
                }
                else if (komponentti.getWeaponmaxrange()>=12) {
                    lisays +=2;
                }
                else if (komponentti.getWeaponmaxrange()>=9) {
                    lisays +=1;
                }
                else if (komponentti.getWeaponmaxrange()<5) {
                    lisays -=1;
                }
                rating+= lisays;
            }
        }
        
          boolean olikoVasemmanKadenActuatori = false;
          int vasemmanKadenDamaget = 0;
        for (Komponentti komponentti : getMechinKomponentit("LEFT ARM")) {
            if (komponentti.getKategoria().contentEquals("ASE") && !komponentti.getWeapontype().contentEquals("MISSILE")) {
                 vasemmanKadenDamaget += (komponentti.getWeapondamage()/2);
            }
            if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                if (komponentti.getVarustetype().contentEquals("ACTUATORS")) {
                    olikoVasemmanKadenActuatori=true;
                }
            }
        }
        if (olikoVasemmanKadenActuatori) {rating+=vasemmanKadenDamaget;}
        
          boolean olikoOikeanKadenActuatori = false;
          int oikeanKadenDamaget = 0;
        for (Komponentti komponentti : getMechinKomponentit("RIGHT ARM")) {
            if (komponentti.getKategoria().contentEquals("ASE") && !komponentti.getWeapontype().contentEquals("MISSILE")) {
                 oikeanKadenDamaget += (komponentti.getWeapondamage()/2);
            }
            if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                if (komponentti.getVarustetype().contentEquals("ACTUATORS")) {
                    olikoOikeanKadenActuatori=true;
                }
            }
        }
        if (olikoOikeanKadenActuatori) {rating+=oikeanKadenDamaget;}
        
        if (sensorLevel == 5) {rating = rating/5;}
        else {rating = ((rating * (18+(3-sensorLevel)))/18);}
        
        if (targettingComputerLevel<5) //{rating = rating * 2;}
        {rating = ((rating * (12+(3-sensorLevel)))/12);} 
        
        if ((heatTotal) > (getHeatsinks()+10)) {rating = rating - (rating/3);}
        else if ((heatTotal) > (getHeatsinks()+5)) {rating = rating - (rating/4);}
        else if ((heatTotal) > getHeatsinks()) {rating = rating - (rating/6);}
        else if (heatTotal < getHeatsinks()) {int heatbonus = (getHeatsinks()-(heatTotal)); if (heatbonus<=5) {rating+=heatbonus;} else {rating+=5;}}
        
        return rating;
    }
      public void poistaKomponentti(int komponentti_id, String kohde) throws NamingException, SQLException {
          
      }
      public void poistaKomponentti(int komponentisto_id) throws NamingException, SQLException {
          //if (Tarkistaja.tarkistaKohteenLaillisuus(kohde)) {
//       int komponentisto_id = 0;
//       ArrayList<Komponentisto> kaikki = Komponentisto.getKomponentisto();
//       for (Komponentisto yksilo : kaikki) {
//           if (yksilo.getBattlemech_id()==this.mech_id && yksilo.getComponent_id()==komponentti_id && kohde.equalsIgnoreCase(kohde)) {
//               komponentisto_id=yksilo.getKomponentisto_id();
//           }
//       }
       
       if (komponentisto_id>0) {
       String sql = "DELETE FROM komponentisto WHERE komponentisto_id = "+komponentisto_id;//+" AND sijainti = '"+kohde+"' AND component_id = "+komponentti_id;//" AND sijainti = '"+kohde+"'";
                
          
       
  //Tietokanta yhteys0 = new Tietokanta();
  Connection yhteys = Tietokanta.getYhteys();
  PreparedStatement kysely = yhteys.prepareStatement(sql);
   //ResultSet tulokset = 
  kysely.executeUpdate();
  //    try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
                }
         // }
   }
      
     
   public void asennaKomponentti(int komponentti_id, String kohde) throws NamingException, SQLException {
       if (Tarkistaja.tarkistaKohteenLaillisuus(kohde)) {
       int komponentisto_id = Komponentisto.getUusiID();
       
       
       String sql = "INSERT INTO komponentisto (komponentisto_id, battlemech_id, component_id, sijainti) "
                +"VALUES ("+komponentisto_id+", "+this.mech_id+", "+komponentti_id+", '"+kohde+"')";
          
       
  //Tietokanta yhteys0 = new Tietokanta();
  Connection yhteys = Tietokanta.getYhteys();
  PreparedStatement kysely = yhteys.prepareStatement(sql);
 kysely.executeUpdate();
      
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
       }
   }
   
   public boolean getVaroitusvapaa() throws NamingException, SQLException {
       if (getVaroitukset(1).size()>0) {return false;}
       return true;
   }
   
   public boolean getHuomautusvapaa() throws NamingException, SQLException {
       if (getVaroitukset(2).size()>0) {return false;}
       return true;
   }
   
   public static boolean voikoKayttajaLuodaUudenMechin(int kayttaja_id) throws NamingException, SQLException {
       List<Mech> mechit = getMechit();
       for (Mech mech : mechit) {
           if (mech.getUser_id()==kayttaja_id) {
//                if (mech.getVaroitukset(1).size()>0) {
//                    return false;
//                        }
                if (mech.getNettopaino()==0) {
                    return false;
                        }
                }
       }
       return true;
   }
      
   public ArrayList<String>getVaroitukset(int varoitustaso) throws NamingException, SQLException {
       return MechData.getVaroitukset(this, varoitustaso);
   }
    
    public int getNettopaino() throws NamingException, SQLException {
        int nettopaino = 0;
        for (Komponentti komponentti : getMechinKomponentit("ALL")) {
            nettopaino += komponentti.getMassa();
        }
        return nettopaino;
    }
    
    public int getItemsallowedextension() {
        if (this.paino>=100) {return 9;}
        if (this.paino>=80) {return 8;}
        //else if (this.paino>=70) {return 7;}
        else if (this.paino>=60) {return 6;}
        //else if (this.paino>=50) {return 5;}
        else if (this.paino>=40) {return 4;}
        else {return 3;}
    }
    
    public int getItemsallowedtorso() {
        if (this.paino>=100) {return 13;}
        else if (this.paino>=80) {return 12;}
        else if (this.paino>=60) {return 10;}
        else if (this.paino>=40) {return 8;}
        else {return 6;}
    }
    
    public int getItemshead() throws NamingException, SQLException {
        return itemLaskuri(getHead());
    }
    public int getItemscentertorso() throws NamingException, SQLException {
        return itemLaskuri(getCenterTorso());
    }
    public int getItemsleftarm() throws NamingException, SQLException {
        return itemLaskuri(getLeftArm());
    }
     public int getItemsleftleg() throws NamingException, SQLException {
        return itemLaskuri(getLeftLeg());
    }
      public int getItemslefttorso() throws NamingException, SQLException {
        return itemLaskuri(getLeftTorso());
    }
      public int getItemsrightarm() throws NamingException, SQLException {
        return itemLaskuri(getRightArm());
    }
     public int getItemsrightleg() throws NamingException, SQLException {
        return itemLaskuri(getRightLeg());
    }
      public int getItemsrighttorso() throws NamingException, SQLException {
        return itemLaskuri(getRightTorso());
    }
    
    public int itemLaskuri(ArrayList<Komponentti> komponentit) {
        
        int laskuri = 0;
        for (Komponentti komponentti : komponentit) {
            if (komponentti.getKokoluokka().equalsIgnoreCase("XL")) {laskuri+=4;}
            if (komponentti.getKokoluokka().equalsIgnoreCase("LARGE")) {laskuri+=3;}
            if (komponentti.getKokoluokka().equalsIgnoreCase("MEDIUM")) {laskuri+=2;}
            if (komponentti.getKokoluokka().equalsIgnoreCase("SMALL")) {laskuri+=1;}
//            if (komponentti.getKategoria().equalsIgnoreCase("ASE")) {
//                if (komponentti.getWeaponammo()>0) {laskuri+=0;}
//            }
        }
        return laskuri;
    }
    
    public ArrayList<Komponentti> getHead() throws NamingException, SQLException {
        return getMechinKomponentit("HEAD");
    }
     public ArrayList<Komponentti> getCenterTorso() throws NamingException, SQLException {
        return getMechinKomponentit("CENTER TORSO");
    }
      public ArrayList<Komponentti> getLeftTorso() throws NamingException, SQLException {
        return getMechinKomponentit("LEFT TORSO");
    }
       public ArrayList<Komponentti> getRightTorso() throws NamingException, SQLException {
        return getMechinKomponentit("RIGHT TORSO");
    }
        public ArrayList<Komponentti> getLeftArm() throws NamingException, SQLException {
        return getMechinKomponentit("LEFT ARM");
    }
         public ArrayList<Komponentti> getRightArm() throws NamingException, SQLException {
        return getMechinKomponentit("RIGHT ARM");
    }
         public ArrayList<Komponentti> getLeftLeg() throws NamingException, SQLException {
        return getMechinKomponentit("LEFT LEG");
    }
         public ArrayList<Komponentti> getRightLeg() throws NamingException, SQLException {
        return getMechinKomponentit("RIGHT LEG");
    }
    
//    public ArrayList<Komponentti> getMechinKomponentitSijainneilla(String sijainti) throws NamingException, SQLException {
//        
//    }
         
    public ArrayList<Komponentti> getMechinKomponentit(String sijainti) throws NamingException, SQLException {
         
        String sqljatko = "";
        if (sijainti.equalsIgnoreCase("ALL") || sijainti.length()==0) {
            sqljatko ="";
        } else {sqljatko ="AND sijainti='"+sijainti+"'";}
        
        String sql = "SELECT komponentisto_id, battlemech_id, component_id, sijainti FROM komponentisto WHERE sijainti <> '' AND battlemech_id="+this.mech_id
                +sqljatko
  //        "SELECT komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo FROM komponentti WHERE kategoria='ASE'"
          +" ORDER BY sijainti ASC";
  
  //Tietokanta yhteys0 = new Tietokanta();
  Connection yhteys = Tietokanta.getYhteys();
  PreparedStatement kysely = yhteys.prepareStatement(sql);
  ResultSet tulokset = kysely.executeQuery();

  ArrayList<Komponentti> komponentit = new ArrayList<Komponentti>();
  while (tulokset.next()) {
    //Luodaan tuloksia vastaava olio ja palautetaan olio:
    Komponentti k = Komponentti.getKomponentti(tulokset.getInt("component_id"));
    //asennaKomponentti(k, tulokset.getString("sijainti"));
    k.setSijainti(tulokset.getString("sijainti"));
    k.setKomponentistoID(tulokset.getInt("komponentisto_id"));
    komponentit.add(k);
    
    
  }   

  try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}

  if (sijainti.equalsIgnoreCase("ALL") || sijainti.equalsIgnoreCase("CENTER TORSO")) {
  Reaktori haereaktori = getReaktori();
  if (haereaktori != null) {
  komponentit.add(haereaktori);
  }
  }
  return komponentit;
        
 }
    /**
     * Palauttaa "Light" (20-35t), "Medium" (40-55t), "Heavy" (60-75t) tai "Assault" (80-100t) Mechin painoluokan mukaan
     * @return luokitus
     */
    public String getMechclass() {
        if (this.paino>=80) {return "Assault";}
        else if (this.paino>=60) {return "Heavy";}
        else if (this.paino>=40) {return "Heavy";}
        else {return "Light";}
    }
        
 public void setMassa (int massa) {
        this.paino=massa;
    } 
    public int getMassa () {
        return this.paino;
    }
    
    public Reaktori getReaktori() throws NamingException, SQLException {
        Reaktori palautettava = Reaktori.getReaktori(this.reactor_id);
        if (palautettava.getReaktori_id()==0 || palautettava.getNimi().equalsIgnoreCase("Unnamed Reactor")) {return null;}
        return palautettava;
    }
 
    /**
     * Super-räyhäkkäästi koodattu metodi, joka etsii ensimmäisen vapaan id-numeron...
     * @return vpaaa id
     * @throws NamingException
     * @throws SQLException 
     */
    public static int getUusiID() throws NamingException, SQLException {
        List<Mech> vanhat = getMechit();
        int suurinID = 1;

        
        for (Mech mech : vanhat) {
            if (mech.getMech_id()>=suurinID) {
                suurinID=mech.getMech_id();
            }
        }

        return suurinID+1;
    }
    
     public void vaihdaReaktori(int reaktori_id) throws NamingException, SQLException {
         if (Reaktori.getReaktori(reaktori_id).getReaktori_id()>0) {
        
          
                        String sql = "UPDATE mech SET reactor_id = "+reaktori_id+" WHERE mech_id = "+this.mech_id;
                      Connection yhteys = Tietokanta.getYhteys();
                           PreparedStatement kysely = yhteys.prepareStatement(sql);
                           //kysely.setString(1, Integer.toString(vierailija.getID()));
                           kysely.executeUpdate();
                         //  vierailija.lisaaVierailukerta();


                
                try { kysely.close(); } catch (Exception e) {}
                try { yhteys.close(); } catch (Exception e) {}

         }
      }
    
      public void muutaPainoluokka(int uusipainoluokka) throws NamingException, SQLException {
        if (uusipainoluokka>=20 && uusipainoluokka<=100 && (uusipainoluokka % 5 == 0) ) {
          
                        String sql = "UPDATE mech SET paino = "+uusipainoluokka+" WHERE mech_id = "+this.mech_id;
                      Connection yhteys = Tietokanta.getYhteys();
                           PreparedStatement kysely = yhteys.prepareStatement(sql);
                           //kysely.setString(1, Integer.toString(vierailija.getID()));
                           kysely.executeUpdate();
                         //  vierailija.lisaaVierailukerta();


                
                try { kysely.close(); } catch (Exception e) {}
                try { yhteys.close(); } catch (Exception e) {}
  
  //return vierailija;
        }
      }
      
      
           public int getCost () throws NamingException, SQLException {
        //int hinta = 1;
        
                int hinta = this.paino*this.paino*100;
        for (Komponentti komponentti : getMechinKomponentit("")) {
            hinta+= komponentti.getCost();
        }
        return hinta;
    }
      
       public String getCoststring() throws NamingException, SQLException {
           double hinta = getCost();
           String palaute ="";
           String takaliite ="";
           if (hinta>=500000) {
               hinta=hinta/1000000;
               takaliite = "M";
               palaute = ""+hinta;
               if (hinta>=0.955) {
               palaute = palaute.substring(0, 3);}
               else {palaute = palaute.substring(0, 4);}
           }
           else if (hinta>=10000) {
               hinta=hinta/1000;
               takaliite = "";
               palaute = ""+(int)hinta+" 000";
               //palaute = (String) palaute.subSequence(0, 1);
           }
           else 
           {
           palaute = ""+(int)hinta;
           //palaute.subSequence(0, 1);
            }
           return palaute+takaliite;
       }
       
       public static void vaihdaNimi(String mech_id_string, String uusinimi) throws NamingException, SQLException {
           
           int mech_id = Integer.parseInt(mech_id_string);
           if (uusinimi.length()>39) {uusinimi=uusinimi.substring(0, 38);}
         
           if (uusinimi.length()>=2 && Mech.getMech(mech_id) != null && Tarkistaja.onkoAlfanumeerinen(uusinimi)) {
                         String sql = "UPDATE mech SET nimi = '"+uusinimi+"' WHERE mech_id = "+mech_id;
                         Connection yhteys = Tietokanta.getYhteys();
                         PreparedStatement kysely = yhteys.prepareStatement(sql);
             
                         kysely.executeUpdate();
      
                          try { kysely.close(); } catch (Exception e) {}
                          try { yhteys.close(); } catch (Exception e) {}
           }
       }
       
       public static String poistaMech(int mech_id, int kayttaja_id, int oikeustaso) throws NamingException, SQLException {
           String sql = "";
           String palaute = "Nothing happened!";
           
           Mech poistettava = Mech.getMech(mech_id);
           if (poistettava != null) {
           if (poistettava.getUser_id()!=kayttaja_id && oikeustaso <1) {
               palaute = "You can only delete your own mechs!";
               } else if (poistettava.getUser_id()==kayttaja_id || oikeustaso >0) {
                    palaute = "Deleting "+poistettava.getNimi()+" (#"+poistettava.getMech_id()+")...";    
               }
           } else {palaute = "The mech you tried to delete was not found!";}
           
       if (oikeustaso<1) {
           sql = "DELETE FROM mech WHERE mech_id = "+ mech_id+ " AND user_id = "+kayttaja_id;}
       else {
           sql = "DELETE FROM mech WHERE mech_id = "+ mech_id;}
       
         Connection yhteys = Tietokanta.getYhteys();
             PreparedStatement kysely = yhteys.prepareStatement(sql);
             //kysely.executeQuery();
             //ResultSet tulokset = kysely.executeQuery();
             kysely.executeUpdate();
      //try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
   
            sql = "DELETE FROM komponentisto WHERE mech_id = "+ mech_id;
       
          yhteys = Tietokanta.getYhteys();
              kysely = yhteys.prepareStatement(sql);
             //kysely.executeQuery();
             //ResultSet tulokset = kysely.executeQuery();
             kysely.executeUpdate();
      //try { tulokset.close(); } catch (Exception e) {}
  try { kysely.close(); } catch (Exception e) {}
  try { yhteys.close(); } catch (Exception e) {}
  
    return palaute;
   }
}