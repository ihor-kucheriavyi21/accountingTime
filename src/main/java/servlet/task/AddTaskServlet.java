package servlet.task;

import model.entity.Task;
import model.entity.User;
import model.service.CategoryService;
import model.service.TaskService;
import model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;

public class AddTaskServlet extends HttpServlet {
    private static final TaskService taskService = ServiceFactory.getTaskService();
    private static final UserService userService = ServiceFactory.getUserService();
    private static final CategoryService categoryService = ServiceFactory.getCategoryService();
    private static final Logger LOGGER = Logger.getLogger(AddTaskServlet.class);

    //todo check prg pattern
    //todo add verify field for more than 3 symbols
    //todo exception
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF8");
        String name = req.getParameter("name");
        String amountOfTime = (req.getParameter("time"));
        String categoryId = (req.getParameter("getCategory"));
        if (!taskService.requestForAddTaskIsValid(name, amountOfTime, categoryId)) {
            LOGGER.error("request for add task not is  valid");
        } else {
            User user = (User) req.getSession().getAttribute("user");

            int idUser = userService.getUserId(user);

            Category category = categoryService.getCategoryById(Integer.parseInt(categoryId));
            Task task = new Task(name, new Time(System.currentTimeMillis()), Integer.parseInt(amountOfTime), category, idUser);

            int idTask = taskService.saveTask(task);

            task.setIdTask(idTask);
            task.setIdTask(idUser);
            user.tasks.add(task);
        }
        resp.sendRedirect(req.getContextPath() + "/main");

    }


}
