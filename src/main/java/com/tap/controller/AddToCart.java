package com.tap.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tap.dto.OrderItem;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int menuId = Integer.parseInt(req.getParameter("menuId"));
        String name = req.getParameter("name");                    // ✅ NEW
        BigDecimal price = new BigDecimal(req.getParameter("price")); // ✅ NEW
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        HttpSession session = req.getSession();

        @SuppressWarnings("unchecked")
        Map<Integer, OrderItem> cart =
            (Map<Integer, OrderItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        OrderItem item = cart.get(menuId);

        if (item == null) {
            item = new OrderItem();
            item.setMenuId(menuId);
            item.setItemName(name);          // ✅ REQUIRED
            item.setPrice(price);            // ✅ REQUIRED
            item.setQuantity(quantity);
            item.setTotalPrice(
                price.multiply(new BigDecimal(quantity))
            );
            cart.put(menuId, item);
        } else {
            int newQty = item.getQuantity() + quantity;
            item.setQuantity(newQty);
            item.setTotalPrice(
                item.getPrice().multiply(new BigDecimal(newQty))
            );
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("cart.jsp");
    }
}
