package app.todo.todo.model;

import javax.persistence.GenerationType;
import javax.persistence.*;

import java.util.Objects;

/**
 * 1. HttpSession [#6869 #209565]
 * Уровень : 3. МидлКатегория : 3.2. Servlet JSPТопик : 3.2.6. Filter, Security
 * Пользователь, логин пароль
 * Когда браузер отправляет запрос в tomcat создается объект HttpSession.
 * Этот объект связан с работой текущего пользователя.
 *
 * user-role связь many-to-one.Пользователь может иметь только одну роль.
 * Пользователь.
 * В модели "Пользователь" есть поле Role. Это поле содержит целый объект.
 * Hibernate загрузит этот объект сам.
 * Аннотации.
 * ManyToOne
 * JoinColumn(name = "role_id")
 * Указывают, что связь между таблицами - many-to-one и указывают по какому полю идет связь.
 * 0. ToOne [#6873]10
 * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.2. Mapping
 * 1. Откройте проект ТУДУ- список.
 * 2. Добавьте в проект схему user-item(event).
 * 3. Добавьте форму авторизации и регистрации(RegServlet + AuthServlet).
 * 4. В таблице выводить все задания и автора задания.
 * 1. ToMany [#301848]
 * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.2. Mapping
 * OneToMany. рассмотрим Unidirectional связь,
 * т.е. переход от родительской сущности происходит только в одну сторону.
 * перепишем модели данных следующим образом.
 * @author SlartiBartFast-art
 * @since 11.11.21
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "events_id")
    private Event event;

    public User() {
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static User of(String name, String email, String password, Event event) {
        User user = new User();
        user.name = name;
        user.email = email;
        user.password = password;
        user.event = event;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && name.equals(user.name)
                && email.equals(user.email)
                && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", password='" + password
                + ", Event='" + event
                + '\'' + '}';
    }

}