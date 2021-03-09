package com.eroger.domain;

import java.util.Arrays;

public enum Rating {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
    ;

    private int rating;

    Rating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}
