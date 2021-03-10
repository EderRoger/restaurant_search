package com.eroger.service;

import com.eroger.domain.Restaurant;
import com.eroger.domain.SearchCriteria;
import com.eroger.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class RestaurantService {

    private RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> findByFilters(SearchCriteria searchCriteria) {

        searchCriteriaIsValid(searchCriteria);

        List<Restaurant> restaurantList = new ArrayList<>();
        int paramsCount = 0;

        if (nonNull(searchCriteria.getRestaurantName())) {
            paramsCount++;
            restaurantList = repository.findByName(searchCriteria, restaurantList);
        }
        if (nonNull(searchCriteria.getDistance())) {
            paramsCount++;
            restaurantList = repository.findByDistance(searchCriteria, restaurantList);
        }
        if (nonNull(searchCriteria.getPrice())) {
            paramsCount++;
            restaurantList = repository.findByPrice(searchCriteria, restaurantList);
        }
        if (nonNull(searchCriteria.getRating())) {
            paramsCount++;
            restaurantList = repository.findByRating(searchCriteria, restaurantList);
        }
        if (nonNull(searchCriteria.getCuisine())) {
            paramsCount++;
            restaurantList = repository.findByCuisine(searchCriteria, restaurantList);
        }

        List<Restaurant> result = restaurantList.stream().limit(5).collect(Collectors.toList());

        if (paramsCount > 1 && result.size() > 1) {
            List<Restaurant> sortedList = sortByDistance(restaurantList.stream().limit(5).collect(Collectors.toList()));

            if(checkIfTwoFirstRestaurantHasTheSameDistance(sortedList)
                    && checkIfTwoFirstRestaurantHasTheSameRating(sortedList))
                return sortByPrice(sortedList);

            if(checkIfTwoFirstRestaurantHasTheSameDistance(sortedList))
                return sortByRating(sortedList);

            return sortedList;

        }
        // return all
        return result;
    }

    private boolean checkIfTwoFirstRestaurantHasTheSameDistance(List<Restaurant> restaurantList) {
        if (!restaurantList.isEmpty() && restaurantList.size() >= 2)
            return restaurantList.get(0).getDistance().equals(restaurantList.get(1).getDistance());

        return false;
    }

    private boolean checkIfTwoFirstRestaurantHasTheSameRating(List<Restaurant> restaurantList) {
        if (!restaurantList.isEmpty() && restaurantList.size() >= 2)
            return restaurantList.get(0).getCustomerRating().getRating() == restaurantList.get(1)
                    .getCustomerRating().getRating();

        return false;
    }

    private List<Restaurant> sortByDistance(List<Restaurant> restaurants) {
        return restaurants.stream().sorted(Comparator.comparing(Restaurant::getDistance)).collect(Collectors.toList());
    }

    private List<Restaurant> sortByRating(List<Restaurant> restaurants) {
        return restaurants.stream().limit(2).sorted(Comparator.comparing(Restaurant::getCustomerRating))
                .collect(Collectors.toList());
    }

    private List<Restaurant> sortByPrice(List<Restaurant> restaurants) {
        return restaurants.stream().limit(2).sorted(Comparator.comparing(Restaurant::getPrice))
                .collect(Collectors.toList());
    }

    private void searchCriteriaIsValid(SearchCriteria searchCriteria) {
        if (isNull(searchCriteria)) {
            throw new IllegalArgumentException("Criteria must be informed");
        }
    }
}
