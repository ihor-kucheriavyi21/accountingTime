package servlet;

import model.entity.User;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterUserServlet extends HttpServlet {
    private final static String addUser = "/WEB-INF/view/addUser.jsp";
    UserService userService = new UserService();
    //todo Create exception class for validate regitsration
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(addUser).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        String name = req.getParameter("login");
        String password = req.getParameter("pass");
        String repPass = req.getParameter("repPass");

        if (!userService.requestIsValidByFillingFields(name, password)) {
            req.setAttribute("errorMessage", "Please fill in all fields");
            req.getRequestDispatcher(addUser).forward(req, resp);
        } else if (!userService.requestIsValidByPass(password, repPass)) {
            req.setAttribute("errorMessage", "Passwords are not the same");
            req.getRequestDispatcher(addUser).forward(req, resp);
        } else {
            User user = new User(name, password);
            int idUser = userService.saveUser(user);
            user = new User(idUser, name, password, 1);
            req.getSession().setAttribute("user", user);


            user.setId(idUser);
            resp.sendRedirect(req.getContextPath() + "/main");
        }
    }

}
