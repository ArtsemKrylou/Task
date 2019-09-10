package dao;

import mappers.EntrantMapper;
import models.Entrant;

import java.util.List;
import java.util.Properties;

import service.ExecutorService;
import utils.PropertyReader;

public class EntrantDao implements Dao<Entrant> {
    private static Properties properties = PropertyReader.readProperty();
    private ExecutorService executor;

    private static final String SELECT_ALL = properties.getProperty("entrantDao.selectAll");
    private static final String UPDATE = properties.getProperty("entrantDao.update");
    private static final String DELETE = properties.getProperty("entrantDao.delete");
    private static final String CREATE = properties.getProperty("entrantDao.create");
    private static final String SELECT_BY_ID = properties.getProperty("entrantDao.selectById");
    private static final String SELECT_LAST = properties.getProperty("entrantDao.selectLast");
    public EntrantDao(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public List<Entrant> getAll() {
        return executor.executorSelectList(SELECT_ALL, new EntrantMapper());
    }

    @Override
    public void update(Entrant entity) {
        executor.executorUpdate(UPDATE, entity.getName(), entity.getId());

    }

    @Override
    public Entrant getEntityById(Long id) {
        return executor.executorSelect(SELECT_BY_ID, new EntrantMapper(),id);
    }

    @Override
    public void delete(Long id) {
        executor.executorUpdate(DELETE,id);

    }

    @Override
    public Entrant create(Entrant entity) {
        executor.executorUpdate(CREATE,entity.getName());
        return executor.executorSelect(SELECT_LAST, new EntrantMapper());

    }
}
