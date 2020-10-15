package servlet.category;

import factory.ServiceFactory;
import model.service.CategoryService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCategory extends HttpServlet {
    private static final CategoryService categoryService = ServiceFactory.getCategoryService();
    private static final Logger LOGGER = Logger.getLogger(DeleteCategory.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("idCategory") != null) {
            int idCategoryForDelete = Integer.parseInt(req.getParameter("idCategory"));
            categoryService.deleteCategory(categoryService.getCategoryById(idCategoryForDelete));
            LOGGER.info("Delete category id = " + idCategoryForDelete);
        }

        resp.sendRedirect(req.getContextPath() + "/categories");

    }
}
