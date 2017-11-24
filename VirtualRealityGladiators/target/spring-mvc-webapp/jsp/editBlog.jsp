<%-- 
    Document   : editBlog
    Created on : Nov 13, 2017, 10:54:43 AM
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
                <form id="editBlogForm" role="form" class="form-horizontal" action="${pageContext.request.contextPath}/editBlog" method="POST">
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="editBlogTitle">Title</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="editBlogTitle" name="editBlogTitle" required value="${blog.title}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editBlogAuthors" class="col-md-4 control-label">Authors (Separate with a comma)</label>
                        <div class="col-md-8">
                            <input type="text" id="authors" name="editBlogAuthors" class="form-control" value="${authors}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="editBlogCategories">Categories (CTRL + click to select multiple)</label>
                        <div class="col-md-8">
                            <select form="editBlogForm" class="form-control" multiple="multiple" required name="editBlogCategories">
                                <option disabled value="">Select Categories</option>
                                <c:forEach var="cat" items="${categories}">
                                    <c:set var="contains" value="false"/>
                                    <c:forEach var="category" items="${blog.categories}">
                                        <c:if test="${category.category == cat.category}">
                                            <c:set var="contains" value="true"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${contains}">
                                            <option value="${cat.categoryId}" selected>
                                                <c:out value="${cat.category}"/>
                                            </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${cat.categoryId}">
                                                <c:out value="${cat.category}"/>
                                            </option>
                                        </c:otherwise>
                                    </c:choose> 
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editBlogTags" class="col-md-4 control-label">Tags (CTRL + click to select multiple)</label>
                        <div class="col-md-8">
                            <select form="editBlogForm" multiple="multiple" class="form-control" required name="editBlogTags">
                                <option disabled value="">Select Tags</option>
                                <c:forEach var="tag" items="${tags}">
                                    <c:set var="contains" value="false"/>
                                    <c:forEach var="tagg" items="${blog.hashtags}">
                                        <c:if test="${tagg.hashtag == tag.hashtag}">
                                            <c:set var="contains" value="true"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${contains}">
                                            <option value="${tag.tagId}" selected>
                                                <c:out value="${tag.hashtag}"/>
                                            </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${tag.tagId}">
                                                <c:out value="${tag.hashtag}"/>
                                            </option>s
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editBlogImage" class="col-md-4 control-label">Image</label>
                        <div class="col-md-8">
                            <input type="text" id="image" class="form-control" name="editBlogImage" value="${blog.image.path}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editBlogImageDesc" class="col-md-4 control-label">Image Description:</label>
                        <div class="col-md-8">
                            <input type="text" name="editBlogImageDesc" value="${blog.image.description}" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editBlogSummary" class="col-md-4 control-label">Summary</label>
                        <div class="col-md-8">
                            <textarea id="summary" name="editBlogSummary" class="form-control" style="height:200px"><c:out value="${blog.summary}"/></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editBlogEditor" class="col-md-4 control-label">Blog Post</label>
                        <div class="col-md-8">
                            <textarea id="blogEditor" name="editBlogEditor" class="col-md-12" style="height: 200px"><c:out value="${blog.post}"/></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <input type="hidden" name="editBlogApproved" value="true"/>
                            <input type="hidden" name="editBlogAdmin" value="true"/>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_EDITOR')">
                            <input type="hidden" name="editBlogApproved" value="false"/>
                        </sec:authorize>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="editBlogId" value="${blog.blogId}"/>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-2">
                            <input type="submit" class="btn btn-warning" value="Submit">
                        </div>
                        <div class="col-md-2">
                            <input type="submit" class="btn btn-warning" value="Cancel" formaction="${pageContext.request.contextPath}/cancelEdit"/>
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
