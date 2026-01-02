package com.tap.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import com.tap.dto.OrderItem;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateCartQty")
public class UpdateCartQty extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int menuId = Integer.parseInt(req.getParameter("menuId"));
        String action = req.getParameter("action");

        if (action == null) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        @SuppressWarnings("unchecked")
        Map<Integer, OrderItem> cart =
            (Map<Integer, OrderItem>) session.getAttribute("cart");

        if (cart == null || !cart.containsKey(menuId)) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        OrderItem item = cart.get(menuId);

        if ("increase".equals(action)) {
            int qty = item.getQuantity() + 1;
            item.setQuantity(qty);
            item.setTotalPrice(
                item.getPrice().multiply(BigDecimal.valueOf(qty))
            );

        } else if ("decrease".equals(action)) {
            int qty = item.getQuantity() - 1;

            if (qty <= 0) {
                cart.remove(menuId);
            } else {
                item.setQuantity(qty);
                item.setTotalPrice(
                    item.getPrice().multiply(BigDecimal.valueOf(qty))
                );
            }
        }

        if (cart.isEmpty()) {
            session.removeAttribute("cart");
        } else {
            session.setAttribute("cart", cart);
        }

        resp.sendRedirect("cart.jsp");
    }
}
