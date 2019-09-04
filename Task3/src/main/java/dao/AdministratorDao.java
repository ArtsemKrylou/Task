package dao;

import mappers.AdministratorMapper;
import models.Administrator;
import service.ExecutorService;

import java.util.List;

public class AdministratorDao implements Dao<Administrator> {

    private ExecutorService executor;

    private static final String SELECT_ALL = "SELECT * FROM task2.administrator";
    private static final String UPDATE = "update task2.administrator set name = ? where id = ?";
    private static final String DELETE = "delete from administrator where id = ?";
    private static final String CREATE = "insert into administrator(name) VALUES (?)";
    private static final String SELECT_BY_ID = "SELECT * FROM task2.administrator WHERE user_id = ?";
    private static final String SELECT_LAST = " SELECT * FROM administrator HAVING id = (SELECT MAX(id) FROM administrator)";

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
