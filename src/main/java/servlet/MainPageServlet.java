package servlet;

import db.SQLDatabaseManager;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageServlet extends HttpServlet {
    private final static String index = "/WEB-INF/view/index.jsp";


    @Override
    public void init() throws ServletException {
        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.getInstance();
        User.tasks = sqlDatabaseManager.findAllTask();
        System.out.println("##INITIAL COMM#");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("#####DOGET");
        String n = (String) req.getSession().getAttribute("name");
        req.setAttribute("userName", n);
        req.setAttribute("tasks", User.tasks.values());
        req.getRequestDispatcher(index).forward(req, resp);
    }


}
