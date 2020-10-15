package servlet.task;

import model.entity.Task;
import model.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTaskServlet extends HttpServlet {
    TaskService taskService = new TaskService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        int idTask = Integer.parseInt(request.getParameter("id"));

        Task task = taskService.getTaskById(idTask);
        taskService.deleteTask(task);
        response.sendRedirect(request.getContextPath() +"main");
    }
}
