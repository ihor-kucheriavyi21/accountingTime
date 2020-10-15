package model.service;

import db.CategoryDao;
import model.entity.Category;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void deleteCategory(Category category) {
        categoryDao.delete(category);
    }

    public int saveCategory(Category category) {
        return categoryDao.save(category);
    }

    public Category getCategoryById(int idCategory) {
        return categoryDao.getCategoryById(idCategory);
    }

    public List<Category> getAllCategory() {
        return categoryDao.getAll();
    }

    public boolean requestForAddCategoryIsValid(String nameEng, String nameRu) {
        return nameEng != null && nameEng.length() > 2 &&
                nameRu != null && nameRu.length() > 2;
    }
}
