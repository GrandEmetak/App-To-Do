package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.todo.Store;
import ru.job4j.todo.model.Event;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Использовать для работы с БД через Хибернате
 * в случае необходимости пойска по ключам использовать:
 * var list = session
 * .createQuery("from ru.job4j.tracker.model.Item where name = :key")
 * .setParameter("key", key)
 * .list();
 * 3. Лямбды и шаблон wrapper. [#49295]
 * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.1. Конфигурирование
 * Упростите предыдущий проект (to do List) с использованием шаблона wrapper.
 * посмотреть код методов до переделывания кода на лямбда и шаблон wrapper
 * https://github.com/SlartiBartFast-art/job4j_todo/commit/a93ebe67dcde879a2cb2e0c2939e6fc98ce4b529
 *
 * @author SlartiBartFast-art
 * @since 13.10.21
 */
public class HbnStore implements Store {
    private static final HbnStore INST = new HbnStore();

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public static HbnStore instOf() {
        return INST;
    }

    /**
     * Лямбды и шаблон wrapper.
     * Здесь мы можем применить шаблон проектирования wrapper.
     * Function<T,R>
     * Функциональный интерфейс Function<T,R> представляет функцию перехода
     * от объекта типа T к объекту типа R
     * Метод apply()- это основной абстрактный функциональный метод Function интерфейса.
     * Он принимает в качестве входных данных параметр T типа  и выдает выходной объект типа R.
     * Interface EntityTransaction.commit()
     * - Зафиксируйте текущую транзакцию ресурса, записав все незатронутые изменения в базу данных.
     *
     * @param command
     * @param <T>
     * @return
     */
    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * Возращает Лист всех событий
     * метод написан с учетом Лямбды и шаблон wrapper.
     *
     * @return
     */
    @Override
    public List<Event> findAll() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.todo.model.Event").list()
        );
    }

    @Override
    public List<User> findAllUser() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.todo.model.User").list()
        );
    }

    /**
     * Метов добавляет событие(Event) в БД
     * метод написан с учетом Лямбды и шаблон wrapper.
     *
     * @param event
     * @return
     */
    @Override
    public Event add(Event event) {
        return this.tx(
                session -> {
                    session.save(event);
                    return event;
                }
        );
    }

    /**
     * Сохраняет существующего юзера и новую задачу
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) {
        return this.tx(
                session -> {
                    session.save(user);
                    return user;
                }
        );
    }

    /**
     * The method removes an object Event Object from DB
     *
     * @param id Object Event which the we want to delete from BD
     * @return
     */
    @Override
    public boolean delete(int id) {
        return this.tx(
                session -> {
                    Event result = session.get(Event.class, id);
                    session.delete(result);
                    if (result != null) {
                        return true;
                    }
                    return false;
                }
        );
    }

    /**
     * The method finding an object Event Object from DB
     *
     * @param id Event Object
     * @return
     */
    public Event findById(int id) {
        return this.tx(
                session -> session.get(Event.class, id)
        );
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0) {
            createUser(user);
        } else {
            updateUser(user);
        }
    }

    private User createUser(User user) {
        return this.tx(
                session -> {
                    session.save(user);
                    return user;
                }
        );
    }

    private boolean updateUser(User user) {
        return this.tx(
                session -> {
                    String hql = "UPDATE User set name = :name, "
                            + "email = :email, "
                            + "password = :password "
                            + "WHERE id = :userId";
                    Query query = session.createQuery(hql);
                    query.setParameter("name", user.getName());
                    query.setParameter("email", user.getEmail());
                    query.setParameter("password", user.getPassword());
                    query.setParameter("userId", user.getId());
                    var t = query.executeUpdate();
                    System.out.println("ТО что пришло " + t);
                    if (t != 0) {
                        return true;
                    }
                    return false;
                }
        );
    }

    @Override
    public User findByEmail(String email) {
        return (User) this.tx(
                session -> {
                    String sql = "from ru.job4j.todo.model.User where email = :email";
                   Query query = session.createQuery(sql);
                   query.setParameter("email", email);
                   var t = query.list();
                    return t.get(0);
                }
        );
    }

    @Override
    public Event create(Event event) {
        return null;
    }

    /**
     * The method is updating DB events
     *
     * @param event Object to update
     * @return true if it was successful or false
     */
    @Override
    public boolean update(Event event) {
        return this.tx(
                session -> {
                    String hql = "UPDATE Event set description = :description, "
                            + "created = :created, "
                            + "done = :done, "
                            + "rank =:rank "
                            + "WHERE id =:eventId";
                    Query query = session.createQuery(hql);
                    query.setParameter("description", event.getDescription());
                    query.setParameter("created", event.getCreated());
                    query.setParameter("done", event.isDone());
                    query.setParameter("rank", event.getRank());
                    query.setParameter("eventId", event.getId());
                    int result = query.executeUpdate();
                    System.out.println("ТО что пришло " + result);
                    if (result == 1) {
                        return true;
                    }
                    return false;
                }
        );
    }

    /**
     * метод проводит конвертацию времени из LocalDateTime в String
     *
     * @param localDateTime
     * @return
     */
    private String convert(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy.");
        return localDateTime.format(dateTimeFormatter);
    }

    public static void main(String[] args) {

 /*       var rsl = hbnStore.delete(ev.getId());
        System.out.println("То что вернул delete(ID) : " + rsl);
        var fnd = hbnStore.findById(3);
        System.out.println("То что нашили по findById(id) :" + fnd);
        Event event1 = new Event(3, "workout legs day!", Timestamp.valueOf(LocalDateTime.now()), false, "normal");
        var updRsl = hbnStore.update(event1);
        System.out.println("После update(event) : " + updRsl);
        var t = hbnStore.findAll();
        for (Event eventT : t) {
            System.out.println("ТО что пришло из БД : " + eventT);
        }*/
        HbnStore hbnStore = new HbnStore();
//        Event event = new Event("car wash", Timestamp.valueOf(LocalDateTime.now()), false, "normal");
//        var ev = hbnStore.add(event);
//        System.out.println("То что вернул метод add(Event event) :" + ev);
//        hbnStore.save(User.of("John Smith", "JohnPost@post.com", "WordPassword", ev));
//        for (User user : hbnStore.findAllUser()) {
//            System.out.println("ТО что нашли по User : " + user + " " + user.getEvent().getDescription());
//        }
        String email = "PetrArsentev@mail.ru";
        var usr = hbnStore.findByEmail(email);
        System.out.println(" то что нашли по почте : " + usr);
    }
}
