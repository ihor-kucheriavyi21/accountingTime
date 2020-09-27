package servlet;

import db.SQLDatabaseManager;
import model.entity.Task;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;

public class AddTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("#####DOPOST");

        req.setCharacterEncoding("UTF8");
        SQLDatabaseManager databaseManager;
        databaseManager = SQLDatabaseManager.getInstance();

        if (!requestIsValid(req)) {
            System.out.println("request for add task not is  valid");
            resp.sendRedirect(req.getContextPath() + "/");
        }

        final String name = req.getParameter("name");
        final int amountOfTime = Integer.parseInt(req.getParameter("time"));
        Task task = new Task(name, new Time(System.currentTimeMillis()), amountOfTime);
        int idTask = databaseManager.insertTask(task);

        task.setIdTask(idTask);
        User.tasks.put(idTask, task);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private boolean requestIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("name");
        return name != null && name.length() > 0;
    }
}
