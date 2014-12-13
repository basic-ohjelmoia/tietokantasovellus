package Mechlab.Models;

import Mechlab.Tietokanta.Tarkistaja;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 * Tämä luokka/pseudomalli sisältää joukon avustavia toimintoja Mech-mallille.
 */
public class MechData {

    public MechData() {
    }

    public static int getArmorvalue(Mech mech, String loc, boolean haluanArmorValuen) throws NamingException, SQLException {
        loc = loc.toUpperCase();

        int panssari = 0;
        int panssarikerroin = 8;
        int lightMechPenalty = 0;
        int innerstructure = 0;
        ArrayList<String> rivit = new ArrayList<String>();


        if (mech.getPaino() < 40) {
            panssari = 2;
            lightMechPenalty = -1;
        } //panssarikerroin = 18;}
        else if (mech.getPaino() < 60) {
            panssari = 2;
        } //panssarikerroin = 15;}
        else if (mech.getPaino() < 80) {
            panssari = 3;
        } //panssarikerroin = 16;}
        else {
            panssari = 4;
        }
        //panssarikerroin = 17;}
        //int panssari

        int armor_hd = 1;
        int is_hd = 0;
        int armor_la = panssari / 2;
        int is_la = 0;
        int armor_lt = panssari * 2;
        int is_lt = 0;
        int armor_ll = panssari;
        int is_ll = 0;
        int armor_ra = panssari / 2;
        int is_ra = 0;
        int armor_rt = panssari * 2;
        int is_rt = 0;
        int armor_rl = panssari;
        int is_rl = 0;
        int armor_ct = panssari * 3;
        int is_ct = 0;

        panssari = 2 + (panssari * 10);
        innerstructure = mech.getInnerStructure();    // KOKEILLAAN
        is_hd = 1 + (innerstructure / 16);
        is_ct = 3 + (innerstructure / 6);
        is_lt = 2 + (innerstructure / 8);
        is_rt = 2 + (innerstructure / 8);
        is_ll = 1 + (innerstructure / 10);
        is_rl = 1 + (innerstructure / 10);
        is_la = 1 + (innerstructure / 12);
        is_ra = 1 + (innerstructure / 12);

        for (Komponentti komponentti : mech.getMechinKomponentit("ALL")) {
            if (komponentti.getKategoria().equalsIgnoreCase("VARUSTE")) {
                if (komponentti.getVarustetype().equalsIgnoreCase("ARMOR PLATING")) {

                    int muutos = panssarikerroin * komponentti.getMassa();

                    if (komponentti.getMassa() == 1) {
                    }
                    if (komponentti.getMassa() == 2) {
                        muutos += (2 - lightMechPenalty);
                    }
                    if (komponentti.getMassa() == 3) {
                        muutos += (4 - lightMechPenalty);
                    }
                    if (komponentti.getMassa() >= 4) {
                        muutos += (6 - lightMechPenalty);
                    }

//                    int panssarinmassa = komponentti.getMassa();
//                    if (panssarinmassa == 1) {muutos = (muutos*panssarinmassa)+8;}
//                    if (panssarinmassa == 2) {muutos = (muutos*panssarinmassa)+24;}
//                    if (panssarinmassa == 3) {muutos = (muutos*panssarinmassa)+56;}        // 16 vs 56
//                    if (panssarinmassa >= 4) {muutos = (muutos*panssarinmassa)+(20*panssarinmassa);}
//                    
                    if (komponentti.getVarustetier() == 3) {
                    }
                    if (komponentti.getVarustetier() == 2) {
                        muutos += (2 - lightMechPenalty);
                    }
                    if (komponentti.getVarustetier() == 1) {
                        muutos += (4 - lightMechPenalty);
                    }

                    if (komponentti.getSijainti().equalsIgnoreCase("HEAD")) {
                        armor_hd += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("LEFT ARM")) {
                        armor_la += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("LEFT TORSO")) {
                        armor_lt += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("LEFT LEG")) {
                        armor_ll += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("RIGHT ARM")) {
                        armor_ra += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("RIGHT TORSO")) {
                        armor_rt += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("RIGHT LEG")) {
                        armor_rl += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("CENTER TORSO")) {
                        armor_ct += muutos;
                    }
                    //muutos += panssarikerroin;

                    panssari += muutos;
                }
            }

        }

        if (loc.equalsIgnoreCase("H")) {
            if (haluanArmorValuen) {
                return armor_hd;
            } else {
                return is_hd;
            }
        }
        if (loc.equalsIgnoreCase("CT")) {
            if (haluanArmorValuen) {
                return armor_ct;
            } else {
                return is_ct;
            }
        }
        if (loc.equalsIgnoreCase("LT")) {
            if (haluanArmorValuen) {
                return armor_lt;
            } else {
                return is_lt;
            }
        }
        if (loc.equalsIgnoreCase("RT")) {
            if (haluanArmorValuen) {
                return armor_rt;
            } else {
                return is_rt;
            }
        }
        if (loc.equalsIgnoreCase("LA")) {
            if (haluanArmorValuen) {
                return armor_la;
            } else {
                return is_la;
            }
        }
        if (loc.equalsIgnoreCase("RA")) {
            if (haluanArmorValuen) {
                return armor_ra;
            } else {
                return is_ra;
            }
        }
        if (loc.equalsIgnoreCase("LL")) {
            if (haluanArmorValuen) {
                return armor_ll;
            } else {
                return is_ll;
            }
        }
        if (loc.equalsIgnoreCase("RL")) {
            if (haluanArmorValuen) {
                return armor_rl;
            } else {
                return is_rl;
            }
        }
        return 0;
    }

