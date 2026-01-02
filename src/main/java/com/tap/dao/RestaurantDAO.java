package com.tap.dao;

import java.util.List;
import com.tap.dto.Restaurant;

public interface RestaurantDAO {

    void addRestaurant(Restaurant restaurant);

    List<Restaurant> getAllAvailableRestaurants();

    Restaurant getRestaurantById(int restaurantId);
}
