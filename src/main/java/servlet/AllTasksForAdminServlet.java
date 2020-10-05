package servlet;

import db.DAO;
import db.TaskDaoImpl;
import model.entity.Task;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllTasksForAdminServlet extends HttpServlet {
    private final static String allTask = "/WEB-INF/view/tasks.jsp";
//todo addFilet to check if Admin try connect to page with allTasks

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAO<Task> dao = new TaskDaoImpl();
        User user = (User) req.getSession().getAttribute("user");
        System.out.println("DOGET ALLL TASKS FOR ADMIN SSERVLET");

        System.out.println(user.getIdRole());


        req.setAttribute("tasks", dao.getAll().values());
        req.getRequestDispatcher(allTask).forward(req, resp);
    }
}
