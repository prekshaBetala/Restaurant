package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.dto.Restaurant;
import com.tap.util.DBUtil;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String INSERT =
        "INSERT INTO restaurants(admin_id,name,address,food_type,rating,estimated_time,image_url,is_available) " +
        "VALUES(?,?,?,?,?,?,?,?)";

    private static final String FETCH_ALL =
        "SELECT * FROM restaurants WHERE is_available=true";

    private static final String FETCH_BY_ID =
        "SELECT * FROM restaurants WHERE restaurant_id=?";

    @Override
    public void addRestaurant(Restaurant r) {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setInt(1, r.getAdminId());
            ps.setString(2, r.getName());
            ps.setString(3, r.getAddress());
            ps.setString(4, r.getFoodType());
            ps.setBigDecimal(5, r.getRating());
            ps.setInt(6, r.getEstimatedTime());
            ps.setString(7, r.getImageUrl());
            ps.setBoolean(8, r.isAvailable());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getAllAvailableRestaurants() {
        List<Restaurant> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(FETCH_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Restaurant r = new Restaurant();
                r.setRestaurantId(rs.getInt("restaurant_id"));
                r.setAdminId(rs.getInt("admin_id"));
                r.setName(rs.getString("name"));
                r.setAddress(rs.getString("address"));
                r.setFoodType(rs.getString("food_type"));
                r.setRating(rs.getBigDecimal("rating"));
                r.setEstimatedTime(rs.getInt("estimated_time"));
                r.setImageUrl(rs.getString("image_url"));
                r.setAvailable(rs.getBoolean("is_available"));
                r.setCreatedAt(rs.getTimestamp("created_at"));

                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        Restaurant r = null;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(FETCH_BY_ID)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                r = new Restaurant();
                r.setRestaurantId(rs.getInt("restaurant_id"));
                r.setName(rs.getString("name"));
                r.setAddress(rs.getString("address"));
                r.setFoodType(rs.getString("food_type"));
                r.setRating(rs.getBigDecimal("rating"));
                r.setEstimatedTime(rs.getInt("estimated_time"));
                r.setImageUrl(rs.getString("image_url"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
