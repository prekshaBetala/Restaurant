package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tap.dao.UserDAO;
import com.tap.dto.User;
import com.tap.util.DBUtil;

public class UserDAOImpl implements UserDAO {

    private static final String INSERT =
        "INSERT INTO users(name,email,password) VALUES(?,?,?)";

    private static final String FETCH_BY_EMAIL =
        "SELECT * FROM users WHERE email=?";

    @Override
    public void addUser(User user) {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(FETCH_BY_EMAIL)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

