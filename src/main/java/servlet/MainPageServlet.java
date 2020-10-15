package servlet;

import db.TaskDaoImpl;
import model.entity.User;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageServlet extends HttpServlet {
    private final static String index = "/WEB-INF/view/index.jsp";
    TaskDaoImpl taskDao = new TaskDaoImpl();
    UserService userService = new UserService();


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
