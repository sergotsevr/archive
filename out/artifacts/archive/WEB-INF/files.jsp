<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 11.05.2019
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Архив-файлы</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h3>Добро пожаловать ${name}</h3>
    <div class="col-4 get">
        <nav class="nav flex-column">
            <a class="nav-link active" href="/archive/index.html">Главная</a>
            <a class="nav-link" href="/archive/static/about.html">О нас</a>
        </nav>
    </div>
    <div class="get">
        <table class="table">
            <thead>
                <caption>Отсканированные документы</caption>
                <tr>
                    <th>Номер документа</th>
                    <th>Наименование документов</th>
                    <th>Крайние даты</th>
                    <th>Страницы</th>
                    <th>Раcширение</th>
                    <th>Размер</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${List}" var="var" varStatus="myItemStats" >
                    <tr><td>${var[1]}</td><td><a href="/archive/dw?name=${myItem[i]}">${var[2]}</a></td><td>${var[3]}</td><td>${var[4]}</td><td>${var[5]}</td><td>${var[6]}</td></tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
