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
                      The drop-down-menu below allows you to choose a reactor for your mech. This is the most vital component of your mech!<br>
                      The reactors are rated for POWER, COOLING, MASS and SIZE.<br>
                      The POWER output can be considered as the most important value, as it determines the mech's movement speed.<br>
                      COOLING is also important as it set the reference level for the mech's ability to dissipate heat. The COOLING value can (and often should) be complemented by installing additional HEAT SINKS.<br>
                      
                  </ul>
        
                  </div>
<%--</ul>--%>