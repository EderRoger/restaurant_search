package com.eroger.repository;

import com.eroger.domain.Cuisine;
import com.eroger.domain.Restaurant;
import com.eroger.service.ParseCSVService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RestaurantRepository {
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<Cuisine> cuisines = new ArrayList<>();

    public RestaurantRepository() {

        try {
            ParseCSVService parseCSVService = new ParseCSVService();
            restaurants = parseCSVService.restaurants;
            cuisines = parseCSVService.cuisines;
        } catch (FileNotFoundException e) {
            Logger.getLogger(this.getClass().getName()).warning(e.getMessage());
        }
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public  Cuisine findCuisineById(Integer cuisineId) {
        return cuisines.stream().filter(c -> c.getId().equals(cuisineId)).findFirst().get();
    }
}
