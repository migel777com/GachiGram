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

@WebServlet("/LeaveCommentServlet")
public class LeaveCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int author_id = (int) session.getAttribute("userId");
        String content = request.getParameter("comment_content").trim();
        int post_id = Integer.parseInt(request.getParameter("comment_post_id"));

        User.saveComment(post_id, author_id, content);

        String target_id = request.getParameter("target_id").trim();
        request.getRequestDispatcher("targetUserPage.jsp?targetId="+target_id).forward(request, response);
    }
}
