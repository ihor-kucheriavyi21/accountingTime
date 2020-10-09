package db;

import model.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.getInstance();

    public List<Category> getAll() {
        List<Category> list = null;
        try (Connection connection = sqlDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT  * FROM category")) {
            list = new LinkedList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String categoryName = resultSet.getString(2);
                Category category = new Category(id, categoryName);
                list.add(category);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public int save(Category category) {
        return 0;
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(Category category) {
    }

    @Override
    public Category getCategoryById(int idCategory) {
        Category category = null;
        ResultSet resultSet = null;
        try (Connection connection = sqlDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT  * FROM category where id=?")) {
            statement.setInt(1, idCategory);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(2);
                category = new Category(idCategory, name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return category;
    }
}
