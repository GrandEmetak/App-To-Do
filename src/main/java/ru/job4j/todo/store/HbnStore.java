package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.Store;
import ru.job4j.todo.model.Event;
import ru.job4j.todo.model.Item;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Использовать для работы с БД через Хибернате
 * в случае необходимости пойска по ключам использовать:
 * var list = session
 *                     .createQuery("from ru.job4j.tracker.model.Item where name = :key")
 *                     .setParameter("key", key)
 *                     .list();
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

    @Override
    public Event create(Event event) {
        return null;
    }

    /**
     * Возращает Лист всех событий
     * @return
     */
    @Override
    public List<Event> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.todo.model.Event").list();
        session.getTransaction().commit();
        session.close();
        System.out.println(" All OK ->");
        return result;
    }

/* var list = session
                    .createQuery("from ru.job4j.tracker.model.Item where name = :key")
                    .setParameter("key", key)
                    .list();
* */

    @Override
    public boolean update(Event event) {
        return false;
    }

    /**
     * Удаляет Событие из Базы Эвент по id
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                Event result = session.get(Event.class, id);
                session.delete(result);
                session.getTransaction().commit();
                session.close();
                rsl = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Session session");
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Event add(Event event) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(event);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public Event findById(int id) {
        Event event = null;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                Event result = session.get(Event.class, id);
                session.getTransaction().commit();
                session.close();
                event = result;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Session session");
            e.printStackTrace();
        }
        return event;
    }
  /*  @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        List<Item> itemList = new ArrayList<>();
        try {
            session.beginTransaction();
            var list = session
                    .createQuery("from ru.job4j.tracker.model.Item where name = :key")
                    .setParameter("key", key)
                    .list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }*/

    public static void main(String[] args) {
        HbnStore hbnStore = new HbnStore();
        Event event = new Event("car wash", Timestamp.valueOf(LocalDateTime.now()), false, "normal");
        var ev = hbnStore.add(event);
        var t = hbnStore.findAll();
        for (Event eventT : t) {
            System.out.println("ТО что пришло из БД : " + eventT);
        }
    }
}
