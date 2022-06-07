import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        //arrange
        LocalTime openTime = LocalTime.of(8, 0, 0);
        LocalTime closeTime = LocalTime.of(17, 0, 0);

        //act
        service.addRestaurant("ABC", "NewYork", openTime, closeTime);
        service.addRestaurant("IJK", "Washington", openTime, closeTime);
        service.addRestaurant("PQR", "California", openTime, closeTime);
        service.addRestaurant("XYZ", "Texas", openTime, closeTime);

        //assert
        assertEquals(service.addRestaurant("ABC", "NewYork", openTime, closeTime), service.findRestaurantByName("ABC"));
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        LocalTime openTime = LocalTime.of(8, 0, 0);
        LocalTime closeTime = LocalTime.of(17, 0, 0);

        //act
        Restaurant r1 = new Restaurant("ABC", "NewYork", openTime, closeTime);
        Restaurant r2 = new Restaurant("IJK", "Washington", openTime, closeTime);
        Restaurant r3 = new Restaurant("PQR", "Washington", openTime, closeTime);
        Restaurant r4 = new Restaurant("XYZ", "Washington", openTime, closeTime);

        service.addRestaurant(r1.getName(), r1.getLocation(), openTime, closeTime);
        service.addRestaurant(r2.getName(), r2.getLocation(), openTime, closeTime);
        service.addRestaurant(r3.getName(), r3.getLocation(), openTime, closeTime);
        service.addRestaurant(r4.getName(), r4.getLocation(), openTime, closeTime);

        //assert
        restaurantNotFoundException exception = assertThrows(restaurantNotFoundException.class, () -> service.findRestaurantByName("BlueLagoon"));
        assertEquals("No restaurant(s) found", exception.getMessage());
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}