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
  <h1><span class="label label-info">Browse Mechs</span><small>This page lists all mechs in the database.</small></h1>
</div>

    
  <ul class="nav nav-pills" role="tablist">
      <li role="presentation" <c:if test="${view != 'operational' && view != 'owned'}">class="active"</c:if>><a href="mechselaa?view=all&weight=${maxweight}">View all mechs</a></li>
        <li role="presentation" <c:if test="${view == 'operational'}">class="active"</c:if>><a href="mechselaa?view=operational&weight=${maxweight}">View operational mechs</a></li>
        <li role="presentation" <c:if test="${view == 'owned'}">class="active"</c:if>><a href="mechselaa?view=owned&weight=${maxweight}">View owned mechs</a></li>
        <li role="presentation"><a href="mechluouusi">Add a new prototype</a></li>
        </ul>
          <ul class="nav nav-pills" role="tablist">
              <li role="presentation" <c:if test="${minweight == 20 && maxweight == 100}">class="active"</c:if>><a href="mechselaa?view=${view}&weight=0">No Filter</a></li>
      <li role="presentation" <c:if test="${maxweight == 35}">class="active"</c:if>><a href="mechselaa?view=${view}&weight=35">Filter Light Mechs</a></li>
      <li role="presentation" <c:if test="${maxweight == 55}">class="active"</c:if>><a href="mechselaa?view=${view}&weight=55">Filter Medium Mechs</a></li>
      <li role="presentation" <c:if test="${maxweight == 75}">class="active"</c:if>><a href="mechselaa?view=${view}&weight=75">Filter Heavy Mechs</a></li>
      <li role="presentation" <c:if test="${minweight == 80 && maxweight == 100}">class="active"</c:if>><a href="mechselaa?view=${view}&weight=100">Filter Assault Mechs</a></li>
        </ul>
          <p>
<!--                 <div class="container">      
      Currently active mechs and mechs under construction.
     </div>-->
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
             
          <%--<td>id#</td><td>Name</td><td>Weight</td><td>Weapon Rating</td><td>Defence Rating</td><td>Armor Value</td><td>Heat Sinks</td>
          <td>Walk (Run) Speed</td><td>Jump Rating</td><td>Owner</td><td>Unit Cost</td><td>Status</td><td>Edit</td><td>Delete</td>--%>
          <%--<t:mechselaaetiketit/>--%>
         <table class="table table-hover" width="100%">
          <t:mechselaaetiketit/> 
          
             <div class="mech"><tr>
                <td>${mech.mech_id}</td>
                <td><c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">PRE-PRODUCTION </c:if><c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">PROTOTYPE </c:if><c:out value="${mech.nimi}"/></td>
                <td><c:if test="${mech.nettopaino != mech.paino}"><c:out value="${mech.nettopaino}"/>t /</c:if><c:out value="${mech.paino}"/>t</td>
                <td><c:out value="${mech.weaponrating}"/></td>
                <td><c:out value="${mech.defencerating}"/></td>
                <td><c:out value="${mech.armorrating}"/></td>
                <td><c:out value="${mech.heatsinks}"/></td>
                <td><c:out value="${mech.walkingspeed}"/> km/h</td> 
                <td><c:out value="${mech.runningspeed}"/> km/h</td>
                <td><c:out value="${mech.jumprating}"/> m</td>
                <td><c:out value="${mech.ownername}"/></td>
                <td><c:out value="${mech.coststring}"/> CR</td>
                <td>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">Pre-production</c:if>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">Prototype</c:if>
                    <c:if test="${mech.varoitusvapaa == true}">Operational <span class="glyphicon glyphicon-ok" aria-hidden="true"></span></c:if></td>
                <td><a href="mechedit?id=${mech.mech_id}"><c:if test="${mech.user_id == userid || naviadmin != null}">
                            EDIT</c:if><c:if test="${mech.user_id != userid && naviadmin == null}">VIEW</c:if></a></td>
                <td><a href="mechluouusi?poista=${mech.mech_id}"><c:if test="${mech.user_id == userid || naviadmin != null}">
                            DELETE</c:if><c:if test="${mech.user_id != userid || naviadmin == null}"></c:if></a></td>
                    
                    
                 
                 </tr></div>
      
             </table>              </div>
  </div></c:if>
                </c:forEach>
  
                  
