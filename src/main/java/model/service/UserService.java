package model.service;

import db.UserDao;
import model.entity.User;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    public UserService(){}
    public int getUserId(User user) {
        return userDao.getCurrentUser(user).getId();
    }

    public User getUser(User user) {
        return userDao.getCurrentUser(user);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public boolean requestIsValidByFillingFields(String name, String pass) {
        return (name != null && name.length() > 2)
                && (pass != null && pass.length() > 2);
    }

    public boolean requestIsValidByPass(String pass, String repPass) {
        return pass.equals(repPass) && pass.length() > 2;
    }

    public boolean requestIsValidByName(String name) {
        return name.length() > 2;
    }

    public int saveUser(User user) {

        return userDao.save(user);
    }

    public boolean checkIfExist(String user, String password) {
        if (userDao.validate(user, password))
            return true;
        return false;
    }

    public boolean checkIfUserRoleIsValid(String URI, User user) {
        boolean flag = true;
        if (user == null) {
            flag = false;
        } else {
            if (URI.equals("/categories") && user.getIdRole() == 1) {
                flag = false;
            }
            if (URI.equals("/allTask") && user.getIdRole() == 1) {
                flag = false;
            }
            if (URI.equals("/main") && user.getIdRole() == 2) {
                flag = false;
            }
        }
        return flag;
    }
}
