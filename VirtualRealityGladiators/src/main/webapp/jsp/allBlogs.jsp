<%-- 
    Document   : allBlogs
    Created on : Nov 10, 2017, 1:14:15 PM
    Author     : adam-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Blog Post </title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/custom_main.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/blog-post.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/img/gears_standin.jpg" style="background-size: cover">
        <%@include file="snippet_pageBanner.jsp" %>
        <%@include file="snippet_navbar.jsp" %>
        <%@include file="snippet_sampleBlog.jsp" %>
        <!-- Page Content -->
        
    </body>
<!--    <footer class="py-5 bg-dark">
        <div class="container">
            <hr>
            <p class="m-0 text-center text-white">Copyright &copy; PONG 2017</p>
        </div>
         /.container 
    </footer>-->
</html>
