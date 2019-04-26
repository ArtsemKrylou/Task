package service;

import factories.ConnectionFactory;
import mappers.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExecutorService {
    private final Connection connection;

    public ExecutorService() {
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connection = connectionFactory.getConnection();
    }

    public <T> List<T> executorSelectList(String query, Mapper<T> mapper, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParams(stmt, params);
            List<T> list = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                list.add(mapper.map(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public <T> T executorSelect(String query, Mapper<T> mapper, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParams(stmt, params);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapper.map(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public void executorUpdate(String query, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParams(stmt, params);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
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
        } else if (object instanceof Long) {
            stmt.setLong(i, (Long) object);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
