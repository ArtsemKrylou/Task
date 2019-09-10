package dao;

import mappers.CertificateMapper;
import models.Certificate;
import service.ExecutorService;
import utils.PropertyReader;

import java.util.List;
import java.util.Properties;

public class CertificateDao implements Dao<Certificate> {
   private static Properties properties = PropertyReader.readProperty();
   private ExecutorService executor;

    private static final String SELECT_ALL = properties.getProperty("certificateDao.selectAll");
    private static final String UPDATE = properties.getProperty("certificateDao.update");
    private static final String DELETE = properties.getProperty("certificateDao.delete");
    private static final String CREATE = properties.getProperty("certificateDao.create");
    private static final String SELECT_BY_ID = properties.getProperty("certificateDao.selectById");
    private static final String SELECT_LAST = properties.getProperty("certificateDao.selectLast");

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
