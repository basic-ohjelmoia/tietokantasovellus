<%-- 
    Document   : pohja
    Created on : 14.11.2014, 0:38:14
    Author     : mikromafia
--%>
<%@tag description="Generic template for Mechlab pages" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="pageTitle"%>
<!DOCTYPE html>
<%--<ul class="nav nav-pills" role="tablist">--%>
<div class="btn-group">
<button class="btn btn-default btn-xs dropdown-toggle" 
		        type="button" data-toggle="dropdown">
    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span><span class="caret"></span></button>
                  <ul class="dropdown-menu" role="menu">
                      <table>
                          <tr><td>Volume</td><td>Units</td></tr>
           <c:forEach var="komponentti" items="${leftarm}"><tr>
               <td>${komponentti.kokoluokka}</td><td>
                  <c:if test="${komponentti.kokoluokkalyhyt == 'Sm'}">= 1 vol</c:if>
                  <c:if test="${komponentti.kokoluokkalyhyt == 'Md'}">= 2 vol</c:if>
                  <c:if test="${komponentti.kokoluokkalyhyt == 'Lg'}">= 3 vol</c:if>
                  <c:if test="${komponentti.kokoluokkalyhyt == 'Xl'}">= 5 vol</c:if>
                   </td></tr> </c:forEach>
               <tr>
               <td><b>Total</b></td><td>${mech.itemsleftarm} / ${mech.itemsallowedextension} vol</td>
               </tr>
               </table>
                  </ul>
        
                  </div>
<%--</ul>--%>