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
        <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
        
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    
      <body>
          <%--<c:if test="${kirjautuneenNimi != null}">--%>
              
              
              
              
          <%-- </c:if><c:if test="${kirjautuneenNimi == null}">Et ole kirjautunut sisään!</c:if>--%>
          <nav class="navbar navbar-inverse" role="navigation">
  <ul class="nav nav-tabs">
    <li class="${navimechselaa}"><a href="/mechselaa">MechLab.SELAA</a></li>
    <li><a href="main_1_1.html">MechLab.RAKENNA</a></li>
    <c:if test="${naviadmin != null}"><li><a href="main_1_1_2.html">Admin.YLLÄPITO</a></li></c:if>
    <li><a href="#">Käyttäjä.ASETUKSET</a></li>
    <li class="${navilogin}"><a href="${naviloginosoite}">Käyttäjä.${naviloginmoodi}</a></li>
    <li><c:if test="${kirjautuneenNimi != null}"><span class="label label-default">Tervetuloa, ${kirjautuneenNimi}! Tämä on ${vierailukerta}. vierailusi.</span></c:if><c:if test="${kirjautuneenNimi == null}"><span class="label label-info">Et ole kirjautunut sisään!</span></c:if></li>
  </ul>
          </nav>
            
        <div class="container">
            <c:if test="${virheViesti != null}">
                <div class="alert alert-danger">${virheViesti}</div>
            </c:if>
           
            
            
            <jsp:doBody/>
        </div>
    </body>
</html>