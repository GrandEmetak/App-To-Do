package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/*вариант обойтись без jsp
mapping  afterLogin.do
* */
public class AfteLoginServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();
    private List<User> userList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("afterLogin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
//        for (User user : HbnStore.instOf().findByEmail(вы)) {
//            System.out.println("ТО что достакм : " + user);
//            userList.add(user);
//        }

//        String email = req.getParameter("user");
//        System.out.println("SErvlet Geet String user " + email);
//        resp.setContentType("application/json; charset=utf-8");
//        OutputStream output = resp.getOutputStream();
//        String json = GSON.toJson(userList);
//        output.write(json.getBytes(StandardCharsets.UTF_8));
//        output.flush();
//        output.close();

     /* было до добавления логин и авторизация
        for (Event event : HbnStore.instOf().findAll()) {
    System.out.println("ТО что достакм : " + event);
    placeList.add(event);
}
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(placeList);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();*/
    }
}
