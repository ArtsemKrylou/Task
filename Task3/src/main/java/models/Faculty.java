package models;

import java.util.List;
import java.util.Objects;

public class Faculty {

    private String name;
    private List<Entrant> entrants;
    private long id;

    public Faculty() {
    }

    public Faculty(String name, List<Entrant> entrants, long id) {
        this.name = name;
        this.entrants = entrants;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Faculty)) return false;
        Faculty faculty = (Faculty) o;
        return getId() == faculty.getId() &&
                Objects.equals(getName(), faculty.getName()) &&
                Objects.equals(getEntrants(), faculty.getEntrants());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEntrants(), getId());
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", entrants=" + entrants +
                ", id=" + id +
                '}';
    }
}
