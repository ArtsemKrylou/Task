package models;


import java.util.List;
import java.util.Objects;

public class Entrant {

   private String name;
   private Certificate certificate;
   private List<Subject> subjects;
   private long id;

    public Entrant(String name, Certificate certificate, List<Subject> subjects, long id) {
        this.name = name;
        this.certificate = certificate;
        this.subjects = subjects;
        this.id = id;
    }

    public Entrant(String name, Certificate certificate, List<Subject> subjects) {
        this.name = name;
        this.certificate = certificate;
        this.subjects = subjects;
    }

    public Entrant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
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
        if (!(o instanceof Entrant)) return false;
        Entrant entrant = (Entrant) o;
        return getId() == entrant.getId() &&
                Objects.equals(getName(), entrant.getName()) &&
                Objects.equals(getCertificate(), entrant.getCertificate()) &&
                Objects.equals(getSubjects(), entrant.getSubjects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCertificate(), getSubjects(), getId());
    }

    @Override
    public String toString() {
        return "Entrant{" +
                "name='" + name + '\'' +
                ", certificate=" + certificate +
                ", subjects=" + subjects +
                ", id=" + id +
                '}';
    }
}
