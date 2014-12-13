
package Mechlab.Tietokanta;

/**
 * Erilaisia tarkistavia metodeita sisältävä apuluokka.
 * Tarkistaja on tärkeä puskuri, joka pyrkii estämään väärää muotoa olevien tietojen tallentumista tietokantaan.
 */
public class Tarkistaja {
    
    public static String lyhentaja(String kokoluokka) {
        if (kokoluokka.equalsIgnoreCase("HEAD")) {return ("<b>H</b>");}
        if (kokoluokka.equalsIgnoreCase("LEFT ARM")) {return ("<b>LA</b>");}
        if (kokoluokka.equalsIgnoreCase("RIGHT ARM")) {return ("<b>RA</b>");}
        if (kokoluokka.equalsIgnoreCase("LEFT LEG")) {return ("<b>LL</b>");}
        if (kokoluokka.equalsIgnoreCase("RIGHT LEG")) {return ("<b>RL</b>");}
        if (kokoluokka.equalsIgnoreCase("LEFT TORSO")) {return ("<b>LT</b>");}
        if (kokoluokka.equalsIgnoreCase("RIGHT TORSO")) {return ("<b>RT</b>");}
        if (kokoluokka.equalsIgnoreCase("Center TORSO")) {return ("<b>CT</b>");}
        
        if (kokoluokka.equalsIgnoreCase("SMALL")) {return ("Sm");}
        if (kokoluokka.equalsIgnoreCase("MEDIUM")) {return ("Md");}
        if (kokoluokka.equalsIgnoreCase("LARGE")) {return ("Lg");}
        if (kokoluokka.equalsIgnoreCase("XL")) {return ("Xl");}
        
        return "n/a";
    }
    
    /**
     * Tarkistaa onko joku merkkijono alfanumeerinen JA riittävän lyhyt (alle 39 merkkiä).
     * @param merkkijono
     * @return 
     */
    public static boolean onkoAlfanumeerinen(String merkkijono){
//    String pattern= "^[a-zA-Z0-9ä-Ö]*$";
//        if (string.matches(pattern) && string.length()<40){
//            return true;
//        }
//        return false;   
        
        
        boolean onkoAlfanumeerinen = true;
            for (int i=0;i<merkkijono.length();++i) {
                 if (i>38 || !Character.isLetterOrDigit(merkkijono.charAt(i))) {
                    onkoAlfanumeerinen = false;
                 
                 break;
                 }
            }
       return onkoAlfanumeerinen;

}
    
    public static String tarkistaVolume(String volume) {
          if (volume.equalsIgnoreCase("SMALL")) {return volume;}
         else if (volume.equalsIgnoreCase("MEDIUM")){return volume;}
         else if (volume.equalsIgnoreCase("LARGE")) {return volume;}
         else if (volume.equalsIgnoreCase("XL")){return volume;}
         else {return "MEDIUM";}
        
    }
    
   
    
     public static String tarkistaLocation(String location) {
          if (location.equalsIgnoreCase("ALL")) {return location;}
         else if (location.equalsIgnoreCase("HEAD")){return location;}
         else if (location.equalsIgnoreCase("ANY_TORSO")){return location;}
         else if (location.equalsIgnoreCase("ARMS")){return location;}
         else if (location.equalsIgnoreCase("ARMS_TORSO")){return location;}
         else if (location.equalsIgnoreCase("HEAD_TORSO")){return location;}
         else if (location.equalsIgnoreCase("NOT_LEGS")){return location;}
         else if (location.equalsIgnoreCase("ARMS_LEGS")){return "ARMS_LEGS";}
         else if (location.equalsIgnoreCase("NOT_HEAD")){return "ARMS_LEGS";}   // "NOT_HEAD" poistettu tarpeettomana, tämä rivi mukana legacy-varmistuksena 
         else {return "ARMS_TORSO";}
         
    }
//ENERGY KINETIC AUTO MISSILE MELEE 
         public static String tarkistaVarusteTyyppi(String tyyppi) {
          if (tyyppi.equalsIgnoreCase("HEAT SINK")) {return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("TARGETTING COMPUTER")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("JUMP JET")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("ANTI MISSILE SYSTEM")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("ARMOR PLATING")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("ACTIVE CAMO")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("ARMOR PLATING")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("GYROSCOPE")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("COCKPIT")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("SENSORS")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("ACTUATORS")){return tyyppi;}

         else {return "HEAT SINK";}
         
    }
         
         public static boolean tarkistaKohteenLaillisuus(String loc) {
             if (loc.equalsIgnoreCase("HEAD")) {return true;}
             if (loc.equalsIgnoreCase("CENTER TORSO")) {return true;}
             if (loc.equalsIgnoreCase("LEFT LEG")) {return true;}
             if (loc.equalsIgnoreCase("LEFT ARM")) {return true;}
             if (loc.equalsIgnoreCase("LEFT TORSO")) {return true;}
             if (loc.equalsIgnoreCase("RIGHT LEG")) {return true;}
             if (loc.equalsIgnoreCase("RIGHT ARM")) {return true;}
             if (loc.equalsIgnoreCase("RIGHT TORSO")) {return true;}
             return false;
         }

         public static String tarkistaAseTyyppi(String tyyppi) {
          if (tyyppi.equalsIgnoreCase("ENERGY")) {return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("KINETIC")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("AUTO")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("MELEE")){return tyyppi;}
         else if (tyyppi.equalsIgnoreCase("MISSILE")){return tyyppi;}

         else {return "ENERGY";}
         
    }
         
         public static boolean tarkistaKokoluokanRiittavyys (String kokoluokka, int mechinpaino) {
             return tarkistaLegActuatorinRiittavyys(kokoluokka, mechinpaino);
         }
         
         public static boolean tarkistaKokoluokkaKokoluokkaaVastaan(String tavoiteKokoluokka, String vertailtavaKokoluokka) {
             
             if (tavoiteKokoluokka.equalsIgnoreCase("SMALL")) {return true;}
             if (tavoiteKokoluokka.equalsIgnoreCase("MEDIUM") && !vertailtavaKokoluokka.equalsIgnoreCase("SMALL")) {return true;}
             if (tavoiteKokoluokka.equalsIgnoreCase("LARGE") && !vertailtavaKokoluokka.equalsIgnoreCase("SMALL") && !vertailtavaKokoluokka.equalsIgnoreCase("MEDIUM")) {return true;}
             if (tavoiteKokoluokka.equalsIgnoreCase("XL") && vertailtavaKokoluokka.equalsIgnoreCase("XL")) {return true;}

             return false;
         }
         
         public static boolean tarkistaLegActuatorinRiittavyys(String kokoluokka, int mechinpaino) {
             if (kokoluokka.equalsIgnoreCase("SMALL") && mechinpaino<40) {return true;}
             if (kokoluokka.equalsIgnoreCase("MEDIUM") && mechinpaino<60) {return true;}
             if (kokoluokka.equalsIgnoreCase("LARGE") && mechinpaino<80) {return true;}
             if (kokoluokka.equalsIgnoreCase("XL")) {return true;}
             return false;
         }

         

}

