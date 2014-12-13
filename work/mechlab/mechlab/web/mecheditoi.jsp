<%-- 
    Document   : sivu
    Created on : 14.11.2014, 0:29:37
    Author     : mikromafia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Mechlab! Create a Mech!">
 
<div class="page-header">
  <h1><span class="label label-info">The Mechlab</span><small>View and construct your mechs here.</small></h1>
</div>
    
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
        <c:if test="${naviadmin == null && userid != mech.user_id}">
            <ul class="nav nav-pills" role="tablist">
            <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Simulate Combat<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <li>Simulate combat versus...</li>
              
               <c:forEach var="mech2" items="${mechit}">
          
          <c:if test="${mech.nettopaino > 0 && mech.varoitusvapaa == true}">
             
                <li><a href="mechsimuloi?attacker=${mech.mech_id}&defender=${mech2.mech_id}">${mech2.paino}t ${mech2.nimi} (${mech2.weaponrating}WR/${mech2.defencerating}DR/${mech2.armorrating}AR)</a></li>
                
                </c:if>
               </c:forEach>
                  </ul>
        </li></ul>
        </c:if>
          
        <c:if test="${naviadmin != null || userid == mech.user_id}">  
           <ul class="nav nav-pills" role="tablist">
               <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Rename Mech<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
               <form action="mechedit" method="POST" role="form">
                
  <div class="form-group">
      <input type="hidden" name="mechid" value="${mech.mech_id}"/>
    <input type="nimi" class="form-control" name="mechnimi" id="mechnimi" value="${mech.nimi}">
  </div><button type="submit" class="btn btn-default">Rename Mech</button>
              </form></ul>  </li>
               <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Change Weight Class<span class="caret"></span></a>
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
        <%--<li role="presentation" class="disabled"><a href="mechluouusi?kopioi=${mech.mech_id}">Copy As A New Prototype</a></li>--%>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Simulate Combat<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <li>Simulate combat versus...</li>
              
               <c:forEach var="mech2" items="${mechit}">
          
          <c:if test="${mech.nettopaino > 0 && mech.varoitusvapaa == true}">
             
                <li><a href="mechsimuloi?attacker=${mech.mech_id}&defender=${mech2.mech_id}">${mech2.paino}t ${mech2.nimi} (${mech2.weaponrating}WR/${mech2.defencerating}DR/${mech2.armorrating}AR)</a></li>
                
                </c:if>
               </c:forEach>
                  </ul>
        </li>
        <li role="presentation"><a href="mechselaa">Save & Exit</a></li>
        
        </ul>
          <p>
          </c:if>
              
         <div class="table-responsive">
            <table class="table table-hover" width="100%">
<!--      <tr>
          <td>id#</td>
          <td>Mech Name</td>
          
          
          <td>Weight Class</td>
          
          
          <td>Weapon Rating</td><td>Defence Rating</td><td>Armor Value</td><td>Heat Sinks</td>
          <td>Walk (Run) Speed</td><td>Jump Rating</td><td>Owner</td><td>Unit Cost</td>
      </tr>-->
      <t:mechselaaetiketit/>
      <%--<c:forEach var="mech" items="${mechit}">--%>
                                  
  
            
    
     
        <tr>
                <td>${mech.mech_id}</td>
                <td><span class="label label-default"><c:out value="${mech.nimi}"/></span></td>
                <td><c:out value="${mech.nettopaino}"/>t / <c:out value="${mech.paino}"/>t</td>
                <td><c:out value="${mech.weaponrating}"/></td>
                <td><c:out value="${mech.defencerating}"/></td>
                <td><c:out value="${mech.armorrating}"/></td>
                <td><c:out value="${mech.heatsinks}"/></td>
                <td><c:out value="${mech.walkingspeed}"/> km/h</td>
                <td><c:out value="${mech.runningspeed}"/> km/h</td>
                <td><c:out value="${mech.jumprating}"/> m</td>
                <td><c:out value="${mech.ownername}"/></td>
                <td><c:out value="${mech.coststring}"/> CR</td>
                <td> <c:if test="${mech.varoitusvapaa == false && mech.nettopaino==0}">Pre-production</c:if>
                    <c:if test="${mech.varoitusvapaa == false && mech.nettopaino>0}">Prototype</c:if>
                    <span class="label label-success"><c:if test="${mech.varoitusvapaa == true}">Operational</span> <span class="glyphicon glyphicon-ok" aria-hidden="true"></span></c:if>
                </td>
                <td></td>
                <td></td>
                    
                    
                 
                 </tr></div>
                <%-- </c:forEach>--%>
  </table>
         </div>
  
  
   <div class="panel panel-default">
        <div class="panel-heading"><h3><span class="label label-default">${mech.nimi}: ${mech.paino} ton ${mech.mechclass} Mech</span></h3></div>
  <div class="panel-body"></div>
