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

    //todo find themes for bootstrap
    @Override
    public void init() throws ServletException {
        System.out.println("##INITIAL COMM#");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("#####DOGET MAIN");
        User user = (User) req.getSession().getAttribute("user");
        System.out.println("INFORMATION ABOUT USER" + user);
        System.out.println(user.tasks);
        user.tasks = taskDao.getAllForCurrentUser(userService.getUserId(user));
        req.setAttribute("tasks", user.tasks.values());
        req.setAttribute("categories", taskService.getAllCategory());
        System.out.println(user.getIdRole());

        req.getRequestDispatcher(index).forward(req, resp);
    }


}
