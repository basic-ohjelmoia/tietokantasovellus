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
        <li role="presentation"><a href="komponenttieditoi?id=0&equipment=kylla">Equipment Lab</a></li>
        <li role="presentation" class="active"><a href="komponenttieditoi?id=0&reactor=kylla">Reactor Lab</a></li>
        <li role="presentation"><a href="#">Search</a></li>
        </ul>
          <p>
              
         
                 <div class="container">      
      <h1> <c:if test="${reaktori.kategoria != null}"><span class="label label-default">EDIT: <c:out value="${reaktori.nimi}"/></c:if>
          <c:if test="${reaktori.kategoria == null}"><span class="label label-default">CREATE NEW: <c:out value="${reaktori.nimi}"/></c:if>    </h1>
      Select parameters for a reactor (admin access only).
     </div>
          <p>
              
         <div class="table-responsive">
             
                          
  <table class="table">
            
    
     
        <tr>
          <td>id#</td><td>Name</td><td>Designation</td><td>Cooling Factor</td><td>Power Generation</td>
          <td>Weigth</td><td>Power/Weight Ratio</td><td>Physical Volume</td><td>Locations Allowed</td><td>Unit Cost</td><td>Number of Installs</td>
      </tr>
      
      
          
             <div class="equipment"><tr>
                <td>${reaktori.reaktori_id}</td>
                <td><c:out value="${reaktori.nimi}"/></td>
                <td><c:out value="${reaktori.lyhenne}"/></td>
                <td><c:out value="${reaktori.cooling}"/></td>
                <td><c:out value="${reaktori.teho}"/></td>
                <td><c:out value="${reaktori.massa}"/></td>
                <td><c:out value="${reaktori.efficiency}"/></td>
                <td><c:out value="${reaktori.kokoluokka}"/></td>
                <td>CENTER TORSO</td>
                <td><c:out value="${reaktori.cost}"/> CR</td>
                <td><c:out value="${komponentti.asennuskerrat}"/></td>
                    
                    
                 
                 </tr></div>
      
  </table>
                
       
                <%--       <form action="komponenttieditoi" method="POST">
                        id <input type="text" name="weaponid" value="${reaktori.komponentti_id}" /> 
weapon name <input type="text" name="weaponname"  value="default" />
type: <input type="text" name="weapontype"  value="default"/>
<button type="submit">MUUTA</button>
</form>
                
                <p> --%>
                
     <form action="komponenttieditoi" method="POST" role="form">
                
          <div class="form-group">
                      <label class="radio-inline">
  <%--<input type="radio" name="weaponid" id="weaponid" value="${komponentti.komponentti_id}" checked> id: ${komponentti.komponentti_id}--%>
  <input type="hidden" name="reactorid" value="${reaktori.reaktori_id}"/>
</label>
                          </div>

                    
  <div class="form-group">
    <label for="Component Name">Reactor name: ${komponentti.nimi}</label>
    <input type="hidden" name="equipmentname" id="equipmentname" value="${reaktori.nimi}">
  </div>
  
         
<!--                    <div class="form-group">
                      <label class="radio-inline">
  <input type="radio" name="reactorid" id="reactorid" value="${reaktori.reaktori_id}" checked> id: ${reaktori.reaktori_id}
</label>
                          </div>

                    
  <div class="form-group">
    <label for="Component Name">Reactor name</label>
    <input type="nimi" class="form-control" name="equipmentname" id="equipmentname" value="${reaktori.nimi}"><br>
    Leave empty to generate a common name.<br>
  </div>-->
  
   <div class="form-group">
                        <label for="Component Name">Cooling Factor</label>

                              <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="5"> 5
</label>
                        <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="6"> 6
</label>
      <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="7"> 7
</label>
      <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="8"> 8
</label>
      <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="9"> 9
</label>
      <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="10"> 10
</label><label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="11"> 11
</label>
      <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="12"> 12
</label>
      <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="13"> 13
</label>
 <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="14"> 14
</label>
      <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="15"> 15
</label>
                        
      <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="16"> 16
</label>
      <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="18"> 18
</label>
                            <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="20"> 20
</label>
      
                             <label class="radio-inline">
  <input type="radio" name="cooling" id="cooling" value="keep" checked> Current: <c:out value="${reaktori.cooling}"/>
</label>
</div>
  <%--Note: Some equipment types will automatically default to cooling rating of 0 (e.g. armor plating, actuators, cooling sinks).<br>--%>
   
   <div class="form-group">
                        <label for="Component Name">Power Output</label>

                              <label class="radio-inline">
  <input type="radio" name="power" id="power" value="80"> 80
</label>
                        <label class="radio-inline">
  <input type="radio" name="power" id="power" value="100"> 100
</label>
      <label class="radio-inline">
  <input type="radio" name="power" id="power" value="120"> 120
</label>
      <label class="radio-inline">
  <input type="radio" name="power" id="power" value="140"> 140
</label>
      <label class="radio-inline">
  <input type="radio" name="power" id="power" value="160"> 160
</label>
      <label class="radio-inline">
  <input type="radio" name="power" id="power" value="180"> 180
</label><label class="radio-inline">
  <input type="radio" name="power" id="power" value="200"> 200
</label>
      <label class="radio-inline">
  <input type="radio" name="power" id="power" value="225"> 225
</label>
      <label class="radio-inline">
  <input type="radio" name="power" id="power" value="250"> 250
</label>
 <label class="radio-inline">
  <input type="radio" name="power" id="power" value="275"> 275
</label>
      <label class="radio-inline">
  <input type="radio" name="power" id="power" value="300"> 300
</label>
                        
      <label class="radio-inline">
  <input type="radio" name="power" id="power" value="325"> 325
</label>
      <label class="radio-inline">
  <input type="radio" name="power" id="power" value="350"> 350
</label>
                              <label class="radio-inline">
  <input type="radio" name="power" id="power" value="375"> 375
</label>
                              <label class="radio-inline">
  <input type="radio" name="power" id="power" value="400"> 400
</label>
                                                 <label class="radio-inline">
  <input type="radio" name="power" id="power" value="425"> 425
</label>
                              <label class="radio-inline">
  <input type="radio" name="power" id="power" value="450"> 450
</label>
                                                 <label class="radio-inline">
  <input type="radio" name="power" id="power" value="475"> 475
</label>
                                         <label class="radio-inline">
  <input type="radio" name="power" id="power" value="500"> 550
</label>
      
      
                             <label class="radio-inline">
  <input type="radio" name="power" id="power" value="keep" checked> Current: <c:out value="${reaktori.teho}"/>
</label>
</div>
  <%--Note: Some equipment types will automatically default to cooling rating of 0 (e.g. armor plating, actuators, cooling sinks).<br>--%>
  
  
  <div class="form-group">
      <label for="Component Name">Reactor weigth</label>
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
  <input type="radio" name="weight" id="weight" value="9"> 9 t
</label>
<label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="10"> 10 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="11"> 11 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="12"> 12 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="13"> 13 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="14"> 14 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="15"> 15 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="16"> 16 t
</label>
          <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="18"> 18 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="20"> 20 t
</label>
      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="25"> 25 t
</label>

      <label class="radio-inline">
  <input type="radio" name="weight" id="weight" value="keep" checked> Current: <c:out value="${reaktori.massa}"/>
</label>
      
</div>
  

  <button type="submit" class="btn btn-default">DONE</button>
  <a href="komponenttiselaa">CANCEL</a>
</form>
  
</div>
      
</t:pohja>
      