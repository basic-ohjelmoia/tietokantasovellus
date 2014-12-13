
package Mechlab.Models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.naming.NamingException;

/**
 * Simulaatio kahden mechin välisestä taistelusta ajetaan tässä luokassa.
 */
public class Simulator {

    public Simulator () {
        
    }
    
    /**
     * Palauttaa taistelun kulun ja tuloksen pitkänä ArrayList<String>:inä
     * @param attacker
     * @param defender
     * @return
     * @throws NamingException
     * @throws SQLException 
     */
    public static ArrayList<String> getResults (Mech attacker, Mech defender) throws NamingException, SQLException {
        ArrayList<String> results = new ArrayList<String>();
        
        ArrayList<Komponentti> extendedRangeAttacker = new ArrayList<Komponentti>();
        ArrayList<Komponentti> longRangeAttacker = new ArrayList<Komponentti>();
        ArrayList<Komponentti> mediumRangeAttacker = new ArrayList<Komponentti>();
        ArrayList<Komponentti> shortRangeAttacker = new ArrayList<Komponentti>();
        
        ArrayList<Komponentti> extendedRangeDefender = new ArrayList<Komponentti>();
        ArrayList<Komponentti> longRangeDefender= new ArrayList<Komponentti>();
        ArrayList<Komponentti> mediumRangeDefender = new ArrayList<Komponentti>();
        ArrayList<Komponentti> shortRangeDefender = new ArrayList<Komponentti>();
        
        boolean attackerHasAMS = false;
        int attackerAMSuses = 0;
        boolean attackerHasCamo = false;
        int attackerCamoHealth = 0;
        int attackerCamoHeat = 0;
        
        boolean attackerEjected = false;
        boolean defenderEjected = false;
        
        int attackerMelee = 0;
        
        boolean defenderHasAMS = false;
        int defenderAMSuses = 0;
        boolean defenderHasCamo = false;
        int defenderCamoHealth = 0;
        int defenderCamoHeat = 0;
        
        int defenderMelee = 0;
        
        int attackerJump = attacker.getJumprating();
        int defenderJump = defender.getJumprating();
        
        
        for (Komponentti komponentti : attacker.getMechinKomponentit("ALL")) {
            
            
            if (komponentti.getKategoria().equalsIgnoreCase("VARUSTE")) {
                if (komponentti.getVarustetype().equalsIgnoreCase("ANTI MISSILE SYSTEM")) {
                    attackerHasAMS = true;
                    attackerAMSuses = 8 - komponentti.getVarustetier();
                }
                if (komponentti.getVarustetype().equalsIgnoreCase("ACTIVE CAMO")) {
                    attackerHasCamo = true;
                    attackerCamoHealth = 4 - komponentti.getVarustetier();
                    attackerCamoHeat = komponentti.getHeat();
                }
            }
            if (komponentti.getKategoria().equalsIgnoreCase("ASE")) {
                if (komponentti.getWeapontype().equalsIgnoreCase("MELEE")) {
                    attackerMelee++;
                }
                
                if (komponentti.getWeaponmaxrange()>=15) {
                    extendedRangeAttacker.add(komponentti);
                }
                else if (komponentti.getWeaponmaxrange()>=9) {
                    longRangeAttacker.add(komponentti);
                }
                else if (komponentti.getWeaponmaxrange()>=5) {
                    mediumRangeAttacker.add(komponentti);
                }
                else  {
                    shortRangeAttacker.add(komponentti);
                }
            }
        }
        
        for (Komponentti komponentti : defender.getMechinKomponentit("ALL")) {
              if (komponentti.getKategoria().equalsIgnoreCase("VARUSTE")) {
                if (komponentti.getVarustetype().equalsIgnoreCase("ANTI MISSILE SYSTEM")) {
                    defenderHasAMS = true;
                    defenderAMSuses = 8 - komponentti.getVarustetier();
                }
                if (komponentti.getVarustetype().equalsIgnoreCase("ACTIVE CAMO")) {
                    defenderHasCamo = true;
                    defenderCamoHealth = 4 - komponentti.getVarustetier();
                    defenderCamoHeat = komponentti.getHeat();
                }
            }

            if (komponentti.getKategoria().equalsIgnoreCase("ASE")) {
                if (komponentti.getWeapontype().equalsIgnoreCase("MELEE")) {
                    defenderMelee++;
                }
                
                if (komponentti.getWeaponmaxrange()>=15) {
                    extendedRangeDefender.add(komponentti);
                }
                else if (komponentti.getWeaponmaxrange()>=9) {
                    longRangeDefender.add(komponentti);
                }
                else if (komponentti.getWeaponmaxrange()>=5) {
                    mediumRangeDefender.add(komponentti);
                }
                else  {
                    shortRangeDefender.add(komponentti);
                }
            }
        }
            int attackerHeat = 0;
            int defenderHeat = 0;
            
            int attackerWeaponRating = attacker.getWeaponrating();
            int attackerDefRating = attacker.getDefenserating();
            int attackerArmor = attacker.getArmorrating();
            int attackerStructure = attacker.getInnerStructure();
            int attackerNopeus = attacker.getRunningspeed();
            
            int defenderWeaponRating = defender.getWeaponrating();
            int defenderDefRating = defender.getDefenserating();
            int defenderArmor = defender.getArmorrating();
            int defenderStructure = defender.getInnerStructure();
            int defenderNopeus = defender.getRunningspeed();
            
            if (defenderNopeus>(attackerNopeus*1.45)) {attackerDefRating-=30;}
            else if (defenderNopeus>(attackerNopeus*1.25)) {attackerDefRating-=18;}
            else if (defenderNopeus>(attackerNopeus*1.15)) {attackerDefRating-=8;}
            
            if ((defenderNopeus*1.45)<(attackerNopeus)) {defenderDefRating-=30;}
            else if ((defenderNopeus*1.25)<(attackerNopeus)) {defenderDefRating-=18;}
            else if ((defenderNopeus*1.15)<(attackerNopeus)) {defenderDefRating-=8;}
            
            if ((defenderDefRating-attackerWeaponRating)>40) {defenderWeaponRating+=20; defenderWeaponRating+=10;} // vähennetään un-resolved-simulaatioita
            if ((attackerDefRating-defenderWeaponRating)>40) {defenderWeaponRating+=10; defenderWeaponRating+=20;}
            
            
            ArrayList<Komponentti> attackerWeapons = new ArrayList<Komponentti>();
            ArrayList<Komponentti> defenderWeapons = new ArrayList<Komponentti>();
            
            boolean attackerMobility=true;
            boolean defenderMobility=true;
            
            int vuoro = 1;
            int etaisyys = 4;
            Random randomi = new Random();
            while (true) {
                attackerWeapons.clear();
                defenderWeapons.clear();
                String range = "";
                
                
                
                
                if (etaisyys == 4 || etaisyys == 3 || (etaisyys == 2 && (vuoro % 2 == 0)) || (etaisyys == 1 && (vuoro % 3 == 0))) {defenderWeapons.addAll(extendedRangeDefender); attackerWeapons.addAll(extendedRangeAttacker); range = "Extended Range";}
                if (etaisyys == 3 || etaisyys == 2 || (etaisyys == 1 && (vuoro % 2 == 0))) {defenderWeapons.addAll(longRangeDefender); attackerWeapons.addAll(longRangeAttacker);range = "Long Range";}
                if (etaisyys == 2 || etaisyys == 1) {attackerWeapons.addAll(mediumRangeAttacker); defenderWeapons.addAll(mediumRangeDefender);range = "Medium Range";}
                if (etaisyys == 1) {attackerWeapons.addAll(shortRangeAttacker);defenderWeapons.addAll(shortRangeDefender); range = "Short Range";}
            
                results.add("NEXTTURN1");
                results.add("TURN "+vuoro+", Combatants at "+range);
                results.add("NEXTTURN2");
                if (attackerHeat<30) {
                    
                        boolean defenderUsedCamo = false;
                        
                        for (Komponentti ase : attackerWeapons) {
                            if (((attackerHeat+ase.getHeat()<30) || ase.getHeat()<5 || attackerArmor<24) &&
                                    
                                    (!ase.getWeapontype().equalsIgnoreCase("MELEE") || (vuoro % 5 ==0 || vuoro % 3 ==0)) && (ase.getWeaponammo()==0 || (ase.getWeaponammoleft()>0))) {
                            attackerHeat += ase.getHeat();
                            int damage = ase.getWeapondamage();
                            int hitmodifier = 0;
                            if (ase.getWeapontype().equalsIgnoreCase("AUTO") || ase.getWeapontype().equalsIgnoreCase("MISSILE")) {
                                if (ase.getWeapondamage()>=5) {damage -= randomi.nextInt(ase.getWeapondamage()/2);} 
                                else {damage -= randomi.nextInt(2);}
                                if (damage<1) {damage=1;}
                                
                                if (ase.getWeapontype().equalsIgnoreCase("AUTO")) {hitmodifier = 16;}
                                if (ase.getWeapontype().equalsIgnoreCase("MISSILE")) {hitmodifier = 8;}
                                
                                if (etaisyys>1 && defenderHasCamo && defenderCamoHealth>0) {if (!defenderUsedCamo) {results.add(defender.getNimi()+ " is using Active Camo!");} defenderUsedCamo = true; hitmodifier -= (3*(etaisyys+defenderCamoHealth));}
                                
                                if (defenderUsedCamo && defender.getPaino()<40) {hitmodifier-=5;}
                                else if (defenderUsedCamo && defender.getPaino()<60) {hitmodifier-=0;}
                                else if (defenderUsedCamo && defender.getPaino()>=60) {hitmodifier+=5;}
                                
                                if (ase.getWeapontype().equalsIgnoreCase("MISSILE") && ase.getWeaponammo()==1) {hitmodifier = 10000; if (defenderUsedCamo) {hitmodifier=75;}}
                                else if (ase.getWeapontype().equalsIgnoreCase("MISSILE") && ase.getWeaponminrange()>1 && etaisyys==1) {results.add(attacker.getNimi()+" gets a poor missile lock!"); damage=damage/2;}
                                else if (ase.getWeapontype().equalsIgnoreCase("MISSILE") && ase.getWeaponmaxrange()>=12 && etaisyys>=3) {results.add(attacker.getNimi()+" gets a solid missile lock!"); hitmodifier+=15;}
                                if (ase.getWeapontype().equalsIgnoreCase("MISSILE") && defenderHasAMS && defenderAMSuses>0) {results.add(defender.getNimi()+ " Anti Missile System has shot down (some) incoming missiles!"); defenderAMSuses--; if (defenderAMSuses==0) {results.add("WARNING! Anti Missile System has ran out of stores!");}; hitmodifier-=10; if (damage>8) {defenderAMSuses--;}; damage=damage-(damage/2);}
                                
                            }
                            
                            
                            
                            String huomautus = "";
                            int lahipenalty = 0;
                            if (ase.getWeapontype().equalsIgnoreCase("ENERGY") || ase.getWeapontype().equalsIgnoreCase("KINETIC")) {
                                if (ase.getWeaponmaxrange()>=15 && (etaisyys == 1 || etaisyys == 2)) {
                                    lahipenalty = ((ase.getWeaponmaxrange())/etaisyys);
                                    if (etaisyys==2) {lahipenalty+=2;}
                                    if (etaisyys==1) {lahipenalty+=5;}
                                    huomautus = "(Severe aiming penalty!)";
                                }
                                else if (ase.getWeaponmaxrange()>=12 && (etaisyys == 1)) {
                                    lahipenalty = ((ase.getWeaponmaxrange())/etaisyys);
                                    huomautus = "(Aiming penalty!)";
                                }
                            }     
                            
                        
                            
                            ase.spendAmmo(); if (ase.getWeaponammoleft()<1 && ase.getWeaponammo()>0) {
                                                        huomautus+=" (Out of ammo!)";
                                                            if (ase.getWeaponmaxrange()>=15) {
                                                    extendedRangeAttacker.remove(ase);
                                            } 
                                            else if (ase.getWeaponmaxrange()>=9) {
                                                longRangeAttacker.remove(ase);
                                            }
                                            else if (ase.getWeaponmaxrange()>=5) {
                                                mediumRangeAttacker.remove(ase);
                                            }
                                            else {shortRangeAttacker.remove(ase);}
                            }
                            
                            if (defenderHeat>=30  || defenderNopeus ==0) {
                                hitmodifier+=30;
                            }
                            
                            if (defenderDefRating<1) {defenderDefRating=1;}
                            if (defenderWeaponRating<1) {defenderWeaponRating=1;}
                            if (attackerDefRating<1) {attackerDefRating=1;}
                            if (attackerWeaponRating<1) {attackerWeaponRating=1;}
                            
                            if ((randomi.nextInt(90)+(randomi.nextInt((attackerWeaponRating/2)+1)-randomi.nextInt(defenderDefRating+1))+hitmodifier-lahipenalty)>((45-(vuoro*1.5))+(etaisyys*3))) {
                            defenderArmor -= damage;
                            defenderDefRating--;
                            
                            
                            
                            if (defenderDefRating<1) {defenderDefRating=1;}
                            results.add(attacker.getNimi()+" hits "+defender.getNimi()+" with "+ase.getNimi()+"! "+huomautus);
                            if (defenderHasCamo && defenderCamoHealth>0) {defenderCamoHealth--; if (defenderCamoHealth==0) {results.add("CRITICAL! "+defender.getNimi()+" Active Camo has been disabled!");}}
                            
                            int mod = 0;
                            if (defenderArmor<defender.getPaino()) {mod=5;}
                            if (defenderStructure==0) {mod=15;}
                            
                             if (randomi.nextInt(88+(damage))>(90-mod) && (defenderArmor/2)<(defender.getArmorrating()/3) && defenderArmor<150) {
                                Komponentti tuhoutuva = null;
                                String tuhoutui = "";
                                boolean ammohit = false;
                                
                                if (randomi.nextInt(2)==1) {
                                
                                if (extendedRangeDefender.size()>0) {tuhoutuva = extendedRangeDefender.get(randomi.nextInt(extendedRangeDefender.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;} tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; extendedRangeDefender.remove(tuhoutuva);}
                                else if (mediumRangeDefender.size()>0) {tuhoutuva = mediumRangeDefender.get(randomi.nextInt(mediumRangeDefender.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;} tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; mediumRangeDefender.remove(tuhoutuva);}
                                else if (longRangeDefender.size()>0) {tuhoutuva = longRangeDefender.get(randomi.nextInt(longRangeDefender.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;} tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; longRangeDefender.remove(tuhoutuva);}
                                else if (shortRangeDefender.size()>0)  {tuhoutuva = shortRangeDefender.get(randomi.nextInt(shortRangeDefender.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;} tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; shortRangeDefender.remove(tuhoutuva);}
                                } else {
                                    if (shortRangeDefender.size()>0)  {tuhoutuva = shortRangeDefender.get(randomi.nextInt(shortRangeDefender.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;} tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; shortRangeDefender.remove(tuhoutuva);}
                                    else if (longRangeDefender.size()>0) {tuhoutuva = longRangeDefender.get(randomi.nextInt(longRangeDefender.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;} tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; longRangeDefender.remove(tuhoutuva);}
                                    else if (mediumRangeDefender.size()>0) {tuhoutuva = mediumRangeDefender.get(randomi.nextInt(mediumRangeDefender.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;} tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; mediumRangeDefender.remove(tuhoutuva);}
                                    else if (extendedRangeDefender.size()>0) {tuhoutuva = extendedRangeDefender.get(randomi.nextInt(extendedRangeDefender.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;} tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; extendedRangeDefender.remove(tuhoutuva);}
                                }
                                if (tuhoutui.length()>0) {results.add(tuhoutui); 
                                    defenderWeaponRating-=2;
                                    if (defenderWeaponRating<1) {defenderWeaponRating=1;}
                                }
                                if (ammohit) {results.add("SUPER CRITICAL! Ammo magazine explodes!"); defenderArmor-=10+(randomi.nextInt(30));}
                                
                            } else if (randomi.nextInt(88+(damage))>(80-mod) && (defenderArmor<(defender.getArmorrating()/3) && defenderNopeus>0)) {defenderNopeus=defenderNopeus/2; if (defenderNopeus<=8) {defenderNopeus=0; results.add("CRITICAL! "+defender.getNimi()+" leg actuators destroyed! The mech is crippled!");} else {results.add("CRITICAL! "+defender.getNimi()+" leg actuators damaged!"); defenderMobility=false;}}
                             else if (randomi.nextInt(88+(damage))>(70-mod) && (defenderArmor<(defender.getArmorrating()/2) && defenderJump>0)) {defenderJump=defenderJump/2; if (defenderJump<=10) {defenderJump=0; results.add("CRITICAL! "+defender.getNimi()+" jump jets destroyed!");} else {results.add("CRITICAL! "+defender.getNimi()+" jump jets damaged!");} }
                            
                            if (defenderArmor<0 && defenderStructure>0) {defenderArmor=defenderStructure; defenderStructure=0; defenderDefRating = 0; results.add("WARNING! "+defender.getNimi()+ " hull breached!");}
                            
                            if (defenderArmor<0) {results.add(defender.getNimi()+" has been destroyed!"); if (damage<10) {results.add("The pilot manages to EJECT!");} else {results.add("The pilot failed to escape the catastrophic damage!");} break;}
                            } else {
                            results.add(attacker.getNimi()+" misses "+defender.getNimi()+" with "+ase.getNimi()+"! "+huomautus);
                            }
                        } else if (ase.getHeat()>=5) {
                            results.add(attacker.getNimi()+" is conserving fire to reduce heat...");
                        } 
                    }
                        
                    if (defenderUsedCamo) {defenderHeat+=3;}
                    
                }
                if (defenderArmor<0) {break;}
                        
                
                if (defenderHeat<30) {
                    
                        boolean attackerUsedCamo = false;
                        for (Komponentti ase : defenderWeapons) {
                            if (((defenderHeat+ase.getHeat()<30) || ase.getHeat()<5 || defenderArmor<24) &&
                                    (!ase.getWeapontype().equalsIgnoreCase("MELEE") || (vuoro % 5 ==0 || vuoro % 3 ==0)) && (ase.getWeaponammo()==0 || (ase.getWeaponammoleft()>0))) {
                            defenderHeat += ase.getHeat();
                            int damage = ase.getWeapondamage();
                            int hitmodifier = 0;
                            if (ase.getWeapontype().equalsIgnoreCase("AUTO") || ase.getWeapontype().equalsIgnoreCase("MISSILE")) {
                                if (ase.getWeapondamage()>=5) {damage -= randomi.nextInt(ase.getWeapondamage()/2);} 
                                else {damage -= randomi.nextInt(2);}
                                if (damage<1) {damage=1;}
                                
                                  if (ase.getWeapontype().equalsIgnoreCase("AUTO")) {hitmodifier = 16;}
                                if (ase.getWeapontype().equalsIgnoreCase("MISSILE")) {hitmodifier = 8;}
                                
                                if (etaisyys > 1 && attackerHasCamo && attackerCamoHealth>0) {if (!attackerUsedCamo) {results.add(attacker.getNimi()+ " is using Active Camo!");} attackerUsedCamo = true; hitmodifier -= (3*(etaisyys+attackerCamoHealth));}
                                
                                 if (attackerUsedCamo && attacker.getPaino()<40) {hitmodifier-=5;}
                                else if (attackerUsedCamo && attacker.getPaino()<60) {hitmodifier-=0;}
                                else if (attackerUsedCamo && attacker.getPaino()>=60) {hitmodifier+=5;}
                                
                                if (ase.getWeapontype().equalsIgnoreCase("MISSILE") && ase.getWeaponammo()==1) {hitmodifier = 10000; if (attackerUsedCamo) {hitmodifier=75;}}
                                else if (ase.getWeapontype().equalsIgnoreCase("MISSILE") && ase.getWeaponminrange()>1 && etaisyys==1) {results.add(defender.getNimi()+" gets a poor missile lock!"); damage=damage/2;}
                                else if (ase.getWeapontype().equalsIgnoreCase("MISSILE") && ase.getWeaponmaxrange()>=12 && etaisyys>=3) {results.add(defender.getNimi()+" gets a solid missile lock!"); hitmodifier+=15;}
                                if (ase.getWeapontype().equalsIgnoreCase("MISSILE") && attackerHasAMS && attackerAMSuses>0) {results.add(attacker.getNimi()+ " Anti Missile System has shot down (some) incoming missiles!"); attackerAMSuses--; if (attackerAMSuses==0) {results.add("WARNING! Anti Missile System has ran out of stores!");}; hitmodifier-=10; if (damage>8) {attackerAMSuses--;};damage=damage-(damage/2);}
                                
                            }
                            String huomautus = "";
                               int lahipenalty = 0;
                            if (ase.getWeapontype().equalsIgnoreCase("ENERGY") || ase.getWeapontype().equalsIgnoreCase("KINETIC")) {
                                  if (ase.getWeaponmaxrange()>=15 && (etaisyys == 1 || etaisyys == 2)) {
                                    lahipenalty = ((ase.getWeaponmaxrange())/etaisyys);
                                    if (etaisyys==2) {lahipenalty+=2;}
                                    if (etaisyys==1) {lahipenalty+=5;}
                                    huomautus = "(Severe aiming penalty!)";
                                }
                                else if (ase.getWeaponmaxrange()>=12 && (etaisyys == 1)) {
                                    lahipenalty = ((ase.getWeaponmaxrange())/etaisyys);
                                    huomautus = "(Aiming penalty!)";
                                }
                            }  
                            ase.spendAmmo(); if (ase.getWeaponammoleft()<1 && ase.getWeaponammo()>0) {
                                            huomautus=" (Out of ammo!)";
                                           
                                            if (ase.getWeaponmaxrange()>=15) {
                                                    extendedRangeDefender.remove(ase);
                                            } 
                                            else if (ase.getWeaponmaxrange()>=9) {
                                                longRangeDefender.remove(ase);
                                            }
                                            else if (ase.getWeaponmaxrange()>=5) {
                                                mediumRangeDefender.remove(ase);
                                            }
                                            else {shortRangeDefender.remove(ase);}
                                            
                                            }
                              if (attackerHeat>=30 || attackerNopeus ==0) {
                                hitmodifier+=30;
                            }
                              
                                    if (defenderDefRating<1) {defenderDefRating=1;}
                            if (defenderWeaponRating<1) {defenderWeaponRating=1;}
                            if (attackerDefRating<1) {attackerDefRating=1;}
                            if (attackerWeaponRating<1) {attackerWeaponRating=1;}
                            
                            if ((randomi.nextInt(90)+(randomi.nextInt((defenderWeaponRating/2)+1)-randomi.nextInt(attackerDefRating+1))+hitmodifier-lahipenalty)>((45-(vuoro*1.5))+(etaisyys*3))) {
                            attackerArmor -= damage;
                            attackerDefRating--;
                            
                            
                            
                            if (attackerDefRating<1) {attackerDefRating=1;}
                            results.add(defender.getNimi()+" hits "+attacker.getNimi()+" with "+ase.getNimi()+"! "+huomautus);
                            
                            if (attackerHasCamo && attackerCamoHealth>0) {attackerCamoHealth--; if (attackerCamoHealth==0) {results.add("CRITICAL! "+attacker.getNimi()+" Active Camo has been disabled!");}}
                            
                                int mod = 0;
                            if (attackerArmor<attacker.getPaino()) {mod=5;}
                            if (attackerStructure==0) {mod=15;}
                            
                            if (randomi.nextInt(88+(damage))>(90-mod) && (attackerArmor/2)<(attacker.getArmorrating()/3) && attackerArmor<150) {
                                Komponentti tuhoutuva = null;
                                String tuhoutui = "";
                                boolean ammohit = false;
                                if (randomi.nextInt(2)==1) {

                                    if (extendedRangeAttacker.size()>0) {tuhoutuva = extendedRangeAttacker.get(randomi.nextInt(extendedRangeAttacker.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;}; tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; extendedRangeAttacker.remove(tuhoutuva);}
                                else if (mediumRangeAttacker.size()>0) {tuhoutuva = mediumRangeAttacker.get(randomi.nextInt(mediumRangeAttacker.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;}; tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; mediumRangeAttacker.remove(tuhoutuva);}
                                else if (longRangeAttacker.size()>0) {tuhoutuva = longRangeAttacker.get(randomi.nextInt(longRangeAttacker.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;}; tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; longRangeAttacker.remove(tuhoutuva);}
                                else if (shortRangeAttacker.size()>0)  {tuhoutuva = shortRangeAttacker.get(randomi.nextInt(shortRangeAttacker.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;}; tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; shortRangeAttacker.remove(tuhoutuva);}
                                } else {
                                 if (shortRangeAttacker.size()>0)  {tuhoutuva = shortRangeAttacker.get(randomi.nextInt(shortRangeAttacker.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;}; tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; shortRangeAttacker.remove(tuhoutuva);}
                            else if (longRangeAttacker.size()>0) {tuhoutuva = longRangeAttacker.get(randomi.nextInt(longRangeAttacker.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;}; tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; longRangeAttacker.remove(tuhoutuva);}
                                else if (mediumRangeAttacker.size()>0) {tuhoutuva = mediumRangeAttacker.get(randomi.nextInt(mediumRangeAttacker.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;}; tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; mediumRangeAttacker.remove(tuhoutuva);}
                                      else if (extendedRangeAttacker.size()>0) {tuhoutuva = extendedRangeAttacker.get(randomi.nextInt(extendedRangeAttacker.size())); if (tuhoutuva.getWeaponammoleft()>0) {ammohit=true;}; tuhoutui = "CRITICAL! "+tuhoutuva.getNimi() + " has been destroyed!"; extendedRangeAttacker.remove(tuhoutuva);}
                                }
                                if (tuhoutui.length()>0) {results.add(tuhoutui); 
                                    attackerWeaponRating-=2;
                                    if (attackerWeaponRating<1) {attackerWeaponRating=1;}
                                }
              if (ammohit) {results.add("SUPER CRITICAL! Ammo magazine explodes!"); defenderArmor-=10+(randomi.nextInt(30));}
                                
                            }  else if (randomi.nextInt(88+(damage))>(80-mod) && (attackerArmor<(attacker.getArmorrating()/3) && attackerNopeus>0)) {attackerNopeus=attackerNopeus/2; if (attackerNopeus<=8) {attackerNopeus=0; results.add("CRITICAL! "+attacker.getNimi()+" leg actuators destroyed! The mech is crippled!");} else {results.add("CRITICAL! "+attacker.getNimi()+" leg actuators damaged!"); attackerMobility=false;}}
                             else if (randomi.nextInt(88+(damage))>(70-mod) && (attackerArmor<(attacker.getArmorrating()/2) && attackerJump>0)) {attackerJump=attackerJump/2; if (attackerJump<=10) {attackerJump=0; results.add("CRITICAL! "+attacker.getNimi()+" jump jets destroyed!");} else {results.add("CRITICAL! "+attacker.getNimi()+" jump jets damaged!");} 
                            
                            }

                            if (attackerArmor<0 && attackerStructure>0) {attackerArmor=attackerStructure; attackerStructure=0; attackerDefRating=0; results.add("WARNING! "+attacker.getNimi()+ " hull breached!");}    
                               
                            if (attackerArmor<0) {results.add(attacker.getNimi()+" has been destroyed!"); if (damage<10) {results.add("The pilot manages to eject!");} else {results.add("The pilot failed to escape the catastrophic damage!");} break;}
                            } else {
                            results.add(defender.getNimi()+" misses "+attacker.getNimi()+" with "+ase.getNimi()+"! "+huomautus);
                            }
                        }  else if (ase.getHeat()>=5) {
                            results.add(defender.getNimi()+" is conserving fire to reduce heat...");
                        }  
                    }
                        if (attackerUsedCamo) {attackerHeat+=3;}
                }    
                if (attackerArmor<0) {break;}
                
                
                
                
                
                
                
                
                attackerHeat-=attacker.getHeatsinks();
                defenderHeat-=defender.getHeatsinks();
                
                
                
                if (attackerHeat>33) {results.add(attacker.getNimi()+" OVERHEATED!");}
                else if (attackerHeat>20) {results.add(attacker.getNimi()+" heat level critical!");}
                else if (attackerHeat>10) {results.add(attacker.getNimi()+" heat level high!");}
                
                
                if (defenderHeat>33) {results.add(defender.getNimi()+" OVERHEATED!");}
                else if (defenderHeat>20) {results.add(defender.getNimi()+" heat level critical!");}
                else if (defenderHeat>10) {results.add(defender.getNimi()+" heat level high!");}
                
            if (shortRangeAttacker.size()+mediumRangeAttacker.size()+longRangeAttacker.size()+extendedRangeAttacker.size()==0 && attackerArmor>0) {
                results.add("WARNING! "+attacker.getNimi()+" has lost all weapon systems!");
                results.add("PILOT EJECTS! "+attacker.getNimi()+" has been abandoned!");
                attackerArmor=-1;attackerEjected=true; break;
            }
            
            if (shortRangeDefender.size()+mediumRangeDefender.size()+longRangeDefender.size()+extendedRangeDefender.size()==0 && defenderArmor>0) {
                results.add("WARNING! "+defender.getNimi()+" has lost all weapon systems!");
                results.add("PILOT EJECTS! "+defender.getNimi()+" has been abandoned!");
                defenderArmor=-1; defenderEjected=true; break;
            }
                
                
            if (attackerHeat<30 && ((longRangeAttacker.size()+extendedRangeAttacker.size())>
                    (longRangeDefender.size()+extendedRangeDefender.size())) && vuoro % 5 == 0 && attackerJump>defenderJump) {
                etaisyys+=2;
                    results.add(attacker.getNimi()+" uses jump jets to evade "+defender.getNimi());
            } else  if (attackerHeat<30 && ((longRangeAttacker.size()+extendedRangeAttacker.size())<
                    (longRangeDefender.size()+extendedRangeDefender.size())) && vuoro % 5 == 0 && attackerJump>defenderJump) {
                etaisyys-=2;
                    results.add(attacker.getNimi()+" uses jump jets to catch "+defender.getNimi());
            }
            
            else if (defenderHeat<30 && ((longRangeAttacker.size()+extendedRangeAttacker.size())>
                    (longRangeDefender.size()+extendedRangeDefender.size())) && vuoro % 5 == 0 && attackerJump<defenderJump) {
                etaisyys-=2;
                    results.add(defender.getNimi()+" uses jump jets to catch "+attacker.getNimi());
            } else  if (defenderHeat<30 && ((longRangeAttacker.size()+extendedRangeAttacker.size())<
                    (longRangeDefender.size()+extendedRangeDefender.size())) && vuoro % 5 == 0 && attackerJump<defenderJump) {
                etaisyys+=2;
                    results.add(defender.getNimi()+" uses jump jets to evade "+attacker.getNimi());
            }
            
                
                else if (attackerHeat<20 && (attackerNopeus>defenderNopeus) && ((longRangeAttacker.size()+extendedRangeAttacker.size())>
                    longRangeDefender.size()+extendedRangeDefender.size()) && vuoro % 3 == 0) {
                    etaisyys++;
                    results.add(attacker.getNimi()+" uses it's superior speed to move away from "+defender.getNimi());
                }
            else if (attackerHeat<20 && (attackerNopeus>defenderNopeus) && ((longRangeAttacker.size()+extendedRangeAttacker.size())<
                    longRangeDefender.size()+extendedRangeDefender.size()) && vuoro % 3 == 0) {
                    etaisyys--;
                    results.add(attacker.getNimi()+" uses it's superior speed to close in on "+defender.getNimi());
                }
            else if (defenderHeat<20 && (attackerNopeus<defenderNopeus) && ((longRangeAttacker.size()+extendedRangeAttacker.size())>
                    longRangeDefender.size()+extendedRangeDefender.size()) && vuoro % 3 == 0) {
                    etaisyys--;
                    results.add(defender.getNimi()+" uses it's superior speed to close in on "+attacker.getNimi());
                }
            else if (defenderHeat<20 && (attackerNopeus<defenderNopeus) && ((longRangeAttacker.size()+extendedRangeAttacker.size())<
                    longRangeDefender.size()+extendedRangeDefender.size()) && vuoro % 3 == 0) {
                    etaisyys++;
                    results.add(defender.getNimi()+" uses it's superior speed to move away from "+attacker.getNimi());
                }
            else {if (vuoro % 2 == 0) {etaisyys--;}}
            
            if (etaisyys<1) {etaisyys=1;}
            if (etaisyys>4) {etaisyys=4;}
            
            vuoro++;
            if (vuoro>30) {results.add("NEXTTURN1");results.add("This combat simulation is unresolved!"); results.add("NEXTTURN2"); break;}
        }
            String tulos ="";
        if ((attackerArmor<0 || attackerEjected ) && (defenderArmor>(defender.getArmorrating()*0.75))) {
            tulos =defender.getNimi()+" survived the battle with minor damage!";
        }
        else if ((attackerArmor<0 || attackerEjected ) && (defenderArmor>(defender.getArmorrating()*0.6))) {
            tulos =defender.getNimi()+" survived the battle with moderate damage!";
        }
        else if ((attackerArmor<0 || attackerEjected ) && (defenderArmor>(defender.getArmorrating()*0.4))) {
            tulos =defender.getNimi()+" survived the battle with heavy damage!";
        }
        else if ((attackerArmor<0 || attackerEjected ) && (defenderArmor>(defender.getArmorrating()*0.10))) {
            tulos =defender.getNimi()+" survived the battle with critical damage!";
        }
        else if ((attackerArmor<0 || attackerEjected ) && (defenderArmor<=(defender.getArmorrating()*0.10))) {
            tulos =defender.getNimi()+" survived the battle but was damaged beyond repair!";
        }
        
        else  if ((defenderArmor<0 || attackerEjected ) && (attackerArmor>(attacker.getArmorrating()*0.75))) {
            tulos =attacker.getNimi()+" survived the battle with minor damage!";
        }
        else if ((defenderArmor<0 || attackerEjected ) && (attackerArmor>(attacker.getArmorrating()*0.6))) {
            tulos =attacker.getNimi()+" survived the battle with moderate damage!";
        }
        else if ((defenderArmor<0 || attackerEjected ) && (attackerArmor>(attacker.getArmorrating()*0.4))) {
            tulos =attacker.getNimi()+" survived the battle with heavy damage!";
        }
        else if ((defenderArmor<0 || attackerEjected ) && (attackerArmor>(attacker.getArmorrating()*0.10))) {
            tulos =attacker.getNimi()+" survived the battle with critical damage!";
        }
        else if ((defenderArmor<0 || attackerEjected ) && (attackerArmor<=(attacker.getArmorrating()*0.10))) {
            tulos =attacker.getNimi()+" survived the battle but was damaged beyond repair!";
        }
        else if (attackerArmor>0 && attackerArmor>=0) {tulos = attacker.getNimi()+" has "+attackerArmor+" points of armor left. "+defender.getNimi()+" has "+defenderArmor+" points of armor left.";}

        if (tulos.length()>0) {results.add("NEXTTURN1");results.add("RESULT: "+tulos);results.add("NEXTTURN2");}
        
        return results;
        
    }
}
