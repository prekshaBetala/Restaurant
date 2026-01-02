package com.tap.controller;

import java.io.IOException;
import java.util.Map;

import com.tap.dto.OrderItem;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/removeFromCart")
public class RemoveFromCart extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int menuId = Integer.parseInt(req.getParameter("menuId"));

        HttpSession session = req.getSession();

        @SuppressWarnings("unchecked")
        Map<Integer, OrderItem> cart =
            (Map<Integer, OrderItem>) session.getAttribute("cart");

        if (cart != null) {
            cart.remove(menuId);
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("cart.jsp");
    }
}
