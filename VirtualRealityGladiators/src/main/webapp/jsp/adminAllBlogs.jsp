<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <title>All Blogs</title>
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
                <h1 style="text-align: center">All Blogs</h1>
                <table id="adminAllBlogs" class="table table-hover" style="overflow-y: auto; height:400px; color:gold">
                    <tr style="font-weight: bold; text-transform: uppercase; color: white">
                        <td width="15%">Title</td>
                        <td width="10">Date</td>
                        <td width="10%">User</td>
                        <td width="35%">Summary</td>
                        <td width="10%">Approved</td>
                        <td width="10%"></td>
                        <td width="10%"></td>
                    </tr>
                    <tbody id="blogRows">
                        <c:forEach var="blog" items="${allBlogs}">
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/blog/${blog.blogId}">
                                        <c:out value="${blog.title}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${blog.date}"/>
                                </td>
                                <td>
                                    <c:out value="${blog.user.userName}"/>
                                </td>
                                <td>
                                    <c:out value="${blog.summary}"/>
                                </td>
                                <td>
                                    <c:out value="${blog.approved}"/>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/displayEditBlog/${blog.blogId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <button class="btn btn-danger" onclick="deleteBlog(${blog.blogId})">Delete Post</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
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
