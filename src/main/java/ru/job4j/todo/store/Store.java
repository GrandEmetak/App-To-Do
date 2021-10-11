package ru.job4j.todo.store;

import ru.job4j.todo.model.Item;

import java.sql.Timestamp;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * модель хранилища
 */
public class Store {
    private static final Store INST = new Store();

    private final Map<Integer, Item> items = new ConcurrentHashMap<>();

    private Store() {
        items.put(1, new Item(1, "walk the dog", new Timestamp(System.currentTimeMillis()), false));
        items.put(2, new Item(2, "grocery shopping", new Timestamp(System.currentTimeMillis()), false));
        items.put(3, new Item(3, "workout", new Timestamp(System.currentTimeMillis()), false));
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Item> findAll() {
        return items.values();
    }
}
