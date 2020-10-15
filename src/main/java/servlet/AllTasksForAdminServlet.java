package servlet;

import db.DAO;
import db.TaskDaoImpl;
import model.entity.Task;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllTasksForAdminServlet extends HttpServlet {
    private final static String allTask = "/WEB-INF/view/tasks.jsp";
//todo addFilet to check if Admin try connect to page with allTasks

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("sortNumber") != null) {
            cookie = new Cookie("sortNum", req.getParameter("sortNumber"));
            resp.addCookie(cookie);
        }
        int numSort = Integer.parseInt(cookie.getValue());
        req.setAttribute("tasks", taskService.getAllTaskSorted(numSort, String.valueOf(req.getSession().getAttribute("language"))));
        req.setAttribute("nameSort", numSort);

        req.getRequestDispatcher(ALL_TASK).forward(req, resp);
    }

}
