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
                      This panel lists the current armor values and internal structure values for the mech. The values are listed for each location of the mech.<br>
                      <b>Armor:</b> While every mech is offered some rudimentary armor based on it's weight class (tonnage), the armor values are primarily raised by installing ARMOR PLATNG.<br>
                      <b>Internal Structure:</b> Internal Structure is based on the mech's weight class (tonnage) and the amount of components installed on each mech location. Any armor plating will NOT count towards internal structure values.<br>
                      <b>How damage is applied:</b> Any hits will always damage the local armor first. Once the armor has been chipped away, any further damage will be deducted from the internal structure. Whenever internal structure takes a hit, components in that location will be destroyed accordingly. Once all internal structure is lost, the whole location is considered to be destroyed.<br>
                      <b>How damage is transfered:</b> Damage to arms transfers to the side torsos, damage to legs and the side torsos transfers to the center torso. Damage to head is not transferable. If a torso of either side is destroyed, the arm attached to that side is automatically lost.<br>
                      <b>Destruction:</b> A mech can be properly destroyed in two ways: 1) By destroying the center torso and/or the reactor. 2) By destroying the head and/or the cockpit.<br> 
                  </ul>
        
                  </div>
<%--</ul>--%>