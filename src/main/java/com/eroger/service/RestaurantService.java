package com.eroger.service;

import com.eroger.domain.Restaurant;
import com.eroger.domain.SearchCriteria;
import com.eroger.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class RestaurantService {

    private RestaurantRepository repository = new RestaurantRepository();

    public List<Restaurant> findByFilters(SearchCriteria searchCriteria) {
        List<Restaurant> newList = new ArrayList<>();
        int paramsCount = 0;

        if (nonNull(searchCriteria.getRestaurantName())) {
            paramsCount++;
            if (newList.isEmpty())
                newList = repository.getRestaurants().stream().filter(r -> r.getName().contains(searchCriteria.getRestaurantName())).collect(Collectors.toList());
            else
                newList = newList.stream().filter(r -> r.getName().contains(searchCriteria.getRestaurantName())).collect(Collectors.toList());
        }
        if (nonNull(searchCriteria.getDistance())) {
            paramsCount++;
            if (newList.isEmpty())
                newList = repository.getRestaurants().stream().filter(r -> r.getDistance() <= searchCriteria.getDistance()).collect(Collectors.toList());
            else
                newList = newList.stream().filter(r -> r.getDistance() <= searchCriteria.getDistance()).collect(Collectors.toList());
        }
        if (nonNull(searchCriteria.getPrice())) {
            paramsCount++;
            if (newList.isEmpty())
                newList = repository.getRestaurants().stream().filter(r -> r.getPrice().compareTo(searchCriteria.getPrice()) <= 0).collect(Collectors.toList());
            else
                newList = newList.stream().filter(r -> r.getPrice().compareTo(searchCriteria.getPrice()) <= 0).collect(Collectors.toList());
        }
        if (nonNull(searchCriteria.getRating())) {
            paramsCount++;
            if (newList.isEmpty())
                newList = repository.getRestaurants().stream().filter(r -> r.getCustomerRating().getRating() >= searchCriteria.getRating().getRating()).collect(Collectors.toList());
            else
                newList = newList.stream().filter(r -> r.getCustomerRating().getRating() >= searchCriteria.getRating().getRating()).collect(Collectors.toList());
        }
        if (nonNull(searchCriteria.getCuisine())) {
            paramsCount++;
            if (newList.isEmpty())
                newList = repository.getRestaurants().stream().filter(r -> r.getCuisine().getName().contains(searchCriteria.getCuisine())).collect(Collectors.toList());
            else
                newList = newList.stream().filter(r -> r.getCuisine().getName().contains(searchCriteria.getCuisine())).collect(Collectors.toList());
        }


        List<Restaurant> result = newList.stream().limit(5).collect(Collectors.toList());

        if (paramsCount > 1 && result.size() > 1) {
            return sortByDistance(newList.stream().limit(5).collect(Collectors.toList()));
        }

        // return all
        return result;
    }

//    When multiple matches are found, you should sort them as described below.
//    Sort the restaurants by Distance first.
//    After the above process, if two matches are still equal, then the restaurant with a higher customer rating wins.
//    After the above process, if two matches are still equal, then the restaurant with a lower price wins.
//    After the above process, if two matches are still equal, then you can randomly decide the order.
//            Example: if the input is Customer Rating = 3 and Price = 15. Mcdonald’s is 4 stars
//    with an average spend = \$10, and it is 1 mile away.
//    And KFC is 3 stars with an average spend = \$8, and it is 1 mile away. Then we should consider Mcdonald’s as a better match than KFC.
//        (They both matches the search criteria -> we compare distance -> we get a tie -> we then compare customer rating -> Mcdonald’s wins)

    private List<Restaurant> sortByDistance(List<Restaurant> restaurants) {
        return restaurants.stream().sorted(Comparator.comparing(Restaurant::getDistance)).collect(Collectors.toList());
    }
}
