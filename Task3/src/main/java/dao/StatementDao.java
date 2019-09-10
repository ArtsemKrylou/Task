package dao;

import mappers.StatementMapper;
import models.Statement;
import service.ExecutorService;
import utils.PropertyReader;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class StatementDao implements Dao<Statement> {
    private static Properties properties = PropertyReader.readProperty();
    private ExecutorService executor;

    private static final String SELECT_ALL = properties.getProperty("statementDao.selectAll");
    private static final String CREATE = properties.getProperty("statementDao.create");
    private static final String DELETE = properties.getProperty("statementDao.delete");
    private static final String SELECT_BY_ID = properties.getProperty("statementDao.selectById");
    private static final String SELECT_LAST = properties.getProperty("statementDao.selectLast");

    public StatementDao (ExecutorService executor) {this.executor = executor;}


    @Override
    public List<Statement> getAll() {
        Statement statement = executor.executorSelect(SELECT_ALL, new StatementMapper());
        return Collections.singletonList(statement);
    }

    @Override
    public void update(Statement entity) {

    }

    @Override
    public Statement getEntityById(Long id) {
        return executor.executorSelect(SELECT_BY_ID, new StatementMapper(), id);
    }

    @Override
    public void delete(Long id) {
        executor.executorUpdate(DELETE);
    }

    public void deleteAll(){
        executor.executorUpdate(DELETE);
    }

    @Override
    public Statement create(Statement entity) {
        entity.getEntrants().forEach(entrant -> executor.executorUpdate(CREATE, entrant.getId()));
        return executor.executorSelect(SELECT_LAST, new StatementMapper());
    }
}
