package models;


import java.util.Objects;

public class Certificate {
    private int mark;
    private long id;


    public Certificate() {
    }

    public Certificate(int mark, long id) {
        this.mark = mark;
        this.id = id;
    }

    public Certificate(int mark) {
        this.mark = mark;
    }



    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
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
        if (!(o instanceof Certificate)) return false;
        Certificate that = (Certificate) o;
        return getMark() == that.getMark() &&
                getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMark(), getId());
    }

    @Override
    public String toString() {
        return "Certificate{mark=" + mark +
                ", id=" + id +
                '}';
    }
}
