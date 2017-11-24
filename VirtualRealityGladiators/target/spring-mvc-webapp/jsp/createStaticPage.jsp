
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Editing Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/custom_main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/editingpagecss.css" rel="stylesheet">
        <%@include file="snippet_tinyScript.jsp" %>

    </head>
    <body background="${pageContext.request.contextPath}/img/gears_standin.jpg" style="background-size: cover">
        <div class="container-fluid">
            <%@include file="snippet_pageBanner.jsp" %>
            <%@include file="snippet_navbar.jsp"%>
            <div class="col-md-10">
                <h3 style="text-align:center">Add Static Page</h3>
                <form class="form-horizontal" id="addPageForm" role="fom" action="${pageContext.request.contextPath}/postStaticPage" method="POST">
                    <div class="form-group">
                        <label for="addPageTitle" class="col-md-4 control-label">Title:</label>
                        <div class="col-md-8">
                            <input type="text" name="addPageTitle" class="form-control" placeholder="Title..."/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addPagePost" class="col-md-4 control-label">Post:</label>
                        <div class="col-md-8">
                            <textarea id="blogEditor" name="addPageEditor" class="col-md-12" style="height: 200px"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4">
                            <input type="submit" class="btn btn-warning" value="Add Static Page"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
