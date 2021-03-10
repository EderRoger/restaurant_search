package com.eroger.service;

import com.eroger.domain.Restaurant;
import com.eroger.repository.RestaurantRepository;
import com.eroger.domain.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class SearchService {
    private int paramsCount = 0;

    public List<Restaurant> find(RestaurantRepository repository, SearchCriteria searchCriteria) {
        List<Restaurant> currentRestaurantList = new ArrayList<>();

        if (nonNull(searchCriteria.getRestaurantName())) {
            paramsCount++;
            currentRestaurantList = repository.findByName(searchCriteria, currentRestaurantList);
        }
        if (nonNull(searchCriteria.getDistance())) {
            paramsCount++;
            currentRestaurantList = repository.findByDistance(searchCriteria, currentRestaurantList);
        }
        if (nonNull(searchCriteria.getPrice())) {
            paramsCount++;
            currentRestaurantList = repository.findByPrice(searchCriteria, currentRestaurantList);
        }
        if (nonNull(searchCriteria.getRating())) {
            paramsCount++;
            currentRestaurantList = repository.findByRating(searchCriteria, currentRestaurantList);
        }
        if (nonNull(searchCriteria.getCuisine())) {
            paramsCount++;
            currentRestaurantList = repository.findByCuisine(searchCriteria, currentRestaurantList);
        }

        return currentRestaurantList;
    }

    public int getParamsCount() {
        return paramsCount;
    }
}
