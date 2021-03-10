package com.eroger.controller;

import com.eroger.domain.Restaurant;
import com.eroger.domain.SearchCriteria;
import com.eroger.service.RestaurantService;

import java.util.List;

public class SearchController {
    private RestaurantService service;

    public SearchController(RestaurantService service) {
        this.service = service;
    }

    public List<Restaurant> find(SearchCriteria searchCriteria){
        return service.findBySearchCriteriaFilters(searchCriteria);
    }
 }
