<%@ page contentType="text/javascript; encoding=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="lang" uri="/WEB-INF/custom.tld" %>


$(document).ready(function () {
$('#add-form').bootstrapValidator({
fields: {
first_name: {
validators: {
notEmpty: {
message: '<lang:i18n key="first_name_empty"/>'
}
}
},
last_name: {
validators: {
notEmpty: {
message: '<lang:i18n key="last_name_empty"/>'
}
}
},
email: {
validators: {
notEmpty: {
message: '<lang:i18n key="email_empty"/>'
},
emailAddress: {
message: '<lang:i18n key="email_wrong"/>'
}
}
},
message: {
validators: {
notEmpty: {
message: '<lang:i18n key="message_empty"/>'
}
}
}
}
});
});
