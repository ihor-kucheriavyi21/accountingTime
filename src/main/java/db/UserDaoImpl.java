package db;

import model.entity.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    //todo check Query
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class.getName());

    SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.getInstance();

    @Override
    public boolean validate(String name, String pass) {
        boolean status = false;
        ResultSet rs = null;
        try (Connection con = sqlDatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "select * from person where login=? and pass=?")) {


            ps.setString(1, name);
            ps.setString(2, pass);

            rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException throwables) {
                LOGGER.log(Level.ERROR, throwables.getMessage());
            }
        }
        return status;
    }

    @Override
    public User getCurrentUser(User user) {
        int idUser = -1;
        String login = null;
        String pass = null;
        int idRole = -1;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection con = sqlDatabaseManager.getConnection();) {
            ps = con.prepareStatement(
                    "select * from person where login=? and pass=?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPass());

            rs = ps.executeQuery();
            if (rs.next()) {
                idUser = rs.getInt(1);
                login = rs.getString(2);
                pass = rs.getString(3);
                idRole = rs.getInt(4);
            }
            System.out.println("FROM USER SOUT ID" + idUser);
        } catch (SQLException throwables) {
            LOGGER.log(Level.ERROR, throwables.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    LOGGER.log(Level.ERROR, throwables.getMessage());
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    LOGGER.log(Level.ERROR, throwables.getMessage());
                }
            }
        }
        user = new User(idUser, login, pass, idRole);
        return user;

    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList();
        ResultSet resultSet = null;
        try (Connection connection = sqlDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT  * FROM user")) {
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idUser = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String pass = resultSet.getString(3);
                int idRole = resultSet.getInt(4);
                User user = new User(idUser, username, pass, idRole);
                list.add(user);
            }
        } catch (SQLException throwables) {
            LOGGER.error("Get all exception", throwables);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    LOGGER.error("Get all exception", throwables);
                }
            }
        }
        return list;
    }

    @Override
    public int save(User user) {
        user.setIdRole(1);
        int idUser = -1;
        String query = "INSERT INTO person (login, pass, idRole) values (?,?,?)";
        try (Connection connection = sqlDatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPass());
            preparedStatement.setInt(3, user.getIdRole());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
                idUser = resultSet.getInt(1);

        } catch (SQLException throwables) {
            LOGGER.log(Level.ERROR, throwables.getMessage());
        }
        return idUser;
    }

    @Override
    public void update(User user) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = sqlDatabaseManager.getConnection()) {
            preparedStatement = connection.prepareStatement("update person SET login = ?, pass =?, idRole = ? where id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPass());
            preparedStatement.setInt(3, user.getIdRole());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.log(Level.ERROR, throwables.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                LOGGER.log(Level.ERROR, throwables.getMessage());
            }
        }
    }

    @Override
    public void delete(User user) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = sqlDatabaseManager.getConnection()) {
            preparedStatement = connection.prepareStatement("delete from user where id = ?");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.error("Delete user exception", throwables);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                LOGGER.error("Prepared statement exception", throwables);
            }
        }
    }
}