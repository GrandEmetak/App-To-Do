package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Event;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.HbnStore;
import ru.job4j.todo.store.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Давайте теперь создадим сервлет, который будет отрабатывать запросы.
 * объект типа PrintWriter, используемый для отправки вывода клиенту.
 * Однако, чтобы сделать объект response полезным,
 * следует использовать буферизированный вариант PrintWriter - JspWriter
 * Перед определением класса указана аннотация WebServlet,
 * которая указывает, с какой конечной точкой будет сопоставляться данный сервлет.
 * То есть данный сервлет будет обрабатывать запросы по адресу "/greet".
 * Для обработки GET-запросов (например, при обращении к сервлету из адресной строки браузера)
 * сервлет должен переопределить метод doGet. То есть, к примеру, в данном случае get-запрос
 * по адресу /greet будет обрабатываться методом doGet.
 * два параметра. Параметр типа HttpServletRequest инкапсулирует всю информацию о запросе.
 * А параметр типа HttpServletResponse позволяет управлять ответом.
 */

public class ToDoServlet extends HttpServlet {
     private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String category;
        var fr = req.getParameter("description");
        category = req.getParameter("category");
        if (category.equals("null")) {
            category = "normal";
        }
        System.out.println("String " + fr);
        System.out.println("String " + category);
        Event event = new Event(fr, Timestamp.valueOf(LocalDateTime.now()), false, category);
        HbnStore.instOf().add(event);
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(event);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
