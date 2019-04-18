package by.epam.superproject.selectioncommittee.humans;

import by.epam.superproject.selectioncommittee.Subject;

import java.util.List;
import java.util.Objects;

public class Certificate {

    private List <Subject> subjects;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certificate)) return false;
        Certificate that = (Certificate) o;
        return Objects.equals(subjects, that.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjects);
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "subjects=" + subjects +
                '}';
    }
}
