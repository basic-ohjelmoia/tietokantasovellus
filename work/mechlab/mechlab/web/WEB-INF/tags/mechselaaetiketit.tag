<%-- 
    Document   : pohja
    Created on : 14.11.2014, 0:38:14
    Author     : mikromafia
--%>
<%@tag description="Generic template for Mechlab pages" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="pageTitle"%>
<!DOCTYPE html>

    <tr>
  <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">id <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The id number of this mech.</li></ul></li></ul></td>
  <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Name <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>The name of this mech.</li></ul></li></ul></td>                  
  <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Weight <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Weight of this mech. Two weight values separated by a slash indicates that thee mech has some unused weight allowance.</li></ul></li></ul></td>                  
<td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Weapon Rating <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Weapon Rating is designates the general offensive capability of the mech. It factors in several parameters, including the maximum weapon damage and risk of overheating. Higher the value, the better. </li></ul></li></ul></td>                  
<td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Defense Rating <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Defense Rating is designates the general defensive capability of the mech. It factors in several parameters, including the armor value and movement speed. A high armor value doesn't automatically guarantee a high defense rating!</li></ul></li></ul></td>                  
  <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Armor Value <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Total armor value of the mech. This number is primarily raised by installing armor plating. High weight class of the mech will also offer some extra protection.</li></ul></li></ul></td>                                    
 <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Heat Sinks <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Heat Sinks value indicates how many units of heat the mech can shed each turn. This number is a combination of the reactor cooling value and the installed heat sinks. The mech will overheat when the heat level reaches beyond 30 points. Higher the number, the better!</li></ul></li></ul></td>
 <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Walking Speed <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Maximum walking speed of the mech.</li></ul></li></ul></td>                                    
 <td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Running Speed <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Maximum running speed of the mech.</li></ul></li></ul></td>                                    
<td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Jump Rating <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Jump Rating indicates the maximum air distance traversable by a single jump. Jump Rating is increased by installing Jump Jets.</li></ul></li></ul></td>                                                      
<td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Owner <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                  <li>Name of the mech's owner.</li></ul></li></ul></td>                                                      
<td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Cost <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                <li>Total unit cost of the mech (in galactic credits).</li></ul></li></ul></td>                                                      
<td><ul class="nav nav-pills" role="tablist"><li class="dropdown"><button class="btn btn-default btn-xs dropdown-toggle" type="button" data-toggle="dropdown">Status <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></button><ul class="dropdown-menu" role="menu">
                <li>Mechs have three levels of operational status:</li><li><b>Pre-Production</b> is a mech that has no installed components.</li><li><b>Prototype</b> is a mech that's yet to reach operational status. If a mech has even a single RED issue in it's construction guide, it's considered to be a prototype.</li><li><b>Operational</b> is a mech that's ready for action. A mech with no RED issues in it's construction guide is considered operational.</li></ul></li></ul></td>                                                      
<td></td><td></td></tr>

    
