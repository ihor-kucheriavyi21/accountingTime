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
    private static final  String PROFILE = "/WEB-INF/view/profile.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PROFILE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean somethingChange = false;
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String repPass = req.getParameter("repPass");
        User user = (User) req.getSession().getAttribute("user");
        if (userService.requestIsValidByPass(pass, repPass)) {
            user.setPass(pass);
            somethingChange = true;
        } else if (pass.length() > 0) {
            req.setAttribute("errorMessage", "Password less than 3 characters or not the same");
        }
        if (userService.requestIsValidByName(login)) {
            user.setName(login);
            somethingChange = true;
        } else if (login.length() > 0) {
            req.setAttribute("errorMessageName", "Fill name field more than 2 characters");
        }
        userService.updateUser(user);
        if (somethingChange) {
            resp.sendRedirect(req.getContextPath() + "/profile");
        } else {
            req.getRequestDispatcher(PROFILE).forward(req, resp);
        }
    }
}

