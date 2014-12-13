<%-- 
    Document   : sivu
    Created on : 14.11.2014, 0:29:37
    Author     : mikromafia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Mechlab!">
      <div class="page-header">
  <h1><span class="label label-info">Combat Simulator</span><small>Simulates outcomes for single mech combat.</small></h1>
</div>
    
    <div class="panel panel-default">
        
        
  <div class="panel-body">
      <h3><span class="label label-primary">${attacker.nimi}</span></h3> (${attacker.weaponrating}WR/${attacker.defencerating}DR)<br>
      ...vs...<br>
      <h3><span class="label label-danger">${defender.nimi}</span></h3> (${defender.weaponrating}WR/${defender.defencerating}DR) <br>
  <c:if test="${attacker.paino>defender.paino}">${attacker.nimi} has the weight advantage.<br></c:if>
  <c:if test="${attacker.paino<defender.paino}">${defender.nimi} has the weight advantage.<br></c:if>
  </div>
</div>
    
    
  <ul class="nav nav-pills" role="tablist">
      <li role="presentation" <c:if test="${view != 'operational' && view != 'owned'}">class="active"</c:if>><a href="mechselaa?view=all">View all mechs</a></li>
        <li role="presentation" <c:if test="${view == 'operational'}">class="active"</c:if>><a href="mechselaa?view=operational">View operational mechs</a></li>
        <li role="presentation" <c:if test="${view == 'owned'}">class="active"</c:if>><a href="mechselaa?view=owned">View owned mechs</a></li>
        <li role="presentation"><a href="#">Search</a></li>
        <li role="presentation"><a href="mechluouusi">Add a new prototype</a></li>
        </ul>
          <p>
               
          <p>
              <c:if test="${ilmoitus != null}">
                <div class="alert alert-danger">${ilmoitus}</div>
            </c:if>  
              <div class="panel panel-default">
                        
                        
                  <div class="panel-heading">Simulation Started</div>
  <div class="panel-body">
  <c:forEach var="result" items="${results}">
      <c:if test="${result == 'NEXTTURN1'}"></div><div class="panel-heading"></c:if>
          <c:if test="${result == 'NEXTTURN2'}"></div><div class="panel-body"></c:if>
          <c:if test="${result != 'NEXTTURN2' && result != 'NEXTTURN1'}">${result}</c:if><br>
  </c:forEach>
  </div>
                  <div class="panel-body">
                   <ul class="nav nav-pills" role="tablist">
      
        <li role="presentation"><a href="mechedit?id=${attacker.mech_id}">Return to Previous Page</a></li>
        <li role="presentation"><a href="mechsimuloi?attacker=${attacker.mech_id}&defender=${defender.mech_id}">Repeat the Simulation</a></li>
        
        </ul>
                  </div>
              </div>
               
             
        
             
     
</div>
     
</t:pohja>
      