<%-- 
    Document   : sivu
    Created on : 14.11.2014, 0:29:37
    Author     : mikromafia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Mechlab! Komponentti!">

     <div class="page-header">
  <h1><span class="label label-info">Browse Components</span><small>This page lists all components in the database.</small></h1>
</div>

   <ul class="nav nav-pills" role="tablist">
        <li role="presentation" class ="active"><a href="komponenttiselaa">List Weapons</a></li>
        <c:if test="${naviadmin != null}"><li role="presentation"><a href="komponenttieditoi?id=0">Weapon Lab</a></li>
        <li role="presentation"><a href="komponenttieditoi?id=0&equipment=kylla">Equipment Lab</a></li>
        <li role="presentation"><a href="komponenttieditoi?id=0&reactor=kylla">Reactor Lab</a></li></c:if>
        <li role="presentation"><a href="#">Search</a></li>
        </ul>
    
          <p>
                 <c:if test="${ilmoitus != null}">
                <div class="alert alert-danger">${ilmoitus}</div>
            </c:if>  
              
         
             
             <c:if test="${minimoiweapons == null}">
               <h2>Weapons</h2>           
               
  <div class="table-responsive">
            <table class="table table-hover" width="100%">
            
      <tr>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">id <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The id number of this weapon.</li></ul></li></ul></td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Name <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Name of this weapon.</li></ul></li></ul></td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Designation <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The military designation of this weapon.</li></ul></li></ul></td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Weapon Type <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>There are five weapon types:</li>
                  <li><b>AUTO</b> - Weapons with rapid fire rates ranging from machine guns to pulse lasers. Highly effective against infantry.</li>
                  <li><b>ENERGY</b> - Direct-energy weapons that are designed to punch thru armor. Energy weapons always connect for maximum damage.</li>
                  <li><b>KINETIC</b> - Weapons firing kinetic/kinetic-explosive rounds. Kinetic weapons perform well against both armored and soft targets.</li>
                  <li><b>MELEE</b> - Blunt instruments attached to mech's arms. A melee weapon's damage value is ignored and directly based on the mech's tonnage.</li>
                  <li><b>MISSILE</b> - Weapons firing salvos of rocket propelled grenades/guided missiles. A salvo of missiles will usually score some hits, but getting the whole salvo to connect for maximum damage is difficult at best.</li>
          </ul></li></ul>
  </td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Weight <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The weight of this component in tons. </li></ul></li></ul></td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Damage <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The maximum amount of damage done per hit. </li></ul></li></ul></td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Heat <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The amount of heat generated each time this weapon is fired. </li></ul></li></ul></td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Max. Range<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The maximum effective range of the weapon. </li></ul></li></ul></td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Min. Range<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The minimum effective range of the weapon. Weapons targeted insider their minimum effective range are penalized for accuracy. </li></ul></li></ul></td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Ammo Count <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Number of shots available for the weapon. A value of zero means that the weapon consumes no ammunition. </li></ul></li></ul></td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Physical Volume<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Physical Volume indicates how many installation slots the component eats up when installed. Small components use 1 slot, medium components 2, large components 3 and XL components 5. </li></ul></li></ul></td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Locations Allowed <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Locations Allowed indicates where the component can be installed on a mech (i.e. ARMS_TORSO equals both arms and all three sections of torso). </li></ul></li></ul></td>
          <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Cost <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Unit cost of the component (galactic credits). </li></ul></li></ul></td>
          <c:if test="${naviadmin != null}">
              <td>Edit</td><td>Delete</td></c:if>
              <td>     </td>
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
                <td><c:if test="${komponentti.weaponammo == 0}">&infin;</c:if><c:if test="${komponentti.weaponammo > 0}">${komponentti.weaponammo}</c:if></td>
                <td><c:out value="${komponentti.kokoluokka}"/></td>
                <td><c:out value="${komponentti.sijoituspaikka}"/></td>
                <td><c:out value="${komponentti.cost}"/> CR</td>
                <c:if test="${naviadmin != null}"><td><a href="komponenttieditoi?id=${komponentti.komponentti_id}">EDIT</a></td>
                    <td><a href="komponenttipoista?id=${komponentti.komponentti_id}">DELETE</a></td></c:if>
                    <td>        </td>
                    
                 
                 </tr></div>
      </c:forEach>
  </table>
  </div>
      </c:if>  
               
             <h2>Equipment</h2>       
             <table class="table">
            
      <tr>
           <td> <ul class="nav nav-pills" role="tablist"><li class="dropdown">id<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The id number of this equipment.</li></ul></li></ul></td>
          <td> <ul class="nav nav-pills" role="tablist"><li class="dropdown">Name<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Name of this equipment.</li></ul></li></ul></td>
          <td> <ul class="nav nav-pills" role="tablist"><li class="dropdown">Designation<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The military designation of this equipment.</li></ul></li></ul></td>
          
          <td>Equipment Type</td>
          <td> <ul class="nav nav-pills" role="tablist"><li class="dropdown">Equipment Tier<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Equipment Tier designates the general quality/performance/robustness level of the equipment. Tier I is considered the best while Tier III is the worst.</li></ul></li></ul></td>
          <td> <ul class="nav nav-pills" role="tablist"><li class="dropdown">Equipment Activity<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>There are two activity types: <b>Active</b> and <b>Passive</b>. Active equipment can be turned on and off. Passive equipment is considered to be always on.</li></ul></li></ul></td>
 <td> <ul class="nav nav-pills" role="tablist"><li class="dropdown">Heat<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The amount of heat generated each time this equipment is used. "Passive" equipment will generate this heat each turn!</li></ul></li></ul></td>
                <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Weight <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The weight of this component in tons. </li></ul></li></ul></td>
                <td> <ul class="nav nav-pills" role="tablist"><li class="dropdown">Physical Volume<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Physical Volume indicates how many installation slots the component eats up when installed. Small components use 1 slot, medium components 2, large components 3 and XL components 5. </li></ul></li></ul></td>
          <td> <ul class="nav nav-pills" role="tablist"><li class="dropdown">Locations Allowed<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Locations Allowed indicates where the component can be installed on a mech (i.e. ARMS_TORSO equals both arms and all three sections of torso). </li></ul></li></ul></td>
          <td> <ul class="nav nav-pills" role="tablist"><li class="dropdown">Unit Cost<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Credit cost of the component. </li></ul></li></ul></td>
                  <c:if test="${naviadmin != null}"><td>Edit</td><td>Delete</td></c:if>
      </tr>
      
      <c:forEach var="komponentti" items="${varustekomponentit}">
          
             <div class="komponentti"><tr>
                <td>${komponentti.komponentti_id}</td>
                <td><c:out value="${komponentti.nimi}"/></td>
                <td><c:out value="${komponentti.lyhenne}"/></td>
                <td><c:out value="${komponentti.varustetype}"/></td>
                 <td><c:if test="${komponentti.varustetier == 1}">I</c:if>
                              <c:if test="${komponentti.varustetier == 2}">II</c:if>
                              <c:if test="${komponentti.varustetier == 3}">III</c:if>
                             <%-- ${komponentti.varustetier} --%>
                          </td>
                <td><c:out value="${komponentti.varusteactivity}"/></td>
                <td><c:out value="${komponentti.heat}"/></td>
                <td><c:out value="${komponentti.massa}"/></td>
                <td><c:out value="${komponentti.kokoluokka}"/></td>
                <td><c:out value="${komponentti.sijoituspaikka}"/></td>
                <td><c:out value="${komponentti.cost}"/> CR</td>
                <c:if test="${naviadmin != null}"><td><a href="komponenttieditoi?id=${komponentti.komponentti_id}&equipment=kylla">EDIT</a></td>
                    <td><a href="komponenttipoista?id=${komponentti.komponentti_id}">DELETE</a></td></c:if>
                    
                    
                 
                 </tr></div>
      </c:forEach>
  </table>
             
              <h2>Reactors</h2>       
             <table class="table">
            
      <tr>
          <td>id#</td><td>Name</td><td>Designation</td>
          <td> <ul class="nav nav-pills" role="tablist"><li class="dropdown">Cooling Factor<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Cooling Factor indicates how many units of heat the reactor can shed off each turn. Any additional heat must be shed off by installing Heat Sinks.</li></ul></li></ul></td>
          <td> <ul class="nav nav-pills" role="tablist"><li class="dropdown">Power Output<button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The reactor's power output is used to calculate the mech's movement speed. Higher the output, the better!</li></ul></li></ul></td>
          <td>Weigth</td><td>Power/Weight Ratio</td><td>Physical Volume</td><td>Locations Allowed</td><td>Unit Cost</td><c:if test="${naviadmin != null}"><td>Edit</td><td>Delete</td></c:if>
      </tr>
      
      <c:forEach var="reaktori" items="${reaktorit}">
          
             <div class="reaktori"><tr>
                <td>${reaktori.reaktori_id}</td>
                <td><c:out value="${reaktori.nimi}"/></td>
                <td><c:out value="${reaktori.lyhenne}"/></td>
                <td><c:out value="${reaktori.cooling}"/></td>
                <td><c:out value="${reaktori.teho}"/></td>
                <td><c:out value="${reaktori.massa}"/></td>
                <td><c:out value="${reaktori.efficiency}"/></td>
                <td><c:out value="${reaktori.kokoluokka}"/></td>
                <td><c:out value="${reaktori.sijoituspaikka}"/></td>
                <td><c:out value="${reaktori.cost}"/> CR</td>
                <c:if test="${naviadmin != null}"><td><a href="komponenttieditoi?id=${reaktori.reaktori_id}&reactor=kylla">EDIT</a></td>
                    <td><a href="komponenttipoista?id=${reaktori.reaktori_id}&reactor=kylla">DELETE</a></td></c:if>
                    
                    
                 
                 </tr></div>
      </c:forEach>
  </table>
             
             
  </table>
</div>
<!--      <center>
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
      </center>-->
</t:pohja>
      