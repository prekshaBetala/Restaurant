package com.tap.dao;

import java.util.List;
import com.tap.dto.Menu;

public interface MenuDAO {

    // Add new menu item
    void addMenu(Menu menu);

    // Fetch menu by restaurant
    List<Menu> getMenuByRestaurant(int restaurantId);
}
