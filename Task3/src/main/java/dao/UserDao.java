package dao;

import mappers.UserMapper;
import models.User;
import service.ExecutorService;
import utils.PropertyReader;

import java.util.List;
import java.util.Properties;

public class UserDao implements Dao<User> {
    private static Properties properties = PropertyReader.readProperty();
    private ExecutorService executor;

    private static final String SELECT_ALL = properties.getProperty("userDao.selectAll");
    private static final String UPDATE = properties.getProperty("userDao.update");
    private static final String DELETE = properties.getProperty("userDao.delete");
    private static final String CREATE = properties.getProperty("userDao.create");
    private static final String SELECT_BY_ID = properties.getProperty("userDao.selectById");
    private static final String SELECT_BY_USERNAME = properties.getProperty("userDao.selectByUserName");
    private static final String SELECT_LAST = properties.getProperty("userDao.selectLast");

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
        executor.executorUpdate(CREATE, entity.getUserName(), entity.getPassword(), entity.getEntrant().getId());
        return executor.executorSelect(SELECT_LAST,new UserMapper());

    }

    public User selectByUserName(User entity){
        return executor.executorSelect(SELECT_BY_USERNAME, new UserMapper(), entity.getUserName());
    }
}
