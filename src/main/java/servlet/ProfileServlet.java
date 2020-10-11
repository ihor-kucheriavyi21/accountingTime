package servlet;

import factory.ServiceFactory;
import model.entity.User;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    UserService userService = ServiceFactory.getUserService();
    private final static String profile = "/WEB-INF/view/profile.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(profile).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String repPass = req.getParameter("repPass");
        User user = (User) req.getSession().getAttribute("user");
        if (userService.requestIsValidByPass(pass, repPass)) {
            user.setPass(pass);
        }
        if (userService.requestIsValidByName(login)) {
            user.setName(login);
        }
        userService.updateUser(user);
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}

