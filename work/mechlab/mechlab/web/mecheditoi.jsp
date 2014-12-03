<%-- 
    Document   : sivu
    Created on : 14.11.2014, 0:29:37
    Author     : mikromafia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Mechlab! Create Mech!">
 
    <h1>Mechlab!</h1>
    
<!--    <ul class="nav nav-pills" role="tablist">
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
          <p>-->
         
          
          
          
         <div class="table-responsive">
            <table class="table table-hover" width="100%">
      <tr>
          <td>id#</td>
          <td class="dropdown" style="overflow:visible">
          <ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mech Name<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <li>  <form action="mechedit" method="POST" role="form">
                
  <div class="form-group">
      <input type="hidden" name="mechid" value="${mech.mech_id}"/>
    <input type="nimi" class="form-control" name="mechnimi" id="mechnimi" value="${mech.nimi}">
  </div><button type="submit" class="btn btn-default">RENAME</button>
              </form>  </li>
          </ul>
  </li>
          </ul>
          </td>
          
          <td class="dropdown" style="overflow:visible"><ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Weight Class<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <li>Select a new weight class:</li>
              <li>-Light Mech-</li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=20">20 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=25">25 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=30">30 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=35">35 t</a></li>
            <li>-Medium Mech-</li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=40">40 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=45">45 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=50">50 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=55">55 t</a></li>
            <li>-Heavy Mech-</li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=60">60 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=65">65 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=70">70 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=75">75 t</a></li>
            <li>-Assault Mech-</li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=80">80 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=85">85 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=90">90 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=95">95 t</a></li>
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&painoluokka=100">100 t</a></li>
                  </ul>
        </li>
       
</ul></td>
          
          
          <td>Weapon Rating</td><td>Armor Rating</td><td>Heat Sinks</td>
          <td>Walk (Run) Speed</td><td>Jump Rating</td><td>Owner</td><td>Unit Cost</td><td>Edit</td><td>Delete</td>
      </tr>
      
      <%--<c:forEach var="mech" items="${mechit}">--%>
                                  
  
            
    
     
        <tr>
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
                <td><a href="komponenttieditoi?id=${mech.mech_id}&reactor=kylla">EDIT</a></td>
                <td><a href="komponenttipoista?id=${mech.mech_id}&reactor=kylla">DELETE</a></td>
                    
                    
                 
                 </tr></div>
                <%-- </c:forEach>--%>
  </table>
         </div>
  
  
  
             <h2>Components</h2>       
              <div class="table-responsive">
                  
  <table class="table table-hover" width="100%">
      <tr>
          <td>
              
              <div class="panel panel-default">
  <div class="panel-heading">Weapons Installed</div>
  <div class="panel-body">
       <div class="table-responsive">
           <table class="table table-condensed">
               <tr>
                   <td>Loc</td><td>Name</td><td>Dmg</td><td>Rng</td><td>Heat</td><td>Ammo</td>
               </tr>
               
       <c:forEach var="komponentti" items="${allcomponents}">
           
                  <c:if test="${komponentti.kategoria == 'ASE'}"><tr>
                          <td>${komponentti.sijaintilyhyt}</td><td>${komponentti.nimi}</td><td>${komponentti.weapondamage}</td><td>${komponentti.weaponmaxrange}</td><td>${komponentti.heat}</td><td><c:if test="${komponentti.weaponammo == 0}">&infin;</c:if><c:if test="${komponentti.weaponammo > 0}">${komponentti.weaponammo}</c:if></td>
                      </tr>
                 </c:if>
                      
              </c:forEach>
                      <tr>
                      <td></td><td></td><td><u>Total</u></td><td><u>Max</u></td><td><u>Total</u></td><td></td>
                      </tr>
                      <tr>
                      <td></td><td></td><td>${mech.totalweapondamage}</td><td>${mech.maxweaponrange}</td><td>${mech.totalweaponheat}</td><td>(vs ${mech.heatsinks} hs)</td>
                      </tr>
           </table>
  </div>
