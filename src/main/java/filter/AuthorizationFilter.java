package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private final List<String> allowedURIs = Arrays.asList("/", "/Login", "/LoginServlet", "/RegisterServlet", "/Register", "/welcome");

    public void init(FilterConfig config) throws ServletException {
        // Initialization code, if needed
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String requestedURI = request.getRequestURI();


        // Allow css files to be accessed

        if (requestedURI.endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }

        if (requestedURI.endsWith(".ico")) {
            chain.doFilter(request, response);
            return;
        }

        if (requestedURI.contains(".jpg")) {
            chain.doFilter(request, response);
            return;
        }

        if (requestedURI.contains(".png")) {
            chain.doFilter(request, response);
            return;
        }


        System.out.println("Requested URI: " + requestedURI);
        if (allowedURIs.contains(requestedURI)) {
            chain.doFilter(request, response);
            return;
        }

        boolean loggedIn = request.getSession().getAttribute("username") != null;

        if (loggedIn) {
            System.out.println("USER IS LOGGED IN");
            System.out.println(request.getSession().getAttribute("username"));
            // User is authorized or accessing an allowed URI
            chain.doFilter(request, response);
        } else {
            // User is not authorized, redirect them to the login page or show an error page
            System.out.println("MEORE");
            request.getSession().setAttribute("authenticationError", "You must login first!");
            response.sendRedirect("/Login");
        }
    }

    public void destroy() {
        // Cleanup code, if needed
    }
}