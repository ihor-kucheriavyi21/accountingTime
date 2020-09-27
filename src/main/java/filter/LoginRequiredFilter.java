package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginRequiredFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getSession().getAttribute("name") != null) {
            filterChain.doFilter(request, servletResponse);
        } else {
            RequestDispatcher rd = servletRequest.getRequestDispatcher("login");
            rd.forward(servletRequest, servletResponse);
        }
        System.out.println("#####FILTER" + request.getRequestURI());
    }

    @Override
    public void destroy() {

    }
}
