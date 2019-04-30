package mappers;

import models.Administrator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorMapper implements Mapper<Administrator>  {
    @Override
    public Administrator map(ResultSet resultSet) throws SQLException {
        Administrator administrator = new Administrator();
        administrator.setName(resultSet.getString("name"));
        return administrator;
    }
}
