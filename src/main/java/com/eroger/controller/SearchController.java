package com.eroger.controller;

import com.eroger.domain.Restaurant;
import com.eroger.domain.SearchCriteria;
import com.eroger.service.RestaurantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    private RestaurantService service;

    public SearchController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping("/find")
    public List<Restaurant> find(@RequestBody SearchCriteria searchCriteria) {
        return service.findBySearchCriteriaFilters(searchCriteria);
    }
}