</div>
              
              <div class="table-responsive">
                  
  <table class="table table-hover" width="100%">
      <tr>
          <td>
              
              <div class="panel panel-default">
                  <div class="panel-heading">Weapons Installed <t:weaponsinstalled></t:weaponsinstalled></div>
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
            
                 
                      <div class="panel-heading">Equipment Installed <t:equipmentinstalled></t:equipmentinstalled></div>
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

                               
                      <div class="panel-heading">Armor Values <t:armorvalues></t:armorvalues></div>
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
  <div class="panel-heading">LEFT ARM (${mech.itemsleftarm}/${mech.itemsallowedextension}) <c:if test="${mech.itemsleftarm > mech.itemsallowedextension}">OVERLOADED</c:if> <t:volumeLA></t:volumeLA>   
</div>
  <div class="panel-body">
           <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_la}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_la}"/><c:out value="%"/>;">
      <c:if test="${armor_la > 11}">A<c:if test="${armor_la > 18}">rmor</c:if>:</c:if> <c:out value="${armor_la}"/>
  </div>
      <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="<c:out value="${is_la}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${is_la}"/><c:out value="%"/>;">
          <c:if test="${is_la > 11}">S<c:if test="${is_la > 18}">tructure</c:if>:</c:if> <c:out value="${is_la}"/>
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
    <span class="label label-default">ADD A NEW COMPONENT</span> <t:addnewcomponent></t:addnewcomponent>
               
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
           
     </div>
          </td>
          
<td>
               <div class="panel panel-default">
  <div class="panel-heading">HEAD (${mech.itemshead}/${mech.itemsallowedextension}) <c:if test="${mech.itemshead > mech.itemsallowedextension}">OVERLOADED</c:if> <t:volumeHD></t:volumeHD></div>
  <div class="panel-body">
           <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_hd}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_hd}"/><c:out value="%"/>;">
      <c:if test="${armor_hd > 11}">A<c:if test="${armor_hd > 18}">rmor</c:if>:</c:if> <c:out value="${armor_hd}"/>
    </div>
    <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="<c:out value="${is_hd}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${is_hd}"/><c:out value="%"/>;">
      <c:if test="${is_hd > 11}">S<c:if test="${is_hd > 18}">truct.</c:if>:</c:if> <c:out value="${is_hd}"/>
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
    <span class="label label-default">ADD A NEW COMPONENT</span> <t:addnewcomponent></t:addnewcomponent>
               
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
                   
              </div>
          </td>
          <td class="dropdown" style="overflow:visible">
               <div class="panel panel-default">
  <div class="panel-heading">RIGHT ARM (${mech.itemsrightarm}/${mech.itemsallowedextension}) <c:if test="${mech.itemsrightarm > mech.itemsallowedextension}">OVERLOADED</c:if> <t:volumeRA></t:volumeRA></div>
  <div class="panel-body">
      <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_ra}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_ra}"/><c:out value="%"/>;">
        <c:if test="${armor_ra > 11}">A<c:if test="${armor_ra > 18}">rmor</c:if>:</c:if> <c:out value="${armor_ra}"/>
    </div>
    <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="<c:out value="${is_ra}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${is_ra}"/><c:out value="%"/>;">
      <c:if test="${is_ra > 11}">S<c:if test="${is_ra > 18}">truct.</c:if>:</c:if> <c:out value="${is_ra}"/>
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
    
            <span class="label label-default">ADD A NEW COMPONENT</span> <t:addnewcomponent></t:addnewcomponent>   
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
           
     </div>
          </td>
      </tr>
      
      <tr>
          <td>
               <div class="panel panel-default">
  <div class="panel-heading">LEFT TORSO (${mech.itemslefttorso}/${mech.itemsallowedtorso}) <c:if test="${mech.itemslefttorso > mech.itemsallowedtorso}">OVERLOADED</c:if><t:volumeLT></t:volumeLT></div>
  <div class="panel-body">
      <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_lt}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_lt}"/><c:out value="%"/>;">
      <c:if test="${armor_lt > 11}">A<c:if test="${armor_lt > 18}">rmor</c:if>:</c:if> <c:out value="${armor_lt}"/>
    </div>
    <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="<c:out value="${is_lt}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${is_lt}"/><c:out value="%"/>;">
      <c:if test="${is_lt > 11}">S<c:if test="${is_lt > 18}">truct.</c:if>:</c:if> <c:out value="${is_lt}"/>
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
  <span class="label label-default">ADD A NEW COMPONENT</span> <t:addnewcomponent></t:addnewcomponent>
               
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
           
     </div>
          </td>
          
