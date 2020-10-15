package servlet;

import factory.ServiceFactory;
import model.entity.User;
import model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class LoginServlet extends HttpServlet {
    private final UserService userService = ServiceFactory.getUserService();
    private static final String LOGIN = "/WEB-INF/view/login.jsp";
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("This is a logging statement from log4j");
        RequestDispatcher rd = request.getRequestDispatcher(LOGIN);
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String name = req.getParameter("username");
        String password = req.getParameter("userpass");
        boolean isUserValid = userService.checkIfExist(name, password);
        if (isUserValid) {
            LOGGER.info("Successful login");
            User user = userService.getUser(new User(name, password));
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("language", Locale.getDefault().toString());
            if (user.getIdRole()==1)
            resp.sendRedirect("/main");
            else if (user.getIdRole()==2)
                resp.sendRedirect("/allTask");

        } else {
            req.setAttribute("errorMessage", "Invalid Credentials");
            req.getRequestDispatcher(LOGIN).forward(req, resp);
        }
    }
}
