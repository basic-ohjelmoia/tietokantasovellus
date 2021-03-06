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
 * Malli Komponentisto-taululle. Komponentisto on liitostaulu, johon
 * tallennetaan merkintä jokaisesta komponentista, joka on asennettu mihinkään
 * mechiin. Samalla mechilla voi ja kuuluu olla useita komponentisto-merkintöjä!
 * Mech on käytännössä komponentistonsa summa
 */
public class Komponentisto extends Komponentti {

    private int komponentisto_id;
    private int battlemech_id;
    private int component_id;
    private String sijainti;

    public Komponentisto(int id, int mech_id, int comp_id, String loc) {
        this.komponentisto_id = id;
        this.battlemech_id = mech_id;
        this.component_id = comp_id;
        this.sijainti = loc;

    }

    @Override
    public int getKomponentisto_id() {
        return this.komponentisto_id;
    }

    public int getBattlemech_id() {
        return this.battlemech_id;
    }

    public int getComponent_id() {
        return this.component_id;
    }

    @Override
    public String getSijainti() {
        return this.sijainti;
    }

    /**
     * Metodi, joka palauttaa uuden "vapaan" id:n numeron. Käytetään uusien
     * komponentisto-merkintöjen luonnin yhteydessä.
     *
     * @return
     * @throws NamingException
     * @throws SQLException
     */
    public static int getUusiID() throws NamingException, SQLException {
        List<Komponentisto> vanhat = getKomponentisto();
        int suurinID = 1;


        for (Komponentisto komponentisto : vanhat) {
            if (komponentisto.getKomponentisto_id() >= suurinID) {
                suurinID = komponentisto.getKomponentisto_id();
            }
        }

        return suurinID + 1;
    }

    /**
     * Silloin tällöin kutsuttava metodi, joka siivoa komponentisto-taulusta
     * virheellisiä merkintöjä, käytännössä sellaisia, jossa mech on poistettu
     * tietokannasta tai joissa komponenttia ei ole jostain syystä asennettu
     * lailliseen sijaintiin.
     *
     * @throws NamingException
     * @throws SQLException
     */
    public static void siivoaKomponentisto() throws NamingException, SQLException {

        String sql = "DELETE FROM komponentisto WHERE sijainti IS NULL OR sijainti = ''";

        //Tietokanta yhteys0 = new Tietokanta();
        Connection yhteys = Tietokanta.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.executeUpdate();


//  kayttajat.add(new Kayttaja(999, "none", "nopw"));

        //Suljetaan kaikki resutuloksetsit:

        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }

    }

    
    /**
     * Palauttaa koko Komponentisto-taulun sisällön olioina. 
     * @return
     * @throws NamingException
     * @throws SQLException 
     */
    public static ArrayList<Komponentisto> getKomponentisto() throws NamingException, SQLException {

        boolean pitaakoSiivota = false;
        String sql = "SELECT komponentisto_id, battlemech_id, component_id, sijainti FROM komponentisto";

        //Tietokanta yhteys0 = new Tietokanta();
        Connection yhteys = Tietokanta.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();

        ArrayList<Komponentisto> komponentisto = new ArrayList<Komponentisto>();
        while (tulokset.next()) {
            //Luodaan tuloksia vastaava olio ja palautetaan olio:
            Komponentisto k = new Komponentisto(tulokset.getInt("komponentisto_id"), tulokset.getInt("battlemech_id"), tulokset.getInt("component_id"), tulokset.getString("sijainti"));
            if (tulokset.getString("sijainti").isEmpty() || tulokset.getString("sijainti").contentEquals("") || tulokset.getString("sijainti") == null) {
                pitaakoSiivota = true;
            }
            komponentisto.add(k);

        }

        try {
            tulokset.close();
        } catch (Exception e) {
        }
        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }


        if (pitaakoSiivota) {
            siivoaKomponentisto();
        }

        return komponentisto;
    }
}
