package db;

import model.entity.Task;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskDaoImpl implements TaskDao {
    private static final Logger logger = Logger.getLogger(TaskDaoImpl.class.getName());
    SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.getInstance();

    @Override
    public Map getAll() {
        Map<Integer, Task> map = null;
        try (PreparedStatement statement = sqlDatabaseManager.getConnection().prepareStatement("SELECT  * FROM task")) {
            map = putInMap(statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return map;
    }

    //todo exception зробити for idTask
    @Override
    public int save(Task task) {
        int idTask = -1;
        String query = "INSERT INTO task (name, recordingTime, amountOfTime, idPerson, idStatus) values (?,?,?,?,?)";
        task.setIdStatus(1);
        try (PreparedStatement preparedStatement = sqlDatabaseManager.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setTime(2, task.getRecordingTime());
            preparedStatement.setInt(3, task.getAmountOfTime());
            preparedStatement.setInt(4, task.getIdUser());
            preparedStatement.setInt(5, task.getIdStatus());

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
            new RuntimeException(throwables);
            logger.log(Level.WARNING, throwables.getMessage());
        }
        if (idTask == -1) {
            System.out.println("SOMETHING WENT WRONG");
        }
        return idTask;
    }


    @Override
    public void update(Task task) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = sqlDatabaseManager.getConnection()) {
            preparedStatement = connection.prepareStatement("update task SET name = ? where id = ?");
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

    @Override
    public void delete(Task task) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = sqlDatabaseManager.getConnection()) {
            preparedStatement = connection.prepareStatement("delete from task where id = ?");
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

    @Override
    public Map<Integer, Task> getAllForCurrentUser(int idUser) {
        Map<Integer, Task> map = null;
        try (PreparedStatement statement = sqlDatabaseManager.getConnection().prepareStatement("SELECT  * FROM task where idPerson = ? ")) {
            statement.setInt(1, idUser);
            map = putInMap(statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return map;
    }

    private Map<Integer, Task> putInMap(PreparedStatement statement) throws SQLException {
        Map<Integer, Task> map = new ConcurrentHashMap<>();
        ResultSet resultSet;
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int idTask = resultSet.getInt(1);
            String taskName = resultSet.getString(2);
            Time time = resultSet.getTime(3);
            int amountOfTime = resultSet.getInt(4);
            int idStatus= resultSet.getInt(6);
            Task task = new Task(idTask, taskName, time, amountOfTime,idStatus);
            map.put(idTask, task);
        }
        resultSet.close();
        return map;

    }
}
