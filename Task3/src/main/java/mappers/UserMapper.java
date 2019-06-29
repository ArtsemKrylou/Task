package mappers;

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

        return user;
    }
}
