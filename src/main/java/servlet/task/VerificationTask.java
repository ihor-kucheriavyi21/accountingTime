package servlet.task;

import factory.ServiceFactory;
import model.entity.Task;
import model.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerificationTask extends HttpServlet {
    private static final TaskService taskService = ServiceFactory.getTaskService();

    //todo check if normalno in jsp show id status
    //todo font bold
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        int idStatus = Integer.parseInt(req.getParameter("idStatus"));
        Task task = taskService.getTaskById(id);
        task.setIdStatus(idStatus);
        taskService.updateTask(task);
        resp.sendRedirect(req.getContextPath() + "/allTask");
    }
}
