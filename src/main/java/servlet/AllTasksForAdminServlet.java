package servlet;

import factory.ServiceFactory;
import model.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllTasksForAdminServlet extends HttpServlet {
    private static final  String ALL_TASK = "/WEB-INF/view/tasks.jsp";
    //todo addFilet to check if Admin try connect to page with allTasks
    //todo create enum to print nameSorting
    private final TaskService taskService = ServiceFactory.getTaskService();
    private Cookie cookie = new Cookie("sortNum", "0");


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
