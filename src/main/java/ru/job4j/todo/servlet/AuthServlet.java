package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 0. Страница Login.jsp [#281230 #209175]
 * Уровень : 3. МидлКатегория : 3.2. Servlet JSPТопик : 3.2.6. Filter, Security +
 * 0. ToOne [#6873]
 * Сервлет проверяет почту и пароль, если они совпадают, то переходит на страницу вакансий.
 * Если нет, то возвращает обратно на страницу Login.
 * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.2. Mapping
 * Добавьте форму авторизации и регистрации.
 * weburl - /auth.do
 * 1. HttpSession [#6869 #209565]
 * Уровень : 3. МидлКатегория : 3.2. Servlet JSPТопик : 3.2.6. Filter, Security
 */
/*@WebServlet(urlPatterns = "/auth.do")*/
public class AuthServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();
    private List<User> userList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       var user = userList.get(0);
        System.out.println("SErvlet AuthServlet doGet " + user);
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(userList);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();

    }

    /**
     * Когда браузер отправляет запрос в tomcat создается объект HttpSession. Этот объект связан с работой текущего пользователя.
     * Если вы открете другой браузер и сделаете новый запрос,
     * то tomcat создаст еще один объект HttpSession, который уже будет связан с другим пользователем.
     * В объекте HttpSession можно хранить информацию о текущем пользователе.
     * Если пользователь ввел верную почту и пароль, то мы записываем в HttpSession детали этого пользователя.
     * HttpSession sc = req.getSession();
     * User admin = new User();
     * admin.setName("Admin");
     * admin.setEmail(email);
     * sc.setAttribute("user", admin);
     * Теперь эти данные можно получить на другой странице.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        var user = HbnStore.instOf().findByEmail(email);
        System.out.println("АвторизацияСервлет to chto nasloc v BD po email : " + user);
        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
            System.out.println("Зашли в if :" + user.getEmail());
            userList.add(user);
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);

            getServletContext().getRequestDispatcher("/afterLogin.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
