package com.example.GachiGram.servlets;

import com.example.GachiGram.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        int id = -1;

        try {
            id = User.logInUser(username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (id != -1) {
            session.setAttribute("userId", id);
            session.setMaxInactiveInterval(15*60);
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        }

        request.setAttribute("logMessage", "Incorrect username or password");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