</div>
            
                 
  <div class="panel-heading">Equipment Installed</div>
  <div class="panel-body">
      <div class="table-responsive">
         <table class="table table-condensed">
               <tr>
                   <td>Loc</td><td>Name</td><td>Tier</td><td>Weight</td><td>Heat</td>
               </tr>
           <c:forEach var="komponentti" items="${allcomponents}">
                  <c:if test="${komponentti.kategoria == 'VARUSTE'}">
                  <c:if test="${komponentti.varustetype != 'ARMOR PLATING'}">
                      <tr>     
                          <td>${komponentti.sijaintilyhyt}</td><td>${komponentti.nimi}</td>
                          <td><c:if test="${komponentti.varustetier == 1}">I</c:if>
                              <c:if test="${komponentti.varustetier == 2}">II</c:if>
                              <c:if test="${komponentti.varustetier == 3}">III</c:if>
                             <%-- ${komponentti.varustetier} --%>
                          </td>
                          
                          <td>${komponentti.massa}</td><td>${komponentti.heat}</td>
                      </tr>
                 </c:if>
                 </c:if>
              </c:forEach>
         </table>
      </div>
  </div>

                               
  <div class="panel-heading">Armor Values</div>
  <div class="panel-body">
       <div class="table-responsive">
           <table class="table table-condensed">
               <tr>
                   <td>Loc</td><td>Armor</td><td>Internal Structure</td>
               </tr>
        <c:forEach var="rivi" items="${armorvalues}">
            <tr>${rivi}</tr> 
              </c:forEach>
           </table>
  <div class="panel-heading"><b>ARMOR TOTAL</b>: ${mech.armorrating}</div>
      
                  
  </div>
</div>
              </div>
          
                            
          </td>
          <td>
               <div class="table-responsive">
      <table class="table table-condensed table-hover">
      <tr>
          <td>
               <div class="panel panel-default">
  <div class="panel-heading">LEFT ARM (${mech.itemsleftarm}/${mech.itemsallowedextension}) <c:if test="${mech.itemsleftarm > mech.itemsallowedextension}">OVERLOADED</c:if> </div>
</div>
  <div class="panel-body">
           <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_la}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_la}"/><c:out value="%"/>;">
      A<c:if test="${armor_la > 25}">rmor</c:if>: <c:out value="${armor_la}"/>
    </div>
</div>
       <div class="table-responsive">
           <table class="table table-condensed table-hover">
               
              <c:forEach var="komponentti" items="${leftarm}">
                  <tr>
              <td><c:out value="${komponentti.nimi}"/></td><td>${komponentti.massa}t</td><td>${komponentti.kokoluokkalyhyt}</td>
                  <td><a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a></td>
                  </tr>  
                  </c:forEach>
           </table>
             </div>
  </div>
               
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
<%--</ul>--%>
                  
<%--<ul class="nav nav-pills" role="tablist">       --%> 
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Melee<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmelee}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'ARMS' || komponentti.sijoituspaikka == 'ARMS_TORSO' || komponentti.sijoituspaikka == 'ARMS_LEGS'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
           
     
          </td>
          
<td>
               <div class="panel panel-default">
  <div class="panel-heading">HEAD (${mech.itemshead}/${mech.itemsallowedextension}) <c:if test="${mech.itemshead > mech.itemsallowedextension}">OVERLOADED</c:if></div>
  <div class="panel-body">
           <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_hd}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_hd}"/><c:out value="%"/>;">
      A<c:if test="${armor_hd > 25}">rmor</c:if>: <c:out value="${armor_hd}"/>
    </div>
</div>
       <div class="table-responsive">
           <table class="table table-condensed table-hover">
               
              <c:forEach var="komponentti" items="${head}">
                  <tr>
              <td><c:out value="${komponentti.nimi}"/></td><td>${komponentti.massa}t</td><td>${komponentti.kokoluokkalyhyt}</td>
                  <td><a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a></td>
                  </tr>  
                  </c:forEach>
           </table>
             </div>
  </div>
               
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=hd">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=hd">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=hd">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>

         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=hd">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'HEAD' || komponentti.sijoituspaikka == 'HEAD_TORSO'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=hd">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
                   
              
          </td>
          <td class="dropdown" style="overflow:visible">
               <div class="panel panel-default">
  <div class="panel-heading">RIGHT ARM (${mech.itemsrightarm}/${mech.itemsallowedextension}) <c:if test="${mech.itemsrightarm > mech.itemsallowedextension}">OVERLOADED</c:if></div>
  <div class="panel-body">
      <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_ra}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_ra}"/><c:out value="%"/>;">
      A<c:if test="${armor_ra > 25}">rmor</c:if>: <c:out value="${armor_ra}"/>
    </div>
</div>
      
       <div class="table-responsive">
           <table class="table table-condensed table-hover">
               
              <c:forEach var="komponentti" items="${rightarm}">
                  <tr>
              <td><c:out value="${komponentti.nimi}"/></td><td>${komponentti.massa}t</td><td>${komponentti.kokoluokkalyhyt}</td>
                  <td><a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a></td>
                  </tr>  
                  </c:forEach>
           </table>
             </div>
  </div>
               
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
<%--</ul>--%>
                  
