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
    TaskService taskService = ServiceFactory.getTaskService();
    private UserService userService = ServiceFactory.getUserService();
    //todo check prg pattern

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
        int categoryId = Integer.parseInt(req.getParameter("getCategory"));
        Category category = taskService.getCategoryById(categoryId);
        Task task = new Task(name, new Time(System.currentTimeMillis()), amountOfTime, category, idUser);

        int idTask = taskService.saveTask(task);

        task.setIdTask(idTask);
        task.setIdTask(idUser);
        user.tasks.add(task);
        resp.sendRedirect(req.getContextPath() + "/main");
    }

    private boolean requestIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("name");
        return name != null && name.length() > 0;
    }
}
