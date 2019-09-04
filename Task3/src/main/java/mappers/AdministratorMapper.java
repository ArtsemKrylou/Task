package mappers;

import models.Administrator;
import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorMapper implements Mapper<Administrator>  {
    @Override
    public Administrator map(ResultSet resultSet) throws SQLException {
        Administrator administrator = new Administrator();
        administrator.setId(resultSet.getLong("id"));
        User user = new User();
        long user_id = resultSet.getLong("user_id");
        user.setId(user_id);
        administrator.setUser(user);
        return administrator;
    }
}
