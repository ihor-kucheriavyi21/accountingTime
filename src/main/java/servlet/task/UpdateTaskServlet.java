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

        User user = (User) req.getSession().getAttribute("user");

        if (user.tasks.containsKey(id)) {
            System.out.println("this key EXIST");
        }
        Task task = user.tasks.get(id);
        task.setTaskName(nameTask);
        task.setAmountOfTime(Integer.parseInt(amountOfTime));
        task.setRecordingTime(new Time(System.currentTimeMillis()));
        taskDao.update(task);
        resp.sendRedirect(req.getContextPath() + "/main");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("####DOGET FOR UPDATE TASK");
        int idTask = Integer.parseInt(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");

        Task task = user.tasks.get(idTask);
        req.setAttribute("task", task);
        req.getRequestDispatcher(index).forward(req, resp);
    }
}
