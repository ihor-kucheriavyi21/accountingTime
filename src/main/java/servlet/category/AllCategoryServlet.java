package servlet.category;

import factory.ServiceFactory;
import model.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllCategoryServlet extends HttpServlet {
    private static final CategoryService categoryService = ServiceFactory.getCategoryService();
    private static final String CATEGORY_JSP = "/WEB-INF/view/categories.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryService.getAllCategory());
        req.getRequestDispatcher(CATEGORY_JSP).forward(req, resp);
    }
}
