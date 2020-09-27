package db;

import model.entity.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLDatabaseManager {
    private static final Logger logger = Logger.getLogger(SQLDatabaseManager.class.getName());
    private static SQLDatabaseManager sqlDatabaseManager;

    private SQLDatabaseManager() {
    }

    public static SQLDatabaseManager getInstance() {
        if (sqlDatabaseManager == null) {
            sqlDatabaseManager = new SQLDatabaseManager();
        }
        return sqlDatabaseManager;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException |
                ClassNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        String url = "jdbc:mysql://localhost:3306/testBDForProject?serverTimezone=Europe/Kiev&useSSL=false&user=root&password=1234";
        return DriverManager.getConnection(url);
    }

    private String getUrlFromProperties() {
        String out = null;
        try (InputStream input = new FileInputStream("app.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            out = prop.getProperty("connectionUrl");
            System.out.println(out);
        } catch (IOException ex) {
            logger.log(Level.WARNING, ex.getMessage());
        }
        return out;
    }

    public int insertTask(Task task) {
        int idTask = -1;
        String query = "INSERT INTO task (taskName, recordingTime, amountOfTime) values (?,?,?)";
        try (PreparedStatement preparedStatement = sqlDatabaseManager.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setTime(2, task.getRecordingTime());
            preparedStatement.setInt(3, task.getAmountOfTime());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            System.out.println(resultSet);
            if (resultSet.next()) {
                idTask = resultSet.getInt(1);
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            logger.log(Level.WARNING, throwables.getMessage());
        }
        if (idTask == -1) {
            System.out.println("SOMETHING WENT WRONG");
        }
        return idTask;
    }

    public void deleteTask(Task task) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = sqlDatabaseManager.getConnection()) {
            preparedStatement = connection.prepareStatement("delete from task where idTask = ?");
            preparedStatement.setInt(1, task.getIdTask());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void updateTask(Task task) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = sqlDatabaseManager.getConnection()) {
            preparedStatement = connection.prepareStatement("update task SET taskName = ? where idTask = ?");
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setInt(2, task.getIdTask());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public Map<Integer, Task> findAllTask() {
        Map<Integer, Task> map = new ConcurrentHashMap<>();
        ResultSet resultSet = null;
        try (Statement statement = sqlDatabaseManager.getConnection().createStatement()) {
            resultSet = statement.executeQuery("SELECT  * FROM task");
            while (resultSet.next()) {
                int idTask = resultSet.getInt(1);
                String taskName = resultSet.getString(2);
                Time time = resultSet.getTime(3);
                int amountOfTime = resultSet.getInt(4);
                Task task = new Task(idTask, taskName, time, amountOfTime);
                map.put(idTask, task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
