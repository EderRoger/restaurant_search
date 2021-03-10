package com.eroger.controller;

import com.eroger.domain.Restaurant;
import com.eroger.domain.SearchCriteria;
import com.eroger.repository.RestaurantRepository;
import com.eroger.service.ParseCuisineCSVService;
import com.eroger.service.ParseRestaurantCSVService;
import com.eroger.service.RestaurantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SearchControllerTest {

    @Test
    public void shouldReturnCorrectResults(){
        SearchController controller  = new SearchController(new RestaurantService(new RestaurantRepository(new ParseRestaurantCSVService(), new ParseCuisineCSVService())));

        SearchCriteria searchCriteria = new SearchCriteria("Del", null, null, null, null);
        List<Restaurant> restaurants = controller.find(searchCriteria);

        Assertions.assertEquals(5, restaurants.size());
    }
}
