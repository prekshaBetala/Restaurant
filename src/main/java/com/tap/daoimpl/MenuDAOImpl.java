package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.dto.Menu;
import com.tap.util.DBUtil;

public class MenuDAOImpl implements MenuDAO {

    private static final String INSERT_MENU =
        "INSERT INTO menu (restaurant_id, name, description, price, rating, imageurl) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FETCH_MENU =
        "SELECT * FROM menu WHERE restaurant_id=?";

    // ============ ADD MENU ============
    @Override
    public void addMenu(Menu m) {

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_MENU)) {

            ps.setInt(1, m.getRestaurantId());
            ps.setString(2, m.getName());
            ps.setString(3, m.getDescription());
            ps.setBigDecimal(4, m.getPrice());
            ps.setBigDecimal(5, m.getRating());
            ps.setString(6, m.getImageurl());

            int rows = ps.executeUpdate();
            System.out.println("✅ MENU INSERTED ROWS = " + rows);

        } catch (Exception e) {
            throw new RuntimeException("❌ Menu insert failed", e);
        }
    }

    // ============ FETCH MENU ============
    @Override
    public List<Menu> getMenuByRestaurant(int restaurantId) {

        List<Menu> menuList = new ArrayList<>();

        try (Connection con = DBUtil.getConnection()) {

            if (con == null) {
                System.out.println("❌ Connection is NULL");
                return menuList;
            }

            PreparedStatement ps = con.prepareStatement(FETCH_MENU);
            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Menu menu = new Menu();

                // ✅ FIXED COLUMN NAME
                menu.setMenuId(rs.getInt("menuId"));

                menu.setName(rs.getString("name"));
                menu.setDescription(rs.getString("description"));
                menu.setPrice(rs.getBigDecimal("price"));
                menu.setRating(rs.getBigDecimal("rating"));
                menu.setImageurl(rs.getString("imageurl"));

                menuList.add(menu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return menuList;
    }
}
