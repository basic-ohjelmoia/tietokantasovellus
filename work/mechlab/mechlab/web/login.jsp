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
  <h1><span class="label label-info">MECHLAB!</span> <small>Welcome to the Wonderful World of 31st Century Armored Combat.</small></h1>
</div>
    
    <div class="panel panel-default">
        
  <div class="panel-body">
  Create components from scratch and then use them to build your own BattleMechs. Test the combat capabilities with simulated combat. This is a course study for database programming. An interpretation of Battletech ruleset and BattleMechs are used here for education purposes only.<br>
  <p>
      <b>Created by TUOMAS HONKALA.</b><br>
  </div>
</div>
    LOGIN:<br>
    <p>
    <form action="Login" method="POST">
Username <input type="text" name="username" value="${kayttaja}" />
Password: <input type="password" name="password" />
<button type="submit">Submit</button>
</form>
<p><center><img src="img/bigmech.png"></center>
</t:pohja>
      
