package dao;

import mappers.SubjectMapper;
import models.Subject;
import service.ExecutorService;

import java.util.List;

public class SubjectDao implements Dao<Subject> {
    private ExecutorService executor;

    private static final String SELECT_ALL = "SELECT * FROM task2.subject";
    private static final String UPDATE = "update task2.subject set name = ?, mark = ? where id = ?";
    private static final String DELETE = "delete from subject where id = ?";
    private static final String CREATE = "insert into subject(name, mark) VALUES (?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM task2.subject WHERE id = ?";

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
    public void create(Subject entity) {
        executor.executorUpdate(CREATE, entity.getName(), entity.getMark());
    }
}
