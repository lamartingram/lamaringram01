<%-- 
    Document   : displayBlog
    Created on : Nov 6, 2017, 1:11:27 PM
    Author     : Daniel McBride
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
        <!-- Page Content -->
        <div class="container">
            <!-- Post Content Column -->
            <div class="col-md-offset-2 col-md-8">
                <!-- Title -->
                <h1 class="mt-4"><c:out value="${blog.title}"/></h1>                   
                <!-- Author -->
                <p class="lead">
                    by
                    <c:out value="${blog.user.userName}"/>
                    <c:out value="${blog.date}"/> 
                </p>
                <hr>
                <!-- Preview Image -->
                <img class="img-fluid rounded" src="${blog.image.path}" alt="">
                <hr>
                <!-- Post Content -->
                <p class="lead"> <c:out value="${blog.summary}"/></p>
                <p> <c:out value="${blog.post}" escapeXml="false"/></p>
                <div>
                    <form>
                        <c:forEach var="tag" items="${blog.hashtags}">
                            <input type="submit" class="btn btn-warning" formaction="${pageContext.request.contextPath}/displayBlogsByTag/${tag.tagId}" method="GET" value="${tag.hashtag}"/>
                        </c:forEach>
                    </form>
                </div>
                <hr>
                <!-- Comments Form -->
                <div class="col-md-12" style="color: gold">
                    <h3 style="text-align: center">Comments</h3>
                    <form class="form-horizontal" name="addCommentForm" action="${pageContext.request.contextPath}/addComment/${blog.blogId}" method="POST">
                        <div class="form-group">
                            <label for="commentAuthor" class="control-label col-md-4">Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="commentAuthor" placeholder="Name..."/>
                            </div>
                            <label for="comment" class="control-label col-md-4">Comment:</label>
                            <div class="col-md-8">
                                <textarea name="comment" class="form-control" rows="3" placeholder="Comment..."></textarea>
                            </div>
                            <div class="col-md-offset-4">
                                <div class="col-md-8">
                                    <input type="hidden" name="commentBlogId" value="${blog.blogId}"/>
                                    <button type="submit" class="btn btn-warning">Submit</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <c:if test="${not empty comments}">
                    <div class="col-md-12" style="color: gold">
                        <table class="table table-responsive">
                            <tr style="font-weight: bold">
                                <td width="20%">Author</td>
                                <td width="20%">Date</td>
                                <td width="50%">Comment</td>
                                <td width="10%"></td>
                            </tr>
                            <c:forEach var="comment" items="${comments}">
                                <tr>
                                    <td>
                                        <c:out value="${comment.author}"/>
                                    </td>
                                    <td>
                                        <c:out value="${comment.date}"/>
                                    </td>
                                    <td>
                                        <c:out value="${comment.comment}"/>
                                    </td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <td>
                                            <form action="${pageContext.request.contextPath}/deleteComment/${comment.commentId}" method="POST">
                                                <input type="hidden" value="true" name="onBlog"/>
                                                <input type="submit" class="btn btn-warning" value="Delete"/>
                                            </form>
                                        </td>
                                    </sec:authorize>
                                    <sec:authorize access="hasRole('ROLE_MODERATOR')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/flagComment/${comment.commentId}/${blog.blogId}">
                                                Flag
                                            </a>
                                        </td>
                                    </sec:authorize>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>
            </div>
        </div>
        <!-- Bootstrap core JavaScript -->
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