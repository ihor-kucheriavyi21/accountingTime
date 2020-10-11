package db;

import model.entity.Category;

import java.util.List;

public interface CategoryDao extends DAO<Category>{
    @Override
    List<Category> getAll();
    @Override
    int save(Category category);
    @Override
    void update(Category category);
    @Override
    void delete(Category category);

    Category getCategoryById(int idCategory);
}
