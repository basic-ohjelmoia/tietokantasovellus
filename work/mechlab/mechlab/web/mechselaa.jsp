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
      <li role="presentation" <c:if test="${view != 'operational' && view != 'owned'}">class="active"</c:if>><a href="mechselaa?view=all">View all mechs</a></li>
        <li role="presentation" <c:if test="${view == 'operational'}">class="active"</c:if>><a href="mechselaa?view=operational">View operational mechs</a></li>
        <li role="presentation" <c:if test="${view == 'owned'}">class="active"</c:if>><a href="mechselaa?view=owned">View owned mechs</a></li>
        <li role="presentation"><a href="#">Search</a></li>
        <li role="presentation"><a href="mechluouusi">Add a new prototype</a></li>
        </ul>
          <p>
                 <div class="container">      
      Currently active mechs and mechs under construction.
     </div>
          <p>
              <c:if test="${ilmoitus != null}">
                <div class="alert alert-danger">${ilmoitus}</div>
            </c:if>  
              <div class="panel panel-default">
                        <c:forEach var="mech" items="${mechit}">
                            <c:if test="${mech.nettopaino == 0 && mech.user_id == userid}">
                  <div class="panel-heading">You have a pre production model waiting!</div>
  <div class="panel-body">
                <div class="table-responsive">
             <table class="table">
            
      <tr>
          <td>id#</td><td>Name</td><td>Weight</td><td>Weapon Rating</td><td>Armor Rating</td><td>Heat Sinks</td>
          <td>Walk (Run) Speed</td><td>Jump Rating</td><td>Owner</td><td>Unit Cost</td><td>Status</td><td>Edit</td><td>Delete</td>
      </tr>
          
             <div class="mech"><tr>
                <td>${mech.mech_id}</td>
                <td><c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">PRE-PRODUCTION </c:if><c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">PROTOTYPE </c:if><c:out value="${mech.nimi}"/></td>
                <td><c:out value="${mech.nettopaino}"/>t / <c:out value="${mech.paino}"/>t</td>
                <td><c:out value="${mech.weaponrating}"/></td>
                <td><c:out value="${mech.armorrating}"/></td>
                <td><c:out value="${mech.heatsinks}"/></td>
                <td><c:out value="${mech.walkingspeed}"/> km/h (<c:out value="${mech.runningspeed}"/> km/h)</td>
                <td><c:out value="${mech.jumprating}"/> m</td>
                <td><c:out value="${mech.ownername}"/></td>
                <td><c:out value="${mech.coststring}"/> CR</td>
                <td>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">Pre-production</c:if>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">Prototype</c:if>
                    <c:if test="${mech.varoitusvapaa == true}">Operational <span class="glyphicon glyphicon-ok" aria-hidden="true"></span></c:if>
                <td><a href="mechedit?id=${mech.mech_id}">EDIT</a></td>
                <td><a href="mechluouusi?poista=${mech.mech_id}">DELETE</a></td>
                    
                    
                 
                 </tr></div>
      
             </table>              </div>
  </div></c:if>
                </c:forEach>
  
                  
