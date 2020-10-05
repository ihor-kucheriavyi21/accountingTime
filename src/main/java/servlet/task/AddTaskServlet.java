package servlet.task;

import model.entity.Task;
import model.entity.User;
import model.service.TaskService;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;

public class AddTaskServlet extends HttpServlet {
    //todo add new task in MAIN PAGE
    TaskService taskService = new TaskService();
    private final static String addTask = "/WEB-INF/view/addTask.jsp";
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(addTask).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");
        if (!requestIsValid(req)) {
            System.out.println("request for add task not is  valid");
            resp.sendRedirect(req.getContextPath() + "/main");
        }

        User user = (User) req.getSession().getAttribute("user");

        int idUser = userService.getUserId(user);

        final String name = req.getParameter("name");
        final int amountOfTime = Integer.parseInt(req.getParameter("time"));
        Task task = new Task(name, new Time(System.currentTimeMillis()), amountOfTime, idUser);

        int idTask = taskService.saveTask(task);

        task.setIdTask(idTask);
        task.setIdTask(idUser);
        user.tasks.put(idTask, task);
        resp.sendRedirect(req.getContextPath() + "/main");
    }

    private boolean requestIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("name");
        return name != null && name.length() > 0;
    }
}