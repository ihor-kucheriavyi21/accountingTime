package servlet;

import factory.ServiceFactory;
import model.entity.User;
import model.service.CategoryService;
import model.service.TaskService;
import model.service.UserService;
import org.apache.log4j.Logger;
import servlet.task.UpdateTaskServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageServlet extends HttpServlet {
    private final static String VIEW_MAIN_JSP = "/WEB-INF/view/main.jsp";
    TaskService taskService = ServiceFactory.getTaskService();
    UserService userService = ServiceFactory.getUserService();
    CategoryService categoryService =ServiceFactory.getCategoryService();
    private static final Logger LOGGER = Logger.getLogger(MainPageServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        LOGGER.info("INFORMATION ABOUT USER" + user);
        user.tasks = taskService.getAllTaskForCurrentUser(userService.getUserId(user));
        req.setAttribute("tasks", user.tasks);
        req.setAttribute("categories", categoryService.getAllCategory());

        req.getRequestDispatcher(VIEW_MAIN_JSP).forward(req, resp);
    }


}
