package ru.job4j.todo.store;

import ru.job4j.todo.model.Category;
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
//    private static final Map<Integer, String> CATEGORY = new ConcurrentHashMap<>();
private final Map<Integer, String> cat = new ConcurrentHashMap<>();

    private int i = 0;
    private final Map<Integer, Item> items = new ConcurrentHashMap<>();

    private Store() {
        items.put(++i, new Item(1, "walk the dog", new Timestamp(System.currentTimeMillis()), false));
        items.put(++i, new Item(2, "grocery shopping", new Timestamp(System.currentTimeMillis()), false));
        items.put(++i, new Item(3, "workout", new Timestamp(System.currentTimeMillis()), false));
//        CATEGORY.put(1, "normal");
//        CATEGORY.put(2, "hard");
//        CATEGORY.put(3, "important");
        cat.put(1, "normal");
        cat.put(2, "hard");
     cat.put(3, "important");
    }

    public static Store instOf() {
        return INST;
    }

    public Item add(Item item) { // , Category category) {
        Item item1 = null;
        item = items.put(++i, item);
        return item;
    }

    public Collection<Item> findAll() {
        return items.values();
    }

//    public static String findCategoryById(int id) {
////        return CATEGORY.get(id);
//
//    }

    public static void main(String[] args) {
        Store store = Store.instOf();
        store.add(new Item(0,
                "desc",
                new Timestamp(System.currentTimeMillis()), false));
        for (Item item : store.findAll()) {
            System.out.println(item);
        }
    }
}
