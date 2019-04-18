package by.epam.superproject.selectioncommittee.humans;

import by.epam.superproject.selectioncommittee.Faculty;

import java.util.Objects;

public class Student {

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
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(faculty, student.faculty) &&
                Objects.equals(certificate, student.certificate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, faculty, certificate);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", faculty=" + faculty +
                ", certificate=" + certificate +
                '}';
    }
}
