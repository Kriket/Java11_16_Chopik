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
                      <a href="/deletelot">${deletelot}</a>
                      <a href="/showauc">${showauc}</a>
                      <c:if test="${user.isAdmin() == true}">
                          <a href="/addauc">${addauc}</a>
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
            <input type="hidden" name="command" value="add_lot">

            <p>
              <label for="price">${startprice}</label>
              <br>
              <input type="number" name="price" id="price" min="1" step="10" required/>
            </p>

            <div class="thing-container" id="thing-container">
                <div class="thing">
                    <p>
                        <label for="itemname">${itemname}</label>
                        <br>
                        <input type="text" name="itemname" id="itemname" required/>
                    </p>

                    <p>
                        <label for="description">${itemdescription}</label>
                        <br>
                        <textarea name="description" id="description" class="description-text" maxlength="1000" rows="7" required></textarea>
                        <%--<input type="text" name="description" id="description" required/>--%>
                    </p>
                </div>
            </div>


            <p>
                <input hidden type="button" id="delete-example" class="add-email-button" value="${deleteitem}">
            </p>

            <p>
                <input type="button" id="additem" class="add-email-button" onclick="addThing()" value="${additem}">
            </p>

            <p>
                <label for="auctions">${aucname}</label>
                <br>
                <select name="auctions" id="auctions" required>
                </select>
            </p>

            <p>
                <input type="button" id="refreshAuc" class="add-email-button" onclick="refreshAuctions()" value="Refresh">
            </p>
<%--
            <p>
                <label for="auction-list">${auction}</label>
                <br>
                <select name="auction" id="auction-list">

                </select>
                <br>
                <input type="button" class="register-button" onclick="getAuctions()" value="${additem}">
            </p>
--%>


            <p class="register-submit">
                <button type="submit" class="register-button">${addlot}</button>
            </p>
        </form>
    </div>

</body>
