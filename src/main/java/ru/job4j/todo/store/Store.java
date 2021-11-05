package ru.job4j.todo.store;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;

import java.sql.Timestamp;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * модель хранилища
 */
public class Store {

    private static final Store INST = new Store();
    // private static final Map<String, String> CATEGORY = new ConcurrentHashMap<>();

    private final AtomicInteger postId = new AtomicInteger();
    private final Map<String, String> cat = new ConcurrentHashMap<>();

    private int i = 0;
    private final Map<Integer, Item> items = new ConcurrentHashMap<>();

    private Store() {
        items.put(postId.incrementAndGet(), new Item(1, "walk the dog", new Timestamp(System.currentTimeMillis()),
                false, new Category(0, "normal")));
        items.put(postId.incrementAndGet(), new Item(2, "grocery shopping", new Timestamp(System.currentTimeMillis()),
                false, new Category(0, "normal")));
        items.put(postId.incrementAndGet(), new Item(3, "workout", new Timestamp(System.currentTimeMillis()),
                false, new Category(0, "normal")));
    }

    public static Store instOf() {
        return INST;
    }

    /**
     * Category category) - добавлять или нет . вопрос
     *
     * @param item
     * @return
     */
    public Item add(Item item, String category) {
        Item item1 = null;
        item.setCategory(new Category(0, category));
        var i = postId.incrementAndGet();
        item.setId(i);
        item1 = items.put(i, item);
        cat.put(category, category);
        return item1;
    }

    public Collection<Item> findAll() {
        return items.values();
    }
/*
    public static String findCategoryS(String catgr) {
        return CATEGORY.get(catgr);
    }

    public String findCategory(String catgr) {
        return cat.get(catgr);
    }*/

    public static void main(String[] args) {
        Store store = Store.instOf();
        store.add(new Item(0,
                "desc",
                new Timestamp(System.currentTimeMillis()), false), "normal");
        store.add(new Item(0,
                "fantan",
                new Timestamp(System.currentTimeMillis()), false), "normal");
        for (Item item : store.findAll()) {
            System.out.println(item);
        }
    }
}
