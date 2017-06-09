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

<%--  add lot  --%>
<fmt:message bundle="${loc}" key="local.lot.startprice" var="startprice" />
<fmt:message bundle="${loc}" key="local.lot.addlot" var="addlot" />
<fmt:message bundle="${loc}" key="local.lot.deletelot" var="deletelot" />
<fmt:message bundle="${loc}" key="local.lot.lotname" var="lotname" />
<fmt:message bundle="${loc}" key="local.lot.item.name" var="itemname" />
<fmt:message bundle="${loc}" key="local.lot.item.description" var="itemdescription" />
<fmt:message bundle="${loc}" key="local.lot.item.additem" var="additem" />
<fmt:message bundle="${loc}" key="local.lot.item.deleteitem" var="deleteitem" />

<%--  add auction  --%>
<fmt:message bundle="${loc}" key="local.auction.aucname" var="aucname" />
<fmt:message bundle="${loc}" key="local.auction.aucstep" var="aucstep" />
<fmt:message bundle="${loc}" key="local.auction.addauc" var="addauc" />
<fmt:message bundle="${loc}" key="local.auction.deleteauc" var="deleteauc" />
<fmt:message bundle="${loc}" key="local.auction.currency" var="currency" />
<fmt:message bundle="${loc}" key="local.auction.startday" var="startday" />



<fmt:message bundle="${loc}" key="local.auction.showauc" var="showauc" />
