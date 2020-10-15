package filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginRequiredFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LoginRequiredFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        LOGGER.info(request.getRequestURI());
        if (request.getSession().getAttribute("user") != null
                || request.getRequestURI().equals("/registration")) {
            filterChain.doFilter(request, servletResponse);
        } else {
            RequestDispatcher rd = servletRequest.getRequestDispatcher("login");
            rd.forward(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