<div class="panel-heading">Battlemechs in database</div>
  <div class="panel-body">
                <div class="table-responsive">
             <table class="table">
            
      <tr>
          <td>id#</td><td>Name</td><td>Weight</td><td>Weapon Rating</td><td>Armor Rating</td><td>Heat Sinks</td>
          <td>Walk (Run) Speed</td><td>Jump Rating</td><td>Owner</td><td>Unit Cost</td><td>Status</td><td>Edit</td><td>Delete</td>
      </tr>
      <c:if test="${view == 'owned'}">
                    
           <c:forEach var="mech" items="${mechit}">
          
          <c:if test="${mech.nettopaino > 0 && mech.user_id == userid}">
             <div class="mech"><tr>
                <td>${mech.mech_id}</td>
                <td><c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">PRE-PRODUCTION </c:if><c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">PROTOTYPE </c:if><c:out value="${mech.nimi}"/></td>
                <td><c:out value="${mech.nettopaino}"/>t / <c:out value="${mech.paino}"/>t</td>
                <td><c:out value="${mech.weaponrating}"/></td>
                <td><c:out value="${mech.armorrating}"/></td>
                <td><c:out value="${mech.heatsinks}"/></td>
                <td><c:out value="${mech.walkingspeed}"/> km/h (<c:out value="${mech.runningspeed}"/> km/h)</td>
                <td><c:out value="${mech.jumprating}"/> m</td>
                <td><c:out value="${mech.ownername}"/></td>
                <td><c:out value="${mech.coststring}"/> CR</td>
                <td>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">Pre-production</c:if>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">Prototype</c:if>
                    <c:if test="${mech.varoitusvapaa == true}">Operational <span class="glyphicon glyphicon-ok" aria-hidden="true"></span></c:if>
                <td><a href="mechedit?id=${mech.mech_id}">EDIT</a></td>
                <td><a href="mechluouusi?poista=${mech.mech_id}">DELETE</a></td>
                    
                    
                 
                 </tr></div>
                </c:if>
         </c:forEach>
      </c:if>
      
      <c:if test="${view == 'operational'}">
                    
           <c:forEach var="mech" items="${mechit}">
          
          <c:if test="${mech.nettopaino > 0 && mech.varoitusvapaa == true}">
             <div class="mech"><tr>
                <td>${mech.mech_id}</td>
                <td><c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">PRE-PRODUCTION </c:if><c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">PROTOTYPE </c:if><c:out value="${mech.nimi}"/></td>
                <td><c:out value="${mech.nettopaino}"/>t / <c:out value="${mech.paino}"/>t</td>
                <td><c:out value="${mech.weaponrating}"/></td>
                <td><c:out value="${mech.armorrating}"/></td>
                <td><c:out value="${mech.heatsinks}"/></td>
                <td><c:out value="${mech.walkingspeed}"/> km/h (<c:out value="${mech.runningspeed}"/> km/h)</td>
                <td><c:out value="${mech.jumprating}"/> m</td>
                <td><c:out value="${mech.ownername}"/></td>
                <td><c:out value="${mech.coststring}"/> CR</td>
                <td>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">Pre-production</c:if>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">Prototype</c:if>
                    <c:if test="${mech.varoitusvapaa == true}">Operational <span class="glyphicon glyphicon-ok" aria-hidden="true"></span></c:if>
                <td><a href="mechedit?id=${mech.mech_id}">EDIT</a></td>
                <td><a href="mechluouusi?poista=${mech.mech_id}">DELETE</a></td>
                    
                    
                 
                 </tr></div>
                </c:if>
         </c:forEach>
      </c:if>
      
      <c:if test="${view != 'operational' && view != 'owned'}">
      <c:forEach var="mech" items="${mechit}">
          
          <c:if test="${mech.nettopaino > 0}">
             <div class="mech"><tr>
                <td>${mech.mech_id}</td>
                <td><c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">PRE-PRODUCTION </c:if><c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">PROTOTYPE </c:if><c:out value="${mech.nimi}"/></td>
                <td><c:out value="${mech.nettopaino}"/>t / <c:out value="${mech.paino}"/>t</td>
                <td><c:out value="${mech.weaponrating}"/></td>
                <td><c:out value="${mech.armorrating}"/></td>
                <td><c:out value="${mech.heatsinks}"/></td>
                <td><c:out value="${mech.walkingspeed}"/> km/h (<c:out value="${mech.runningspeed}"/> km/h)</td>
                <td><c:out value="${mech.jumprating}"/> m</td>
                <td><c:out value="${mech.ownername}"/></td>
                <td><c:out value="${mech.coststring}"/> CR</td>
                <td>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">Pre-production</c:if>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">Prototype</c:if>
                    <c:if test="${mech.varoitusvapaa == true}">Operational <span class="glyphicon glyphicon-ok" aria-hidden="true"></span></c:if>
                <td><a href="mechedit?id=${mech.mech_id}">EDIT</a></td>
                <td><a href="mechluouusi?poista=${mech.mech_id}">DELETE</a></td>
                    
                    
                 
                 </tr></div>
                </c:if>
      </c:forEach>
      </c:if>
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
      