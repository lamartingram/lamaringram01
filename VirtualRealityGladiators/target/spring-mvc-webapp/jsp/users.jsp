<%-- 
    Document   : Users
    Created on : Nov 14, 2017, 10:00:44 AM
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
        <title>Users</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/custom_main.css" rel="stylesheet">
        <style>
            .table-hover tbody tr:hover td {
                background: black;
            }
        </style>
    </head>
    <body background="${pageContext.request.contextPath}/img/gears_standin.jpg" style="background-size: cover">
        <div class="container-fluid">
            <%@include file="snippet_pageBanner.jsp" %>
            <%@include file="snippet_navbar.jsp" %>
            <div class="col-md-8 col-md-offset-2" style="background-color: rgba(0,0,0,0.2)" >

                <div class="col-md-8 col-md-offset-2" style="margin-bottom: 10px;">
                    <div style="float: left" >
                        <h3 style="color:gold"> Users</h3>
                    </div>
                    <div style="float: right">
                        <form>

                            <input type="submit" formaction="${pageContext.request.contextPath}/displayAddUser" value="Add User" class="btn btn-warning"/>
                        </form>
                    </div>
                    <div style="clear: both" />
                </div>

            </div>
            <div class="col-md-8 col-md-offset-2">
                <table class="table table-hover">
                    <tr style="color: white; font-weight: bold">
                        <td>Username</td>
                        <td>Enabled</td>
                        <td>Authority</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr style="color: gold">
                            <td><c:out value="${user.userName}"/></td>
                            <td><c:out value="${user.enabled}"/></td>
                            <td><c:out value="${user.authority}"/></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/displayEditUser/${user.userId}">
                                    Edit
                                </a>
                            </td>
                        </c:forEach>
                </table>
            </div>
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
