package com.tap.dto;

import java.math.BigDecimal;

public class OrderItem {

    private int orderItemId;
    private int orderId;

    private int menuId;          // REQUIRED
    private String itemName;
    private BigDecimal price;    // REQUIRED
    private int quantity;
    private BigDecimal totalPrice;

    public OrderItem() {}

    public int getMenuId() {
        return menuId;
    }
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }
}
