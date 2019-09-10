package dao;

import mappers.FacultyMapper;
import models.Faculty;
import service.ExecutorService;
import utils.PropertyReader;

import java.util.List;
import java.util.Properties;

public class FacultyDao implements Dao<Faculty> {
    private static Properties properties = PropertyReader.readProperty();
    private ExecutorService executor;

    private static final String SELECT_ALL = properties.getProperty("facultyDao.selectAll");
    private static final String UPDATE = properties.getProperty("facultyDao.update");
    private static final String DELETE = properties.getProperty("facultyDao.delete");
    private static final String CREATE = properties.getProperty("facultyDao.create");
    private static final String SELECT_BY_ID = properties.getProperty("facultyDao.selectById");
    private static final String SELECT_BY_NAME = properties.getProperty("facultyDao.selectByName");
    private static final String SELECT_LAST = properties.getProperty("facultyDao.selectLast");

    public FacultyDao (ExecutorService executor) {this.executor = executor;}

    @Override
    public List<Faculty> getAll() {return executor.executorSelectList(SELECT_ALL, new FacultyMapper());}

    @Override
    public void update(Faculty faculty) {
        executor.executorUpdate(UPDATE, faculty.getName(), faculty.getId());
    }

    @Override
    public Faculty getEntityById(Long id) {
        return executor.executorSelect(SELECT_BY_ID, new FacultyMapper(), id);
    }

    @Override
    public void delete(Long id) {executor.executorUpdate(DELETE, id);}

    @Override
    public Faculty create(Faculty entity) {
        entity.getEntrants().forEach(entrant -> executor.executorUpdate(CREATE, entity.getName(), entrant.getId()));
        return executor.executorSelect(SELECT_LAST, new FacultyMapper());
    }

    public Faculty selectByName(String name){
        return executor.executorSelect(SELECT_BY_NAME, new FacultyMapper(), name);
    }
}
