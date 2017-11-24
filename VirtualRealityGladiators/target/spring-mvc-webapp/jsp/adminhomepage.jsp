<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Home</title>
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
            <div class="col-md-12">
                <form method="GET">
                    <div class="col-md-offset-1 col-md-1">
                        <input type="submit" formaction="${pageContext.request.contextPath}/displayCreateBlog" value="Create Blog" class="btn btn-warning"/>
                    </div>
                    <div class="col-md-offset-1 col-md-1">
                        <input type="submit" formaction="${pageContext.request.contextPath}/displayCreateStaticPage" value="Create Static Page" class="btn btn-warning"/>
                    </div>
                    <div class="col-md-offset-1 col-md-1">
                        <input type="submit" formaction="${pageContext.request.contextPath}/adminAllBlogs" value="View All Blogs" class="btn btn-warning"/>
                    </div>
                    <div class="col-md-offset-1 col-md-1">
                        <input type="submit" formaction="${pageContext.request.contextPath}/adminAllPages" value="View All Static Pages" class="btn btn-warning"/>
                    </div>
                    <div class="col-md-offset-1 col-md-1">
                        <input type="submit" formaction="${pageContext.request.contextPath}/allTagsCategories" value="View All Tags/Categories" class="btn btn-warning"/>
                    </div>
                    <div class="col-md-offset-1 col-md-1">
                        <input type="submit" formaction="${pageContext.request.contextPath}/adminUsers" value="Users" class="btn btn-warning"/>
                    </div>
                </form>
            </div>
            <div class="col-md-8 col-md-offset-4" >

                <h3>Pending Posts</h3>

                <div class="col-md-12">                 
                    <div class="col-md-6" style="color: white ">                  
                        <div id="" style="overflow-y: auto; height:400px;">
                            <table id="pendingPostsTable" class="table table-responsive table-bordered table-hover">
                                <tr style="font-weight: bold">
                                    <td>Title</td>
                                    <td>User</td>
                                    <td>Summary</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <c:forEach var="blog" items="${blogsPending}">
                                    <tr>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/blog/${blog.blogId}">
                                                <c:out value="${blog.title}"/>
                                            </a>
                                        </td>
                                        <td>
                                            <c:out value="${blog.user.userName}"/>
                                        </td>
                                        <td>
                                            <c:out value="${blog.summary}"/>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/approveBlog/${blog.blogId}">
                                                Approve
                                            </a>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/disapproveBlog/${blog.blogId}">
                                                Disapprove
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>

                <h3>Flagged Comments</h3>

                <div class="col-md-6" style="color: white">   
                    <div id="" style="overflow-y: auto; height:400px;">
                        <table id="flaggedCommentsTable" class="table table-responsive table-bordered table-hover">
                            <tr style="font-weight: bold">
                                <td>Comment</td>
                                <td>Author</td>
                                <td></td>
                                <td></td>
                            </tr>
                            <c:forEach var="comment" items="${flaggedComments}">
                                <tr>
                                    <td>
                                        <c:out value="${comment.comment}"/>
                                    </td>
                                    <td>
                                        <c:out value="${comment.author}"/>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/deleteComment/${comment.commentId}">
                                            Delete
                                        </a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/approveComment/${comment.commentId}">
                                            Approve
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
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
