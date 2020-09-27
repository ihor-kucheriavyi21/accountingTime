package servlet;

import db.SQLDatabaseManager;
import model.entity.Task;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTaskServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        int idTask = Integer.parseInt(request.getParameter("id"));
        Task task = User.tasks.get(idTask);
        User.tasks.remove(idTask);
        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.getInstance();
        sqlDatabaseManager.deleteTask(task);

        response.sendRedirect(request.getContextPath() + "/");
    }
}
