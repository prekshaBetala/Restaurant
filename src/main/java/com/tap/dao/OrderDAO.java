package com.tap.dao;

import com.tap.dto.Order;

public interface OrderDAO {
    int placeOrder(Order order);
}
