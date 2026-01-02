package com.tap.controller;

import java.io.IOException;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.daoimpl.RestaurantDAOImpl;
import com.tap.dto.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 🔐 Session check (login required)
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userEmail") == null) {
            resp.sendRedirect("login.html");
            return;
        }

        // 📦 Fetch restaurants from DB
        RestaurantDAO dao = new RestaurantDAOImpl();
        List<Restaurant> restaurantList = dao.getAllAvailableRestaurants();

        // ✅ IMPORTANT: attribute name matches home.jsp
        req.setAttribute("restaurantList", restaurantList);

        // 📄 Forward to JSP
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}

