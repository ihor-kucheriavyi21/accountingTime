package model.service;

import db.UserDaoImpl;
import model.entity.User;

public class UserService {
    //todo move to constructor
    private UserDaoImpl userDao;

    public UserService() {
        userDao = new UserDaoImpl();
    }

    public int getUserId(User user) {
        return userDao.getCurrentUser(user).getId();
    }

    public User getUser(User user) {
        return userDao.getCurrentUser(user);
    }

    public boolean requestIsValidByFillingFields(String name, String pass) {
        return (name != null && name.length() > 0)
                && (pass != null && pass.length() > 0);
    }

    public boolean requestIsValidByPass(String pass, String repPass) {
        return pass.equals(repPass);
    }

    public int saveUser(User user) {

        return userDao.save(user);
    }

    public boolean checkIfExist(String user, String password) {
        if (userDao.validate(user, password))
            return true;
        return false;
    }
}
