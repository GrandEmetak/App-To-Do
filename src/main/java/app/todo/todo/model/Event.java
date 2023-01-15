package app.todo.todo.model;

import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * модель описывает событие в БД  для Хибернате
 * 18.11.21 произведена замена  private TimeStamp created на  private Date created,
 * добавлена к ней аннотация для нибернате @Temporal(TemporalType.TIMESTAMP) - переписан ряд калссав,
 * использующих данное поле Объекта;
 * 5. Date [#331992]00
 * Уровень : 3. Мидл Категория : 3.3. HibernateТопик : 3.3.2. Mapping
 * Hibernate позволяет отображать различные классы даты и времени Java, как свойства сущности
 * модели предметной области. Стандартом SQL определено три типа даты и времени:
 * DATA - JDBC: java.sql.Date;, TIME - java.sql.Time;, TIMESTAMP-java.sql.Timestamp.
 * стоит избегать зависимости  от java.sql пакета, поэтому достаточно эффективным будет
 * использование пакетов java.util или java.time, в которых определены классы для работы
 * со временем и датами. При этом для сопоставления, например java.util.Date, и типов,
 * которые указаны выше используется аннотация @Temporal.
 * Определим модель данных Product, одним из полей которой будет дата производства
 * Т.е. в данном случае мы будем сохранять в БД Timestamp - это определяет следующая аннотация: *
 * Temporal(TemporalType.TIMESTAMP)
 * при этом поле будет проинициализировано текущей датой и временем: *
 * p.created = new Date(System.currentTimeMillis());
 * Если мы не указываем часовой пояс, то драйвер JDBC использует базовый часовой пояс JVM по умолчанию,
 * однако, это может не подходить, если наше приложение будет использоваться пользователями из разных
 * стран по всему миру. Для такого случая мы можем настроить часовой пояс на уровне
 * сессии Session следующим образом:
 * Session session = sessionFactory()
 * .withOptions()
 * .jdbcTimeZone(TimeZone.getTimeZone("UTC"))
 * .openSession();
 * т.е. в данном случае часовой пояс будет определен как UTC - это значение можно изменять
 * различными ключами
 * Данное свойство определения часового пояса можно определить на уровне конфигурационного
 * файла hibernate.cfg.xml - за это отвечает следующее поле:\
 *  property name="hibernate.jdbc.time_zone">Asia/Yekaterinburg</property>
 *
 * @author SlartiBartFast-art
 * @since 18.11.21
 */
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private boolean done;

    private String rank;

    public Event() {
    }

    public Event(int id, String description, Date created, boolean done, String rank) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
        this.rank = rank;
    }

    public Event(String description, Date created, boolean done, String rank) {
        this.description = description;
        this.created = created;
        this.done = done;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return id == event.id
                && done == event.done
                && Objects.equals(description, event.description)
                && Objects.equals(created, event.created)
                && Objects.equals(rank, event.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, done, rank);
    }

    @Override
    public String toString() {
        return "Event{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", created=" + created
                + ", done=" + done
                + ", rank='" + rank + '\''
                + '}';
    }
}
