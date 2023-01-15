package app.todo.manytomany.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * 4. Категории в TУDO List [#331991]
 * Уровень : 3. МидлКатегория : 3.3. HibernateТопик : 3.3.2. Mapping
 * Мы организовали Undirect связь ManyToMany между сущностями.
 * Очевидно, что мы можем создать очень много объектов класса Human,
 * список городов ограничен, при необходимости мы будем добавлять новый город отдельно.
 * @author SlartiBartFast-art
 * @since 18.11.21
 */
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static City of(String name) {
        City city = new City();
        city.name = name;
        return city;
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
        City city = (City) o;
        return id == city.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "City{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