<%--<ul class="nav nav-pills" role="tablist">       --%> 
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Melee<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmelee}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'ARMS' || komponentti.sijoituspaikka == 'ARMS_TORSO' || komponentti.sijoituspaikka == 'ARMS_LEGS'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
           
     
          </td>
      </tr>
      
      <tr>
          <td>
               <div class="panel panel-default">
  <div class="panel-heading">LEFT TORSO (${mech.itemslefttorso}/${mech.itemsallowedtorso}) <c:if test="${mech.itemslefttorso > mech.itemsallowedtorso}">OVERLOADED</c:if></div>
  <div class="panel-body">
      <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_lt}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_lt}"/><c:out value="%"/>;">
      A<c:if test="${armor_lt > 25}">rmor</c:if>:  <c:out value="${armor_lt}"/>
    </div>
</div>
           <table class="table table-condensed table-hover">
               
              <c:forEach var="komponentti" items="${lefttorso}">
                  <tr>
              <td><c:out value="${komponentti.nimi}"/></td><td>${komponentti.massa}t</td><td>${komponentti.kokoluokkalyhyt}</td>
                  <td><a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a></td>
                  </tr>  
                  </c:forEach>
           </table>
             </div>
  </div>
               
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=lt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=lt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=lt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
<%--</ul>--%>
                  
<%--<ul class="nav nav-pills" role="tablist">       --%> 
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=lt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
    
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'ARMS_TORSO' || komponentti.sijoituspaikka == 'HEAD_TORSO' || komponentti.sijoituspaikka == 'ANY_TORSO'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=lt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
           
     
          </td>
          
<td class="dropdown" style="overflow:visible">
               <div class="panel panel-default">
  <div class="panel-heading">CENTER TORSO (${mech.itemscentertorso}/${mech.itemsallowedtorso}) <c:if test="${mech.itemscentertorso > mech.itemsallowedtorso}">OVERLOADED</c:if></div>
  <div class="panel-body">
      <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_ct}"/>" aria-valuemin="0" aria-valuemax="80" style="width: <c:out value="${armor_ct}"/><c:out value="%"/>;">
        A<c:if test="${armor_ct > 25}">rmor</c:if>: <c:out value="${armor_ct}"/>
    </div>
</div>
  <div class="table-responsive">
           <table class="table table-condensed table-hover">
                       <tr>
                     <td>Reactor</td><td>Run km/h</td><td>Power</td><td>Cooling</td><td>Mass</td><td>Size</td></tr>
                       <tr>
                     <td><c:out value="${reaktori.nimi}"/></td><td><c:out value="${mech.runningspeed}"/></td><td>${reaktori.teho}</td><td>${reaktori.cooling}</td><td>${reaktori.massa}t</td><td>${reaktori.kokoluokkalyhyt}</td>
                 </tr>
           </table>
             </div>
                 <ul class="nav nav-pills" role="tablist">
<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-warning-sign"></span> Install New Reactor<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${reaktorit}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&vaihdareaktori=${komponentti.reaktori_id}">${komponentti.nimi} (${komponentti.massa}t) <span class="glyphicon glyphicon-flash"></span>${komponentti.teho} <span class="glyphicon glyphicon-asterisk"></span>${komponentti.cooling}  ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
              
                  </ul>
        </li>                 
        </ul>
               </div>
  <div class="panel-body">
       <div class="table-responsive">
           <table class="table table-condensed table-hover">
               
              <c:forEach var="komponentti" items="${centertorso}">
                  <c:if test="${komponentti.kategoria != 'REAKTORI'}">
                  <tr>
              <td><c:out value="${komponentti.nimi}"/></td><td>${komponentti.massa}t</td><td>${komponentti.kokoluokkalyhyt}</td>
                  <td><a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a></td>
                  </tr>
                  </c:if>
                  </c:forEach>
           </table>
             </div>
  </div>
               
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ct">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ct">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ct">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>

         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ct">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'ARMS_TORSO' || komponentti.sijoituspaikka == 'HEAD_TORSO' || komponentti.sijoituspaikka == 'ANY_TORSO'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ct">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
                   
              
          </td>
          <td class="dropdown" style="overflow:visible">
               <div class="panel panel-default">
  <div class="panel-heading">RIGHT TORSO (${mech.itemsrighttorso}/${mech.itemsallowedtorso}) <c:if test="${mech.itemsrighttorso > mech.itemsallowedtorso}">OVERLOADED</c:if></div>
  <div class="panel-body">
      <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_rt}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_rt}"/><c:out value="%"/>;">
      A<c:if test="${armor_rt > 25}">rmor</c:if>: <c:out value="${armor_rt}"/>
    </div>
