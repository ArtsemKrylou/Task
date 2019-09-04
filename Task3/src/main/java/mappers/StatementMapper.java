package mappers;

import models.Entrant;
import models.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatementMapper implements Mapper<Statement> {
    @Override
    public Statement map(ResultSet resultSet) throws SQLException {
        Statement statement = new Statement();
        statement.setId(resultSet.getLong("id"));
        String entrant_id = resultSet.getString("entrant_id");
        if (entrant_id != null) {
            statement.setEntrants(createEntrant(entrant_id));
        } else {
            statement.setEntrants(Collections.emptyList());
        }
        return statement;
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
