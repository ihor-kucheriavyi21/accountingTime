package servlet;

import service.UserValidationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserValidationService userValidationService = new UserValidationService();

    private final static String login = "/WEB-INF/view/login.jsp";


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(login);
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("userpass");
        boolean isUserValid = userValidationService.isUserValid(name, password);

        if (isUserValid) {
            req.getSession().setAttribute("name", name);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("errorMessage", "Invalid Credentials");
            req.getRequestDispatcher(login).forward(req, resp);
        }
    }
}
