package com.eroger.service;

import com.eroger.domain.Rating;
import com.eroger.domain.Restaurant;
import com.eroger.domain.SearchCriteria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class RestaurantServiceTest {

    // CRITERIA:
    //The search is based on five criteria:
    // **Restaurant Name,
    // Customer Rating(1 star ~ 5 stars),
    // Distance(1 mile ~ 10 miles),
    // Price(how much one person will spend on average, $10 ~ $50),
    // Cuisine(Chinese, American, Thai, etc.).** The requirements are listed below.

//        4. “Best match” is defined as below:
//        - A Restaurant Name match is defined as an exact or partial String match with what users provided. For example, “Mcd” would match “Mcdonald’s”.
//        - A Customer Rating match is defined as a Customer Rating equal to or more than what users have asked for. For example, “3” would match all the 3 stars restaurants plus all the 4 stars and 5 stars restaurants.
//        - A Distance match is defined as a Distance equal to or less than what users have asked for. For example, “2” would match any distance that is equal to or less than 2 miles from your company.
//        - A Price match is defined as a Price equal to or less than what users have asked for. For example, “15” would match any price that is equal to or less than $15 per person.
//        - A Cuisine match is defined as an exact or partial String match with what users provided. For example, “Chi” would match “Chinese”. You can find all the possible Cuisines in the **cuisines.csv** file. *You can assume each restaurant offers only one cuisine.*
//        - The five parameters are holding an “AND” relationship. For example, if users provide Name = “Mcdonald’s” and Distance = 2, you should find all “Mcdonald’s” within 2 miles.

    @Test
    public void shouldFindByName() {
        SearchCriteria searchCriteria = new SearchCriteria("Del", null, 0, null, null);
        List<Restaurant> restaurants = new RestaurantService().findByRestaurantName(searchCriteria);
        Assertions.assertEquals(7, restaurants.size());
    }

    @Test
    public void shouldFindByRatings() {
        SearchCriteria searchCriteria = new SearchCriteria(null, Rating.FOUR, 0, null, null);
        List<Restaurant> restaurants = new RestaurantService().findByRestaurantRatings(searchCriteria);
        Assertions.assertEquals(5, restaurants.size());
    }

    @Test
    public void shouldFindByDistance() {
        SearchCriteria searchCriteria = new SearchCriteria(null, null, 3, null, null);
        List<Restaurant> restaurants = new RestaurantService().findByRestaurantDistance(searchCriteria);
        Assertions.assertEquals(1, restaurants.size());
    }

    @Test
    public void shouldFindByPrice() {
        SearchCriteria searchCriteria = new SearchCriteria(null, null, 0, new BigDecimal(30), null);
        List<Restaurant> restaurants = new RestaurantService().findByRestaurantPrice(searchCriteria);
        Assertions.assertEquals(4, restaurants.size());
    }

    @Test
    public void shouldFindByCuisine() {
        SearchCriteria searchCriteria = new SearchCriteria(null, null, 0, null, "Russian");
        List<Restaurant> restaurants = new RestaurantService().findByRestaurantCuisine(searchCriteria);
        Assertions.assertEquals(1, restaurants.size());
    }
}
