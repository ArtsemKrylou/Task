package dao;

import mappers.CertificateMapper;
import models.Certificate;
import service.ExecutorService;

import java.util.List;

public class CertificateDao implements Dao<Certificate> {
   private ExecutorService executor;

    private static final String SELECT_ALL = "SELECT * FROM task2.certificate";
    private static final String UPDATE = "update task2.certificate set mark = ? where id = ?";
    private static final String DELETE = "delete from certificate where id = ?";
    private static final String CREATE = "insert into certificate(mark, entrant_id) VALUES (?, (SELECT id FROM entrant WHERE entrant.id = ?))";
    private static final String SELECT_BY_ID = "SELECT * FROM task2.certificate WHERE entrant_id = ?";
    private static final String SELECT_LAST = " SELECT * FROM certificate HAVING id = (SELECT MAX(id) FROM certificate)";

    public CertificateDao(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public List<Certificate> getAll() {
        return executor.executorSelectList(SELECT_ALL, new CertificateMapper());
    }

    @Override
    public void update(Certificate entity) {

        executor.executorUpdate(UPDATE, entity.getMark(), entity.getId());

    }

    @Override
    public Certificate getEntityById(Long id) {
        return executor.executorSelect(SELECT_BY_ID, new CertificateMapper(), id);
    }

    @Override
    public void delete(Long id) {
        executor.executorUpdate(DELETE,id);

    }

    @Override
    public Certificate create(Certificate entity) {
        executor.executorUpdate(CREATE, entity.getMark(), entity.getId());
        return executor.executorSelect(SELECT_LAST, new CertificateMapper());

    }
}
