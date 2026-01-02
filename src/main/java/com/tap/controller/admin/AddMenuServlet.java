package com.tap.controller.admin;

import java.io.IOException;
import java.math.BigDecimal;

import com.tap.dao.MenuDAO;
import com.tap.daoimpl.MenuDAOImpl;
import com.tap.dto.Menu;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/addMenu")
public class AddMenuServlet extends HttpServlet {

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
            System.out.println("ADMIN AddMenuServlet HIT");

            int restaurantId =
                Integer.parseInt(req.getParameter("restaurantId"));

            Menu menu = new Menu();
            menu.setRestaurantId(restaurantId);
            menu.setName(req.getParameter("name"));
            menu.setDescription(req.getParameter("description"));
            menu.setPrice(new BigDecimal(req.getParameter("price")));

            String rating = req.getParameter("rating");
            menu.setRating(
                (rating == null || rating.trim().isEmpty())
                    ? BigDecimal.ZERO
                    : new BigDecimal(rating)
            );

            menu.setImageurl(req.getParameter("imageurl"));

            MenuDAO dao = new MenuDAOImpl();
            dao.addMenu(menu);

            System.out.println("✅ Menu added successfully");

            // 🔁 Redirect back to menu list
            resp.sendRedirect("../menu?restaurantId=" + restaurantId);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("../error.jsp");
        }
    }
}
