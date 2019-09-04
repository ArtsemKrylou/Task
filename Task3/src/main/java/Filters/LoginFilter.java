package Filters;

import models.User;
import service.AdministratorService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    private AdministratorService administratorService;

    private static final Set<String> EXCLUDED_PATHS = new HashSet<>(Arrays.asList("/login", "/registration"));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null && !EXCLUDED_PATHS.contains(path)) {
            response.sendRedirect("/login");
        } else {

            filterChain.doFilter(request, response);
        }


//        if (!EXCLUDED_PATHS.contains(path)) {
//
//
//            System.out.println(request.getRequestURI());
//            if (user != null) {
//                filterChain.doFilter(servletRequest, servletResponse);
//            } else {
//                response.sendRedirect("/login");
//            }
//        }else {
//            response.sendRedirect(request.getContextPath()+ path);
//        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        administratorService = new AdministratorService();

    }

    @Override
    public void destroy() {

    }
}
