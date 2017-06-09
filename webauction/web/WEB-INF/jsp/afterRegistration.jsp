<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="passfield/css/passfield.css"/>
    <link rel="stylesheet" href="css/auction_css.css">
    <script src="passfield/js/locales.js"></script>
    <script src="passfield/js/passfield.js"></script>
    <script src="passfield/lib/jquery/jquery-1.9.1.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/auction_js.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Congratulation</title>

    <%@ include file="/WEB-INF/jsp/local.jsp" %>

</head>
<body>
<header>
    <div>
        <h1><a href="/">${title}</a></h1>
        <nav>
            <a href="">${addlot}</a>
            <a href="/registration">${signup}</a>
            <a href="/authorization">${signin}</a>

            <div class="dropdown">
                <div class="dropbtn" style="background-image: url(${flag});">${language}</div>
                <div class="dropdown-content">
                    <a href="#" onclick="setLanguage('registration', 'RU');" style="background-image: url(${flagRu});">${languageRu}</a>
                    <a href="#" onclick="setLanguage('registration', 'EN');" style="background-image: url(${flagEn});">${languageEn}</a>
                </div>
            </div>

        </nav>
    </div>
</header>

<div class="row"><h1>Congratulation!</h1></div>
<div class="congrat-text">
    <p>register completed</p>
    <a href="/">Main</a>
</div>
</body>
</html>