<div class="panel-heading">Battlemechs in database</div>
  <div class="panel-body">
                <div class="table-responsive">
           <table class="table table-hover" width="100%">
          <t:mechselaaetiketit/>
      
      <c:if test="${view == 'owned'}">
                    
           <c:forEach var="mech" items="${mechit}">
          
               <c:if test="${mech.nettopaino > 0 && mech.user_id == userid && mech.paino >= minweight && mech.paino <= maxweight}">
             <div class="mech">
                 <tr>
                <td>${mech.mech_id}</td>
                <td><c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">PRE-PRODUCTION </c:if><c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">PROTOTYPE </c:if><c:out value="${mech.nimi}"/></td>
                <td><c:if test="${mech.nettopaino != mech.paino}"><c:out value="${mech.nettopaino}"/>t /</c:if><c:out value="${mech.paino}"/>t</td>
                <td><c:out value="${mech.weaponrating}"/></td>
                <td><c:out value="${mech.defencerating}"/></td>
                <td><c:out value="${mech.armorrating}"/></td>
                <td><c:out value="${mech.heatsinks}"/></td>
                <td><c:out value="${mech.walkingspeed}"/> km/h</td> 
                <td><c:out value="${mech.runningspeed}"/> km/h</td>
                <td><c:out value="${mech.jumprating}"/> m</td>
                <td><c:out value="${mech.ownername}"/></td>
                <td><c:out value="${mech.coststring}"/> CR</td>
                <td>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">Pre-production</c:if>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">Prototype</c:if>
                    <c:if test="${mech.varoitusvapaa == true}">Operational <span class="glyphicon glyphicon-ok" aria-hidden="true"></span></c:if></td>
                <td><a href="mechedit?id=${mech.mech_id}"><c:if test="${mech.user_id == userid || naviadmin != null}">
                            EDIT</c:if><c:if test="${mech.user_id != userid && naviadmin == null}">VIEW</c:if></a></td>
                <td><a href="mechluouusi?poista=${mech.mech_id}"><c:if test="${mech.user_id == userid || naviadmin != null}">
                            DELETE</c:if><c:if test="${mech.user_id != userid || naviadmin == null}"></c:if></a></td>
                    
                    
                 
                 </tr></div>
                </c:if>
         </c:forEach>
      </c:if>
      
      <c:if test="${view == 'operational'}">
                    
           <c:forEach var="mech" items="${mechit}">
          
          <c:if test="${mech.nettopaino > 0 && mech.varoitusvapaa == true  && mech.paino >= minweight && mech.paino <= maxweight}">
             <div class="mech"><tr>
                <td>${mech.mech_id}</td>
                <td><c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">PRE-PRODUCTION </c:if><c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">PROTOTYPE </c:if><c:out value="${mech.nimi}"/></td>
                <td><c:if test="${mech.nettopaino != mech.paino}"><c:out value="${mech.nettopaino}"/>t /</c:if><c:out value="${mech.paino}"/>t</td>
                <td><c:out value="${mech.weaponrating}"/></td>
                <td><c:out value="${mech.defencerating}"/></td>
                <td><c:out value="${mech.armorrating}"/></td>
                <td><c:out value="${mech.heatsinks}"/></td>
                <td><c:out value="${mech.walkingspeed}"/> km/h</td> 
                <td><c:out value="${mech.runningspeed}"/> km/h</td>
                <td><c:out value="${mech.jumprating}"/> m</td>
                <td><c:out value="${mech.ownername}"/></td>
                <td><c:out value="${mech.coststring}"/> CR</td>
                <td>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">Pre-production</c:if>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">Prototype</c:if>
                    <c:if test="${mech.varoitusvapaa == true}">Operational <span class="glyphicon glyphicon-ok" aria-hidden="true"></span></c:if></td>
             <td><a href="mechedit?id=${mech.mech_id}"><c:if test="${mech.user_id == userid || naviadmin != null}">
                            EDIT</c:if><c:if test="${mech.user_id != userid && naviadmin == null}">VIEW</c:if></a></td>
                <td><a href="mechluouusi?poista=${mech.mech_id}"><c:if test="${mech.user_id == userid || naviadmin != null}">
                            DELETE</c:if><c:if test="${mech.user_id != userid || naviadmin == null}"></c:if></a></td>
                    
                    
                 
                 </tr></div>
                </c:if>
         </c:forEach>
      </c:if>
      
      <c:if test="${view != 'operational' && view != 'owned'}">
      <c:forEach var="mech" items="${mechit}">
          
          <c:if test="${mech.nettopaino > 0  && mech.paino >= minweight && mech.paino <= maxweight}">
             <div class="mech"><tr>
                <td>${mech.mech_id}</td>
                <td><c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">PRE-PRODUCTION </c:if><c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">PROTOTYPE </c:if><c:out value="${mech.nimi}"/></td>
                <td><c:if test="${mech.nettopaino != mech.paino}"><c:out value="${mech.nettopaino}"/>t /</c:if><c:out value="${mech.paino}"/>t</td>
                <td><c:out value="${mech.weaponrating}"/></td>
                <td><c:out value="${mech.defencerating}"/></td>
                <td><c:out value="${mech.armorrating}"/></td>
                <td><c:out value="${mech.heatsinks}"/></td>
                <td><c:out value="${mech.walkingspeed}"/> km/h</td> 
                <td><c:out value="${mech.runningspeed}"/> km/h</td>
                <td><c:out value="${mech.jumprating}"/> m</td>
                <td><c:out value="${mech.ownername}"/></td>
                <td><c:out value="${mech.coststring}"/> CR</td>
                <td>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">Pre-production</c:if>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">Prototype</c:if>
                    <c:if test="${mech.varoitusvapaa == true}">Operational <span class="glyphicon glyphicon-ok" aria-hidden="true"></span></c:if></td>
         <td><a href="mechedit?id=${mech.mech_id}"><c:if test="${mech.user_id == userid || naviadmin != null}">
                            EDIT</c:if><c:if test="${mech.user_id != userid && naviadmin == null}">VIEW</c:if></a></td>
                <td><a href="mechluouusi?poista=${mech.mech_id}"><c:if test="${mech.user_id == userid || naviadmin != null}">
                            DELETE</c:if><c:if test="${mech.user_id != userid || naviadmin == null}"></c:if></a></td>
                    
                    
                 
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
      