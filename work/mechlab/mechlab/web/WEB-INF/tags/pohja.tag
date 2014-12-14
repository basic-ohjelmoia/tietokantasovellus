<%-- 
    Document   : pohja
    Created on : 14.11.2014, 0:38:14
    Author     : mikromafia
--%>
<%@tag description="Generic template for Mechlab pages" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="pageTitle"%>
<!DOCTYPE html>
<html>
    <head>
      
<!--<script src="../js/bootstrap.min.js"></script>-->



        
        <title></title>
          <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
               <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    
      <body>
          <%--<c:if test="${kirjautuneenNimi != null}">--%>
              
              
              
              
          <%-- </c:if><c:if test="${kirjautuneenNimi == null}">Et ole kirjautunut sisään!</c:if>--%>
          <nav class="navbar navbar-inverse" role="navigation">
  <ul class="nav nav-tabs">
    <li class="${navimechselaa}"><a href="mechselaa">MechLab.BROWSE</a></li>
    <%--<c:if test="${naviadmin != null}"><li class="${naviyllapito}"><a href="komponenttiselaa">Mechlab.COMPONENTS</a></li></c:if>--%>
    <li class="${naviyllapito}"><a href="komponenttiselaa">Mechlab.COMPONENTS</a></li>
<%--    <li><a href="#">User.SETTINGS</a></li>--%>
    <li class="${navilogin}"><a href="${naviloginosoite}">User.${naviloginmoodi}</a></li>
    <li><c:if test="${kirjautuneenNimi != null}"><span class="label label-default">Welcome, ${kirjautuneenNimi}! This is your visit number ${vierailukerta}.</span></c:if><c:if test="${kirjautuneenNimi == null}"><span class="label label-info">You are not logged in!</span></c:if></li>
  </ul>
          </nav>
            
        <div class="container">
            <c:if test="${virheViesti != null}">
                <div class="alert alert-danger">${virheViesti}</div>
            </c:if>
                <c:if test="${ilmoitus != null}">
                <div class="alert alert-danger">${ilmoitus}</div>
            </c:if>  
        <c:if test="${allcomponents != null || asekomponentit != null || mechit != null}">
        </div>            
        </c:if>
            <jsp:doBody/>
        <c:if test="${allcomponents == null || asekomponentit != null || mechit != null}">
        </div>            
        </c:if>
    </body>
</html>