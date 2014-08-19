<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${pages != null}">
    <div class="pages">
        <ul class="pagination">
            <c:forEach items="${pages}" var="page">
                <c:choose>
                    <c:when test="${page.active}">
                        <li class="active"><a href="?page=<c:out value="${page.number}"/>"><c:out
                                value="${page.title}" escapeXml="false"/></a></li>
                    </c:when>
                    <c:when test="${page.disabled}">
                        <li class="disabled"><a href="?page=<c:out value="${page.number}"/>"><c:out
                                value="${page.title}" escapeXml="false"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="?page=<c:out value="${page.number}"/>"><c:out value="${page.title}"
                                                                                   escapeXml="false"/></a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
</c:if>