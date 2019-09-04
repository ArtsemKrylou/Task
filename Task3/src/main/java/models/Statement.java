package models;

import java.util.List;
import java.util.Objects;

public class Statement {

    private List<Entrant> entrants;
    private long id;

    public Statement() {
    }

    public Statement(List<Entrant> entrants, long id) {
        this.entrants = entrants;
        this.id = id;
    }

    public Statement(List<Entrant> entrants) {
        this.entrants = entrants;
    }

    public List<Entrant> getEntrants() {
        return entrants;
    }

    public void setEntrants(List<Entrant> entrants) {
        this.entrants = entrants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Statement)) return false;
        Statement statement = (Statement) o;
        return getId() == statement.getId() &&
                Objects.equals(getEntrants(), statement.getEntrants());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntrants(), getId());
    }

    @Override
    public String toString() {
        return "Statement{" +
                "entrants=" + entrants +
                ", id=" + id +
                '}';
    }
}
