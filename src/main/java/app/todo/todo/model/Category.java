package app.todo.todo.model;

import java.util.Objects;

/**
 * 4. Категории в TODO List [#331991 #230037]
 * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.2. Mapping
 * Задание.
 * 1. Вернитесь к проекту ToDoList, Добавьте модель данных Category,
 * которая будет отображать категорию задания. Модель должна содержать 2 поля: id и name.
 * @author SlartiBartFast-art
 * @since 18.11.21
 */
public class Category {
    private int id;
    private String name;

    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return id == category.id && name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Category{"
                + "id=" + id
                + ", name='"
                + name + '\''
                + '}';
    }
}