<td class="dropdown" style="overflow:visible">
               <div class="panel panel-default">
  <div class="panel-heading">CENTER TORSO (${mech.itemscentertorso}/${mech.itemsallowedtorso}) <c:if test="${mech.itemscentertorso > mech.itemsallowedtorso}">OVERLOADED</c:if><t:volumeCT></t:volumeCT></div>
  <div class="panel-body">
      <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_ct}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_ct}"/><c:out value="%"/>;">
      <c:if test="${armor_ct > 11}">A<c:if test="${armor_ct > 18}">rmor</c:if>:</c:if> <c:out value="${armor_ct}"/>
    </div>
    <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="<c:out value="${is_ct}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${is_ct}"/><c:out value="%"/>;">
      <c:if test="${is_ct > 11}">S<c:if test="${is_ct > 18}">truct.</c:if>:</c:if> <c:out value="${is_ct}"/>
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
  </div>
        
                     <span class="label label-default"><c:if test="${reaktori.teho > 10}">CHANGE REACTOR TYPE</c:if><c:if test="${reaktori.teho < 10 || reaktori == null}">INSTALL A NEW REACTOR</c:if> </span><c:if test="${reaktori.teho < 10}"><span class="glyphicon glyphicon-warning-sign"></span></c:if>  <t:newreactor></t:newreactor>
                 <ul class="nav nav-pills" role="tablist">
