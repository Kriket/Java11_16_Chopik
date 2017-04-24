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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>

    <script type="text/javascript">
                $(document).ready(function(){ 
                    var passField = new PassField.Field("password", {
                        pattern: "abcdEFGH1234"
                    });
                });
    </script>
</head>
<body>
    <header>
        <div>
            <h1>Super AUCTION</h1>
            <nav>
                <a>Add lot</a>
                <a href="registration.html">Sign up</a>
                <a>Sing in</a>
            </nav>
        </div>
    </header>

    <div class="register-container">
        <form method="post" action = "controller" class="register" id="register" onsubmit="return validateForm()">
            <input type="hidden" name="command" value="sign_up">
            <p>
                <label for="login">Логин:</label>
                <br>
                <input type="text" name="login" id="login" title="Латинские буквы, цифры, '_', первый символ - буква. Не менее 5 символов." pattern="[A-z][A-z\d_]{4,}" required/>
            </p>

            <p>
                <label for="password">Пароль:</label>
                <br>
                <input type="password" name="password" id="password" placeholder="your password"/*title="Не менее 6 символов, не менее одной буквы в каждом регистре и не менее одной цифры." pattern="[A-z\d]{6,}"*/ required/>
                <br>
                <span class="err" id="err-password"></span>
            </p>

            <p>
                <label for="age">Возраст:</label>
                <br>
                <input type="number" name="age" id="age" title="Допустимо: 7 - 120 лет" min="7" max="120" required/>
            </p>

            <p id="email-p">
                <label for="email">Email:</label>
                <br>
                <input type="text" id="email" name="email" title="Должен содержать символы: '@' и '.'" pattern="[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+\.[a-z]{2,3}$" required/>
                <br>
                <input type="button" data-add-email-num=1 onclick="addEmail()" id="add-email-button" class="add-email-button" value="Добавить email">
            </p>

            <p class="register-submit">
                <button type="submit" class="register-button">Зарегистрироваться</button>
            </p>
        </form>
    </div>

    <div class="footer">
        <div>World &copy; TM 2017</div>
    </div>

</body>
</html>
