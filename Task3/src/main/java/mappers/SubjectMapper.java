package mappers;

import models.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements Mapper<Subject> {

    @Override
    public Subject map(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("id"));
        subject.setName(resultSet.getString("name"));
        subject.setMark(resultSet.getInt("mark"));
        return subject;
    }
}
