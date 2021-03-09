package com.eroger.service;

import com.eroger.domain.Rating;
import com.eroger.domain.Restaurant;
import com.eroger.domain.SearchCriteria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class RestaurantServiceTest {

    @Test
    public void shouldFindByName() {
        SearchCriteria searchCriteria = new SearchCriteria("Del", null, null, null, null);
        List<Restaurant> restaurants = new RestaurantService().findByFilters(searchCriteria);
        Assertions.assertEquals(5, restaurants.size());
    }

    @Test
    public void shouldFindByNameSortedByDistance() {
        SearchCriteria searchCriteria = new SearchCriteria("Del", null, null, null, "Japanese");
        List<Restaurant> restaurants = new RestaurantService().findByFilters(searchCriteria);
        Assertions.assertEquals(3, restaurants.get(0).getDistance());
    }

    @Test
    public void shouldFindByRatings() {
        SearchCriteria searchCriteria = new SearchCriteria(null, Rating.FOUR, null, null, null);
        List<Restaurant> restaurants = new RestaurantService().findByFilters(searchCriteria);
        Assertions.assertEquals(5, restaurants.size());
    }

    @Test
    public void shouldFindByDistance() {
        SearchCriteria searchCriteria = new SearchCriteria(null, null, 1, null, null);
        List<Restaurant> restaurants = new RestaurantService().findByFilters(searchCriteria);
        Assertions.assertEquals(5, restaurants.size());
    }

    @Test
    public void shouldFindByPrice() {
        SearchCriteria searchCriteria = new SearchCriteria(null, null, null, new BigDecimal(10), null);
        List<Restaurant> restaurants = new RestaurantService().findByFilters(searchCriteria);
        Assertions.assertEquals(5, restaurants.size());
    }

    @Test
    public void shouldFindByCuisine() {
        SearchCriteria searchCriteria = new SearchCriteria(null, null, null, null, "Russian");
        List<Restaurant> restaurants = new RestaurantService().findByFilters(searchCriteria);
        Assertions.assertEquals(5, restaurants.size());
    }

    @Test
    public void shouldFindByNameAndCuisine() {
        SearchCriteria searchCriteria = new SearchCriteria("Del", null, null, null, "Russian");
        List<Restaurant> restaurants = new RestaurantService().findByFilters(searchCriteria);
        Assertions.assertEquals(3, restaurants.size());
    }

    @Test
    public void shouldFindByRatingDistanceValueAndCuisine() {
        SearchCriteria searchCriteria = new SearchCriteria(null, Rating.THREE, 2, new BigDecimal(20), "Japanese");
        List<Restaurant> restaurants = new RestaurantService().findByFilters(searchCriteria);
        Assertions.assertEquals(1, restaurants.size());
    }
}
