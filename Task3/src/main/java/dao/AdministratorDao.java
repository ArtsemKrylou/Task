package dao;

import mappers.AdministratorMapper;
import models.Administrator;
import service.ExecutorService;
import utils.PropertyReader;

import java.util.List;
import java.util.Properties;

public class AdministratorDao implements Dao<Administrator> {
    private static Properties properties = PropertyReader.readProperty();
    private ExecutorService executor;

    private static final String SELECT_ALL = properties.getProperty("administratorDao.selectAll");
    private static final String UPDATE = properties.getProperty("administratorDao.update");
    private static final String DELETE = properties.getProperty("administratorDao.delete");
    private static final String CREATE = properties.getProperty("administratorDao.create");
    private static final String SELECT_BY_ID = properties.getProperty("administratorDao.selectById");
    private static final String SELECT_LAST = properties.getProperty("administratorDao.selectLast");

    public AdministratorDao(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public List<Administrator> getAll() { return executor.executorSelectList(SELECT_ALL, new AdministratorMapper());}

    @Override
    public void update(Administrator administrator) {
        executor.executorUpdate(UPDATE, administrator.getId());

    }

    @Override
    public Administrator getEntityById(Long id) {
        return executor.executorSelect(SELECT_BY_ID, new AdministratorMapper(), id);
    }

    @Override
    public void delete(Long id) { executor.executorUpdate(DELETE, id);}

    @Override
    public Administrator create(Administrator entity) {
        executor.executorUpdate(CREATE);
        return executor.executorSelect(SELECT_LAST, new AdministratorMapper());
    }
}
