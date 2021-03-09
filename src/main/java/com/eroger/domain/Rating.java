package com.eroger.domain;

import java.util.Arrays;

public enum Rating {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
    ;

    private int rating;

    Rating(Integer rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public static Rating findById(Integer id){
        for (int i = 0; i < values().length; i++) {
            if(values()[i].getRating() == id){
                return values()[i];
            }
        }
        return null;
    }
}
