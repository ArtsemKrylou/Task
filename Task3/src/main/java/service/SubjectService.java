package service;

import dao.SubjectDao;
import models.Subject;
import utils.Validator;

import java.util.List;

import static utils.Validator.validateSubject;

public class SubjectService {

   private SubjectDao subjectDao = new SubjectDao(new ExecutorService());

    public List<Subject> getSubjects(){
        return subjectDao.getAll();
    }

    public void updateSubject(Subject subject) {
        if (validateSubject(subject)) {
            subjectDao.update(subject);
        }
    }
    public void deleteSubject(Long id){
        subjectDao.delete(id);
    }

    public void createSubject(Subject subject) {
        if (validateSubject(subject)) {
            subjectDao.create(subject);
        }
    }

    public Subject getSubjectById(Long id){
        return subjectDao.getEntityById(id);
    }
}