    public static int getComponentValues(Mech mech, String ohjain) throws NamingException, SQLException {
        int totalDamage = 0;
        int totalHeatFromWeapons = 0;
        int maxRange = 0;

        for (Komponentti komponentti : mech.getMechinKomponentit("ALL")) {
            if (komponentti.getKategoria().equalsIgnoreCase("ASE")) {
                totalDamage += komponentti.getWeapondamage();
                totalHeatFromWeapons += komponentti.getHeat();
                if (komponentti.getWeaponmaxrange() > maxRange) {
                    maxRange = komponentti.getWeaponmaxrange();
                }
            }
        }


        if (ohjain.equalsIgnoreCase("totalweapondamage")) {
            return totalDamage;
        }
        if (ohjain.equalsIgnoreCase("totalweaponheat")) {
            return totalHeatFromWeapons;
        }
        if (ohjain.equalsIgnoreCase("maxweaponrange")) {
            return maxRange;
        }


        return 0;
    }

    public static ArrayList<String> getVaroitukset(Mech mech, int vakavuus) throws NamingException, SQLException {
        ArrayList<String> varoitukset = new ArrayList<String>();
        ArrayList<String> huomautukset = new ArrayList<String>();
        ArrayList<String> saavutukset = new ArrayList<String>();
        ArrayList<String> luonnehdinta = new ArrayList<String>();

        boolean actuatorLA = false;
        boolean actuatorRA = false;
        boolean actuatorLL = false;
        boolean actuatorRL = false;
        boolean actuatorLLStrong = false;
        boolean actuatorRLStrong = false;
        boolean hasCockpit = false;
        boolean hasSensors = false;
        boolean hasGyroscope = false;
        boolean hasReactor = false;
        boolean hasEnoughPower = false;
        boolean hasAutoWeapons = false;

        int hatchetLA = 0;
        boolean hatchetLAillegal = false;
        int hatchetRA = 0;
        boolean hatchetRAillegal = false;
        String hatchetLAsize = "";
        String hatchetRAsize = "";
        String actuatorLAsize = "";
        String actuatorRAsize = "";

        int totalDamage = 0;
        int totalDamageAmmoWeapons = 0;
        int numberOfAmmoWeapons = 0;
        int ammoTotal = 0;
        int maxWeaponRange = 0;
        int minWeaponRange = 5;
        int totalHeatFromWeapons = 0;

        boolean missileWeaponInstalledOnArm = false;
        boolean otherWeaponsInstalledLeftArm = false;
        boolean otherWeaponsInstalledRightArm = false;

        boolean activeCamoTooSmall = false;

        boolean armor_hd = false;
        boolean armor_la = false;
        boolean armor_ra = false;
        boolean armor_lt = false;
        boolean armor_ct = false;
        boolean armor_rt = false;
        boolean armor_ll = false;
        boolean armor_rl = false;

        for (Komponentti komponentti : mech.getMechinKomponentit("ALL")) {
            if (komponentti.getKategoria().equalsIgnoreCase("ASE")) {
                totalDamage += komponentti.getWeapondamage();
                totalHeatFromWeapons += komponentti.getHeat();

                if (komponentti.getWeaponmaxrange() > maxWeaponRange) {
                    maxWeaponRange = komponentti.getWeaponmaxrange();
                }
                if (komponentti.getWeaponminrange() < minWeaponRange) {
                    minWeaponRange = komponentti.getWeaponminrange();
                }
                if (komponentti.getWeaponammo() > 0) {
                    totalDamageAmmoWeapons += komponentti.getWeapondamage();
                    numberOfAmmoWeapons++;
                    ammoTotal += komponentti.getWeaponammo();
                }
                if (komponentti.getWeapontype().equalsIgnoreCase("AUTO")) {
                    hasAutoWeapons = true;
                }
                if (komponentti.getWeapontype().equalsIgnoreCase("MELEE") && komponentti.getSijaintilyhyt().contains("RA")) {
                    hatchetRA++;
                    hatchetRAsize = komponentti.getKokoluokka();
                    if (!Tarkistaja.tarkistaKokoluokanRiittavyys(komponentti.getKokoluokka(), mech.getPaino())) {
                        hatchetRAillegal = true;
                    }
                }
                if (komponentti.getWeapontype().equalsIgnoreCase("MELEE") && komponentti.getSijaintilyhyt().contains("LA")) {
                    hatchetLA++;
                    hatchetLAsize = komponentti.getKokoluokka();
                    if (!Tarkistaja.tarkistaKokoluokanRiittavyys(komponentti.getKokoluokka(), mech.getPaino())) {
                        hatchetLAillegal = true;
                    }
                }
                if (komponentti.getWeapontype().equalsIgnoreCase("MISSILE") && komponentti.getSijaintilyhyt().contains("A")) {
                    missileWeaponInstalledOnArm = true;
                }
                if (!komponentti.getWeapontype().equalsIgnoreCase("MISSILE") && komponentti.getSijaintilyhyt().contains("LA")) {
                    otherWeaponsInstalledLeftArm = true;
                }
                if (!komponentti.getWeapontype().equalsIgnoreCase("MISSILE") && komponentti.getSijaintilyhyt().contains("RA")) {
                    otherWeaponsInstalledRightArm = true;
                }

            }

            if (komponentti.getKategoria().equalsIgnoreCase("REAKTORI")) {
                hasReactor = true;
                Reaktori reaktori = (Reaktori) komponentti;
                if (reaktori.getTeho() / mech.getPaino() >= 4) {
                    hasEnoughPower = true;
                }
            }
            if (komponentti.getKategoria().equalsIgnoreCase("VARUSTE")) {
                if (komponentti.getVarustetype().equalsIgnoreCase("ACTIVE CAMO")) {
                    if (!Tarkistaja.tarkistaKokoluokanRiittavyys(komponentti.getKokoluokka(), mech.getPaino())) {
                        activeCamoTooSmall = true;
                    }
                }

                if (komponentti.getVarustetype().equalsIgnoreCase("ARMOR PLATING")) {
                    if (komponentti.getSijaintilyhyt().contains("LA") || komponentti.getSijainti().equalsIgnoreCase("LEFT ARM")) {
                        armor_la = true;
                    }
                    if (komponentti.getSijaintilyhyt().contains("RA") || komponentti.getSijainti().equalsIgnoreCase("RIGHT ARM")) {
                        armor_ra = true;
                    }
                    if (komponentti.getSijaintilyhyt().contains("LL") || komponentti.getSijainti().equalsIgnoreCase("LEFT LEG")) {
                        armor_ll = true;
                    }
                    if (komponentti.getSijaintilyhyt().contains("RL") || komponentti.getSijainti().equalsIgnoreCase("RIGHT LEG")) {
                        armor_rl = true;
                    }
                    if (komponentti.getSijaintilyhyt().contains("LT") || komponentti.getSijainti().equalsIgnoreCase("LEFT TORSO")) {
                        armor_lt = true;
                    }
                    if (komponentti.getSijaintilyhyt().contains("RT") || komponentti.getSijainti().equalsIgnoreCase("RIGHT TORSO")) {
                        armor_rt = true;
                    }
                    if (komponentti.getSijaintilyhyt().contains("CT") || komponentti.getSijainti().equalsIgnoreCase("CENTER TORSO")) {
                        armor_ct = true;
                    }
                    if (komponentti.getSijaintilyhyt().contains("H") || komponentti.getSijainti().equalsIgnoreCase("HEAD")) {
                        armor_hd = true;
                    }
                }

                if (komponentti.getVarustetype().equalsIgnoreCase("COCKPIT")) {
                    hasCockpit = true;
                }
                if (komponentti.getVarustetype().equalsIgnoreCase("SENSORS")) {
                    hasSensors = true;
                }
                if (komponentti.getVarustetype().equalsIgnoreCase("GYROSCOPE")) {
                    hasGyroscope = true;
                }
                if (komponentti.getVarustetype().equalsIgnoreCase("ACTUATORS")) {
                    if (komponentti.getSijaintilyhyt().contains("LA")) {
                        actuatorLA = true;
                        actuatorLAsize = komponentti.getKokoluokka();
                    }
                    if (komponentti.getSijaintilyhyt().contains("RA")) {
                        actuatorRA = true;
                        actuatorRAsize = komponentti.getKokoluokka();
                    }
                    if (komponentti.getSijaintilyhyt().contains("LL")) {
                        actuatorLL = true;
                        if (Tarkistaja.tarkistaKokoluokanRiittavyys(komponentti.getKokoluokka(), mech.getPaino())) {
                            actuatorLLStrong = true;
                        }
                    }
                    if (komponentti.getSijaintilyhyt().contains("RL")) {
                        actuatorRL = true;
                        if (Tarkistaja.tarkistaKokoluokanRiittavyys(komponentti.getKokoluokka(), mech.getPaino())) {
                            actuatorRLStrong = true;
                        }
                    }
                }
            }
        }
        if (!hasReactor) {
            varoitukset.add("The mech has no REACTOR installed! (The mech is not operational!)");
        }
        if (!hasCockpit) {
            varoitukset.add("The mech has no COCKPIT installed! (The mech is not operational!)");
        }
        if (!hasGyroscope) {
            varoitukset.add("The mech has no GYROSCOPE installed! (The mech is not operational!)");
        }
        if (!hasSensors) {
            varoitukset.add("The mech has no SENSORS installed! (The mech is not combat ready!)");
        }

        if (mech.getPaino() < mech.getNettopaino()) {
            varoitukset.add("The current BUILD has exceeded the mech's weight class! (Remove components or increase weight class!)");
        }
        if (mech.getPaino() > mech.getNettopaino()) {
            huomautukset.add("The current BUILD still has weight allowance for additional components.");
        }
        if (mech.getPaino() == mech.getNettopaino()) {
            saavutukset.add("The current BUILD matches the mech's weight class.");
        }

        if (mech.getItemshead() > mech.getItemsallowedextension()) {
            varoitukset.add("The mech is OVERLOADED with components: head!");
        }
        if (mech.getItemscentertorso() > mech.getItemsallowedtorso()) {
            varoitukset.add("The mech is OVERLOADED with components: center torso!");
        }
        if (mech.getItemslefttorso() > mech.getItemsallowedtorso()) {
            varoitukset.add("The mech is OVERLOADED with components: left torso!");
        }
        if (mech.getItemsrighttorso() > mech.getItemsallowedtorso()) {
            varoitukset.add("The mech is OVERLOADED with components: right torso!");
        }
        if (mech.getItemsleftleg() > mech.getItemsallowedextension()) {
            varoitukset.add("The mech is OVERLOADED with components: left leg!");
        }
        if (mech.getItemsrightleg() > mech.getItemsallowedextension()) {
            varoitukset.add("The mech is OVERLOADED with components: right leg!");
        }
        if (mech.getItemsleftarm() > mech.getItemsallowedextension()) {
            varoitukset.add("The mech is OVERLOADED with components: left arm!");
        }
        if (mech.getItemsrightarm() > mech.getItemsallowedextension()) {
            varoitukset.add("The mech is OVERLOADED with components: right arm!");
        }

        if (activeCamoTooSmall) {
            varoitukset.add("The mech's ACTIVE CAMO system is not compatible with a mech of this weight class! (Install a larger ACTIVE CAMO or remove the current one.)");
        }

        if (hatchetRA > 1 || hatchetLA > 1) {
            varoitukset.add("The mech cannot have more than one MELEE WEAPON per arm!");
        } else if (hatchetRAillegal || hatchetLAillegal) {
            if (hatchetRAillegal) {
                varoitukset.add("The right arm MELEE WEAPON is not robust enough for a mech of this WEIGHT CLASS!");
            }
            if (hatchetLAillegal) {
                varoitukset.add("The left arm MELEE WEAPON is not robust enough for a mech of this WEIGHT CLASS!");
            }
        } else if ((hatchetRA == 1 && !actuatorRA) || (hatchetLA == 1 && !actuatorLA)) {
            if (hatchetRA == 1 && !actuatorRA) {
                varoitukset.add("The right arm MELEE WEAPON is useless without a right arm ACTUATOR! Remove the melee weapon or install an actuator!");
            }
            if (hatchetLA == 1 && !actuatorLA) {
                varoitukset.add("The left arm MELEE WEAPON is useless without a left arm ACTUATOR! Remove the melee weapon or install an actuator!");
            }
        } else if (hatchetRA == 1 && (!Tarkistaja.tarkistaKokoluokkaKokoluokkaaVastaan(hatchetRAsize, actuatorRAsize)
                && (!Tarkistaja.tarkistaKokoluokanRiittavyys(hatchetRAsize, mech.getPaino())))) {
            varoitukset.add("The right arm MELEE WEAPON is oversized for a mech of this weight class. To compensate you must install a " + hatchetRAsize + " sized ARM ACTUATOR!");
        } else if (hatchetLA == 1 && (!Tarkistaja.tarkistaKokoluokkaKokoluokkaaVastaan(hatchetLAsize, actuatorLAsize)
                && (!Tarkistaja.tarkistaKokoluokanRiittavyys(hatchetLAsize, mech.getPaino())))) {
            varoitukset.add("The left arm MELEE WEAPON is oversized for a mech of this weight class. To compensate you must install a " + hatchetLAsize + " sized ARM ACTUATOR!");
        }




        if (!actuatorLA && !actuatorRA) {
            String jatko = "";
            if (otherWeaponsInstalledLeftArm || otherWeaponsInstalledRightArm) {
                jatko = " (Direct-fire weapons installed on arms will not receive a bonus to weapon rating.)";
            }
            huomautukset.add("The mech is missing arm ACTUATORS." + jatko);
        }
        if (!actuatorLA && actuatorRA) {
            String jatko = "";
            if (otherWeaponsInstalledLeftArm) {
                jatko = " (Direct-fire weapons installed there will not receive a bonus to weapon rating.)";
            }
            huomautukset.add("The left arm is missing ACTUATORS." + jatko);
        }
        if (actuatorLA && !actuatorRA) {
            String jatko = "";
            if (otherWeaponsInstalledRightArm) {
                jatko = " (Direct-fire weapons installed there will not receive a bonus to weapon rating.)";
            }
            huomautukset.add("The right arm is missing ACTUATORS." + jatko);
        }
        if (!actuatorLL && !actuatorRL) {
            varoitukset.add("The mech is missing leg ACTUATORS. (The mech is immobile!)");
        }
        if (!actuatorLL && actuatorRL) {
            huomautukset.add("The left leg is missing ACTUATORS. (Mobility halved!)");
        }
        if (!actuatorRL && actuatorLL) {
            huomautukset.add("The right leg is missing ACTUATORS. (Mobility halved!)");
        }
        if (!actuatorLLStrong || !actuatorRLStrong) {
            huomautukset.add("The leg ACTUATORS are insufficient for mech of this weight class. (Penalty to mobility!)");
        }

        if (hasReactor && !hasEnoughPower) {
            huomautukset.add("The mech has an underpowered REACTOR! (The mech will move slowly!)");
        }
        if (totalDamage == 0) {
            varoitukset.add("The mech is UNARMED! (This mech is not combat ready!)");
        }

        if (totalHeatFromWeapons > (mech.getHeatsinks() + 10)) {
            huomautukset.add("The mech has insufficient HEAT SINKS installed for the current weapon configuration: heat " + totalHeatFromWeapons + " vs cooling " + mech.getHeatsinks() + ". (Risk of MAJOR overheat!)");
        } else if (totalHeatFromWeapons > (mech.getHeatsinks() + 5)) {
            huomautukset.add("The mech has insufficient HEAT SINKS installed for the current weapon configuration: heat " + totalHeatFromWeapons + " vs cooling " + mech.getHeatsinks() + ".");
        }

        if (totalDamage > 0 && maxWeaponRange < 5) {
            huomautukset.add("The mech is armed with only short range WEAPONS!");
        } else if (totalDamage > 0 && maxWeaponRange < 9) {
            huomautukset.add("The mech has no long to extended range WEAPONS!");
        } else if (totalDamage > 0 && maxWeaponRange < 12) {
            huomautukset.add("The mech has no extended range WEAPONS!");
        }
        if (!hasEnoughPower && maxWeaponRange < 12) {
            huomautukset.add("Combination of slow speed and lack of long range WEAPONS is a potential liability for this build.");
        }

        if (!hasEnoughPower && minWeaponRange > 2) {
            huomautukset.add("Combination of slow speed and lack of close range WEAPONS is a potential liability for this build.");
        } else if (totalDamage > 0 && minWeaponRange > 2) {
            huomautukset.add("The mech has no close range WEAPONS!");
        }

        if (!hasAutoWeapons) {
            huomautukset.add("The mech has no anti-infantry WEAPONS. (All weapons from category AUTO count as anti-infantry.)");
        }

        if (missileWeaponInstalledOnArm) {
            huomautukset.add("Missile WEAPONS installed on arms contribute no additional bonus to weapon rating.");
        }
        if (totalDamage > 0 && totalDamageAmmoWeapons == totalDamage) {
            huomautukset.add("The mech has no energy based WEAPONS installed. (The mech cannot sustain a fight!)");
        }

        if (totalDamage > 0 && ((totalDamageAmmoWeapons * 2) >= totalDamage)) {
            if ((ammoTotal / numberOfAmmoWeapons) <= 5) {
                {
                    huomautukset.add("The mech has very limited AMMO stores! (The mech cannot sustain a fight!)");
                }
            } else if ((ammoTotal / numberOfAmmoWeapons) <= 10) {
                {
                    huomautukset.add("The mech has limited AMMO stores! (The mech cannot sustain a fight!)");
                }
            }
        }
        String armorString = "";
        boolean useampi = false;

        if (!armor_hd) {
            armorString += "head";
            useampi = true;
        }
        if (!armor_ct) {
            if (armorString.length() > 0) {
                armorString += ", ";
            }
            armorString += "center torso";
            useampi = true;
        }
        if (!armor_lt) {
            if (armorString.length() > 0) {
                armorString += ", ";
            }
            armorString += "left torso";
            useampi = true;
        }
        if (!armor_rt) {
            if (armorString.length() > 0) {
                armorString += ", ";
            }
            armorString += "right torso";
            useampi = true;
        }
        if (!armor_ll) {
            if (armorString.length() > 0) {
                armorString += ", ";
            }
            armorString += "left leg";
            useampi = true;
        }
        if (!armor_rl) {
            if (armorString.length() > 0) {
                armorString += ", ";
            }
            armorString += "right leg";
            useampi = true;
        }
        if (!armor_la) {
            if (armorString.length() > 0) {
                armorString += ", ";
            }
            armorString += "left arm";
            useampi = true;
        }
        if (!armor_ra) {
            if (armorString.length() > 0) {
                armorString += ", ";
            }
            armorString += "right arm";
            useampi = true;
        }

//        if (mech.getArmorvalue("h", true)<6) {armorString+="head"; useampi=true;}
//        if (mech.getArmorvalue("ct", true)<6) {if (armorString.length()>0) {armorString+=", ";} armorString+="center torso"; useampi=true;}
//        if (mech.getArmorvalue("lt", true)<6) {if (armorString.length()>0) {armorString+=", ";} armorString+="left torso"; useampi=true;}
//        if (mech.getArmorvalue("rt", true)<6) {if (armorString.length()>0) {armorString+=", ";} armorString+="right torso"; useampi=true;}
//        if (mech.getArmorvalue("ll", true)<6) {if (armorString.length()>0) {armorString+=", ";} armorString+="left leg"; useampi=true;}
//        if (mech.getArmorvalue("rl", true)<6) {if (armorString.length()>0) {armorString+=", ";} armorString+="right leg"; useampi=true;}
//        if (mech.getArmorvalue("la", true)<6) {if (armorString.length()>0) {armorString+=", ";} armorString+="left arm"; useampi=true;}
//        if (mech.getArmorvalue("ra", true)<6) {if (armorString.length()>0) {armorString+=", ";} armorString+="right arm"; useampi=true;}

        //mech.getArmorvalue("h", true)

        armorString = "The mech has no ARMOR PLATING installed: " + armorString + ".";
        if (useampi) {
            huomautukset.add(armorString);
        }

        if (varoitukset.isEmpty() && !huomautukset.isEmpty()) {
            saavutukset.add("With no RED flags detected this mech now counts as OPERATIONAL!");
        }
        if (varoitukset.isEmpty() && huomautukset.isEmpty()) {
            saavutukset.add("With all systems GREEN this mech now counts as OPERATIONAL!");
        }

        if (varoitukset.size() > 2) {
            huomautukset.clear();
        }
        if (varoitukset.size() > 1) {
            saavutukset.clear();
        }




        if (vakavuus == 1) {
            return varoitukset;
        }
        if (vakavuus == 2) {
            return huomautukset;
        }
        if (vakavuus == 3) {
            return saavutukset;
        }
        if (vakavuus == 0) {
            return luonnehdinta;
        }
        return null;
    }

