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
                      This panel lists all weapons currently installed on this mech. LOC signifies location, NAME is self-explanatory, DMG is the weapon's damage rating, RNG is the weapon's maximum range, HEAT is the weapon's heat generation per shot and AMMO is the amount of ammunition available for the weapon.<br>
                      Below the weapon list you can find the damage total for all weapons, best maximum range and the calculated heat total for an "alpha strike" (all weapons shot simultaneously). The heat total is compared against the mech's cooling capability ("vs hs").<br>
                      Note that weapons with high damage values are more likely to score component busting "critical hits"!<br>
                  </ul>
        
                  </div>
<%--</ul>--%>