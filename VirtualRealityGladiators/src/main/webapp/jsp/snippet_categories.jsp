<li class="dropdown-toggle">
    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
        Categories
        <span class="caret"></span>
    </button>

    <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
        <li>
        <c:forEach var="category" items="${categories}">
            <div class="form-group">
                <a href="${pageContext.request.contextPath}/category/${category.category}">${category.category}</a>
            </div>
        </c:forEach>
<!--            <div class="form-group">
                <a href="${pageContext.request.contextPath}/category/fighters">Fighters</a>
            </div>
            <div class="form-group">
                <a href="${pageContext.request.contextPath}/category/arenas">Arenas</a>
            </div>-->
        </li>
    </ul>
</li>