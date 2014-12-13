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
                      This panel lists all equipment currently installed on this mech. LOC signifies location, NAME is self-explanatory, TIER is the equipment's general quality level (I being the best, III the worst), WEIGHT is self-explanatory and HEAT is the amount of heat the equipment produces when activated (passive-type equipment will generate heat every turn regardless).<br>
                      Please note that any armor plating installed will NOT show up on the equipment list!<br>
                      
                  </ul>
        
                  </div>
<%--</ul>--%>