</div>
       <div class="table-responsive">
           <table class="table table-condensed table-hover">
               
              <c:forEach var="komponentti" items="${righttorso}">
                  <tr>
              <td><c:out value="${komponentti.nimi}"/></td><td>${komponentti.massa}t</td><td>${komponentti.kokoluokkalyhyt}</td>
                  <td><a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a></td>
                  </tr>  
                  </c:forEach>
           </table>
             </div>
  </div>
               
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
<%--</ul>--%>
                  
<%--<ul class="nav nav-pills" role="tablist">       --%> 
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'ARMS_TORSO' || komponentti.sijoituspaikka == 'HEAD_TORSO' || komponentti.sijoituspaikka == 'ANY_TORSO'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
           
     
          </td>
      </tr>
      
      <tr>
          <td class="dropdown" style="overflow:visible">
               <div class="panel panel-default">
  <div class="panel-heading">LEFT LEG (${mech.itemsleftleg}/${mech.itemsallowedextension}) <c:if test="${mech.itemsleftleg > mech.itemsallowedextension}">OVERLOADED</c:if></div>
  <div class="panel-body">
      <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_ll}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_ll}"/><c:out value="%"/>;">
      A<c:if test="${armor_ll > 25}">rmor</c:if>: <c:out value="${armor_ll}"/>
    </div>
</div>
       <div class="table-responsive">
           <table class="table table-condensed table-hover">
               
              <c:forEach var="komponentti" items="${leftleg}">
                  <tr>
              <td><c:out value="${komponentti.nimi}"/></td><td>${komponentti.massa}t</td><td>${komponentti.kokoluokkalyhyt}</td>
                  <td><a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a></td>
                  </tr>  
                  </c:forEach>
           </table>
             </div>
  </div>
               
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ll">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ll">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ll">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
<%--</ul>--%>
                  
<%--<ul class="nav nav-pills" role="tablist">       --%> 
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ll">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
       
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'ARMS_LEGS'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ll">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
           
     
          </td>
          
          <td></td>
          <td class="dropdown" style="overflow:visible">
               <div class="panel panel-default">
  <div class="panel-heading">RIGHT LEG (${mech.itemsrightleg}/${mech.itemsallowedextension}) <c:if test="${mech.itemsrightleg > mech.itemsallowedextension}">OVERLOADED</c:if></div>
  <div class="panel-body">
      <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_rl}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_rl}"/><c:out value="%"/>;">
      A<c:if test="${armor_rl > 25}">rmor</c:if>: <c:out value="${armor_rl}"/>
    </div>
</div>
       <div class="table-responsive">
           <table class="table table-condensed table-hover">
               
              <c:forEach var="komponentti" items="${rightleg}">
                  <tr>
              <td><c:out value="${komponentti.nimi}"/></td><td>${komponentti.massa}t</td><td>${komponentti.kokoluokkalyhyt}</td>
                  <td><a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a></td>
                  </tr>  
                  </c:forEach>
           </table>
             </div>
  </div>
               
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rl">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rl">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rl">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
<%--</ul>--%>
                  
<%--<ul class="nav nav-pills" role="tablist">       --%> 
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rl">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
                  </ul>
        </li>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'ARMS_LEGS'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rl">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokkalyhyt}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
           
     
          </td>
      </tr>
      
      
      
  </table>
               </div>
          </td>
           <td>
              
              <div class="panel panel-default">
  <div class="panel-heading">Construction Guide</div>
  <div class="panel-body">
      <c:if test="${varoitukset != null}">
      <c:forEach var="varoitus" items="${varoitukset}">
              <div class="alert alert-danger">${varoitus}</div>
                  </c:forEach>
              </c:if>
                  <c:if test="${huomautukset != null}">
              <c:forEach var="huomautus" items="${huomautukset}">
              <div class="alert alert-warning">${huomautus}</div>
                  </c:forEach>
              </c:if>
                  <c:if test="${saavutukset != null}">
                            <c:forEach var="saavutus" items="${saavutukset}">
              <div class="alert alert-success">${saavutus}</div>
                  </c:forEach>
              </c:if>
      
      
       
</div>
            
    
              </div>
          
                            
          </td>
      </tr>
  </table>
              </div>
             


 

<!-- Navbar -->
        

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
<!--<link type="text/css" rel="stylesheet" href="jquery.dropdown.css" />
<script type="text/javascript" src="jquery.dropdown.js"></script>-->

</div>
              </div>
</t:pohja>   