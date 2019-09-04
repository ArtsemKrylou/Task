package dao;

import mappers.FacultyMapper;
import models.Faculty;
import service.ExecutorService;

import java.util.List;

public class FacultyDao implements Dao<Faculty> {
    private ExecutorService executor;

    private static final String SELECT_ALL = "SELECT faculty.id, faculty.name, GROUP_CONCAT(entrant_id) AS entrant_id FROM task2.faculty GROUP BY faculty.name";
    private static final String UPDATE = "update task2.faculty set name = ?, where id = ?";
    private static final String DELETE = "delete from faculty where id = ?";
    private static final String CREATE = "insert into faculty(name, entrant_id) VALUES (?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM task2.faculty WHERE id = ?";
    private static final String SELECT_BY_NAME = "SELECT faculty.id, faculty.name, GROUP_CONCAT(entrant_id) AS entrant_id FROM task2.faculty  WHERE name = ?";
    private static final String SELECT_LAST = " SELECT * FROM faculty HAVING id = (SELECT MAX(id) FROM faculty)";

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
