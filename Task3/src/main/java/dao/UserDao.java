package dao;

import mappers.UserMapper;
import models.User;
import service.ExecutorService;

import java.util.List;

public class UserDao implements Dao<User> {

    private ExecutorService executor;

    private static final String SELECT_ALL = "SELECT * FROM task2.user";
    private static final String UPDATE = "update task2.user set name = ?, password = ? where id = ?";
    private static final String DELETE = "delete from user where id = ?";
    private static final String CREATE = "insert into user(name, password) VALUES (?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM task2.user WHERE id = ?";
    private static final String SELECT_BY_USERNAME = "SELECT * FROM task2.user WHERE username = ?";
    private static final String SELECT_LAST = " SELECT * FROM user HAVING id = (SELECT MAX(id) FROM user)";

    public UserDao(ExecutorService executor) {
        this.executor = executor;
    }


    @Override
    public List<User> getAll() {
        return executor.executorSelectList(SELECT_ALL, new UserMapper());
    }

    @Override
    public void update(User entity) {
        executor.executorUpdate(UPDATE, entity.getUserName(), entity.getPassword(), entity.getId());
    }

    @Override
    public User getEntityById(Long id) {
        return executor.executorSelect(SELECT_BY_ID, new UserMapper(), id);
    }

    @Override
    public void delete(Long id) {
    executor.executorUpdate(DELETE, id);
    }

    @Override
    public User create(User entity) {
        executor.executorUpdate(CREATE, entity.getUserName(), entity.getPassword(), entity.getId());
        return executor.executorSelect(SELECT_LAST,new UserMapper());

    }

    public User selectByUserName(User entity){
        return executor.executorSelect(SELECT_BY_USERNAME, new UserMapper(), entity.getUserName());
    }
}
