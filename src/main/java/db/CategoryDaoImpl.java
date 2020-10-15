package db;

import model.entity.Category;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private static final Logger LOGGER = Logger.getLogger(CategoryDaoImpl.class);

    SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.getInstance();

    public List<Category> getAll() {
        List<Category> list = null;
        try (Connection connection = sqlDatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT  * FROM category ORDER BY id DESC ");
             ResultSet resultSet = statement.executeQuery()) {
            list = new LinkedList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String categoryName = resultSet.getString(2);
                String categoryNameUrk = resultSet.getString(3);
                Category category = new Category(id, categoryName, categoryNameUrk);
                list.add(category);
            }
        } catch (SQLException throwables) {
            LOGGER.error("Exception get all category ", throwables);
        }
        return list;
    }

    @Override
    public int save(Category category) {
        int idCategory = -1;
        String query = "INSERT INTO category (nameEng, nameRu) values (?,?)";
        try (Connection connection = sqlDatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, category.getNameEng());
            preparedStatement.setString(2, category.getNameRu());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            System.out.println(resultSet);
            if (resultSet.next()) {
                idCategory = resultSet.getInt(1);
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            LOGGER.error("Exception save category ", throwables);
        }
        if (idCategory == -1) {
            LOGGER.error("Did not add a category\n");
        }
        return idCategory;
    }

    @Override
    public void update(Category category) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = sqlDatabaseManager.getConnection()) {
            preparedStatement = connection.prepareStatement("update category SET nameEng = ?, nameRu =? where id = ?");
            preparedStatement.setString(1, category.getNameEng());
            preparedStatement.setString(2, category.getNameRu());
            preparedStatement.setInt(3, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.error("Exception update category ", throwables);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                LOGGER.error("Exception close statement ", throwables);
            }
        }
    }

    @Override
    public void delete(Category category) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = sqlDatabaseManager.getConnection()) {
            preparedStatement = connection.prepareStatement("delete from category where id = ?");
            preparedStatement.setInt(1, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.error("Exception delete category ", throwables);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                LOGGER.error("Exception close statement", throwables);
            }
        }
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
                String nameUkr = resultSet.getString(3);

                category = new Category(idCategory, name, nameUkr);
            }
        } catch (SQLException throwables) {
            LOGGER.error("Exception getCategory by ID ", throwables);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    LOGGER.error("Exception result set close ", throwables);
                }
            }
        }

        return category;
    }
}
