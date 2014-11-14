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
          <nav class="navbar navbar-inverse" role="navigation">
  <ul class="nav nav-tabs">
    <li class="active"><a href="#">MechLab.SELAA</a></li>
    <li><a href="main_1_1.html">MechLab.RAKENNA</a></li>
    <li><a href="main_1_1_2.html">Admin.YLLÄPITO</a></li>
    <li><a href="#">Käyttäjä.ASETUKSET</a></li>
    <li><a href="#">Käyttäjä.KIRJAUDU</a></li>
  </ul>
          </nav>
            
        <div class="container">
            <c:if test="${virheViesti != null}">
                <div class="alert alert-danger">Virhe! ${virheViesti}</div>
            </c:if>
            
            
            <jsp:doBody/>
        </div>
    </body>
</html>