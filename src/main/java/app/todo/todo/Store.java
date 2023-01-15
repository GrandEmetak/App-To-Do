package app.todo.todo;

import app.todo.todo.model.Event;
import app.todo.todo.model.User;

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
