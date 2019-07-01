<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 10.05.2019
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Документы</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="row">
    <div class="col-4 get">
        <p>Добро пожаловать! </p>
        <p>Login  ${login}</p> <br>
        <p>Password  ${password}</p> <br>
    </div>
        <div class="col-4 get">
            <nav class="nav flex-column">
                <a class="nav-link active" href="/archive/index.html">Главная</a>
                <a class="nav-link" href= "/archive/file?name=${login}">Файлы</a>
                <a class="nav-link" href="/archive/static/about.html">О нас</a>
            </nav>
        </div>
</div>
</body>
</html>
