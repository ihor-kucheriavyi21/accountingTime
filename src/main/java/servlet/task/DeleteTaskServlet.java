package servlet.task;

import model.entity.Task;
import model.entity.User;
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
        User user = (User) request.getSession().getAttribute("user");
        int idTask = Integer.parseInt(request.getParameter("id"));
        Task task = user.tasks.get(idTask);
        user.tasks.remove(idTask);
        taskService.deleteTask(task);
        response.sendRedirect(request.getContextPath() + "/main");
    }
}
