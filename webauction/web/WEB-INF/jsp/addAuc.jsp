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
    <title>Add Lot</title>

    <%@ include file="/WEB-INF/jsp/local.jsp" %>
</head>
<body>
    <header>
        <div>
            <h1><a href="/">${title}</a></h1>
            <nav>

                  <c:if test="${user != null}">
                      <a href="/addlot">${addlot}</a>
                      <a href="/deletelot">${deletelot}</a>
                      <a href="/showauc">${showauc}</a>
                      <c:if test="${user.isAdmin() == true}">
                          <a href="/deleteauc">${deleteauc}</a>
                      </c:if>
                      <form method="post" action = "controller" id="sign_out">
                          <input type="hidden" name="command" value="sign_out">
                          <a href="#" onclick="document.getElementById('sign_out').submit(); return false;">${signout}</a>
                      </form>
                  </c:if>

                <div class="dropdown">
                    <div class="dropbtn" style="background-image: url(${flag});">${language}</div>
                    <div class="dropdown-content">
                        <a href="#" onclick="setLanguage('addlot', 'RU');" style="background-image: url(${flagRu});">${languageRu}</a>
                        <a href="#" onclick="setLanguage('addlot', 'EN');" style="background-image: url(${flagEn});">${languageEn}</a>
                    </div>
                </div>

            </nav>
        </div>
    </header>

    <div class="register-container">
        <form method="post" action = "controller" class="register" id="register">
            <input type="hidden" name="command" value="add_auc">

            <label for="aucname">${aucname}</label>
            <br>
            <input type="text" name="aucname" id="aucname" required/>

            <p>
              <label for="step">${aucstep}</label>
              <br>
              <input type="number" name="step" id="step" min="10" step="10" required/>
            </p>

            <p>
            <label for="currency">${currency}</label>
            <br>
            <select name="currency" id="currency" required>
            	<option>USD</option>
            	<option>EUR</option>
            	<option>JPY</option>
            	<option>GBP</option>
            	<option>CHF</option>
            	<option>AUD</option>
            	<option>CAD</option>
            	<option>NZD</option>
            	<option>CNY</option>
            </select>
            </p>

            <p>
                <label for="startdate">${startday}</label>
                <br>
                <input type="date" name="startdate" id="startdate" required/>
            </p>


            <p class="register-submit">
                <button type="submit" class="register-button">${addauc}</button>
            </p>
        </form>
    </div>

</body>
