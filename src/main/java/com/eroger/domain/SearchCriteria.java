package com.eroger.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class SearchCriteria {
    private String restaurantName;
    private Integer rating;
    private Integer distance;
    private BigDecimal price;
    private String cuisine;

    public SearchCriteria(String restaurantName, Integer rating, Integer distance, BigDecimal price, String cuisine) {
        this.restaurantName = restaurantName;
        this.rating = rating;
        this.distance = distance;
        this.price = price;
        this.cuisine = cuisine;
    }

//    public List<Restaurant> find(RestaurantRepository repository) {
//        List<Restaurant> currentRestaurantList = new ArrayList<>();
//
//        if (nonNull(getRestaurantName())) {
//            paramsCount++;
//            currentRestaurantList = repository.findByName(this, currentRestaurantList);
//        }
//        if (nonNull(getDistance())) {
//            paramsCount++;
//            currentRestaurantList = repository.findByDistance(this, currentRestaurantList);
//        }
//        if (nonNull(getPrice())) {
//            paramsCount++;
//            currentRestaurantList = repository.findByPrice(this, currentRestaurantList);
//        }
//        if (nonNull(getRating())) {
//            paramsCount++;
//            currentRestaurantList = repository.findByRating(this, currentRestaurantList);
//        }
//        if (nonNull(getCuisine())) {
//            paramsCount++;
//            currentRestaurantList = repository.findByCuisine(this, currentRestaurantList);
//        }
//
//        return currentRestaurantList;
//    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Rating getRating() {
        if(Objects.nonNull(rating))
            return Rating.findById(rating);
        return null;
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
