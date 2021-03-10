package com.eroger.service;

import com.eroger.domain.Restaurant;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ParseRestaurantCSVService implements ParseService{

    public List<Restaurant> parse() throws FileNotFoundException {
        String fileName = "src/main/resources/files/restaurants.csv";

        return new CsvToBeanBuilder(new FileReader(fileName))
                .withType(Restaurant.class)
                .build()
                .parse();
    }
}
