package db;

import model.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAll();

    int save(Category category);

    void update(Category category);

    void delete(Category category);

    Category getCategoryById(int idCategory);
}
