package servlet;

import model.entity.User;
import model.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService();

    private final static String LOGIN = "/WEB-INF/view/login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(LOGIN);
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("userpass");
        boolean isUserValid = userService.checkIfExist(name, password);
        if (isUserValid) {
            User user = userService.getUser(new User(name, password));
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/main");
        } else {
            req.setAttribute("errorMessage", "Invalid Credentials");
            req.getRequestDispatcher(LOGIN).forward(req, resp);
        }
    }
}
