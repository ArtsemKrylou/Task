package models;


import java.util.Objects;

public class Entrant {

   private String name;
   private Faculty faculty;
   private Certificate certificate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entrant)) return false;
        Entrant entrant = (Entrant) o;
        return Objects.equals(name, entrant.name) &&
                Objects.equals(faculty, entrant.faculty) &&
                Objects.equals(certificate, entrant.certificate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, faculty, certificate);
    }

    @Override
    public String toString() {
        return "Entrant{" +
                "name='" + name + '\'' +
                ", faculty=" + faculty +
                ", certificate=" + certificate +
                '}';
    }
}
