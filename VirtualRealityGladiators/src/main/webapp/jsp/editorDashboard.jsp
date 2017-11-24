<%-- 
    Document   : editorDashboard
    Created on : Nov 13, 2017, 10:26:01 AM
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
        <title>Editor Dashboard</title>
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
            <ul class="list-group" id="errorMessages">
                <c:forEach var="currentMessage" items="${errorMessages}">
                    <li class="list-group-item list-group-item-danger"><c:out value="${currentMessage}"/>
                    </c:forEach>
            </ul>
            <div class="col-md-12">
                <form>
                    <div class="col-md-offset-5 col-md-2">
                        <input type="submit" formaction="${pageContext.request.contextPath}/displayCreateBlog" value="Create Blog" class="btn btn-warning"/>
                    </div>
                </form>
            </div>
            <div class="col-md-8 col-md-offset-2" >
                <h3>Pending Approval</h3>
                <div class="col-md-12">
                    <div style="overflow-y: auto; height: 400px">
                        <table class="table table-hover">
                            <tr style="font-weight: bold; color: white">
                                <td width="25%">Title</td>
                                <td width="50%">Summary</td>
                                <td width="25%">Date</td>
                            </tr>
                            <tbody id="pendingRows" style="color: gold">
                                <c:forEach var="blog" items="${pendingBlogs}">
                                    <tr>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/blog/${blog.blogId}">
                                                <c:out value="${blog.title}"/>
                                            </a>
                                        </td>
                                        <td>
                                            <c:out value="${blog.summary}"/>
                                        </td>
                                        <td>
                                            <c:out value="${blog.date}"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <h3>Rejected Posts</h3>
                <div class="col-md-12">
                    <div style="overflow-y: auto; height: 400px">
                        <table class="table table-hover">
                            <tr style="color: white">
                                <td width="20%">Title</td>
                                <td width="40%">Summary</td>
                                <td width="20%">Date</td>
                                <td width="10%"></td>
                                <td width="10%"></td>
                            </tr>
                            <tbody id="rejectedRows" style="color: gold">
                                <c:forEach var="blog" items="${rejectedBlogs}">
                                    <tr>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/blog/${blog.blogId}">
                                                <c:out value="${blog.title}"/>
                                            </a>
                                        </td>
                                        <td>
                                            <c:out value="${blog.summary}"/>
                                        </td>
                                        <td>
                                            <c:out value="${blog.date}"/>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/displayEditBlog/${blog.blogId}">
                                                Edit
                                            </a>
                                        </td>
                                        <td>
                                            <button class="btn btn-danger" onclick="deleteBlogEditor(${blog.blogId})">Delete Post</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/delete.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
    <footer class="py-5 bg-dark">
        <div class="container">
            <hr>
            <p class="m-0 text-center text-white">Copyright &copy; PONG 2017</p>
        </div>
        <!-- /.container -->
    </footer>
</html>
