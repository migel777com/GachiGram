package com.example.GachiGram.servlets;

import com.example.GachiGram.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CreatePostServlet")
public class CreatePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int user_id = Integer. parseInt(session.getAttribute("userId").toString());
        String title = request.getParameter("title").trim();
        String content = request.getParameter("content").trim();
        String vis_status = request.getParameter("vis_status").trim();
        int commendable = Integer. parseInt(request.getParameter("commendable").trim());

        try {
            User.createPost(user_id,title,content,vis_status,commendable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("hello");
        response.setContentType("text/plain");
        request.getRequestDispatcher("/userPage.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}