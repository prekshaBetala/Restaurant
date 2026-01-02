package com.tap.controller;

import java.io.IOException;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.daoimpl.MenuDAOImpl;
import com.tap.dto.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 🔹 1. READ restaurantId FROM REQUEST
        String restaurantIdParam = req.getParameter("restaurantId");

        if (restaurantIdParam == null) {
            resp.sendRedirect("home");
            return;
        }

        int restaurantId = Integer.parseInt(restaurantIdParam);

        // 🔹 2. STORE restaurantId IN SESSION (VERY IMPORTANT)
        HttpSession session = req.getSession();
        session.setAttribute("restaurantId", restaurantId);

        // 🔹 3. FETCH MENU FOR THIS RESTAURANT
        MenuDAO menuDAO = new MenuDAOImpl();
        List<Menu> menuList = menuDAO.getMenuByRestaurant(restaurantId);

        // 🔹 4. SEND DATA TO JSP
        req.setAttribute("menuList", menuList);

        // 🔹 5. FORWARD TO menu.jsp
        req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }
}
