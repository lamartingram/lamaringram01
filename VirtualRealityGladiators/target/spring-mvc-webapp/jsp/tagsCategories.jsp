<%-- 
    Document   : tagsCategories
    Created on : Nov 13, 2017, 3:33:58 PM
    Author     : adam-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <title>Tags/Categories</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/custom_main.css" rel="stylesheet">
    <style>
        .table-hover tbody tr:hover td {
            background: black;
        }
        h2 {
            text-align: center;
        }
    </style>
    <body background="${pageContext.request.contextPath}/img/gears_standin.jpg" style="background-size: cover">
        <div class="container-fluid">
            <%@include file="snippet_pageBanner.jsp" %>
            <%@include file="snippet_navbar.jsp" %>
            <div class="row">
                <div class="col-md-offset-1 col-md-4">
                    <h3>Tags</h3>
                    <div class="col-md-12"  style="overflow-y: auto; height:400px;">
                        <table class="table table-hover">
                            <tr style="color:white; font-weight: bold">
                                <td>Tag</td>
                                <td></td>
                                <td></td>
                            </tr>
                            <c:forEach var="tag" items="${tags}">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/displayBlogsByTag/${tag.tagId}">
                                            <c:out value="${tag.hashtag}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/displayEditTag/${tag.tagId}">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/deleteTag/${tag.tagId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div class="col-md-offset-2 col-md-4">
                    <h3>Categories</h3>
                    <div class="col-md-12"  style="overflow-y: auto; height:400px;">
                        <table class="table table-hover">
                            <tr style="color:white; font-weight: bold">
                                <td>Category Name</td>
                                <td></td>
                                <td></td>
                            </tr>
                            <c:forEach var="cat" items="${categories}">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/category/${cat.category}">
                                            <c:out value="${cat.category}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/displayEditCategory/${cat.categoryId}">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/deleteCategory/${cat.categoryId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
    </body>
</html>
