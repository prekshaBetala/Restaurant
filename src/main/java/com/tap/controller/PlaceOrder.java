package com.tap.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

import com.tap.dao.OrderDAO;
import com.tap.dao.OrderItemDAO;
import com.tap.daoimpl.OrderDAOImpl;
import com.tap.daoimpl.OrderItemDAOImpl;
import com.tap.dto.Order;
import com.tap.dto.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/placeOrder")
public class PlaceOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId");
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");

        if (userId == null || restaurantId == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        @SuppressWarnings("unchecked")
        Map<Integer, OrderItem> cart =
            (Map<Integer, OrderItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        String paymentMethod = req.getParameter("paymentMode");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");


        if (paymentMethod == null || address == null || address.isEmpty()) {
            resp.sendRedirect("checkout.jsp");
            return;
        }

        // ✅ USE AMOUNT FROM JSP
        BigDecimal grandTotal =
            new BigDecimal(req.getParameter("amount"));

        // CREATE ORDER
        Order order = new Order();
        order.setUserId(userId);
        order.setRestaurantId(restaurantId);
        order.setTotalAmount(grandTotal);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setDeliveryAddress(address);
        order.setPaymentMethod(paymentMethod);
        order.setStatus("PLACED");

        OrderDAO orderDAO = new OrderDAOImpl();
        int orderId = orderDAO.placeOrder(order);

        // SAVE ORDER ITEMS
        OrderItemDAO itemDAO = new OrderItemDAOImpl();
        for (OrderItem cartItem : cart.values()) {
            OrderItem dbItem = new OrderItem();
            dbItem.setOrderId(orderId);
            dbItem.setMenuId(cartItem.getMenuId()); // ✅ IMPORTANT
            dbItem.setItemName(cartItem.getItemName());
            dbItem.setQuantity(cartItem.getQuantity());
            dbItem.setTotalPrice(cartItem.getTotalPrice());
            itemDAO.addOrderItem(dbItem);
        }

        session.removeAttribute("cart");
        session.setAttribute("paymentMethod", paymentMethod);

        resp.sendRedirect("order_success.jsp?orderId=" + orderId);
    }
}
