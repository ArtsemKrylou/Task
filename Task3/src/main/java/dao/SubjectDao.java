package dao;

import factories.ConnectionFactory;
import mappers.SubjectMapper;
import models.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao implements Dao<Subject> {
    private final Connection connection;

    public SubjectDao(){
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connection = connectionFactory.getConnection();}

    private List<Subject> executorSelect(String query, Object...params){
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParams(stmt, params);
            List<Subject> list = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                 list.add(new SubjectMapper().map(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private int executorUpdate(String query, Object...params){
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParams(stmt, params);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public List<Subject> select(String query, Object...params) {
       return executorSelect(query, params);

    }

    @Override
    public int create(String query, Object...params) {
        return executorUpdate(query, params);
    }

    @Override
    public int update(String query, Object...params) {
        return executorUpdate(query, params);
    }

    @Override
    public int delete(String query, Object...params) {
        return executorUpdate(query, params);
    }
    private void setParams(PreparedStatement stmt, Object[] params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                try {
                    setParam(stmt, i + 1, params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }


    private void setParam(PreparedStatement stmt, int i, Object object) throws SQLException {
        if (object instanceof String) {
            stmt.setString(i, (String) object);
        } else if (object instanceof Integer) {
            stmt.setInt(i, (Integer) object);
        }else if (object instanceof Long) {
            stmt.setLong(i, (Long) object);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
