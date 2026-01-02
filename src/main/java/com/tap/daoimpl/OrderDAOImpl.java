package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.tap.dao.OrderDAO;
import com.tap.dto.Order;
import com.tap.util.DBUtil;

public class OrderDAOImpl implements OrderDAO {

    private static final String INSERT_ORDER =
        "INSERT INTO orders " +
        "(user_id, restaurant_id, total_amount, order_date, delivery_address, payment_method, status) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public int placeOrder(Order order) {

        int orderId = 0;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps =
                 con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setBigDecimal(3, order.getTotalAmount());
            ps.setTimestamp(4, order.getOrderDate());
            ps.setString(5, order.getDeliveryAddress());
            ps.setString(6, order.getPaymentMethod());
            ps.setString(7, order.getStatus());

            ps.executeUpdate();

            // ✅ GET GENERATED order_id
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }
}


