package com.tap.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1️⃣ Get existing session
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // 2️⃣ Read payment method from payment.jsp
        String paymentMethod = req.getParameter("paymentMethod");

        if (paymentMethod == null || paymentMethod.isEmpty()) {
            resp.sendRedirect("payment.jsp");
            return;
        }

        // 3️⃣ Store payment method in session
        session.setAttribute("paymentMethod", paymentMethod);

        // 4️⃣ Redirect to PlaceOrder servlet (NOT success page)
        resp.sendRedirect("placeOrder");
    }
}
