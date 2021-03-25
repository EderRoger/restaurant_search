package com.eroger.service;

import com.eroger.domain.Cuisine;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class ParseCuisineCSVService implements ParseService<Cuisine>{
    public List<Cuisine> parse() throws FileNotFoundException {
        String fileName = "src/main/resources/files/cuisines.csv";

        return  new CsvToBeanBuilder(new FileReader(fileName))
                .withType(Cuisine.class)
                .build()
                .parse();
    }
}
