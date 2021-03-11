package com.eroger.service;

import com.eroger.domain.Restaurant;
import com.eroger.domain.SearchCriteria;
import com.eroger.repository.RestaurantRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class RestaurantService {

    private final RestaurantRepository repository;
    private final SearchService searchService;

    public RestaurantService(final RestaurantRepository repository, final SearchService searchService) {
        this.repository = repository;
        this.searchService = searchService;
    }

    public List<Restaurant> findBySearchCriteriaFilters(final SearchCriteria searchCriteria) {

        checkIfSearchCriteriaIsValid(searchCriteria);

        List<Restaurant> currentRestaurantList = searchService.find(repository, searchCriteria);

        List<Restaurant> resultList = currentRestaurantList.stream().limit(5).collect(Collectors.toList());

        if (searchService.getParamsCount() > 1 && resultList.size() > 1) {
            List<Restaurant> sortedList = sortByDistance(currentRestaurantList.stream().limit(5)
                    .collect(Collectors.toList()));

            if (checkIfTwoFirstRestaurantHasTheSameDistance(sortedList)
                    && checkIfTwoFirstRestaurantHasTheSameRating(sortedList))
                return sortByPrice(sortedList);

            if (checkIfTwoFirstRestaurantHasTheSameDistance(sortedList))
                return sortByRating(sortedList);

            return sortedList;

        }
        // return all
        return resultList;
    }

    private boolean checkIfTwoFirstRestaurantHasTheSameDistance(final List<Restaurant> restaurantList) {
        if (!restaurantList.isEmpty() && restaurantList.size() >= 2)
            return restaurantList.get(0).getDistance().equals(restaurantList.get(1).getDistance());

        return false;
    }

    private boolean checkIfTwoFirstRestaurantHasTheSameRating(final List<Restaurant> restaurantList) {
        if (!restaurantList.isEmpty() && restaurantList.size() >= 2)
            return restaurantList.get(0).getCustomerRating().getRating() == restaurantList.get(1)
                    .getCustomerRating().getRating();

        return false;
    }

    private List<Restaurant> sortByDistance(final List<Restaurant> restaurants) {
        return restaurants.stream().sorted(Comparator.comparing(Restaurant::getDistance)).collect(Collectors.toList());
    }

    private List<Restaurant> sortByRating(final List<Restaurant> restaurants) {
        return restaurants.stream().limit(2).sorted(Comparator.comparing(Restaurant::getCustomerRating).reversed())
                .collect(Collectors.toList());
    }

    private List<Restaurant> sortByPrice(final List<Restaurant> restaurants) {
        return restaurants.stream().limit(2).sorted(Comparator.comparing(Restaurant::getPrice))
                .collect(Collectors.toList());
    }

    private void checkIfSearchCriteriaIsValid(final SearchCriteria searchCriteria) {
        if (isNull(searchCriteria)) {
            throw new IllegalArgumentException("Criteria must be informed");
        } else {
            if (nonNull(searchCriteria.getRating()) && searchCriteria.getRating().getRating() > 5) {
                throw new IllegalArgumentException("Ratings must be between 1 - 5");
            }

            if (nonNull(searchCriteria.getDistance()) &&
                    (searchCriteria.getDistance() < 1 || searchCriteria.getDistance() > 10)) {
                throw new IllegalArgumentException("Distance must be between 1 - 10 miles");
            }
        }
    }
}
