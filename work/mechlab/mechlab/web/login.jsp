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
    
    <form action="Login" method="POST">
Käyttäjänimi: <input type="text" name="username" value="${kayttaja}" />
Salasana: <input type="password" name="password" />
<button type="submit">Kirjaudu</button>
</form>
</t:pohja>
      
