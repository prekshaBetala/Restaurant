package com.tap.dto;

import java.math.BigDecimal;

public class Menu {

    private int menuId;
    private int restaurantId;
    private String name;
    private String description;   // ✅ added
    private BigDecimal price;
    private BigDecimal rating;     // ✅ added
    private String imageurl;       // ✅ added

    // ===== Getters & Setters =====

    public int getMenuId() {
        return menuId;
    }
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // ✅ description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // ✅ price
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // ✅ rating
    public BigDecimal getRating() {
        return rating;
    }
    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    // ✅ image url
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
