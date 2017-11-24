<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/custom_main.css" rel="stylesheet">
    </head>
    <style>
    .nav-tabs > li > a:hover {
        color: gold;
        background-color: black;
    }
    
    #dropdownMenu1:hover, #dropdownMenu2:hover {
        background-color:black;
        border: 1px solid white;
    }
    
    #dropdownMenu1, #dropdownMenu2 {
        background-color: rgba(0,0,0,0);
        color: gold;
        border: none;
    }
    
    .dropdown-menu {
        background-color: rgba(0,0,0,0.8);
    }
</style>
    <body background="${pageContext.request.contextPath}/img/gears_standin.jpg" style="background-size: cover">
        <div class="container-fluid">      
            <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                <p style="text-align: center; color: red">
                    <c:out value="Invalid login: Incorrect username or password"/>.
                </p>
            </c:if>
            <div class="row">
                <div class="col-md-4"></div>     
                <div class="col-md-4" style="height: 150px; width: 600px; 
                     background-image: url(/VirtualRealityGladiators/img/PongWars3.png);
                     background-size: contain"/>
                <br/>
                <h1 style="text-align: center">P.O.N.G.</h1>
            </div>
            <%@include file="snippet_searchbar.jsp" %>
        </div>
        <div class="col-md-8 col-md-offset-2" style="background-color: rgba(0,0,0,0.2)" >
            <div class="navbar col-md-12"> 
                <ul class="nav nav-tabs" style="border-bottom: none; background-image: url(/VirtualRealityGladiators/img/copper_standin.jpg);">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/index">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/static/about">About</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/static/contact">Contact</a></li>
                    <%@include file="snippet_categories.jsp" %>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/adminHome">Admin Home</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_EDITOR')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/editorDashboard">Editor Home</a>
                        </li>
                    </sec:authorize>
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <%@include file="snippet_logout.jsp" %>
                        </c:when>
                        <c:otherwise>
                            <%@include file="snippet_login.jsp" %>
                        </c:otherwise>
                    </c:choose>
                </ul>     
            </div>
            <div class="col-md-12">  <!-- featured blog div -->
                <h3>   
                    <div> <a href="${pageContext.request.contextPath}/blog/${mostRecent.blogId}"><c:out value="${mostRecent.title}"/></a></div>
                </h3>
                <img src="${mostRecent.image.path}"  width="100%" alt="..."/>

                <div class="col-md-12" style="margin-bottom: 10px;">
                    <div style="float: left" >
                        <h3 style="color:gold">    By: <c:out value="${mostRecent.user.userName}"/> </h3>
                    </div>
                    <div style="float: right">
                        <h3 style="color:gold" >   Date <c:out value="${mostRecent.date}"/> </h3>
                    </div>
                    <div style="clear: both" />
                </div>
            </div>
            <div class="col-md-12"> <!-- blog trio list div -->

                <c:forEach var="blog" items="${blogList}"> 
                    <div class="col-md-4">
                        <div class="thumbnail" style="background-color: rgba(0,0,0,0.2)" >
                            <img src="${blog.image.path}" alt="..."/>
                            <div class="caption" style="overflow-y: auto; height:200px;">
                                <h3> <a href="${pageContext.request.contextPath}/blog/${blog.blogId}"><c:out value="${blog.title}"/></a> </h3>
                                <p> <c:out value="${blog.summary}"/></p>
                                <div class=""><p>By: <c:out value="${blog.user.userName}"/></p> </div>
                                <div><p>Date:<c:out value="${blog.date}"/></p></div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
            <c:forEach var="other" items="${otherBlogs}">
                <div class="col-md-12" style="border:solid" > <!-- other blog lists -->
                    <div class="col-md-3">
                        <img src="${other.image.path}" />
                        <h3><a href="${pageContext.request.contextPath}/blog/${other.blogId}"><c:out value="${other.title}"/></a></h3>
                        <h3>By: <c:out value="${other.user.userName}"/></h3>
                        <h3>Date <c:out value="${other.date}"/></h3>
                    </div>
                    <div class="col-md-9">
                        <h3> <c:out value="${other.summary}"/></h3>
                    </div>
                    <div style="clear: both" />
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</div>
<footer class="py-5 bg-dark">
    <hr>
    <p class="m-0 text-center text-white">Copyright &copy; PONG 2017</p>
</footer>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

