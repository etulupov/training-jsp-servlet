<%@ page contentType="text/html; encoding=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="lang" uri="WEB-INF/custom.tld" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="shortcut icon" href="favicon.ico">
    <title><lang:i18n key="title"/></title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrapValidator.min.css"/>
    <script type="text/javascript" src="js/bootstrapValidator.min.js"></script>
    <script type="text/javascript" src="js/strings.js"></script>
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:out value="${rootUrl}"/>"><lang:i18n key="title"/></a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:out value="${rootUrl}"/>"><lang:i18n key="home"/></a></li>
                <li><a href="#post"><lang:i18n key="new_message"/></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><lang:i18n key="language"/> <b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="?language=en"><img src="image/en.png" alt=""/><lang:i18n key="language_english"/>
                        </a></li>
                        <li><a href="?language=ru"><img src="image/ru.png" alt=""/><lang:i18n key="language_russian"/>
                        </a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="container main">
    <c:if test="${error != null}">
        <c:forEach items="${error}" var="e">
            <div class="alert alert-danger alert-error">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong><lang:i18n key="error"/>!</strong> <c:out value="${e}"/>
            </div>
        </c:forEach>
    </c:if>

    <c:if test="${success != null}">
        <div class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong><lang:i18n key="success"/>!</strong> <c:out value="${success}"/>
        </div>
    </c:if>

    <div class="alert alert-info">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        <strong><lang:i18n key="info"/>!</strong> <lang:i18n key="active_sessions"/> <strong><c:out
            value="${sessions}"/></strong>
    </div>

    <div id="post">
        <form class="form-horizontal" role="form" id="add-form" method="post" action="add">
            <fieldset>
                <legend><h3><lang:i18n key="new_message"/></h3></legend>
                <div class="form-group">
                    <label for="first_name" class="col-sm-2 control-label"><lang:i18n key="first_name"/></label>

                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="first_name" name="first_name"
                               placeholder="<lang:i18n key="first_name_hint"/>"
                               value="<c:out value="${requestScope.first_name}"/>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="last_name" class="col-sm-2 control-label"><lang:i18n key="last_name"/></label>

                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="last_name" name="last_name"
                               placeholder="<lang:i18n key="last_name_hint"/>"
                               value="<c:out value="${requestScope.last_name}"/>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label"><lang:i18n key="email"/></label>

                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="email" name="email"
                               placeholder="<lang:i18n key="email_hint"/>"
                               value="<c:out value="${requestScope.email}"/>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="message" class="col-sm-2 control-label"><lang:i18n key="message"/></label>

                    <div class="col-sm-4">
                        <textarea class="form-control" id="message" name="message"
                                  placeholder="<lang:i18n key="message_hint"/>"><c:out
                                value="${requestScope.message}"/></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-4">
                        <button type="submit" class="btn btn-default"><lang:i18n key="post_message"/></button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>

    <div>
        <div class="page-header"><h3><lang:i18n key="messages_title"/></h3></div>
        <div class="list-group">
            <c:forEach items="${entries}" var="entry">
                <div class="list-group-item">
                    <h4 class="list-group-item-heading"><strong><c:out value="${entry.firstName}"/> <c:out
                            value="${entry.lastName}"/></strong> (<a
                            href="mailto:<c:out value="${entry.email}"/>"><c:out value="${entry.email}"/></a>) </h4>

                    <p class="list-group-item-hint"><lang:i18n key="message_sent_via"/><c:out
                            value="${entry.userAgent}"/></p>

                    <p class="list-group-item-text"><c:out
                            value="${entry.message}" escapeXml="false"/></p>
                </div>
            </c:forEach>
        </div>
    </div>
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
</div>


</body>
</html>
