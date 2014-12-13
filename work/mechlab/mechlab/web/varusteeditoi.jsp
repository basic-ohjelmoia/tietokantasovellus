<%-- 
    Document   : sivu
    Created on : 14.11.2014, 0:29:37
    Author     : mikromafia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Mechlab! Komponentti!">
    
    
    <h1>Mechlab!</h1>
    
    <ul class="nav nav-pills" role="tablist">
        <li role="presentation"><a href="komponenttiselaa">List Weapons</a></li>
        <li role="presentation"><a href="komponenttieditoi?id=0">Weapon Lab</a></li>
        <li role="presentation" class="active"><a href="komponenttieditoi?id=0&equipment=kylla">Equipment Lab</a></li>
        <li role="presentation"><a href="komponenttieditoi?id=0&reactor=kylla">Reactor Lab</a></li>
        <li role="presentation"><a href="#">Search</a></li>
        </ul>
          <p>
              
         
                 <div class="container">      
      <h1> <c:if test="${komponentti.kategoria != null}"><span class="label label-default">EDIT: <c:out value="${komponentti.nimi}"/></c:if>
          <c:if test="${komponentti.kategoria == null}"><span class="label label-default">CREATE NEW: <c:out value="${komponentti.nimi}"/></c:if>    </h1>
      Select parameters for a new non-weapon component (equipment) (admin access only).
     </div>
          <p>
              
         <div class="table-responsive">
             
                          
  <table class="table">
            
    
     
        <tr>
          <td>id#</td><td>Name</td><td>Designation</td><td>Equipment Type</td><td>Equipment Tier</td><td>Active/Passive</td><td>Heat</td>
          <td>Weigth</td><td>Physical Volume</td><td>Locations Allowed</td><td>Unit Cost</td><td>Number of Installs</td>
      </tr>
      
      
          
             <div class="equipment"><tr>
                <td>${komponentti.komponentti_id}</td>
                <td><c:out value="${komponentti.nimi}"/></td>
                <td><c:out value="${komponentti.lyhenne}"/></td>
                <td><c:out value="${komponentti.varustetype}"/></td>
                <td><c:out value="${komponentti.varustetier}"/></td>
                <td><c:out value="${komponentti.varusteactivity}"/></td>
                <td><c:out value="${komponentti.heat}"/></td>
                <td><c:out value="${komponentti.massa}"/></td>
                <td><c:out value="${komponentti.kokoluokka}"/></td>
                <td><c:out value="${komponentti.sijoituspaikka}"/></td>
                <td><c:out value="${komponentti.cost}"/> CR</td>
                <td><c:out value="${komponentti.asennuskerrat}"/></td>
                    
                    
                 
                 </tr></div>
      
  </table>
                
       
                <%--       <form action="komponenttieditoi" method="POST">
                        id <input type="text" name="weaponid" value="${komponentti.komponentti_id}" /> 
weapon name <input type="text" name="weaponname"  value="default" />
type: <input type="text" name="weapontype"  value="default"/>
<button type="submit">MUUTA</button>
</form>
                
                <p> --%>
                
     <form action="komponenttieditoi" method="POST" role="form">
                
          <div class="form-group">
                      <label class="radio-inline">
  <%--<input type="radio" name="weaponid" id="weaponid" value="${komponentti.komponentti_id}" checked> id: ${komponentti.komponentti_id}--%>
  <input type="hidden" name="equipmentid" value="${komponentti.komponentti_id}"/>
</label>
                          </div>

                    
  <div class="form-group">
    <label for="Component Name">Equipment name: ${komponentti.nimi}</label>
    <input type="hidden" name="equipmentname" id="equipmentname" value="${komponentti.nimi}">
  </div>
         
<!--                    <div class="form-group">
                      <label class="radio-inline">
  <input type="radio" name="equipmentid" id="equipmentid" value="${komponentti.komponentti_id}" checked> id: ${komponentti.komponentti_id}
</label>
                          </div>

                    
  <div class="form-group">
    <label for="Component Name">Equipment name</label>
    <input type="nimi" class="form-control" name="equipmentname" id="equipmentname" value="${komponentti.nimi}"><br>
    Leave empty to generate a common name.<br>
  </div>-->
  
    <div class="form-group">
      <label for="Component Name">Equipment type</label>
<label class="radio-inline">
  <input type="radio" name="equipmenttype" id="equipmenttype" value="HEAT SINK" > Heat Sink
</label>
      <label class="radio-inline">
  <input type="radio" name="equipmenttype" id="equipmenttype" value="TARGETTING COMPUTER"> Targetting Computer
</label>
      <label class="radio-inline">
  <input type="radio" name="equipmenttype" id="equipmenttype" value="JUMP JET"> Jump Jet
</label>
      <label class="radio-inline">
  <input type="radio" name="equipmenttype" id="equipmenttype" value="ANTI MISSILE SYSTEM"> Anti Missile System
</label>
         <label class="radio-inline">
  <input type="radio" name="equipmenttype" id="equipmenttype" value="ARMOR PLATING"> Armor Plating
</label>
       <label class="radio-inline">
  <input type="radio" name="equipmenttype" id="equipmenttype" value="ACTIVE CAMO"> Active Camo
</label>
       <label class="radio-inline">
  <input type="radio" name="equipmenttype" id="equipmenttype" value="GYROSCOPE"> Gyroscope
</label>
       <label class="radio-inline">
  <input type="radio" name="equipmenttype" id="equipmenttype" value="COCKPIT"> Cockpit
</label>
            <label class="radio-inline">
  <input type="radio" name="equipmenttype" id="equipmenttype" value="SENSORS"> Sensors
