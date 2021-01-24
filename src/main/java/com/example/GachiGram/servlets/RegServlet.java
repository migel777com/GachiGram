package com.example.GachiGram.servlets;

import com.example.GachiGram.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String repassword = request.getParameter("re-password").trim();

        if(!repassword.equals(password)){

        }else{
            try {
                User.registerUser(username,password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        System.out.println("hello");
        response.setContentType("text/plain");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}

