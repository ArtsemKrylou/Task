package dao;

import mappers.EntrantMapper;
import models.Entrant;

import java.util.List;
import service.ExecutorService;

public class EntrantDao implements Dao<Entrant> {

    private ExecutorService executor;

    private static final String SELECT_ALL = "SELECT entrant.id, entrant.name, GROUP_CONCAT( certificate.id) AS certificate_id, certificate.mark, GROUP_CONCAT( subject.name) AS subject_name, GROUP_CONCAT( subject.mark) AS subject_mark, GROUP_CONCAT(subject.id) AS subject_id FROM entrant\n" +
            "INNER JOIN certificate ON entrant.id = certificate.entrant_id\n" +
            "INNER JOIN subject ON entrant.id = subject.entrant_id group by entrant.id";
    private static final String UPDATE = "update task2.entrant set name = ? where id = ?";
    private static final String DELETE = "delete from entrant where id = ?";
    private static final String CREATE = "insert into entrant(name) VALUES (?)";
    private static final String SELECT_BY_ID = "SELECT entrant.id, entrant.name, GROUP_CONCAT( certificate.id) AS certificate_id, certificate.mark, GROUP_CONCAT( subject.name) AS subject_name, GROUP_CONCAT( subject.mark) AS subject_mark, GROUP_CONCAT(subject.id) AS subject_id FROM entrant\n" +
            "INNER JOIN certificate ON entrant.id = certificate.entrant_id\n" +
            "INNER JOIN subject ON entrant.id = subject.entrant_id WHERE entrant.id = ? group by entrant.id";
    private static final String SELECT_LAST = " SELECT * FROM entrant HAVING id = (SELECT MAX(id) FROM entrant)";
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
