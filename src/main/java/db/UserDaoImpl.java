package db;

import model.entity.User;

import java.sql.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {

    //todo check Query
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

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
            logger.log(Level.WARNING, e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException throwables) {
                logger.log(Level.WARNING, throwables.getMessage());
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
            logger.log(Level.WARNING, throwables.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    logger.log(Level.WARNING, throwables.getMessage());
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    logger.log(Level.WARNING, throwables.getMessage());
                }
            }
        }
        user = new User(idUser, login, pass, idRole);
        return user;


    }

    @Override
    public Map<Integer, User> getAll() {
        return null;
    }

    @Override
    public int save(User user) {
        user.setIdRole(1);
        int idUser = -1;
        String query = "INSERT INTO person (login, pass, idRole) values (?,?,?)";
        try (PreparedStatement preparedStatement = sqlDatabaseManager.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPass());
            preparedStatement.setInt(3, user.getIdRole());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
                idUser = resultSet.getInt(1);

        } catch (SQLException throwables) {
            logger.log(Level.WARNING, throwables.getMessage());
        }
        if (idUser == -1) {
            System.out.println("SOMETHING WENT WRONG");
        }
        return idUser;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}