
<c:forEach var="blog" items="${blogList}">
    <div class="col-md-4">
        <div class="thumbnail" style="background-color: rgba(0,0,0,0.7); overflow-y: auto; height: 420px">
            <img src="${blog.image.path}" alt="...">
            <div class="caption">
                <h3 style="color: gold; font-family: gothic; font-size: 20pt; text-decoration: underline"> 
                    <a href="${pageContext.request.contextPath}/blog/${blog.blogId}"><c:out value="${blog.title}"/></a> </h3>
                    <p style="color: white; font-family: gothic; font-size: 14pt"><c:out value="${blog.date}"/></p>
                <p style="color: white; font-family: gothic; font-size: 14pt"> <c:out value="By: ${blog.user.userName}"/></p>
                <p style="color: white; font-family: gothic; font-size: 14pt"> <c:out value="${blog.summary}"/> </p>
            </div>
        </div>
    </div>
</c:forEach>