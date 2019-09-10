package service;

import dao.UserDao;
import models.Entrant;
import models.User;

import java.util.List;

import static utils.Validator.validateUser;

public class UserService {
    private EntrantService entrantService = new EntrantService();
    private UserDao userDao = new UserDao(new ExecutorService());
    private FacultyService facultyService = new FacultyService();

    public List<User> getUsers(){
        return userDao.getAll();
    }

    public void updateUser(User user){
        if (validateUser(user)){
            userDao.update(user);
        }
    }

    public void deleteUser(Long id){
        userDao.delete(id);
    }

    public User createUser(User user){
        Entrant entrant = entrantService.createEntrant(user.getEntrant());
        user.setEntrant(entrant);
        if (validateUser(user)){
           return userDao.create(user);
        }
        return null;
    }

    public User getUserById(Long id){
        return userDao.getEntityById(id);
    }

    public boolean getUserByName(User user){
        User dbUser = userDao.selectByUserName(user);
        if(dbUser != null){
            Entrant entrant = entrantService.getEntrantById(dbUser.getEntrant().getId());
            user.setEntrant(entrant);
            user.setId(dbUser.getId());
            return user.getPassword().equals(dbUser.getPassword());
        }
        return false;
    }
}
