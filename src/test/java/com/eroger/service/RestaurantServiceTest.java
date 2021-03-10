package com.eroger.service;

import com.eroger.domain.Cuisine;
import com.eroger.domain.Rating;
import com.eroger.domain.Restaurant;
import com.eroger.domain.SearchCriteria;
import com.eroger.repository.RestaurantRepository;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestaurantServiceTest {

    RestaurantService service;

    @BeforeEach
    public void setUp() {
        service = new RestaurantService(new RestaurantRepository());
    }

    @Test
    public void shouldFindByName() {
        SearchCriteria searchCriteria = new SearchCriteria("Del", null, null, null, null);
        List<Restaurant> restaurants = service.findByFilters(searchCriteria);
        Assertions.assertEquals(5, restaurants.size());
    }

    @Test
    public void shouldFindByNameSortedByDistance() {
        SearchCriteria searchCriteria = new SearchCriteria("Del", null, null, null, "Japanese");
        List<Restaurant> restaurants = service.findByFilters(searchCriteria);
        Assertions.assertEquals(3, restaurants.get(0).getDistance());
    }

    @Test
    public void shouldFindByRatings() {
        SearchCriteria searchCriteria = new SearchCriteria(null, Rating.FOUR, null, null, null);
        List<Restaurant> restaurants = service.findByFilters(searchCriteria);
        Assertions.assertEquals(5, restaurants.size());
    }

    @Test
    public void shouldFindByDistance() {
        SearchCriteria searchCriteria = new SearchCriteria(null, null, 1, null, null);
        List<Restaurant> restaurants = service.findByFilters(searchCriteria);
        Assertions.assertEquals(5, restaurants.size());
    }

    @Test
    public void shouldFindByPrice() {
        SearchCriteria searchCriteria = new SearchCriteria(null, null, null, new BigDecimal(10), null);
        List<Restaurant> restaurants = service.findByFilters(searchCriteria);
        Assertions.assertEquals(5, restaurants.size());
    }

    @Test
    public void shouldFindByCuisine() {
        SearchCriteria searchCriteria = new SearchCriteria(null, null, null, null, "Russian");
        List<Restaurant> restaurants = service.findByFilters(searchCriteria);
        Assertions.assertEquals(5, restaurants.size());
    }

    @Test
    public void shouldFindByNameAndCuisine() {
        SearchCriteria searchCriteria = new SearchCriteria("Del", null, null, null, "Russian");
        List<Restaurant> restaurants = service.findByFilters(searchCriteria);
        Assertions.assertEquals(2, restaurants.size());
    }

    @Test
    public void shouldFindByRatingDistanceValueAndCuisine() {
        SearchCriteria searchCriteria = new SearchCriteria(null, Rating.THREE, 2, new BigDecimal(20), "Japanese");
        List<Restaurant> restaurants = service.findByFilters(searchCriteria);
        Assertions.assertEquals(1, restaurants.size());
    }

    @Test
    public void shouldThrowExceptionWhenCriteriaIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.findByFilters(null);
        });
    }

    @Test
    public void shouldOrderByRatings(){
        RestaurantRepository repositoryMock = mock(RestaurantRepository.class);
        SearchCriteria searchCriteria = new SearchCriteria("Tes", null, 2, null, null);

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Test1",Rating.FOUR.getRating(),2,new BigDecimal(20),1));
        restaurants.add(new Restaurant("Test2",Rating.THREE.getRating(),4,new BigDecimal(30),1));
        restaurants.add(new Restaurant("Test3",Rating.TWO.getRating(),2,new BigDecimal(20),1));
        restaurants.add(new Restaurant("Test4",Rating.ONE.getRating(),3,new BigDecimal(10),1));

        when(repositoryMock.findByDistance(searchCriteria, new ArrayList<>())).thenReturn(restaurants);
        when(repositoryMock.findByName(searchCriteria, restaurants)).thenReturn(restaurants);
        when(repositoryMock.findCuisineById(1)).thenReturn(new Cuisine(1, "Thai"));

        service = new RestaurantService(repositoryMock);

        Assertions.assertEquals("Test3", service.findByFilters(searchCriteria).get(0).getName());
    }

    @Test
    public void shouldOrderByPrice(){
        RestaurantRepository repositoryMock = mock(RestaurantRepository.class);
        SearchCriteria searchCriteria = new SearchCriteria("Tes", null, 2, null, null);

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Test1",Rating.FOUR.getRating(),5,new BigDecimal(20),1));
        restaurants.add(new Restaurant("Test2",Rating.TWO.getRating(),3,new BigDecimal(30),1));
        restaurants.add(new Restaurant("Test3",Rating.TWO.getRating(),3,new BigDecimal(20),1));
        restaurants.add(new Restaurant("Test4",Rating.ONE.getRating(),4,new BigDecimal(10),1));

        when(repositoryMock.findByDistance(searchCriteria, new ArrayList<>())).thenReturn(restaurants);
        when(repositoryMock.findByName(searchCriteria, restaurants)).thenReturn(restaurants);
        when(repositoryMock.findCuisineById(1)).thenReturn(new Cuisine(1, "Thai"));

        service = new RestaurantService(repositoryMock);

        Assertions.assertEquals("Test3", service.findByFilters(searchCriteria).get(0).getName());
    }
}
