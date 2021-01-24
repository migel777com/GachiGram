package com.example.GachiGram.servlets;

import com.example.GachiGram.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AddFriendServlet")
public class AddFriendServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int user_id = Integer. parseInt(session.getAttribute("userId").toString());

        String username = (String) session.getAttribute("username");

        String username_addresser = request.getParameter("username").trim();

        try {
            User.addFriend(user_id, username, username_addresser);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        request.getRequestDispatcher("/userPage.jsp").forward(request,response);
    }
}
