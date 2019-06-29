package service;

import dao.UserDao;
import models.User;

import java.util.List;

import static utils.Validator.validateUser;

public class UserService {

    private UserDao userDao = new UserDao(new ExecutorService());

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

    public void createUser(User user){
        if (validateUser(user)){
            userDao.create(user);
        }
    }

    public User getUserById(Long id){
        return userDao.getEntityById(id);
    }

    public boolean getUserByName(User user){
        User dbUser = userDao.selectByUserName(user);
        if(dbUser != null){
            return user.getPassword().equals(dbUser.getPassword());
        }
        return false;
    }
}
