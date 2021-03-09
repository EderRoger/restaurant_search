package com.eroger.service;

import com.eroger.domain.Cuisine;
import com.eroger.domain.Restaurant;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ParseCSVService {
    public List<Restaurant> restaurants;
    public List<Cuisine> cuisines;

    public ParseCSVService() throws FileNotFoundException {
        parseCuisines();
        parseRestaurants();
    }

    private void parseRestaurants() throws FileNotFoundException {
        String fileName = "src/main/resources/files/restaurants.csv";

        restaurants = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(Restaurant.class)
                .build()
                .parse();
    }

    private void parseCuisines() throws FileNotFoundException {
        String fileName = "src/main/resources/files/cuisines.csv";

        cuisines = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(Cuisine.class)
                .build()
                .parse();
    }
}
