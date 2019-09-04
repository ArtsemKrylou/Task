package mappers;

import models.Entrant;
import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        Entrant entrant = new Entrant();
        long entrant_id = resultSet.getLong("entrant_id");
        entrant.setId(entrant_id);
        user.setEntrant(entrant);

        return user;
    }
}
