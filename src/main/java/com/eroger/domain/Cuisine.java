package com.eroger.domain;

import com.opencsv.bean.CsvBindByPosition;

public class Cuisine {

    @CsvBindByPosition(position = 0)
    private Integer id;
    @CsvBindByPosition(position = 1)
    private String name;

    public Cuisine() {
    }

    public Cuisine(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