    /**
     * Jos parametriksi annetaan false, stringiin ei liitetä armorrating-arvoa
     * ensimmäiseksi riviksi.
     *
     * @param armorratingRivinKanssa
     * @return
     * @throws NamingException
     * @throws SQLException
     */
    public static ArrayList<String> getArmorratingList(Mech mech, boolean armorratingRivinKanssa) throws NamingException, SQLException {
        int panssari = 0;
        int panssarikerroin = 8;
        int lightMechPenalty = 0;
        int innerstructure = 0;
        ArrayList<String> rivit = new ArrayList<String>();


        if (mech.getPaino() < 40) {
            panssari = 2;
            lightMechPenalty = -1;
        } //panssarikerroin = 18;}
        else if (mech.getPaino() < 60) {
            panssari = 2;
        } //panssarikerroin = 15;}
        else if (mech.getPaino() < 80) {
            panssari = 3;
        } //panssarikerroin = 16;}
        else {
            panssari = 4;
        }
        //panssarikerroin = 17;}
        //int panssari

        int armor_hd = 1;
        int is_hd = 0;
        int armor_la = panssari / 2;
        int is_la = 0;
        int armor_lt = panssari * 2;
        int is_lt = 0;
        int armor_ll = panssari;
        int is_ll = 0;
        int armor_ra = panssari / 2;
        int is_ra = 0;
        int armor_rt = panssari * 2;
        int is_rt = 0;
        int armor_rl = panssari;
        int is_rl = 0;
        int armor_ct = panssari * 3;
        int is_ct = 0;

        panssari = 2 + (panssari * 10);
        innerstructure = mech.getInnerStructure();    // KOKEILLAAN
        is_hd = 1 + (innerstructure / 16);
        is_ct = 3 + (innerstructure / 6);
        is_lt = 2 + (innerstructure / 8);
        is_rt = 2 + (innerstructure / 8);
        is_ll = 1 + (innerstructure / 10);
        is_rl = 1 + (innerstructure / 10);
        is_la = 1 + (innerstructure / 12);
        is_ra = 1 + (innerstructure / 12);

        for (Komponentti komponentti : mech.getMechinKomponentit("ALL")) {
            if (komponentti.getKategoria().equalsIgnoreCase("VARUSTE")) {
                if (komponentti.getVarustetype().equalsIgnoreCase("ARMOR PLATING")) {

                    int muutos = panssarikerroin * komponentti.getMassa();

                    if (komponentti.getMassa() == 1) {
                    }
                    if (komponentti.getMassa() == 2) {
                        muutos += (2 - lightMechPenalty);
                    }
                    if (komponentti.getMassa() == 3) {
                        muutos += (4 - lightMechPenalty);
                    }
                    if (komponentti.getMassa() >= 4) {
                        muutos += (6 - lightMechPenalty);
                    }

//                    int panssarinmassa = komponentti.getMassa();
//                    if (panssarinmassa == 1) {muutos = (muutos*panssarinmassa)+8;}
//                    if (panssarinmassa == 2) {muutos = (muutos*panssarinmassa)+24;}
//                    if (panssarinmassa == 3) {muutos = (muutos*panssarinmassa)+56;}        // 16 vs 56
//                    if (panssarinmassa >= 4) {muutos = (muutos*panssarinmassa)+(20*panssarinmassa);}
//                    
                    if (komponentti.getVarustetier() == 3) {
                    }
                    if (komponentti.getVarustetier() == 2) {
                        muutos += (2 - lightMechPenalty);
                    }
                    if (komponentti.getVarustetier() == 1) {
                        muutos += (4 - lightMechPenalty);
                    }

                    if (komponentti.getSijainti().equalsIgnoreCase("HEAD")) {
                        armor_hd += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("LEFT ARM")) {
                        armor_la += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("LEFT TORSO")) {
                        armor_lt += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("LEFT LEG")) {
                        armor_ll += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("RIGHT ARM")) {
                        armor_ra += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("RIGHT TORSO")) {
                        armor_rt += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("RIGHT LEG")) {
                        armor_rl += muutos;
                    }
                    if (komponentti.getSijainti().equalsIgnoreCase("CENTER TORSO")) {
                        armor_ct += muutos;
                    }
                    //muutos += panssarikerroin;

                    panssari += muutos;
                }
            }

        }


        if (armorratingRivinKanssa) {
            rivit.add("" + panssari);
        }
        rivit.add("<td><b>H</b></td><td>" + armor_hd + "</td><td>" + is_hd + "</td>");
        rivit.add("<td><b>CT</b></td><td>" + armor_ct + "</td><td>" + is_ct + "</td>");
        rivit.add("<td><b>LT/TR</b></td><td>" + armor_lt + "/" + armor_rt + "</td><td>" + is_lt + "/" + is_rt + "</td>");
        rivit.add("<td><b>LA/RA</b></td><td>" + armor_la + "/" + armor_ra + "</td><td>" + is_la + "/" + is_ra + "</td>");
        rivit.add("<td><b>LL/RL</b></td><td>" + armor_ll + "/" + armor_rl + "</td><td>" + is_ll + "/" + is_rl + "</td>");

        return rivit;
    }

