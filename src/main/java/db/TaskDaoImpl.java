package db;

import model.entity.Task;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {
    private static final Logger LOGGER = Logger.getLogger(TaskDaoImpl.class.getName());
    SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.getInstance();
    CategoryDao categoryDao = new CategoryDaoImpl();

    //todo check Query afted adding CATEGORY
    @Override
    public List getAll() {
        List<Task> list = null;
        try (Connection connection = sqlDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT  * FROM task")) {
            list = putInList(statement);
        } catch (SQLException throwables) {
            LOGGER.error("Get all exception", throwables);
        }
        return list;
    }

    //todo exception зробити for Query
    @Override
    public int save(Task task) {
        int idTask = -1;
        String query = "INSERT INTO task (name, recordingTime, amountOfTime,idCategory, idPerson, idStatus) values (?,?,?,?,?,?)";
        task.setIdStatus(1);
        try (Connection connection = sqlDatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setTime(2, task.getRecordingTime());
            preparedStatement.setInt(3, task.getAmountOfTime());
            preparedStatement.setInt(4, task.getCategory().getId());
            preparedStatement.setInt(5, task.getIdUser());
            preparedStatement.setInt(6, task.getIdStatus());

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
            LOGGER.error("Save exception", throwables);
        }
        return idTask;
    }


    @Override
    public void update(Task task) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = sqlDatabaseManager.getConnection()) {
            preparedStatement = connection.prepareStatement("update task SET name = ?, amountOfTime =?, idStatus = ?, idCategory=?, recordingTime=? where id = ?");
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setInt(2, task.getAmountOfTime());
            preparedStatement.setInt(3, task.getIdStatus());
            preparedStatement.setInt(4, task.getCategory().getId());
            preparedStatement.setTime(5, task.getRecordingTime());
            preparedStatement.setInt(6, task.getIdTask());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.error("Update exception", throwables);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                LOGGER.error("Prepared statement exception", throwables);
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
            LOGGER.error("Delete task exception", throwables);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                LOGGER.error("Prepared statement exception", throwables);
            }
        }
    }

    @Override
    public List<Task> getAllForCurrentUser(int idUser) {
        List<Task> list = null;
        try (Connection connection = sqlDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT  * FROM task where idPerson = ? ")) {
            statement.setInt(1, idUser);
            list = putInList(statement);
        } catch (SQLException throwables) {
            LOGGER.error("Get all task for user", throwables);
        }
        return list;
    }

    @Override
    public Task getTaskById(int idTask) {
        Task task = null;
        ResultSet resultSet = null;
        try (Connection connection = sqlDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT  * FROM task where id=?")) {
            statement.setInt(1, idTask);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String taskName = resultSet.getString(2);
                Time time = resultSet.getTime(3);
                int amountOfTime = resultSet.getInt(4);
                int idCategory = resultSet.getInt(5);
                int idPerson = resultSet.getInt(6);
                int idStatus = resultSet.getInt(7);
                task = new Task(idTask, taskName, time, amountOfTime, categoryDao.getCategoryById(idCategory),
                        idPerson, idStatus);
            }
        } catch (SQLException throwables) {
            LOGGER.error("Get task by id exception", throwables);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    LOGGER.error("Result set exception", throwables);
                }
            }
        }

        return task;
    }

    private List<Task> putInList(PreparedStatement statement) {
        ArrayList<Task> list = new ArrayList();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idTask = resultSet.getInt(1);
                String taskName = resultSet.getString(2);
                Time time = resultSet.getTime(3);
                int amountOfTime = resultSet.getInt(4);
                int idCategory = resultSet.getInt(5);
                int idPerson = resultSet.getInt(6);
                int idStatus = resultSet.getInt(7);
                Task task = new Task(idTask, taskName, time, amountOfTime, categoryDao.getCategoryById(idCategory),
                        idPerson, idStatus);
                list.add(task);
            }
        } catch (SQLException throwables) {
            LOGGER.error("Put in list exception", throwables);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    LOGGER.error("Result set exception", throwables);

                }
            }
        }


        return list;

    }
}
