<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>UserPage</title>
    <link rel="stylesheet" href="passfield-master/css/passfield.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="passfield-master/js/passfield.js"></script>
</head>
<body>
 <div id="mypass-wrap" class="control-group">
                <input type="password" id="mypass" 
                    placeholder="пароль" />
            </div>

            $("#mypass").passField();
    <h2>
        I am the <i>userPage.jsp</i>
    </h2>
<c:out value="Hello"/>
</body>
</html>
