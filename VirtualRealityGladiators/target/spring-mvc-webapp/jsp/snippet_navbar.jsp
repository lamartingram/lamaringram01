
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="col-md-2"></div>
<div class="navbar col-md-8">
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

<div class="col-md-2"></div>