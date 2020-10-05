package db;

import model.entity.User;

import java.util.Map;

public interface UserDao extends DAO<User> {
    @Override
    void delete(User o);

    @Override
    void update(User o);

    @Override
    int save(User o);

    @Override
    Map getAll();

    boolean validate(String name, String pass);

    User getCurrentUser(User user);

}
