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
                      The drop-down-menus under the "ADD A NEW COMPONENT" label allow you to pick and choose new components (i.e. weapons and equipment) for your mech. All components are installed per location.<br>
                      Please note that not all components are available for all locations - for example: a cockpit can be only installed on the mech's head.<br>
                      The component's physical volume must also be taken into account. Generally speaking the extremities (head, arms and legs) allow for less components to be installed than the torso locations.<br>
                      SM sized components will require only 1 unit of volume while XL sized components require 5 units of volume.<br>
                  </ul>
        
                  </div>
<%--</ul>--%>