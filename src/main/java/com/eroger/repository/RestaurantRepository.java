package com.eroger.repository;

import com.eroger.domain.Cuisine;
import com.eroger.domain.Restaurant;
import com.eroger.domain.SearchCriteria;
import com.eroger.service.ParseCSVService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    public List<Restaurant> findByName(final SearchCriteria searchCriteria, final List<Restaurant> currentRestaurantList) {
        if (currentRestaurantList.isEmpty())
            return this.restaurants.stream().filter(r -> r.getName().contains(searchCriteria.getRestaurantName()))
                    .collect(Collectors.toList());

        return currentRestaurantList.stream().filter(r -> r.getName().contains(searchCriteria.getRestaurantName()))
                .collect(Collectors.toList());
    }

    public List<Restaurant> findByDistance(final SearchCriteria searchCriteria, final List<Restaurant> currentRestaurantList) {
        if (currentRestaurantList.isEmpty())
            return this.restaurants.stream().filter(r -> r.getDistance() <= searchCriteria.getDistance())
                    .collect(Collectors.toList());

        return currentRestaurantList.stream().filter(r -> r.getDistance() <= searchCriteria.getDistance())
                .collect(Collectors.toList());
    }

    public List<Restaurant> findByPrice(final SearchCriteria searchCriteria, final List<Restaurant> currentRestaurantList) {
        if (currentRestaurantList.isEmpty())
            return this.restaurants.stream().filter(r -> r.getPrice().compareTo(searchCriteria.getPrice()) <= 0)
                    .collect(Collectors.toList());

        return currentRestaurantList.stream().filter(r -> r.getPrice().compareTo(searchCriteria.getPrice()) <= 0)
                .collect(Collectors.toList());
    }

    public List<Restaurant> findByRating(final SearchCriteria searchCriteria, final List<Restaurant> currentRestaurantList) {
        if (currentRestaurantList.isEmpty())
            return this.restaurants.stream().filter(r -> r.getCustomerRating().getRating() >=
                    searchCriteria.getRating().getRating()).collect(Collectors.toList());

        return currentRestaurantList.stream().filter(r -> r.getCustomerRating().getRating() >=
                searchCriteria.getRating().getRating()).collect(Collectors.toList());
    }

    public List<Restaurant> findByCuisine(final SearchCriteria searchCriteria, final List<Restaurant> currentRestaurantList) {
        if (currentRestaurantList.isEmpty())
            return this.restaurants.stream().filter(r -> findCuisineById(r.getCuisineId()).getName()
                    .contains(searchCriteria.getCuisine())).collect(Collectors.toList());

        return currentRestaurantList.stream().filter(r -> findCuisineById(r.getCuisineId()).getName()
                .contains(searchCriteria.getCuisine())).collect(Collectors.toList());
    }

    public Cuisine findCuisineById(final Integer cuisineId) {
        return cuisines.stream().filter(c -> c.getId().equals(cuisineId)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
