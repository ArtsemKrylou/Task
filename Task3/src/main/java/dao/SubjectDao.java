package dao;

import mappers.SubjectMapper;
import models.Subject;
import service.ExecutorService;
import utils.PropertyReader;

import java.util.List;
import java.util.Properties;

public class SubjectDao implements Dao<Subject> {
    private static Properties properties = PropertyReader.readProperty();
    private ExecutorService executor;

    private static final String SELECT_ALL = properties.getProperty("subjectDao.selectAll");
    private static final String UPDATE = properties.getProperty("subjectDao.update");
    private static final String DELETE = properties.getProperty("subjectDao.delete");
    private static final String CREATE = properties.getProperty("subjectDao.create");
    private static final String SELECT_BY_ID = properties.getProperty("subjectDao.selectById");
    private static final String SELECT_LAST = properties.getProperty("subjectDao.selectLast");


    public SubjectDao(ExecutorService executor) {
        this.executor = executor;
    }


    @Override
    public List<Subject> getAll() {
        return executor.executorSelectList(SELECT_ALL, new SubjectMapper());
    }

    @Override
    public void update(Subject subject) {
        executor.executorUpdate(UPDATE, subject.getName(), subject.getMark(), subject.getId());
    }

    @Override
    public Subject getEntityById(Long id) {
        return executor.executorSelect(SELECT_BY_ID, new SubjectMapper(), id);
    }

    @Override
    public void delete(Long id) {
        executor.executorUpdate(DELETE, id);
    }

    @Override
    public Subject create(Subject entity) {
        executor.executorUpdate(CREATE, entity.getName(), entity.getMark(),entity.getId());
        return executor.executorSelect(SELECT_LAST, new SubjectMapper());
    }
}
