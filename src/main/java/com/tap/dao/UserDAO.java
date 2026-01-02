package com.tap.dao;

import com.tap.dto.User;

public interface UserDAO {

    void addUser(User user);

    User getUserByEmail(String email);
}
