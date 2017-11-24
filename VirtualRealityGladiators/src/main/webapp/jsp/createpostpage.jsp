
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Editing Page</title>
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
            <div class="col-md-offset-2 col-md-6" style="background-color: rgba (0,0,0,0.7)">
                <h1 style="text-align: center">Create Blog Post</h1>
                <form id="addBlogForm" role="form" class="form-horizontal" action="postBlog" method="POST">
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="addBlogTitle">Title</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="addBlogTitle" name="addBlogTitle" required placeholder="Title of the Blog..">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addBlogAuthors" class="col-md-4 control-label">Authors (Separate with a comma)</label>
                        <div class="col-md-8">
                            <input type="text" id="authors" name="addBlogAuthors" class="form-control" required placeholder="Add Authors of the Blog ..">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="addBlogCategories">Categories (CTRL + click to select multiple)</label>
                        <div class="col-md-6">
                            <select form="addBlogForm" class="form-control" multiple="multiple" required name="addBlogCategories">
                                <option disabled value="">Select Categories</option>
                                <c:forEach var="cat" items="${categories}">
                                    <option value="${cat.categoryId}">
                                        <c:out value="${cat.category}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addBlogTags" class="col-md-4 control-label">Tags (CTRL + click to select multiple)</label>
                        <div class="col-md-6">
                            <select form="addBlogForm" multiple="multiple" class="form-control" required name="addBlogTags">
                                <option disabled value="">Select Tags</option>
                                <c:forEach var="tag" items="${tags}">
                                    <option value="${tag.tagId}">
                                        <c:out value="${tag.hashtag}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addBlogImage" class="col-md-4 control-label">Image</label>
                        <div class="col-md-8">
                            <input type="text" id="image" class="form-control" name="addBlogImage" placeholder="Image..">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addBlogImageDesc" class="col-md-4 control-label">Image Description:</label>
                        <div class="col-md-8">
                            <input type="text" name="addBlogImageDesc" placeholder="Image Description" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addBlogSummary" class="col-md-4 control-label">Summary</label>
                        <div class="col-md-8">
                            <textarea id="summary" name="addBlogSummary" class="form-control" placeholder="Write something about the Blog.." style="height:200px"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="addBlogEditor" class="col-md-4 control-label">Blog Post</label>
                        <div class="col-md-8">
                            <textarea id="blogEditor" name="addBlogEditor" class="col-md-12" style="height: 200px"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <input type="hidden" name="addBlogApproved" value="true"/>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_EDITOR')">
                            <input type="hidden" name="addBlogApproved" value="false"/>
                        </sec:authorize>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4">
                            <input type="submit" class="btn btn-warning" value="Submit">
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-2" style="background-color: rgba (0,0,0,0.7);">
                <div>
                    <form action="${pageContext.request.contextPath}/displayAddCategoryPage" method="GET">
                        <input type="submit" class="btn btn-warning" value="Create Category"/>
                    </form>
                </div>
                <div>
                    <form action="${pageContext.request.contextPath}/displayAddTagPage" method="GET">
                        <input type="submit" class="btn btn-warning" value="Create Tag"/>
                    </form>
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

