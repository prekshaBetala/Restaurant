package com.tap.controller;

import java.io.IOException;

import com.tap.dao.UserDAO;
import com.tap.daoimpl.UserDAOImpl;
import com.tap.dto.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	 private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        UserDAO dao = new UserDAOImpl();
        dao.addUser(user);

        resp.sendRedirect("login.html");
    }
}

