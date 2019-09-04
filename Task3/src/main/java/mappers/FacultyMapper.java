package mappers;

import models.Entrant;
import models.Faculty;
import models.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FacultyMapper implements Mapper<Faculty> {
    @Override
    public Faculty map(ResultSet resultSet) throws SQLException {

        Faculty faculty = new Faculty();
        faculty.setId(resultSet.getLong("id"));
        faculty.setName(resultSet.getString("name"));
        faculty.setEntrants(createEntrant(resultSet.getString("entrant_id")));
        return faculty;
    }

    private List<Entrant> createEntrant(String idString){
        String[] ids = idString.split(",");
        return Stream.iterate(0, integer -> integer + 1)
                .limit(ids.length)
                .map(integer -> {
                    long id = Long.parseLong(ids[integer]);
                    Entrant entrant = new Entrant();
                    entrant.setId(id);
                    return entrant;
                })
                .collect(Collectors.toList());
    }
}
