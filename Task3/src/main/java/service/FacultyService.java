package service;

import dao.FacultyDao;
import models.Entrant;
import models.Faculty;

import java.util.List;
import java.util.stream.Collectors;

public class FacultyService {

    private FacultyDao facultyDao = new FacultyDao(new ExecutorService());
    private EntrantService entrantService = new EntrantService();

    public List<Faculty> getFaculty(){

      return facultyDao.getAll()
              .stream()
              .map(faculty -> getFacultyByName(faculty.getName()))
              .collect(Collectors.toList());
    }

    public void updateFaculty(Faculty faculty){
        facultyDao.update(faculty);
    }

    public void deleteFaculty(Long id){
        facultyDao.delete(id);
    }

    public void createFaculty(Faculty faculty){
        facultyDao.create(faculty);
    }

    public Faculty getFacultyByName(String name){
        Faculty faculty = facultyDao.selectByName(name);
        List<Entrant> entrants = faculty.getEntrants()
                .stream()
                .map(entrant -> entrantService.getEntrantById(entrant.getId()))
                .collect(Collectors.toList());
        faculty.setEntrants(entrants);
        return faculty;
    }

}
