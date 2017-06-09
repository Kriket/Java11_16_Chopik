<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/auction_css.css">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/auction_js.js"></script>
  <title>Super Auction</title>
  <%@ include file="/WEB-INF/jsp/local.jsp" %>
</head>
<body>
  <header>
    <div>
      <h1><a href="/">${title}</a></h1>
      <nav>

          <c:if test="${user == null}">
              <a href="/registration">${signup}</a>
              <a href="/authorization">${signin}</a>
          </c:if>

          <c:if test="${user != null}">
              <a href="/addlot">${addlot}</a>
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
            <a href="#" onclick="setLanguage('index', 'RU');" style="background-image: url(${flagRu});">${languageRu}</a>
            <a href="#" onclick="setLanguage('index', 'EN');" style="background-image: url(${flagEn});">${languageEn}</a>
          </div>
        </div>

      </nav>
    </div>
  </header>

  <div class="row">
    <H1>${newlots}</H1>
  </div>

  <div class="row">

    <div class="col-12">
      <div class="card-container">
        <div class="card">
          <div class="front">
            <!--<a href="" alt="Name of lot"></a>-->
            <p>Photo of lot</p>
          </div>
          <div class="back">
            <h1>Some info</h1>
          </div>
        </div>
      </div>

      <div class="card-container">
        <div class="card">
          <div class="front">
            <!--<a href="" alt="Name of lot"></a>-->
            <p>hover on me</p>
          </div>
          <div class="back">
            <h1>Some info</h1>
          </div>
        </div>
      </div>

      <div class="card-container">
        <div class="card">
          <div class="front">
            <!--<a href="" alt="Name of lot"></a>-->
            <p>hover on me</p>
          </div>
          <div class="back">
            <h1>Some info</h1>
          </div>
        </div>
      </div>

      <div class="card-container">
        <div class="card">
          <div class="front">
            <!--<a href="" alt="Name of lot"></a>-->
            <p>hover on me</p>
          </div>
          <div class="back">
            <h1>Some info</h1>
          </div>
        </div>
      </div>
    </div>

  </div>

  <div class="row">
    <H1>${popularlots}</H1>
  </div>

  <div class="row">

    <div class="col-12">
      <div class="card-container">
        <div class="card">
          <div class="front">
            <!--<a href="" alt="Name of lot"></a>-->
            <p>Photo of lot</p>
          </div>
          <div class="back">
            <h1>Some info</h1>
          </div>
        </div>
      </div>

      <div class="card-container">
        <div class="card">
          <div class="front">
            <!--<a href="" alt="Name of lot"></a>-->
            <p>hover on me</p>
          </div>
          <div class="back">
            <h1>Some info</h1>
          </div>
        </div>
      </div>

      <div class="card-container">
        <div class="card">
          <div class="front">
            <!--<a href="" alt="Name of lot"></a>-->
            <p>hover on me</p>
          </div>
          <div class="back">
            <h1>Some info</h1>
          </div>
        </div>
      </div>
    </div>

  </div>

  <div class="footer">
    <div>World &copy; TM 2017</div>
  </div>

</body>
</html>
