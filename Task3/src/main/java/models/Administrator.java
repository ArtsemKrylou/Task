package models;

import java.util.List;
import java.util.Objects;

public class Administrator {

    private long id;
    private User user;

    public Administrator() {
    }

    public Administrator(long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Administrator(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrator)) return false;
        Administrator that = (Administrator) o;
        return getId() == that.getId() &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser());
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
