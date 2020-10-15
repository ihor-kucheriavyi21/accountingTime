package servlet.task;

import db.TaskDaoImpl;
import model.entity.Task;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;

public class UpdateTaskServlet extends HttpServlet {
    private final static String index = "/WEB-INF/view/update.jsp";
    TaskDaoImpl taskDao = new TaskDaoImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String nameTask = req.getParameter("name");
        String amountOfTime = req.getParameter("time");

        Task task = taskService.getTaskById(id);
        LOGGER.info("Update task" + task);
        if (nameTask.length() > 2) {
            task.setTaskName(nameTask);
        }
        if (amountOfTime.length() > 0) {
            task.setAmountOfTime(Integer.parseInt(amountOfTime));
        }
        task.setRecordingTime(new Time(System.currentTimeMillis()));
        LOGGER.info("Task after update" + task);
        taskService.updateTask(task);
        resp.sendRedirect(req.getContextPath() + "/main");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int idTask = Integer.parseInt(req.getParameter("id"));

        Task task = taskService.getTaskById(idTask);
        req.setAttribute("task", task);
        req.getRequestDispatcher(UPDATE_JSP).forward(req, resp);
    }
}
