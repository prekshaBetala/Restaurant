package com.tap.controller.admin;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/admin/home")
public class AdminHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(false);

        if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
            resp.sendRedirect("../login.html");
            return;
        }

        try {
            req.getRequestDispatcher("/admin/adminHome.jsp")
               .forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
