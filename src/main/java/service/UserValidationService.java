package service;

import db.UserDao;

public class UserValidationService {
    public boolean isUserValid(String user, String password) {
        if (UserDao.validate(user, password))
            return true;
        return false;
    }
}
