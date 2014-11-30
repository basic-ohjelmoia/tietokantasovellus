<%-- 
    Document   : sivu
    Created on : 14.11.2014, 0:29:37
    Author     : mikromafia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Mechlab!">
    <h1>Mechlab!</h1>
    
  <ul class="nav nav-pills" role="tablist">
        <li role="presentation" class="active"><a href="#">Listaa kaikki</a></li>
        <li role="presentation"><a href="#">Listaa omat</a></li>
        <li role="presentation"><a href="#">Haku</a></li>
        </ul>
          <p>
                 <div class="container">      
      <h1>BattleMech-tietokanta</h1>
      Täältä löydät kaikki MechLabiin tallennetut BattleMechit.
     </div>
          <p>
              <c:if test="${ilmoitus != null}">
                <div class="alert alert-danger">${ilmoitus}</div>
            </c:if>  
              
         <div class="table-responsive">
             
             <h2>Oikeat Mechit Tietokannassa</h2>       
             <table class="table">
            
      <tr>
          <td>id#</td><td>Name</td><td>Weight</td><td>Weapon Rating</td><td>Armor Rating</td><td>Heat Sinks</td>
          <td>Walk (Run) Speed</td><td>Jump Rating</td><td>Owner</td><td>Unit Cost</td><td>Edit</td><td>Delete</td>
      </tr>
      
      <c:forEach var="mech" items="${mechit}">
          
             <div class="mech"><tr>
                <td>${mech.mech_id}</td>
                <td><c:out value="${mech.nimi}"/></td>
                <td><c:out value="${mech.nettopaino}"/>t / <c:out value="${mech.paino}"/>t</td>
                <td><c:out value="${mech.weaponrating}"/></td>
                <td><c:out value="${mech.armorrating}"/></td>
                <td><c:out value="${mech.heatsinks}"/></td>
                <td><c:out value="${mech.walkingspeed}"/> km/h (<c:out value="${mech.runningspeed}"/> km/h)</td>
                <td><c:out value="${mech.jumprating}"/> m</td>
                <td><c:out value="${mech.ownername}"/></td>
                <td><c:out value="${mech.coststring}"/> CR</td>
                <td><a href="mechedit?id=${mech.mech_id}">EDIT</a></td>
                <td><a href="mechluouusi?poista=${mech.mech_id}">DELETE</a></td>
                    
                    
                 
                 </tr></div>
      </c:forEach>
  </table>
             
             <h2>Static List</h2>       
  <table class="table">
      <tr>
          <td>#</td><td>Name</td><td>Weight</td><td>Max.Speed</td><td>Jump Jets</td><td>Armor Rating</td><td>Weapon Rating</td><td>Heat Sinks</td><td>Cost</td><td>Owner</td><td>...</td><td>,,,</td>
      </tr>
      <tr>
          <td>1</td><td>Locust</td><td>20 t</td><td>110 kph</td><td>7</td><td>48</td><td>8</td><td>10</td><td>15300 CR</td><td>Horst</td><td>INFO</td><td>DEL</td>
      </tr>
      <tr>
          <td>2</td><td>Locust II</td><td>25 t</td><td>90 kph</td><td>6</td><td>72</td><td>11</td><td>10</td><td>21500 CR</td><td>Horst</td><td>INFO</td><td>DEL</td>
      </tr>
      <tr>
          <td>3</td><td>Warhammer</td><td>60 t</td><td>80 kph</td><td>4</td><td>168</td><td>33</td><td>15</td><td>73000 CR</td><td>Dieter</td><td>INFO</td><td>DEL</td>
      </tr>
      <tr>
          <td>4</td><td>Rifleman</td><td>65 t</td><td>60 kph</td><td>0</td><td>192</td><td>25</td><td>12</td><td>80000 CR</td><td>Berner</td><td>INFO</td><td>DEL</td>
      </tr>
      <tr>
          <td>5</td><td><a href="main_1.html">Archer</a></td><td>70 t</td><td>60 kph</td><td>4</td><td>208</td><td>45</td><td>18</td><td>95000 CR</td><td>Berner</td><td>INFO</td><td>DEL</td>
      </tr>
      <tr>
          <td>6</td><td>Marauder</td><td>75 t</td><td>60 kph</td><td>0</td><td>240</td><td>40</td><td>20</td><td>155000 CR</td><td>Dieter</td><td>INFO</td><td>DEL</td>
      </tr>
      <tr>
          <td>7</td><td>Atlas</td><td>100 t</td><td>50 kph</td><td>0</td><td>304</td><td>55</td><td>30</td><td>255500 CR</td><td>Dieter</td><td>INFO</td><td>DEL</td>
      </tr>
      <tr>
          <td>8</td><td>Atlas Turbo</td><td>100 t</td><td>80 kph</td><td>0</td><td>248</td><td>42</td><td>20</td><td>189850 CR</td><td>Dieter</td><td>INFO</td><td>DEL</td>
      </tr>
      <tr>
          <td>9</td><td>Atlas C</td><td>100 t</td><td>30 kph</td><td>0</td><td>304</td><td>55</td><td>35</td><td>232000 CR</td><td>Dieter</td><td>INFO</td><td>DEL</td>
      </tr>
      <tr>
          <td>9</td><td>Atlas D</td><td>100 t</td><td>30 kph</td><td>0</td><td>356</td><td>65</td><td>25</td><td>320000 CR</td><td>Dieter</td><td>INFO</td><td>DEL</td>
      </tr>
  </table>
</div>
      <center>
          <div class="container">     
                 <nav>
                <ul class="pagination">
                    <li class="disabled"><a href="#">&laquo;</a></li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&raquo;</a></li>
                </ul>
                </nav>
          </div>
      </center>
</t:pohja>
      