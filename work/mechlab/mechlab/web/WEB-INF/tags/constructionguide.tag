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
                      The <b>Construction Guide</b> advices you on how to make your mech as functional as possible. The advisories is color coded according to severity. While YELLOWs are sometimes unavoidable, ideally a mech only has GREEN advisories.<br>
                      <p>
                          <div class="panel panel-default">
  <div class="panel-body">
      
              <div class="alert alert-danger"><span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span> RED advisories signify major technical faults that prevent the mech from being operational.</div>
               
              <div class="alert alert-warning"><span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span> YELLOW advisories signify minor technical compromises that diminish the mech's abilities, but not operation.</div>
                 
              <div class="alert alert-success"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> GREEN advisories signify a milestone in the mech's operational status.</div>
                
      
      
       
</div>
            
    
              </div>
                      
                      
                  </ul>
        
                  </div>
<%--</ul>--%>