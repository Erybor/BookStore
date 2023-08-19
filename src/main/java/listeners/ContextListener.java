package listeners;

import dao.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Connection conn = null;
        conn = ConnectionManager.getConnection();
        System.out.println("KONTEQST LISENERAAAAAA");

        UserDAO userDAO = new UserDAO(conn);
        BookDAO bookDAO = new BookDAO(conn);
        AuthorDAO authorDAO = new AuthorDAO(conn);
        ReviewDAO reviewDAO = new ReviewDAO(conn);
        FollowerDAO followerDAO = new FollowerDAO(conn);
        UserPostDAO userPostDAO = new UserPostDAO(conn);

        System.out.println("USER DAOO:" + userDAO);
        ServletContext sc = servletContextEvent.getServletContext();
        sc.setAttribute("userDAO", userDAO);
        sc.setAttribute("bookDAO", bookDAO);
        sc.setAttribute("authorDAO", authorDAO);
        sc.setAttribute("reviewDAO", reviewDAO);
        sc.setAttribute("followerDAO", followerDAO);
        sc.setAttribute("postDAO", userPostDAO);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // TODO : close connection
    }
}
