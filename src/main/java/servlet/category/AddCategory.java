package servlet.category;

import factory.ServiceFactory;
import model.entity.Category;
import model.service.CategoryService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCategory extends HttpServlet {
    private static final CategoryService categoryService = ServiceFactory.getCategoryService();
    private static final Logger LOGGER = Logger.getLogger(AddCategory.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameEng = req.getParameter("nameEng");
        String nameRu = req.getParameter("nameRu");
        if (categoryService.requestForAddCategoryIsValid(nameEng, nameRu)) {
            LOGGER.info("Add category" + "name in English" + nameEng);
            Category category = new Category(nameEng, nameRu);
            categoryService.saveCategory(category);
        }
        resp.sendRedirect("/categories");
    }
}
