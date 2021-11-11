package ru.job4j.todo;

import org.hibernate.SessionFactory;
import ru.job4j.todo.model.Event;
import ru.job4j.todo.model.Item;

import java.util.List;

public interface Store {

    public Event create(Event event);

    public List<Event> findAll();

    public boolean update(Event event);

    public boolean delete(int id);

    public Event add(Event event);

    public Event findById(int id);
}
