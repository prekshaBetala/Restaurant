package com.tap.controller.admin;

import java.io.IOException;
import java.math.BigDecimal;

import com.tap.dao.RestaurantDAO;
import com.tap.daoimpl.RestaurantDAOImpl;
import com.tap.dto.Restaurant;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/addRestaurant")
public class AddRestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // 🔒 ADMIN SECURITY CHECK
        HttpSession session = req.getSession(false);
        if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
            resp.sendRedirect("../login.html");
            return;
        }

        try {
            Restaurant r = new Restaurant();

            // ✅ admin id from session (BETTER than hardcoding)
            r.setAdminId((int) session.getAttribute("userId"));

            r.setName(req.getParameter("name"));
            r.setAddress(req.getParameter("address"));
            r.setFoodType(req.getParameter("foodType"));

            // ✅ SAFE rating handling
            String ratingStr = req.getParameter("rating");
            r.setRating(
                (ratingStr == null || ratingStr.trim().isEmpty())
                    ? BigDecimal.ZERO
                    : new BigDecimal(ratingStr)
            );

            // ✅ SAFE estimated time
            String timeStr = req.getParameter("estimatedTime");
            r.setEstimatedTime(
                (timeStr == null || timeStr.trim().isEmpty())
                    ? 0
                    : Integer.parseInt(timeStr)
            );

            r.setImageUrl(req.getParameter("imageUrl"));
            r.setAvailable(true);

            RestaurantDAO dao = new RestaurantDAOImpl();
            dao.addRestaurant(r);

            System.out.println("✅ Restaurant added successfully");

            // 🔁 Redirect back to admin dashboard
            resp.sendRedirect("home");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("../error.jsp");
        }
    }
}
