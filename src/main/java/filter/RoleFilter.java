package filter;

import factory.ServiceFactory;
import model.entity.User;
import model.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")

public class RoleFilter implements Filter {
    UserService userService = ServiceFactory.getUserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        User user = (User) req.getSession().getAttribute("user");
        if (userService.checkIfUserRoleIsValid(req.getRequestURI(), user)) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("unknown");
            rd.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
