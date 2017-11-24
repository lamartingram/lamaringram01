<%-- 
    Document   : editUser
    Created on : Nov 14, 2017, 11:29:17 AM
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
                <form class="form-horizontal" role="form" method="POST" id="editUserForm" action="${pageContext.request.contextPath}/editUser">
                    <div class="form-group">
                        <label for="editUserName" class="control-label col-md-4">Username:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="editUserName" value="${user.userName}" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8" style="color: gold">
                            <c:choose>
                                <c:when test="${user.enabled}">
                                    <input type="checkbox" name="editUserEnabled" checked value="true">Enable User?<br>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" name="editUserEnabled" value="true">Enable User?<br>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editUserAuthority" class="control-label col-md-4">Authority:</label>
                        <div class="col-md-8">
                            <select required name="editUserAuthority" form="editUserForm">
                                <c:choose>
                                    <c:when test="${user.authority eq 'ROLE_ADMIN'}">
                                        <option value="1" selected>Admin</option>
                                        <option value="2">Editor</option>
                                        <option value="3">Moderator</option>
                                    </c:when>
                                    <c:when test="${user.authority eq 'ROLE_EDITOR'}">
                                        <option value="1">Admin</option>
                                        <option value="2" selected>Editor</option>
                                        <option value="3">Moderator</option>
                                    </c:when>
                                    <c:when test="${user.authority eq 'ROLE_MODERATOR'}">
                                        <option value="1">Admin</option>
                                        <option value="2">Editor</option>
                                        <option value="3" selected>Moderator</option>
                                    </c:when>
                                </c:choose>
                            </select>
                        </div>
                    </div>
                        <div class="form-group">
                            <input type="hidden" value="${user.userId}" name="editUserId"/>
                        </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-2">
                            <input type="submit" value="Edit User" class="btn btn-warning"/>
                        </div>
                        <div class="col-md-2">
                            <input type="submit" formaction="${pageContext.request.contextPath}/adminUsers" value="Cancel"/>
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