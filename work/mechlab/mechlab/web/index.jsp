<%-- 
    Document   : index
    Created on : 2.11.2014, 2:56:23
    Author     : mikromafia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Tietokantasovellus</title>
    <meta charset="utf-8" />
    <style>
      h1 { font-size: 1.4em; }
      h2 { font-size: 1.1em; }
    </style>
  </head>
  <body>
      
      
      
    <h1>MechLab-tietokantasovelluksen esittelysivu</h1>
    <p>Yleisiä linkkejä</p>
    <ul>
      <!-- Korvaa ohessa olevat sanat GITHUBTUNNUS ja 
           REPOSITORIO omilla tiedoillasi: -->
      <li><a href="https://github.com/basic-ohjelmoia/tietokantasovellus">Työn repositorio</a></li>
      <li><a href="https://github.com/basic-ohjelmoia/tietokantasovellus/blob/master/doc/dokumentaatio.pdf?raw=true">Työn dokumentaatio</a> (päivitetty 23.11.2014)</li>

      <!-- Viikolla 2 voit laittaa kommentoidut linkitkin sivuille. 
           Ota kommenttimerkit pois sen kielen ympäriltä, jota käytät -->

      <!-- Linkit Java-kielelle.
           Korvaa sana cstunnus omalla tktl-tunnuksellasi ja sana 
           Tietokantasovellus tietokantasovelluksesi usersille 
           pystytetyllä nimellä: 
      -->
    </ul>
      <p>
  <u> Uutta viikolla 2:</u><br>
      </p>
      <ul>
      <li><a href="http://t-tuho.users.cs.helsinki.fi/ConnectionTest">Connectiontest-ohjelma</a></li>
      <li><a href="http://t-tuho.users.cs.helsinki.fi/mechlab/html-demo/main.html">HTML-demosivujen etusivu</a></li>
      <li><a href="http://t-tuho.users.cs.helsinki.fi/mechlab/html-demo/main_1.html">HTML-demosivu 2 </a></li>
      <li><a href="http://t-tuho.users.cs.helsinki.fi/mechlab/html-demo/main_1_1.html">HTML-demosivu 3 </a></li>
      <li><a href="http://t-tuho.users.cs.helsinki.fi/mechlab/html-demo/main_1_1_2.html">HTML-demosivu 4 </a></li>
      <li><a href="http://t-tuho.users.cs.helsinki.fi/mechlab/Kayttaja">Kayttaja-servlet</a> (servletin toimintaa muutettu, joten linkki on obsolete)</li>
      </ul>
      <!-- Linkit PHP-kielelle.
           Korvaa sana cstunnus omalla tktl-tunnuksellasi ja sana 
           Tietokantasovellus sen hakemiston nimellä, missä 
           sovelluksesi usersin htdocs-kansiossa sijaitsee:
      -->
      <!--
      <li><a href="http://cstunnus.users.cs.helsinki.fi/connectiontest.php">Connectiontest-ohjelma</a></li>
      <li><a href="http://cstunnus.users.cs.helsinki.fi/Tietokantasovellus/html-demo/index.html">HTML-demosivujen etusivu</a></li>
      <li><a href="http://cstunnus.users.cs.helsinki.fi/Tietokantasovellus/listaustesti.php">Ensimmäinen oma tietokantalistaus</a></li>
      -->
      
      <!-- Viikolla 3 laita alla kommentoitu linkki osoittamaan omaan kirjautumissivuusi. -->
      
       <p>
  <u>Uutta viikolla 3:</u><br>
      </p>
      <ul>
      <li><a href="http://t-tuho.users.cs.helsinki.fi/mechlab/login">Sovelluksen kirjautumissivu</a></li>
      </ul>
      <p>
          Onnistunut sisäänkirjautuminen vie <a href="http://t-tuho.users.cs.helsinki.fi/mechlab/mechselaa">mechselaa-näkymään</a>, jossa ei sinänsä ole enempää toiminnallisuutta kuin uloskirjautuminen.<br>
      </p>
          Toimivia <b>käyttäjätunnus</b> : salasana -yhdistelmiä:<br>
      <ul>
          <li><b>Testi Testaaja</b> : 12345</li>
          <li><b>abc</b> : 123</li>
          <li><b>admin</b> : admin</li>
      </ul>
      </p>
      <p>
          Huomionarvoisia yksityiskohtia:<br>
      <ul><li> Navikko muuttuu sen mukaan onko käyttäjä kirjautuneena vai ei ("kirjaudu" vs "poistu"). Kirjautunutta käyttäjää tervehditään nimeltä ja ilmoitetaan vierailukerta (vierailukerrat tallennetaan Postgresql:n kayttaja-tauluun).</li>
          <li> Jos käyttäjällä on admin-status (kuten käyttäjätunnuksella "admin" on), navikkoon lisätään ylimääräinen ylläpito-painike.</li>
          <li> Login-sivu osaa reagoida, jos käyttäjä on juuri kirjautunut ulos ("Olet kirjautunut ulos Mechlabista!").</li>
          <li><a href="http://t-tuho.users.cs.helsinki.fi/mechlab/mechselaa">Mechselaa</a>-sivulle ei pääse sisäänkirjautumatta. Jos sivulle pyrkii suoraan ilman kirjautumista, sovellus palauttaa käyttäjän automaattisesti login-sivulle ja muistuttaa sisäänkirjautumisen pakollisuudesta.</li>
      </ul>          

    
        <p>
  <u>Uutta viikolla 4:</u><br>
      </p>
      <ul>
      <li><a href="http://t-tuho.users.cs.helsinki.fi/mechlab/login">Sovelluksen kirjautumissivu</a></li>
      <li><a href="https://github.com/basic-ohjelmoia/tietokantasovellus">Työn repositorio</a></li>
      <li><a href="https://github.com/basic-ohjelmoia/tietokantasovellus/blob/master/doc/dokumentaatio.pdf?raw=true">Työn dokumentaatio</a> (päivitetty 23.11.2014)</li>
      </ul>
      <p>
          ADMIN.Ylläpito on projektin ensimmäinen toimiva selaa/luo/muokkaa/poista (CRUD) -näkymä. Siellä voi syöttää komponentteja (aseita) tietokantaan. Varuste-tyyppisten komponenttien lisäämisen on määrä tapahtua samalla sivulla, mutta sitä ei ole vielä implementoitu loppuun asti. Tietokannan kannalta aseet ja varusteet tallennetaan samaan Komponentti-tauluun (niistä vain luetaan vähän eri parametreja).<br>
      </p>
         Edellisviikolta tutut <b>käyttäjätunnus</b> : salasana -yhdistelmät toimivat yhä. Huomaa, että ainoastaan admin-tunnuksella pääsee edellä mainittuun Admin.Ylläpito-näkymään (komponentti-CRUD).<br>
      <ul>
          <li><b>Testi Testaaja</b> : 12345</li>
          <li><b>abc</b> : 123</li>
          <li><b>admin</b> : admin</li>
      </ul>
      </p>
      <p>
          Huomionarvoisia yksityiskohtia:<br>
      <ul><li> Komponenttien luominen ja editointi tapahtuu valmiiksi rajattujen vaihtoehtojen puitteissa, joten käyttäjän ei ole mahdollista antaa "vääriä" syötteitä. Nimi-kentän implementaatio on tosin vielä sikäli keskeneräinen, että SQL-injektiot lienevät vielä mahdollisia (aika loppui kesken...).</li>
          <li> Jos käyttäjä jättää nimen syöttämättä komponentilleen, komponentti saa nimekseen designaatio-tunnuksensa. Designaatiota ei "arvota" randomisti, vaan se perustuu komponentin parametreihin. Taulua ei siis tarvitse täyttää "Unnamed Componeteilla."</li>
          <li> Komponentteja luodessa/editoidessa käyttäjän ei tarvitse klikata kaikkia parametreja läpi, vaan ainostaan niitä, joita on muuttamassa. Esimerkiksi jos haluaa muuttaa aseen vahinkoarvoa, pelkkä uuden vahinkoarvon klikkaaminen ja tallentaminen riittää.</li>
          <li> Uusi komponentti luodaan Admin.Ylläpito-näkymän Weapon Lab -alasivulla (haku-toiminto ei vielä tee mitään).</li>
          <li> Sivusto käyttää tällä hetkellä englantia ja suomea sekaisin, mutta tarkoitus on muokata kaikki näkymät englanninkielisiksi.</li>
          <li> Admin.Ylläpito/Komponentti-näkymän sivutus on implementoimatta (sivunumerointi on placeholder), tosin en ole täysin vakuuttunut, että kyseinen näkymä edes tarvitsee sivutusta (lopputuotteessa "oikea" komponenttimäärä jäänee muutamaan kymmeneen).</li>
          <li> Login-ruudun jälkeinen MechSelaa-näkymän Mech-listaus on yhä pelkkä placeholder. Mech-näkymää ei liene järkevää toteuttaa ennen kuin kaikki rakennuspalikat (komponentit, reaktorit jne.) on implementoitu.</li>
      </ul>          

      
    <!--
    <h2>Kirjautumistunnuksia testausta varten</h2>

    <p>Laita tähän viikolla 3 lista tunnuksista, joilla sovellusta voi testata</p>
    <p>Esim:<br />
    Pääkäyttäjän tunnus ja salasana:<br /> admin, kissakala
    </p>
    -->
  </body>
</html>
