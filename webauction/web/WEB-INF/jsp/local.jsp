<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.title" var="title" />

<fmt:message bundle="${loc}" key="local.signup" var="signup" />
<fmt:message bundle="${loc}" key="local.signin" var="signin" />
<fmt:message bundle="${loc}" key="local.signout" var="signout" />

<fmt:message bundle="${loc}" key="local.language" var="language" />
<fmt:message bundle="${loc}" key="local.language.en" var="languageEn" />
<fmt:message bundle="${loc}" key="local.language.ru" var="languageRu" />
<fmt:message bundle="${loc}" key="local.language.flag" var="flag" />
<fmt:message bundle="${loc}" key="local.language.flag.ru" var="flagRu" />
<fmt:message bundle="${loc}" key="local.language.flag.en" var="flagEn" />

<fmt:message bundle="${loc}" key="local.newlots" var="newlots" />
<fmt:message bundle="${loc}" key="local.popularlots" var="popularlots" />

<fmt:message bundle="${loc}" key="local.placeholder.password" var="placeholderPassword" />
<fmt:message bundle="${loc}" key="local.placeholder.email" var="placeholderEmail" />
<fmt:message bundle="${loc}" key="local.placeholder.nickname" var="placeholderNickname" />

<%--  registration  --%>
<fmt:message bundle="${loc}" key="local.registration.nickname" var="nickname" />
<fmt:message bundle="${loc}" key="local.registration.password" var="password" />
<fmt:message bundle="${loc}" key="local.registration.email" var="email" />
<fmt:message bundle="${loc}" key="local.registration.addemail" var="addemail" />
<fmt:message bundle="${loc}" key="local.registration.deleteemail" var="deleteemail" />