<%@ page contentType="text/html; encoding=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="lang" uri="WEB-INF/custom.tld" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="shortcut icon" href="favicon.ico">
    <title><lang:i18n key="title"/> - <lang:i18n key="error"/></title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="alert alert-danger alert-error">
        <strong><lang:i18n key="error"/>!</strong> <lang:i18n key="error_404"/>
    </div>
</div>
</body>
</html>