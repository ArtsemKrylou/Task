package service;

import dao.AdministratorDao;
import models.Administrator;
import models.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AdministratorService {

    private AdministratorDao administratorDao = new AdministratorDao(new ExecutorService());
    private UserService userService = new UserService();

    public boolean isAdmin(Long id){
        Administrator administrator = administratorDao.getEntityById(id);
        return administrator != null;
    }




}
