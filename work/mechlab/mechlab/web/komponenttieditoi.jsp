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
        <li role="presentation" class="active"><a href="komponenttieditoi?id=0">Weapon Lab</a></li>
        <li role="presentation"><a href="komponenttieditoi?id=0&equipment=kylla">Equipment Lab</a></li>
        <li role="presentation"><a href="komponenttieditoi?id=0&reactor=kylla">Reactor Lab</a></li>
        <li role="presentation"><a href="#">Search</a></li>
        </ul>
          <p>
              
         
                 <div class="container">      
      <h1> <c:if test="${komponentti.kategoria != null}"><span class="label label-default">EDIT: <c:out value="${komponentti.nimi}"/></c:if>
          <c:if test="${komponentti.kategoria == null}"><span class="label label-default">CREATE NEW: <c:out value="${komponentti.nimi}"/></c:if>    </h1>
      Select parameters for a new weapon component (admin access only).
     </div>
          <p>
              
         <div class="table-responsive">
             
                          
  <table class="table">
            
      <tr>
          <td>id#</td><td>Name</td><td>Designation</td><td>Weapon Type</td><td>Weigth</td><td>Damage</td><td>Heat</td>
          <td>Max. Range</td><td>Min. Range</td><td>Ammo Count</td><td>Physical Volume</td><td>Locations Allowed</td><td>Unit Cost</td>
      </tr>
    
      
          
             <div class="komponentti"><tr>
                <td>${komponentti.komponentti_id}</td>
                <td><c:out value="${komponentti.nimi}"/></td>
                <td><c:out value="${komponentti.lyhenne}"/></td>
                <td><c:out value="${komponentti.weapontype}"/></td>
                <td><c:out value="${komponentti.massa}"/></td>
                <td><c:out value="${komponentti.weapondamage}"/></td>
                <td><c:out value="${komponentti.heat}"/></td>
                <td><c:out value="${komponentti.weaponmaxrange}"/></td>
                <td><c:out value="${komponentti.weaponminrange}"/></td>
                <td><c:out value="${komponentti.weaponammo}"/></td>
                <td><c:out value="${komponentti.kokoluokka}"/></td>
                <td><c:out value="${komponentti.sijoituspaikka}"/></td>
                <td><c:out value="${komponentti.cost}"/> CR</td>
                <td><a href="komponenttieditoi?id=${komponentti.komponentti_id}">EDIT</a></td>
                    
                    
                 
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
  <input type="radio" name="weaponid" id="weaponid" value="${komponentti.komponentti_id}" checked> id: ${komponentti.komponentti_id}
</label>
                          </div>

                    
  <div class="form-group">
    <label for="Component Name">Weapon name</label>
    <input type="nimi" class="form-control" name="weaponname" id="weaponname" value="${komponentti.nimi}">
  </div>
                
    <div class="form-group">
      <label for="Component Name">Weapon type</label>
<label class="radio-inline">
  <input type="radio" name="weapontype" id="weapontype" value="energy" > ENERGY
</label>
      <label class="radio-inline">
  <input type="radio" name="weapontype" id="weapontype" value="kinetic"> KINETIC
</label>
      <label class="radio-inline">
  <input type="radio" name="weapontype" id="weapontype" value="auto"> AUTO
</label>
      <label class="radio-inline">
  <input type="radio" name="weapontype" id="weapontype" value="missile"> MISSILE
</label>
         <label class="radio-inline">
  <input type="radio" name="weapontype" id="weapontype" value="melee"> MELEE
</label>
         <label class="radio-inline">
  <input type="radio" name="weapontype" id="weapontype" value="keep" checked> Current: <c:out value="${komponentti.weapontype}"/>
</label>
      
    </div>
      
      
  <div class="form-group">
      <label for="Component Name">Component weigth</label>
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
</label><label class="radio-inline">
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
  <input type="radio" name="weight" id="weight" value="keep" checked> Current: <c:out value="${komponentti.massa}"/>
</label>
      
</div>
  
  
   <div class="form-group">
                        <label for="Component Name">Damage</label>

                              <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="1"> 1
</label>
                        <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="2"> 2
</label>
      <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="3"> 3
</label>
      <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="4"> 4
</label>
      <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="5"> 5
</label>
      
                        <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="6"> 6
</label>
      <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="7"> 7
</label>
      <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="8"> 8
</label>
                        <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="9"> 9
</label>
                      <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="10"> 10
</label>
                        <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="11"> 11
</label>
      <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="12"> 12
</label>
                        <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="13"> 13
</label>
      <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="15"> 15
</label>
                        <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="18"> 18
</label>
        <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="20"> 20
</label>
      <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="25"> 25
</label>
<label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="30"> 30
</label>
<label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="100"> 100
</label>
                             <label class="radio-inline">
  <input type="radio" name="damage" id="damage" value="keep" checked> Current: <c:out value="${komponentti.weapondamage}"/>
</label>


      
</div>
  
                    
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
  <input type="radio" name="heat" id="heat" value="9"> 9
</label>
 <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="10"> 10
</label>
                         <label class="radio-inline">
  <input type="radio" name="heat" id="heat" value="11"> 11
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
  
   <div class="form-group">
      <label for="Component Name">Weapon range (max/min)</label>
<label class="radio-inline">
  <input type="radio" name="range" id="range" value="cqb" > Close Quarters (1/1)
</label>
      <label class="radio-inline">
  <input type="radio" name="range" id="range" value="short"> Short (3/1)
</label>
      <label class="radio-inline">
  <input type="radio" name="range" id="range" value="med"> Medium (5/1)
</label>
      <label class="radio-inline">
  <input type="radio" name="range" id="range" value="long"> Long (9/2)
</label>
         <label class="radio-inline">
  <input type="radio" name="range" id="range" value="vlng"> Extended (12/3)
</label>
         <label class="radio-inline">
  <input type="radio" name="range" id="range" value="arty"> Artillery (15/5)
</label>
         <label class="radio-inline">
  <input type="radio" name="range" id="range" value="keep" checked> Current: <c:out value="${komponentti.weaponmaxrange}"/> (max)/<c:out value="${komponentti.weaponminrange}"/> (min)
</label>
      
    </div>
  
     <div class="form-group">
                        <label for="Component Name">Ammo (#)</label>

                              <label class="radio-inline">
  <input type="radio" name="ammo" id="ammo" value="infinite"> INFINITE
</label>
                        <label class="radio-inline">
  <input type="radio" name="ammo" id="ammo" value="single"> SINGLE SHOT (1)
</label>
      <label class="radio-inline">
  <input type="radio" name="ammo" id="ammo" value="limited"> LIMITED (5)
</label>
      <label class="radio-inline">
  <input type="radio" name="ammo" id="ammo" value="standard"> STANDARD (10)
</label>
      <label class="radio-inline">
  <input type="radio" name="ammo" id="ammo" value="extended"> EXTENDED (15)
</label>
   <label class="radio-inline">
  <input type="radio" name="ammo" id="ammo" value="plentiful"> PLENTIFUL (25)
</label>
    <label class="radio-inline">
  <input type="radio" name="ammo" id="ammo" value="keep" checked> Current: <c:out value="${komponentti.weaponammo}"/>
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
  
  

  <button type="submit" class="btn btn-default">DONE</button>
  <a href="komponenttiselaa">CANCEL</a>
</form>
  
</div>
      
</t:pohja>
      