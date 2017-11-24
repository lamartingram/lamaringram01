<%-- 
    Document   : addCategory
    Created on : Nov 13, 2017, 2:48:44 PM
    Author     : adam-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Category</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/custom_main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/editingpagecss.css" rel="stylesheet">
        <%@include file="snippet_tinyScript.jsp" %>

    </head>
    <body background="${pageContext.request.contextPath}/img/gears_standin.jpg" style="background-size: cover">
        <div class="container-fluid">
            <%@include file="snippet_pageBanner.jsp" %>
            <%@include file="snippet_navbar.jsp" %>
            <ul class="list-group" id="errorMessages">
                <c:forEach var="currentMessage" items="${errorMessages}">
                    <li class="list-group-item list-group-item-danger"><c:out value="${currentMessage}"/>
                    </c:forEach>
            </ul>
            <div class="col-md-offset-3 col-md-6">
                <h1 style="text-align: center">Edit Tag</h1>
                <form class="form-horizontal" role="form" method="POST" action="${pageContext.request.contextPath}/editTag">
                    <div class="form-group">
                        <label for="editTagName" class="control-label col-md-4">Name:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="editTagName" value="${tag.hashtag}" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="hidden" value="${tag.tagId}" name="editTagId"/>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4">
                            <input type="submit" value="Edit Tag" class="btn btn-warning"/>
                            <input type="submit" value="Cancel" class="btn btn-warning" formaction="${pageContext.request.contextPath}/allTagsCategories"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <footer class="py-5 bg-dark">
        <div class="container">
            <hr>
            <p class="m-0 text-center text-white">Copyright &copy; PONG 2017</p>
        </div>
        <!-- /.container -->
    </footer>
</html>
