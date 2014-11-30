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
            <table class="table">
      <tr>
          <td>id#</td><td>
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
          
          <td><ul class="nav nav-pills" role="tablist">
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
             
             <h2>Components</h2>       
  <table class="table"><table class="table">
      <tr>
          <td>
              <h4>LEFT ARM (${mech.itemsleftarm}/${mech.itemsallowedextension}) <c:if test="${mech.itemsleftarm > mech.itemsallowedextension}">OVERLOADED</c:if></h4>
              <c:forEach var="komponentti" items="${leftarm}">
              <li><c:out value="${komponentti.nimi}"/> (${komponentti.massa}t) ${komponentti.kokoluokka}
                  <%--<a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentti_id}&minne=la"><span class="glyphicon glyphicon-remove"></span></a>--%>
                  <a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a>
              </li>
              
              
                    </c:forEach>
           
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
</ul>
<ul class="nav nav-pills" role="tablist">        
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Melee<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmelee}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'ARMS' || komponentti.sijoituspaikka == 'ARMS_TORSO' || komponentti.sijoituspaikka == 'ARMS_LEGS'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=la">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
               
          </td>
          <td>
              <h4>HEAD (${mech.itemshead}/${mech.itemsallowedextension}) <c:if test="${mech.itemshead > mech.itemsallowedextension}">OVERLOADED</c:if></h4>
              <c:forEach var="komponentti" items="${head}">
              <li><c:out value="${komponentti.nimi}"/> (${komponentti.massa}t) ${komponentti.kokoluokka}
              <a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a>
                    </c:forEach>
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=hd">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=hd">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=hd">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
</ul>
<ul class="nav nav-pills" role="tablist">        
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=hd">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <%--<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Melee<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmelee}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=hd">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
              
                  </ul>
        </li>--%>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'HEAD' || komponentti.sijoituspaikka == 'HEAD_TORSO'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=hd">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
              
              
          </td>
          <td>
              <h4>RIGHT ARM (${mech.itemsrightarm}/${mech.itemsallowedextension})</h4>
              <c:forEach var="komponentti" items="${rightarm}">
              <li><c:out value="${komponentti.nimi}"/> (${komponentti.massa}t) ${komponentti.kokoluokka}
              <a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a>
                    </c:forEach>
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
</ul>
<ul class="nav nav-pills" role="tablist">        
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Melee<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmelee}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'ARMS' || komponentti.sijoituspaikka == 'ARMS_TORSO' || komponentti.sijoituspaikka == 'ARMS_LEGS'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ra">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
          </td>
      </tr>
      <tr>
            <td>
              <h4>LEFT TORSO (${mech.itemslefttorso}/${mech.itemsallowedtorso})</h4>
              <c:forEach var="komponentti" items="${lefttorso}">
              <li><c:out value="${komponentti.nimi}"/> (${komponentti.massa}t) ${komponentti.kokoluokka}
              <a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a>
                    </c:forEach>
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=lt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=lt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=lt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
</ul>
<ul class="nav nav-pills" role="tablist">        
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=lt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'ARMS_TORSO' || komponentti.sijoituspaikka == 'HEAD_TORSO' || komponentti.sijoituspaikka == 'ANY_TORSO'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=lt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
              
              
          </td>
          <td>
              <h4>CENTER TORSO (${mech.itemscentertorso}/${mech.itemsallowedtorso})</h4>
          <li><c:out value="${reaktori.nimi}"/> (${reaktori.massa}t) <span class="glyphicon glyphicon-flash"></span>${reaktori.teho} <span class="glyphicon glyphicon-asterisk"></span>${reaktori.cooling} ${reaktori.kokoluokka}</li>
              <c:forEach var="komponentti" items="${centertorso}">
                  <c:if test="${komponentti.kategoria != 'REAKTORI'}"><li><c:out value="${komponentti.nimi}"/> (${komponentti.massa}t) ${komponentti.kokoluokka}
              <a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a>
                  </c:if>
                    </c:forEach>
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ct">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ct">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ct">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
</ul>
<ul class="nav nav-pills" role="tablist">        
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ct">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Reactor<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${reaktorit}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&vaihdareaktori=${komponentti.reaktori_id}">${komponentti.nimi} (${komponentti.massa}t) <span class="glyphicon glyphicon-flash"></span>${komponentti.teho} <span class="glyphicon glyphicon-asterisk"></span>${komponentti.cooling}  ${komponentti.kokoluokka}</a></li>
                </c:forEach>
              
                  </ul>
        </li>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'ARMS_TORSO' || komponentti.sijoituspaikka == 'HEAD_TORSO' || komponentti.sijoituspaikka == 'ANY_TORSO'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ct">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
              
              
          </td>
        <td>
              <h4>RIGHT TORSO (${mech.itemsrighttorso}/${mech.itemsallowedtorso})</h4>
              <c:forEach var="komponentti" items="${righttorso}">
              <li><c:out value="${komponentti.nimi}"/> (${komponentti.massa}t) ${komponentti.kokoluokka}
              <a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a>
                    </c:forEach>
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
</ul>
<ul class="nav nav-pills" role="tablist">        
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'NOT_LEGS' || komponentti.sijoituspaikka == 'ARMS_TORSO' || komponentti.sijoituspaikka == 'HEAD_TORSO' || komponentti.sijoituspaikka == 'ANY_TORSO'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rt">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
              
              
          </td>
      </tr>
      <tr>
          <td>
              <h4>LEFT LEG ( <c:if test="${mech.itemsleftleg > mech.itemsallowedextension}">!</c:if>${mech.itemsleftleg}<c:if test="${mech.itemsleftleg > mech.itemsallowedextension}">!</c:if>/${mech.itemsallowedextension})</h4>
              <c:forEach var="komponentti" items="${leftleg}">
              <li><c:out value="${komponentti.nimi}"/> (${komponentti.massa}t) ${komponentti.kokoluokka}
                  <a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a>
              </li>
              
              
                    </c:forEach>
           
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ll">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ll">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ll">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
</ul>
<ul class="nav nav-pills" role="tablist">        
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ll">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'ARMS_LEGS'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=ll">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
               
          </td>
          <td>
          </td>
          <td>
              <h4>RIGHT LEG ( <c:if test="${mech.itemsrightleg > mech.itemsallowedextension}">!</c:if>${mech.itemsrightleg}<c:if test="${mech.itemsrightleg > mech.itemsallowedextension}">!</c:if>/${mech.itemsallowedextension})</h4>
              <c:forEach var="komponentti" items="${rightleg}">
              <li><c:out value="${komponentti.nimi}"/> (${komponentti.massa}t) ${komponentti.kokoluokka}
                  <a href="mechasennakomponentti?mechid=${mech.mech_id}&poista=${komponentti.komponentisto_id}"><span class="glyphicon glyphicon-remove"></span></a>
              </li>
              
              
                    </c:forEach>
           
<ul class="nav nav-pills" role="tablist">
  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Auto<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetauto}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rl">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Energy<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetenergy}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rl">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kinetic<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetkinetic}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rl">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
</ul>
<ul class="nav nav-pills" role="tablist">        
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Missile<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${aseetmissile}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rl">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
                </c:forEach>
                  </ul>
        </li>
         <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Equipment<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
              <c:forEach var="komponentti" items="${varusteet}">
                  <c:if test="${komponentti.sijoituspaikka == 'ALL' || komponentti.sijoituspaikka == 'ARMS_LEGS'}">
            <li ><a href="mechasennakomponentti?mechid=${mech.mech_id}&lisaa=${komponentti.komponentti_id}&minne=rl">${komponentti.nimi} (${komponentti.massa}t) ${komponentti.kokoluokka}</a></li>
            </c:if>
                </c:forEach>
                  </ul>
        </li>
        </ul>
               
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
</t:pohja>   