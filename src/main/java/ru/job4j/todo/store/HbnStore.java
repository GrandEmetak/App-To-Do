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

import java.util.function.Function;

import java.sql.Timestamp;
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
     * The method removes an object Event Object from DB
     *
     * @param id Object Event which the we want to delete from BD
     * @return
     */
    @Override
    public boolean delete(int id) {
        final boolean[] rsl = {false};
        return this.tx(
                session -> {
                    Event result = session.get(Event.class, id);
                    session.delete(result);
                    rsl[0] = true;
                    return rsl[0];
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
        final Event[] event = {null};
        return this.tx(
                session -> {
                    Event result = session.get(Event.class, id);
                    event[0] = result;
                    return event[0];
                }
        );
    }

    @Override
    public Event create(Event event) {
        return null;
    }

    /**
     * The method is updating DB events
     * @param event Object to update
     * @return true if it was successful or false
     */
    @Override
    public boolean update(Event event) {
        final boolean[] rsl = {false};
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
                         rsl[0] = true;
                    }
                    return  rsl[0];
                }
        );
    }

    /*
     *Оригинал метода  public List<Event> findAll() без  чета Лямбды и шаблон wrapper.
     * Возращает Лист всех событий
     * @return
     */
    /* @Override
    public List<Event> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.todo.model.Event").list();
        session.getTransaction().commit();
        session.close();
        System.out.println(" All OK ->");
        return result;
    }*/

   /* @Override
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
    }*/

   /* @Override
    public boolean update(Event event) {
        boolean rsl = false;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                Event result = session.get(Event.class, event.getId());
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
    }*/

    /*
     * Удаляет Событие из Базы Эвент по id
     * @param id
     * @return
     */
  /*  @Override
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
    }*/

  /*  @Override
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
    }*/

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
        System.out.println("То что вернул метод add(Event event) :" + ev);
        var rsl = hbnStore.delete(ev.getId());
        System.out.println("То что вернул delete(ID) : " + rsl);
        var fnd = hbnStore.findById(3);
        System.out.println("То что нашили по findById(id) :" + fnd);
        Event event1 = new Event(3, "workout legs day!", Timestamp.valueOf(LocalDateTime.now()), false, "normal");
        var updRsl = hbnStore.update(event1);
        System.out.println("После update(event) : " + updRsl);
        var t = hbnStore.findAll();
        for (Event eventT : t) {
            System.out.println("ТО что пришло из БД : " + eventT);
        }
    }
}