    public static int getWeaponrating(Mech mech) throws NamingException, SQLException {
        int rating = 0;
        int sensorLevel = 5;
        int targettingComputerLevel = 5;
        int damageTotal = 0;
        int heatTotal = 0;

        for (Komponentti komponentti : mech.getMechinKomponentit("ALL")) {
            if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                if (komponentti.getVarustetype().contentEquals("SENSORS")) {
                    if (komponentti.getVarustetier() < sensorLevel) {
                        sensorLevel = komponentti.getVarustetier();
                    }
                }
            }
            if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                if (komponentti.getVarustetype().contentEquals("TARGETTING COMPUTER")) {
                    if (komponentti.getVarustetier() < targettingComputerLevel) {
                        targettingComputerLevel = komponentti.getVarustetier();
                    }
                }
            }

            if (komponentti.getKategoria().contentEquals("ASE")) {
                heatTotal += komponentti.getHeat();
                damageTotal += komponentti.getWeapondamage();

                int lisays = (komponentti.getWeapondamage() / 2);
                if (komponentti.getWeapontype().contentEquals("MELEE")) {
                    lisays = lisays / 2;
                }
                if (komponentti.getWeapontype().contentEquals("MISSILE")) {
                    lisays += (komponentti.getWeaponmaxrange() / 2);
                }
                if (komponentti.getWeaponmaxrange() >= 15) {
                    lisays += 3;
                } else if (komponentti.getWeaponmaxrange() >= 12) {
                    lisays += 2;
                } else if (komponentti.getWeaponmaxrange() >= 9) {
                    lisays += 1;
                } else if (komponentti.getWeaponmaxrange() < 5) {
                    lisays -= 1;
                }
                rating += lisays;
            }
        }

        boolean olikoVasemmanKadenActuatori = false;
        int vasemmanKadenDamaget = 0;
        for (Komponentti komponentti : mech.getMechinKomponentit("LEFT ARM")) {
            if (komponentti.getKategoria().contentEquals("ASE") && !komponentti.getWeapontype().contentEquals("MELEE") && !komponentti.getWeapontype().contentEquals("MISSILE")) {
                vasemmanKadenDamaget += (komponentti.getWeapondamage() / 2);
            }
            if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                if (komponentti.getVarustetype().contentEquals("ACTUATORS")) {
                    olikoVasemmanKadenActuatori = true;
                }
            }
        }
        if (olikoVasemmanKadenActuatori) {
            rating += vasemmanKadenDamaget;
        }

        boolean olikoOikeanKadenActuatori = false;
        int oikeanKadenDamaget = 0;
        for (Komponentti komponentti : mech.getMechinKomponentit("RIGHT ARM")) {
            if (komponentti.getKategoria().contentEquals("ASE") && !komponentti.getWeapontype().contentEquals("MELEE") && !komponentti.getWeapontype().contentEquals("MISSILE")) {
                oikeanKadenDamaget += (komponentti.getWeapondamage() / 2);
            }
            if (komponentti.getKategoria().contentEquals("VARUSTE")) {
                if (komponentti.getVarustetype().contentEquals("ACTUATORS")) {
                    olikoOikeanKadenActuatori = true;
                }
            }
        }
        if (olikoOikeanKadenActuatori) {
            rating += oikeanKadenDamaget;
        }

        if (sensorLevel == 5) {
            rating = rating / 5;
        } else {
            rating = ((rating * (18 + (3 - sensorLevel))) / 18);
        }

        if (targettingComputerLevel < 5) //{rating = rating * 2;}
        {
            rating = ((rating * (15 + (3 - targettingComputerLevel))) / 12);
        }

        if ((heatTotal) > (mech.getHeatsinks() + 10)) {
            rating = rating - (rating / 3);
        } else if ((heatTotal) > (mech.getHeatsinks() + 5)) {
            rating = rating - (rating / 4);
        } else if ((heatTotal) > mech.getHeatsinks()) {
            rating = rating - (rating / 6);
        } else if (heatTotal < mech.getHeatsinks()) {
            int heatbonus = (mech.getHeatsinks() - (heatTotal));
            if (heatbonus <= 5) {
                rating += heatbonus;
            } else {
                rating += 5;
            }
        }

        if (MechData.getVaroitukset(mech, 1).size() == 0) {
            rating = (int) (rating * 1.15);
        }

        return rating;
    }

    public static int getDefenserating(Mech mech) throws NamingException, SQLException {
        int rating = 0;
        int gyroTier = 4;
        int cockpitTier = 4;
        int antiMissileTier = 7;
        int activeCamoTier = 9;
        int weaponsWithAmmo = 0;
        int ammoTonnage = 0;
        int ammoStoredInTorso = 0;
        int armorTotal = 0;
        int heatTotal = 0;

        int walkingSpeed = mech.getWalkingspeed();
        int runningSpeed = mech.getRunningspeed();
        int jumpRating = mech.getJumprating();

        for (Komponentti komponentti : mech.getMechinKomponentit("ALL")) {
            if (komponentti.getKategoria().equalsIgnoreCase("VARUSTE")) {
                if (komponentti.getVarustetype().equalsIgnoreCase("GYROSCOPE")) {
                    if (komponentti.getVarustetier() < gyroTier) {
                        gyroTier = komponentti.getVarustetier();
                    }
                }

                if (komponentti.getVarustetype().equalsIgnoreCase("ANTI MISSILE SYSTEM")) {
                    if (komponentti.getVarustetier() < antiMissileTier) {
                        antiMissileTier = komponentti.getVarustetier();
                    }
                }

                if (komponentti.getVarustetype().equalsIgnoreCase("ACTIVE CAMO")) {
                    if (komponentti.getVarustetier() < activeCamoTier) {
                        activeCamoTier = komponentti.getVarustetier();
                    }
                }

                if (komponentti.getVarustetype().equalsIgnoreCase("COCKPIT")) {
                    if (komponentti.getVarustetier() < cockpitTier) {
                        cockpitTier = komponentti.getVarustetier();
                    }
                }
            }


            if (komponentti.getKategoria().equalsIgnoreCase("ASE")) {
                heatTotal += komponentti.getHeat();

                if (komponentti.getWeapontype().equalsIgnoreCase("MISSILE") || komponentti.getWeapontype().equalsIgnoreCase("AUTO") || komponentti.getWeapontype().equalsIgnoreCase("KINETIC")) {
                    weaponsWithAmmo++;
                    //if (komponentti.getMassa()>5) {ammoTonnage += komponentti.getMassa()/5;} else {ammoTonnage++;}

                    if (komponentti.getSijainti().toUpperCase().contains("TORSO")) {
                        ammoStoredInTorso++;
                    }
                }

            }
        }

//        int rating = 0;
//        int gyroTier = 4;
//        int cockpitTier = 4;
//        int antiMissileTier = 7;
//        int activeCamoTier = 9;
//        int weaponsWithAmmo = 0;
//        int ammoStoredInTorso = 0;
//        int armorTotal = 0;
//        int heatTotal = 0;
//        
//        int walkingSpeed = mech.getWalkingspeed();
//        int runningSpeed = mech.getRunningspeed();
//        int jumpRating = mech.getJumprating();

        rating = mech.getArmorrating() / 5;

        if ((heatTotal) > (mech.getHeatsinks() + 10)) {
            rating = rating - (rating / 5);
        } else if ((heatTotal) > (mech.getHeatsinks() + 5)) {
            rating = rating - (rating / 7);
        } else if ((heatTotal) > mech.getHeatsinks()) {
            rating = rating - (rating / 9);
        } else if (heatTotal < mech.getHeatsinks()) {
            int heatbonus = (mech.getHeatsinks() - (heatTotal));
            if (heatbonus <= 5) {
                rating += heatbonus;
            } else {
                rating += 5;
            }
        }

        if (gyroTier > 3) {
            rating = rating / 5;
        } else {
            rating = ((rating * (21 + (3 - gyroTier))) / 20);
        }

        if (cockpitTier > 3) {
            rating = rating / 5;
        } else {
            rating = ((rating * (18 + (3 - cockpitTier))) / 17);
        }

        if (antiMissileTier <= 3) {
            rating = ((rating * (18 + (3 - antiMissileTier))) / 15);
        }

        if (activeCamoTier <= 3 && mech.getMassa() < 40) {
            rating = ((rating * (21 + (3 - activeCamoTier))) / 15);
        } else if (activeCamoTier <= 3 && mech.getMassa() < 60) {
            rating = ((rating * (18 + (3 - activeCamoTier))) / 15);
        } else if (activeCamoTier <= 3) {
            rating = ((rating * (16 + (3 - activeCamoTier))) / 15);
        }

        if (walkingSpeed >= 90) {
            rating = (int) (rating * 1.39);
        } else if (walkingSpeed >= 80) {
            rating = (int) (rating * 1.33);
        } else if (walkingSpeed >= 70) {
            rating = (int) (rating * 1.25);
        } else if (walkingSpeed >= 60) {
            rating = (int) (rating * 1.15);
        } else if (walkingSpeed < 30) {
            rating = (int) (rating * 0.5);
        } else if (walkingSpeed < 40) {
            rating = (int) (rating * 0.75);
        }

        if (runningSpeed > 120) {
            rating += 15;
        } else if (runningSpeed > 100) {
            rating += 10;
        } else if (runningSpeed > 80) {
            rating += 5;
        }

        rating += jumpRating / 10;

        if (mech.getMassa() < 40) {
            rating += 6;
        } else if (mech.getMassa() < 60) {
            rating += 3;
        }



        if (weaponsWithAmmo > 0) {
            int ammoPenalty = (weaponsWithAmmo * ammoStoredInTorso);
            if ((ammoPenalty * 2) > rating) {
                rating = rating / 2;
            } else {
                rating -= ammoPenalty;
            }
        }

        if (MechData.getVaroitukset(mech, 1).size() == 0) {
            rating = (int) (rating * 1.15);
        }

        if (rating < 0) {
            return 0;
        }

        return rating;
    }
}