<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Reactor<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${reaktorit}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&vaihdareaktori=${komponentti.reaktori_id}">${komponentti.nimi} (${komponentti.massa}t) <span class="glyphicon glyphicon-flash"></span>${komponentti.teho} <span class="glyphicon glyphicon-asterisk"></span>${komponentti.cooling} <span class="glyphicon glyphicon-inbo"></span>${komponentti.kokoluokkalyhyt}</a></li>
                </c:forEach>
              
                  </ul>
        </li>                 
        </ul>
               
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
                 
                     <span class="label label-default">ADD A NEW COMPONENT</span> <t:addnewcomponent></t:addnewcomponent> 
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
     
              </div>
          </td>
          <td class="dropdown" style="overflow:visible">
               <div class="panel panel-default">
  <div class="panel-heading">RIGHT TORSO (${mech.itemsrighttorso}/${mech.itemsallowedtorso}) <c:if test="${mech.itemsrighttorso > mech.itemsallowedtorso}">OVERLOADED</c:if><t:volumeRT></t:volumeRT></div>
  <div class="panel-body">
      <div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_rt}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_rt}"/><c:out value="%"/>;">
      <c:if test="${armor_rt > 11}">A<c:if test="${armor_rt > 18}">rmor</c:if>:</c:if> <c:out value="${armor_rt}"/>
    </div>
    <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="<c:out value="${is_rt}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${is_rt}"/><c:out value="%"/>;">
      <c:if test="${is_rt > 11}">S<c:if test="${is_rt > 18}">truct.</c:if>:</c:if> <c:out value="${is_rt}"/>
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
               <span class="label label-default">ADD A NEW COMPONENT</span> <t:addnewcomponent></t:addnewcomponent>
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
           
               </div>
          </td>
      </tr>
      
      <tr>
          <td class="dropdown" style="overflow:visible">
               <div class="panel panel-default">
  <div class="panel-heading">LEFT LEG (${mech.itemsleftleg}/${mech.itemsallowedextension}) <c:if test="${mech.itemsleftleg > mech.itemsallowedextension}">OVERLOADED</c:if><t:volumeLL></t:volumeLL></div>
  <div class="panel-body">
      <div class="progress">
   <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_ll}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_ll}"/><c:out value="%"/>;">
      <c:if test="${armor_ll > 11}">A<c:if test="${armor_ll > 18}">rmor</c:if>:</c:if> <c:out value="${armor_ll}"/>
    </div>
    <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="<c:out value="${is_ll}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${is_ll}"/><c:out value="%"/>;">
      <c:if test="${is_ll > 11}">S<c:if test="${is_ll > 18}">truct.</c:if>:</c:if> <c:out value="${is_ll}"/>
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
               <span class="label label-default">ADD A NEW COMPONENT</span> <t:addnewcomponent></t:addnewcomponent>
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
           
               </div>
          </td>
          
          <td></td>
          <td class="dropdown" style="overflow:visible">
               <div class="panel panel-default">
  <div class="panel-heading">RIGHT LEG (${mech.itemsrightleg}/${mech.itemsallowedextension}) <c:if test="${mech.itemsrightleg > mech.itemsallowedextension}">OVERLOADED</c:if><t:volumeRL></t:volumeRL></div>
  <div class="panel-body">
      <div class="progress">
   <div class="progress-bar" role="progressbar" aria-valuenow="<c:out value="${armor_rl}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${armor_rl}"/><c:out value="%"/>;">
      <c:if test="${armor_rl > 11}">A<c:if test="${armor_rl > 18}">rmor</c:if>:</c:if> <c:out value="${armor_rl}"/>
    </div>
    <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="<c:out value="${is_rl}"/>" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${is_rl}"/><c:out value="%"/>;">
      <c:if test="${is_rl > 11}">S<c:if test="${is_rl > 18}">truct.</c:if>:</c:if> <c:out value="${is_rl}"/>
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
               <span class="label label-default">ADD A NEW COMPONENT</span> <t:addnewcomponent></t:addnewcomponent>
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
           
               </div>
          </td>
      </tr>
      
      
      
  </table>
               </div>
          </td>
           <td>
              
              <div class="panel panel-default">
                  <div class="panel-heading">Construction Guide <t:constructionguide></t:constructionguide></div>
  <div class="panel-body">
      <c:if test="${varoitukset != null}">
      <c:forEach var="varoitus" items="${varoitukset}">
              <div class="alert alert-danger"><span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span> ${varoitus}</div>
                  </c:forEach>
              </c:if>
                  <c:if test="${huomautukset != null}">
              <c:forEach var="huomautus" items="${huomautukset}">
              <div class="alert alert-warning"><span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span> ${huomautus}</div>
                  </c:forEach>
              </c:if>
                  <c:if test="${saavutukset != null}">
                            <c:forEach var="saavutus" items="${saavutukset}">
              <div class="alert alert-success"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> ${saavutus}</div>
                  </c:forEach>
              </c:if>
      
      
       
</div>
            
    
              </div>
          
                            
          </td>
      </tr>
  </table>
              </div>
             


 

<!-- Navbar -->
        

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
<!--<link type="text/css" rel="stylesheet" href="jquery.dropdown.css" />
<script type="text/javascript" src="jquery.dropdown.js"></script>-->

</div>
              </div>
</t:pohja>   