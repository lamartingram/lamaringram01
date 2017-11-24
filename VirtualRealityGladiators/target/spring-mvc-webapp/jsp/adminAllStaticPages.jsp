<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <title>Index Page</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/custom_main.css" rel="stylesheet">
    <style>
        .table-hover tbody tr:hover td {
            background: black;
        }
    </style>
    <body background="${pageContext.request.contextPath}/img/gears_standin.jpg" style="background-size: cover">
        <div class="container-fluid">
            <%@include file="snippet_pageBanner.jsp" %>
            <%@include file="snippet_navbar.jsp" %>
            <div class="col-md-offset-2 col-md-8">
                <h3 style="text-align: center">All Static Pages</h3>
                <table id="adminStaticPages" class="table table-hover" style="overflow-y: auto; height:400px; color:gold">
                    <tr style="font-weight: bold; text-transform: uppercase; color: white">
                        <td>Title</td>
                        <td>User</td>
                        <td>Date</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <c:forEach var="page" items="${allPages}">
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/static/${page.title}">
                                    <c:out value="${page.title}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${page.user.userName}"/>
                            </td>
                            <td>
                                <c:out value="${page.date}"/>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/displayEditStatic/${page.staticPageId}">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/deleteStatic/${page.staticPageId}">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
    <footer class="py-5 bg-dark">
        <div class="container">
            <hr>
            <p class="m-0 text-center text-white">Copyright &copy; PONG 2017</p>
        </div>
    </footer>
</html>
