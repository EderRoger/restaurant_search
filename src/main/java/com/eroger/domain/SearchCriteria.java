package com.eroger.domain;

import java.math.BigDecimal;

public class SearchCriteria {
    // Customer Rating(1 star ~ 5 stars),
    // Distance(1 mile ~ 10 miles),
    // Price(how much one person will spend on average, $10 ~ $50),
    // Cuisine(Chinese, American, Thai, etc.)

    private String restaurantName;
    private Rating rating;
    private Integer distance;
    private BigDecimal price;
    private String cuisine;

    public SearchCriteria(String restaurantName, Rating rating, Integer distance, BigDecimal price, String cuisine) {
        this.restaurantName = restaurantName;
        this.rating = rating;
        this.distance = distance;
        this.price = price;
        this.cuisine = cuisine;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Rating getRating() {
        return rating;
    }

    public Integer getDistance() {
        return distance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCuisine() {
        return cuisine;
    }
}
