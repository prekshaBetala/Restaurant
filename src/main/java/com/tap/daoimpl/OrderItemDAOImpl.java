package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.tap.dao.OrderItemDAO;
import com.tap.dto.OrderItem;
import com.tap.util.DBUtil;

public class OrderItemDAOImpl implements OrderItemDAO {

    private static final String INSERT_ORDER_ITEM =
        "INSERT INTO order_items (order_id, menu_id, item_name, quantity, total_price) " +
        "VALUES (?, ?, ?, ?, ?)";

    @Override
    public void addOrderItem(OrderItem item) {

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ORDER_ITEM)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getMenuId());   // ✅ IMPORTANT
            ps.setString(3, item.getItemName());
            ps.setInt(4, item.getQuantity());
            ps.setBigDecimal(5, item.getTotalPrice());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Failed to insert order item", e);
        }
    }
}
