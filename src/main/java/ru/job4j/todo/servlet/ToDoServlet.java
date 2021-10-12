package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * Давайте теперь создадим сервлет, который будет отрабатывать запросы.
 *  объект типа PrintWriter, используемый для отправки вывода клиенту.
 *  Однако, чтобы сделать объект response полезным,
 *  следует использовать буферизированный вариант PrintWriter - JspWriter
 *  Перед определением класса указана аннотация WebServlet,
 *  которая указывает, с какой конечной точкой будет сопоставляться данный сервлет.
 *  То есть данный сервлет будет обрабатывать запросы по адресу "/greet".
 *  Для обработки GET-запросов (например, при обращении к сервлету из адресной строки браузера)
 *  сервлет должен переопределить метод doGet. То есть, к примеру, в данном случае get-запрос
 *  по адресу /greet будет обрабатываться методом doGet.
 *  два параметра. Параметр типа HttpServletRequest инкапсулирует всю информацию о запросе.
 *  А параметр типа HttpServletResponse позволяет управлять ответом.
 */
//@WebServlet("/greet")  System.out.println(" получаем значение поля usr ");
public class ToDoServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=utf-8");
        var fr =  req.getParameter("description");
        System.out.println("String " + fr);
        Item item = new Item(0,
//                req.getParameter("desc"),
                req.getParameter("description"),
                new Timestamp(System.currentTimeMillis()), false);
//      for (String category : req.getParameterValues("categ[]")) {
//         // Category category1 = new Category(0, Store.findCategoryById(Integer.parseInt(category)));
//          Category category1 = new Category(0, Store.findCategoryById(Integer.parseInt(category)));
//      }
      Store.instOf().add(item);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        String name = req.getParameter("id");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        try {
            writer.println("Nice to meet you, " + name);
            writer.flush();
        } finally {
            writer.close();
        }
    }

}
