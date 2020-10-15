package servlet;

import factory.ServiceFactory;
import model.entity.User;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class RegisterUserServlet extends HttpServlet {
    private static final  String VIEW_ADD_USER_JSP = "/WEB-INF/view/addUser.jsp";
    UserService userService = ServiceFactory.getUserService();
    //todo Create exception class for validate regitsration
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(VIEW_ADD_USER_JSP).forward(req, resp);
    }
    //todo chech jsp login not redirect this page
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        String name = req.getParameter("login");
        String password = req.getParameter("pass");
        String repPass = req.getParameter("repPass");

        if (!userService.requestIsValidByFillingFields(name, password)) {
            req.setAttribute("errorMessageFilling", "Please fill in the fields with more than two characters");
            req.getRequestDispatcher(VIEW_ADD_USER_JSP).forward(req, resp);
        } else if (!userService.requestIsValidByPass(password, repPass)) {
            req.setAttribute("errorMessageSame", "Passwords are not the same");
            req.getRequestDispatcher(VIEW_ADD_USER_JSP).forward(req, resp);
        } else {
            User user = new User(name, password);
            int idUser = userService.saveUser(user);
            user = new User(idUser, name, password, 1);
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("language", Locale.getDefault().toString());

            user.setId(idUser);
            resp.sendRedirect(req.getContextPath() + "/main");
        }
    }

}
