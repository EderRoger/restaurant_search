package com.eroger.service;

import com.eroger.domain.Restaurant;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class ParseRestaurantCSVService implements ParseService<Restaurant>{

    public List<Restaurant> parse() throws FileNotFoundException {
        String fileName = "src/main/resources/files/restaurants.csv";

        return new CsvToBeanBuilder(new FileReader(fileName))
                .withType(Restaurant.class)
                .build()
                .parse();
    }
}
