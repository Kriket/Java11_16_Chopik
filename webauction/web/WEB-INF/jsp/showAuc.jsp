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
    <title>Auctions</title>

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
                        <a href="#" onclick="setLanguage('showauc', 'RU');" style="background-image: url(${flagRu});">${languageRu}</a>
                        <a href="#" onclick="setLanguage('showauc', 'EN');" style="background-image: url(${flagEn});">${languageEn}</a>
                    </div>
                </div>

            </nav>
        </div>
    </header>

    <div class="register-container register">

        <p>
            <label for="auctions">${aucname}</label>
            <br>
            <select name="auctions" id="auctions" onchange="refreshInfo('showauc', value)">
            </select>
        </p>

        <p>
            <input type="button" id="refreshAuc" class="add-email-button" onclick="refreshAuctions()" value="Refresh">
        </p>

        <c:if test="${sessionScope.auction != null}">
            <c:forEach items="${sessionScope.auction.getLots()}" var = "lot">
                <div class="lot-element" >
                    <p>Lot <c:out value="${lot.getId()}"/></p>
                    <p>This lot contains:</p>
                    <br>
                    <c:forEach items="${lot.getThings()}" var="thing">
                        <p><c:out value="${thing.getName()}"/></p>
                        <p><c:out value="${thing.getDescription()}"/></p>
                    </c:forEach>
                    <br>
                    <p>Current price <c:out value="${lot.getCurrentPrice()}"/></p>
                    <p>Bet <c:out value="${auction.getStep()}"/></p>
                    <form method="post" action = "controller" id="place-bet">
                        <input type="hidden" name="command" value="place_bet">
                        <input type="hidden" name="lotId" value="${lot.getId()}">
                        <input type="hidden" name="currentPrice" value="${lot.getCurrentPrice()}">
                        <input type="hidden" name="step" value="${auction.getStep()}">
                        <input type="hidden" name="buyerId" value="${user.getId()}">
                        <p class="register-submit" id="${lot.getId()}">
                            <button type="submit" class="register-button">Place a bet</button>
                        </p>
                    </form>
                    <c:if test="${user.isAdmin() == true}">
                        <form method="post" action = "controller" id="sell-lot">
                            <input type="hidden" name="command" value="sell_lot">
                            <input type="hidden" name="lotId" value="${lot.getId()}">
                            <p class="register-submit">
                                <button type="submit" class="register-button">Sell lot</button>
                            </p>
                        </form>
                    </c:if>

                </div>
            </c:forEach>
        </c:if>

    </div>

</body>
