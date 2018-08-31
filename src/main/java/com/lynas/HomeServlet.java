package com.lynas;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HomeServlet extends HttpServlet {
    private String message;

    public void init() throws ServletException {





        message = "Hello World";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();
        User user = (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("username", "craig"))
                .uniqueResult();
        System.out.println(user.getUsername());
        sessionFactory.getCurrentSession().close();

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    public void destroy() {
        // do nothing.
    }

}
