package com.eroger.domain;

import com.opencsv.bean.CsvBindByPosition;

import java.math.BigDecimal;

public class Restaurant {

    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByPosition(position = 1)
    private Integer customerRating;
    @CsvBindByPosition(position = 2)
    private Integer distance;
    @CsvBindByPosition(position = 3)
    private BigDecimal price;
    @CsvBindByPosition(position = 4)
    private Integer cuisineId;

    public String getName() {
        return name;
    }

    public Rating getCustomerRating() {
        return Rating.findById(customerRating);
    }

    public Integer getDistance() {
        return distance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getCuisineId() {
        return cuisineId;
    }
}
