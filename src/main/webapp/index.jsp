<%@ page import="ru.job4j.todo.model.Item" %>
<%--
  Created by IntelliJ IDEA.
  User: SlartiBartFast-art
  Date: 08.10.2021
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<!-- Импорты классов java для работы с ними-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.job4j.todo.store.Store" %>
<%@ page import="ru.job4j.todo.model.Item" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html> <!-- Объявление формата документа определяет не только версию HTML (например, html), но и соответствующий DTD-файл в Интернете-->
<html lang="en">
<head><!-- Техническая информация о документе. -->
    <!-- Введенная в нем информация не отображается в окне браузера, однако содержит данные, которые указывают браузеру,
     как следует обрабатывать страницу. -->
    <!-- Required meta tags -->
    <meta charset="utf-8"><!-- Определяем кодировку символов для текущего HTML-документа -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Bootstrap Example</title><!-- Задаем заголовок документа -->
    <!-- Bootstrap CSS --> <!-- Элемент <link> Задать стили для документа-->
    <!--href	Основной атрибут элемента, в качестве значения выступает путь к файлу со стилями. -->
    <!--Элемент <script> позволяет присоединять к документу различные сценарии.
    Текст сценария может располагаться либо внутри этого элемента, либо во внешнем файле. -->
    <!--src	Указывает на месторасположение файла со сценарием, значение атрибута — это url файла, содержащего JavaScript-программу. -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!-- подключаем верхнюю таблицу Бутстрап? -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script><!-- Подключаем сценарии -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>AJAX</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!--добавить библиотеку jquery. для работы с ajax -->
<script>
    function validate() {
        const value1 = $('#validationDefault01').val();
        const value2 = $('#validationDefault02').val();
        if (value1 === '') {
            alert(value1.attr('Поле не заполнено'));
            console.log('not equals')
            return false;
        }
        if (value2 === 'Choose...') {
            alert($('#validationDefault02').attr('title'));
            return false;
        }
        return true;
    }
</script>
<script>
    function addRow() {
        //получаем значение поля usr
        const name = $('#usr').val();

        //получаем ссылку на последний элемент в таблице.
        //и после него добавляем html
        $('#table tr:last').after('<tr><td>' + name + '</td></tr>');
    }

</script>
<script> <%-- Далее нужно написать скрипт, который будет выполнять http запрос. Когда клиент нажимает на кнопку метод .ajax выполняет http запрос. --%>
<%--
type: 'POST', - method
url: '//localhost:8080/job4j_todo/greet',  куда пойдет запрос
description: $('#validationDefault01').val(), <!-- Данные из формы -->
.done(function(data) {   - Если запрос выполнился удачно
alert(data)   -- alert()  Она показывает сообщение и ждёт, пока пользователь нажмёт кнопку «ОК».
fail(function(err) --Если запрос выполнился не удачно --

            alert(data)
 --%>
function add() {
     let item;
    if (validate()) {
        $.ajax({
            type: 'POST',
            url: '//localhost:8080/job4j_todo/greet',
            data: {
                description: $('#validationDefault01').val(),
                category: $('#validationDefault02').val()
            }
        }).done(function(data) {
            $('#table tr:last').after('<tr><td>' + data.toString() + '</td></tr>');

        }).fail(function(err) {
            alert(err);
        })
    }
}
</script>

<title>TO DO</title>
<H1>TO DO</H1> TO DO
<div class="fixed-top">
    <div class="collapse" id="navbarToggleExternalContent">
        <div class="bg-dark p-4">
            <h5 class="text-white h4">Collapsed content</h5>
            <span class="text-muted">Toggleable via the navbar brand.</span>
        </div>
    </div>
    <nav class="navbar navbar-dark bg-dark">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent"
                aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
</div>
<br>
<br>
<br>

<body><!-- Основная часть документа -->
<div class="row">
    <!--<div>	Тег-контейнер для разделов HTML-документа. Используется для группировки блочных элементов с целью форматирования стилями. -->
    <div>
        <!-- Default dropup button Запуск раскрывающихся меню над элементами путем добавления .dropupк родительскому элементу.-->
    </div>
    <div class="container">
        <form class=" need -validation " novalidate>
            <div class="form-group">
                <div class="col-md-6 mb-3">

                    <label for="validationDefault01">Добавить новое задание</label>
                    <textarea class="form-control" id="validationDefault01" rows="5" ></textarea>
                </div>
                <div class="form-row">
                    <div class="col-md-3 mb-3">
                        <label for="validationDefault02">Category</label>
                        <select class="custom-select" id="validationDefault02" required>
                            <option selected disabled value="">Choose...</option>
                            <option>normal</option>
                            <option>hard</option>
                            <option>important</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <button type="submit" class="btn btn-primary" onclick="return add();">Submit form
                        </button>
                    </div>
                </div>
            </div>
            <br>
            <div class="custom-control custom-switch">
                <input type="checkbox" class="custom-control-input" id="customSwitch1">
                <label class="custom-control-label" for="customSwitch1">Show all elements</label>
            </div>
            <table id='table' class="table">
                <caption>list of notes according to the selected category</caption>
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Status</th>
                    <th scope="col">Description</th>
                    <th scope="col">Created</th>
                    <th scope="col">Category</th>
                </tr>
                </thead>
                <tbody>
                <% for (Item item : Store.instOf().findAll()) { %> <!--Java код выпишем через блок % % -->
                <tr>
                    <th scope="row"><%= item.getId() %>
                    </th>
                    <td>
                        <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Check me out</label>
                        </div>
                        <%-- <button type="submit" class="btn btn-primary">Submit</button> --%>
                    </td>
                    <td><%= item.getDescription() %>
                    </td><%--Вывод элемента в jsp %= --%> <!-- Java код написанный в JSP называется скриплет.-->
                    <td><%= item.getCreated() %>
                    </td>
                    <%--  <td><%= item.isDone() %></td> --%>
                </tbody>
                </tr>
                <% } %>
            </table>
            <nav class="navbar navbar-light bg-light">
                <form class="form-inline">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </nav>
        </form>
    </div>
    <%-- --%>

    <%--</div>
--</div><!-- --> --%>
</body> <!-- -->
</html>

