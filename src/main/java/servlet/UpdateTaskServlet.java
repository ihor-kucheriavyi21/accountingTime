package servlet;

import db.SQLDatabaseManager;
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

/*
    @Override
    public void init() throws ServletException {

        final Object users = getServletContext().getAttribute("tasks");

        if (users == null || !(users instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        }
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String nameTask = req.getParameter("name");
        int amountOfTime = Integer.parseInt(req.getParameter("time"));
        if (User.tasks.containsKey(id)) {
            System.out.println("this key EXIST");
        }
        Task task = User.tasks.get(id);
        task.setTaskName(nameTask);
        task.setAmountOfTime(amountOfTime);
        task.setRecordingTime(new Time(System.currentTimeMillis()));
        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.getInstance();
        sqlDatabaseManager.updateTask(task);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("####DOGET FOR UPDATE TASK");
        int idTask = Integer.parseInt(req.getParameter("id"));
        Task task = User.tasks.get(idTask);
        req.setAttribute("task", task);
        req.getRequestDispatcher(index).forward(req, resp);

    }
}
