package dao;

import mappers.StatementMapper;
import models.Statement;
import service.ExecutorService;

import java.util.Collections;
import java.util.List;

public class StatementDao implements Dao<Statement> {

    private ExecutorService executor;

    private static final String SELECT_ALL = "SELECT statement.id, GROUP_CONCAT(entrant_id) AS entrant_id FROM task2.statement";
    private static final String CREATE = "insert into statement(entrant_id) VALUES (?)";
    private static final String DELETE = "delete from statement";
    private static final String SELECT_BY_ID = "SELECT * FROM task2.statement WHERE entrant_id = ?";
    private static final String SELECT_LAST = " SELECT * FROM statement HAVING id = (SELECT MAX(id) FROM statement)";

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
