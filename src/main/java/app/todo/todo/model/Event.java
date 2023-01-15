package app.todo.todo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * модель описывает событие в БД
 */
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    private Timestamp created;

    private boolean done;

    private String rank;

    public Event() {
    }

    public Event(int id, String description, Timestamp created, boolean done, String rank) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
        this.rank = rank;
    }

    public Event(String description, Timestamp created, boolean done, String rank) {
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
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
