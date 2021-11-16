package ru.job4j.todo;

import org.hibernate.SessionFactory;
import ru.job4j.todo.model.Event;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.List;

public interface Store {

    void save(User user);

    List<User> findByEmail(String email);

    public Event create(Event event);

    public List<Event> findAll();

    public List<User> findAllUser();

    public boolean update(Event event);

    public boolean delete(int id);

    public Event add(Event event);

    public User addUser(User user);

    public Event findById(int id);
}
