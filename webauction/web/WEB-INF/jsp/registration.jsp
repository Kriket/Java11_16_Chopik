<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="passfield/css/passfield.css"/>
    <link rel="stylesheet" href="css/auction_css.css">
    <script src="passfield/js/locales.js"></script>
    <script src="passfield/js/passfield.js"></script>
    <script src="passfield/lib/jquery/jquery-1.9.1.js"></script>    
    <script src="js/auction_js.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>

    <script type="text/javascript">
                $(document).ready(function(){ 
                    var passField = new PassField.Field("password", {
                        pattern: "abcdEFGH1234",
                        showWarn: false
                    });
                });
    </script>

    <%@ include file="/WEB-INF/jsp/local.jsp" %>

</head>
<body>
    <header>
        <div>
            <h1><a href="/">${title}</a></h1>
            <nav>
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

    <div class="register-container">
        <form method="post" action = "controller" class="register" id="register" onsubmit="return validateForm()">
            <input type="hidden" name="command" value="sign_up">

            <p>
                <label for="nickname">${nickname}</label>
                <br>
                <input type="text" name="nickname" id="nickname" title="${placeholderNickname}" pattern="[A-z][A-z\d_]{4,}" required/>
            </p>

            <p>
                <label for="password">${password}</label>
                <br>
                <input type="password" name="password" id="password" placeholder="${placeholderPassword}" required/>
                <br>
                <span class="err" id="err-password"></span>
            </p>

            <p id="email-p">
                <label for="email">${email}</label>
                <br>
                <input type="text" id="email" name="email" title="${placeholderEmail}" pattern="[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+\.[a-z]{2,3}$" required/>
                <br>
                <input type="button" data-add-email-num=1 onclick="addEmail()" id="add-email-button" class="add-email-button" value="${addemail}">
            </p>

            <p class="register-submit">
                <button type="submit" class="register-button">${signup}</button>
            </p>
        </form>
    </div>

</body>
</html>
