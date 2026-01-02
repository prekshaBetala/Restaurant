package com.tap.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tap.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/validate")
public class Validate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String sql = "SELECT id, role FROM users WHERE email=? AND password=?";

        try (Connection con = DBUtil.getConnection()) {

            if (con == null) {
                resp.sendRedirect("login.html?error=db");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, email);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {

                        int userId = rs.getInt("id");
                        String role = rs.getString("role");

                        HttpSession session = req.getSession(true);
                        session.setAttribute("userId", userId);
                        session.setAttribute("userEmail", email);
                        session.setAttribute("role", role);

                        System.out.println("LOGIN SUCCESS | role=" + role);

                        // 🔥 ROLE BASED REDIRECT
                        if ("ADMIN".equals(role)) {
                            resp.sendRedirect("admin/adminHome.jsp");
                        } else {
                            resp.sendRedirect("home");
                        }

                    } else {
                        resp.sendRedirect("login.html?error=invalid");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("login.html?error=exception");
        }
    }
}
