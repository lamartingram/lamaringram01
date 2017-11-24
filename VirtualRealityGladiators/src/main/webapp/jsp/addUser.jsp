<%-- 
    Document   : addUser
    Created on : Nov 14, 2017, 10:18:19 AM
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
        <title>Add User</title>
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
                <h1 style="text-align: center">Add User</h1>
                <form class="form-horizontal" role="form" method="POST" id="addUserForm" action="${pageContext.request.contextPath}/addUser">
                    <div class="form-group">
                        <label for="addUserName" class="control-label col-md-4">Username:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="addUserName" placeholder="Username..." required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addUserPassword" class="control-label col-md-4">Password:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="addUserPassword" placeholder="Password..." required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8" style="color: gold">
                            <input type="checkbox" name="addUserEnabled" value="true" checked>Enable User?<br>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addUserAuthority" class="control-label col-md-4">Authority:</label>
                        <div class="col-md-8">
                            <select required name="addUserAuthority" form="addUserForm">
                                <option value="1">Admin</option>
                                <option value="2">Editor</option>
                                <option value="3">Moderator</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4">
                            <input type="submit" value="Create User" class="btn btn-warning"/>
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