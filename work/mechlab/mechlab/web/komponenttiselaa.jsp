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
        <li role="presentation" class ="active"><a href="komponenttiselaa">List Weapons</a></li>
        <li role="presentation"><a href="komponenttieditoi?id=0">Weapon Lab</a></li>
        <li role="presentation"><a href="komponenttieditoi?id=0&equipment=kylla">Equipment Lab</a></li>
        <li role="presentation"><a href="komponenttieditoi?id=0&reactor=kylla">Reactor Lab</a></li>
        <li role="presentation"><a href="#">Search</a></li>
        </ul>
          <p>
                 <div class="container">      
      <h1>Komponentit</h1>
      Täältä löydät kaikki MechLabiin syötetyt komponentit (admin-sivu).
     </div>
          <p>
                 <c:if test="${ilmoitus != null}">
                <div class="alert alert-danger">${ilmoitus}</div>
            </c:if>  
              
         <div class="table-responsive">
             
             <c:if test="${minimoiweapons == null}">
               <h2>Weapons</h2>           
  <table class="table">
            
      <tr>
          <td>id#</td><td>Name</td><td>Designation</td><td>Weapon Type</td><td>Weigth</td><td>Damage</td><td>Heat</td>
          <td>Max. Range</td><td>Min. Range</td><td>Ammo Count</td><td>Physical Volume</td><td>Locations Allowed</td><td>Unit Cost</td><td>Edit</td><td>Delete</td>
      </tr>
      
      <c:forEach var="komponentti" items="${asekomponentit}">
          
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
                <td><a href="komponenttipoista?id=${komponentti.komponentti_id}">DELETE</a></td>
                    
                    
                 
                 </tr></div>
      </c:forEach>
  </table>
      </c:if>  
               
             <h2>Equipment</h2>       
             <table class="table">
            
      <tr>
          <td>id#</td><td>Name</td><td>Designation</td><td>Equipment Type</td><td>Equipment Tier</td><td>Equipment Activity</td><td>Heat</td>
          <td>Mass</td><td>Physical Volume</td><td>Locations Allowed</td><td>Unit Cost</td><td>Edit</td><td>Delete</td>
      </tr>
      
      <c:forEach var="komponentti" items="${varustekomponentit}">
          
             <div class="komponentti"><tr>
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
                <td><a href="komponenttieditoi?id=${komponentti.komponentti_id}&equipment=kylla">EDIT</a></td>
                <td><a href="komponenttipoista?id=${komponentti.komponentti_id}">DELETE</a></td>
                    
                    
                 
                 </tr></div>
      </c:forEach>
  </table>
             
              <h2>Reactors</h2>       
             <table class="table">
            
      <tr>
          <td>id#</td><td>Name</td><td>Designation</td><td>Cooling Factor</td><td>Power Generation</td>
          <td>Weigth</td><td>Physical Volume</td><td>Locations Allowed</td><td>Unit Cost</td><td>Edit</td><td>Delete</td>
      </tr>
      
      <c:forEach var="reaktori" items="${reaktorit}">
          
             <div class="reaktori"><tr>
                <td>${reaktori.reaktori_id}</td>
                <td><c:out value="${reaktori.nimi}"/></td>
                <td><c:out value="${reaktori.lyhenne}"/></td>
                <td><c:out value="${reaktori.cooling}"/></td>
                <td><c:out value="${reaktori.teho}"/></td>
                <td><c:out value="${reaktori.massa}"/></td>
                <td><c:out value="${reaktori.kokoluokka}"/></td>
                <td><c:out value="${reaktori.sijoituspaikka}"/></td>
                <td><c:out value="${reaktori.cost}"/> CR</td>
                <td><a href="komponenttieditoi?id=${reaktori.reaktori_id}&reactor=kylla">EDIT</a></td>
                <td><a href="komponenttipoista?id=${reaktori.reaktori_id}&reactor=kylla">DELETE</a></td>
                    
                    
                 
                 </tr></div>
      </c:forEach>
  </table>
             
             
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
      