</label>
            <label class="radio-inline">
  <input type="radio" name="equipmenttype" id="equipmenttype" value="ACTUATORS"> Actuators
</label>
         <label class="radio-inline">
  <input type="radio" name="equipmenttype" id="equipmenttype" value="keep" checked> Current: <c:out value="${komponentti.varustetype}"/>
</label>
          </div>


 <div class="form-group">
                        <label for="Component Name">Equipment Tier</label>

                              <label class="radio-inline">
  <input type="radio" name="equipmenttier" id="equipmenttier" value="1"> 1 (best)
</label>
                        <label class="radio-inline">
  <input type="radio" name="equipmenttier" id="equipmenttier" value="2"> 2
</label>
      <label class="radio-inline">
  <input type="radio" name="equipmenttier" id="equipmenttier" value="3"> 3 (worst)
</label>
                             <label class="radio-inline">
  <input type="radio" name="equipmenttier" id="equipmenttier" value="keep" checked> Current: <c:out value="${komponentti.varustetier}"/>
</label>
</div>
<%--Note: Tier is used to determine the general effectiveness rating of most equipment types (e.g. Tier I armor plating is tougher than Tier III armor plating).<br>--%>
      

<div class="form-group">
                        <label for="Component Name">Equipment Activity</label>

                              <label class="radio-inline">
  <input type="radio" name="equipmentactivity" id="equipmentactivity" value="1"> Active ("activate on demand")</label>
                        <label class="radio-inline">
  <input type="radio" name="equipmentactivity" id="equipmentactivity" value="2"> Passive ("always on")</label>
  
                             <label class="radio-inline">
  <input type="radio" name="equipmentactivity" id="equipmentactivity" value="keep" checked> Current: <c:out value="${komponentti.varusteactivity}"/>
</label>
</div>
<%--Note: PASSIVE equipment will generate heat each turn. Most equipment types (e.g. heat sinks, armor plating, sensors) will automatically default to PASSIVE.<br>--%>

   <div class="form-group">
                        <label for="Component Name">Heat generated</label>

                              <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="0"> 0
</label>
                        <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="1"> 1
</label>
      <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="2"> 2
</label>
      <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="3"> 3
</label>
      <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="4"> 4
</label>
      <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="5"> 5
</label><label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="6"> 6
</label>
      <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="7"> 7
</label>
      <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="8"> 8
</label>
 <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="10"> 10
</label>
      <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="12"> 12
</label>
      <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="14"> 14
</label>
      <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="16"> 16
</label>
      <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="18"> 18
</label>
      <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="20"> 20
</label>
                             <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="keep" checked> Current: <c:out value="${komponentti.heat}"/>
</label>
</div>
  <%--Note: Some equipment types will automatically default to heat rating of 0 (e.g. armor plating, actuators, heat sinks).<br>--%>
      
  <div class="form-group">
      <label for="Component Name">Equipment weigth</label>
<label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="1"> 1 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="2"> 2 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="3"> 3 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="4"> 4 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="5"> 5 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="6"> 6 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="7"> 7 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="8"> 8 t
</label>
<label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="10"> 10 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="12"> 12 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="15"> 15 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="20"> 20 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="25"> 25 t
</label>

      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="keep" checked> Current: <c:out value="${komponentti.massa}"/>
</label>
      
</div>
  
  
  
                   
    <div class="form-group">
      <label for="Component Name">Physical volume</label>
<label class="radio-inline">
  <input type="radio" name="volume" id="volume" value="Small" > SMALL
</label>
      <label class="radio-inline">
  <input type="radio" name="volume" id="volume" value="Medium"> MEDIUM
</label>
      <label class="radio-inline">
  <input type="radio" name="volume" id="volume" value="Large"> LARGE
</label>
      <label class="radio-inline">
  <input type="radio" name="volume" id="volume" value="XL"> XL
</label>
  
         <label class="radio-inline">
  <input type="radio" name="volume" id="volume" value="keep" checked> Current: <c:out value="${komponentti.kokoluokka}"/>
</label>
          </div>
  
     <div class="form-group">
      <label for="Component Name">Locations allowed</label>
<label class="radio-inline">
  <input type="radio" name="location" id="location" value="ALL" > ALL
</label>
      <label class="radio-inline">
  <input type="radio" name="location" id="location" value="HEAD"> HEAD
</label>
      <label class="radio-inline">
  <input type="radio" name="location" id="location" value="ANY_TORSO"> ANY_TORSO
</label>
      <label class="radio-inline">
  <input type="radio" name="location" id="location" value="ARMS"> ARMS
</label>
      <label class="radio-inline">
  <input type="radio" name="location" id="location" value="ARMS_TORSO"> ARMS_TORSO
</label>
          <label class="radio-inline">
  <input type="radio" name="location" id="location" value="HEAD_TORSO"> HEAD_TORSO
</label>
    <label class="radio-inline">
  <input type="radio" name="location" id="location" value="NOT_LEGS"> NOT_LEGS
</label>
          <label class="radio-inline">
  <input type="radio" name="location" id="location" value="ARMS_LEGS"> ARMS_LEGS
</label>
         <label class="radio-inline">
  <input type="radio" name="location" id="location" value="keep" checked> Current: <c:out value="${komponentti.sijoituspaikka}"/>
</label>
    </div>
  <%--<br>Note: Some equipment types will automatically default to certain location allowances (e.g. cockpit must be located in head).</br>--%>
  
  

  <button type="submit" class="btn btn-default">DONE</button>
  <a href="komponenttiselaa">CANCEL</a>
</form>
  
</div>
      
</t:pohja>
      