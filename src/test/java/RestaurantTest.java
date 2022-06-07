import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        LocalTime openTime = LocalTime.of(1, 0, 0);
        LocalTime closeTime = LocalTime.of(23, 0, 0);
        restaurant = new Restaurant("ABC", "Miami", openTime, closeTime);
        assertEquals(true, restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        LocalTime openTime = LocalTime.of(8, 0, 0);
        LocalTime closeTime = LocalTime.of(23, 0, 0);
        restaurant = new Restaurant("ABC", "Miami", openTime, closeTime);
        assertEquals(false, restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void testOrderCost() {
        LocalTime openTime = LocalTime.of(8, 0, 0);
        LocalTime closeTime = LocalTime.of(17, 0, 0);
        restaurant = new Restaurant("ABC", "Florida", openTime, closeTime);

        Item item1 = new Item("Chicken clear Soup", 5);
        Item item2 = new Item("Crab soup", 5);
        Item item3 = new Item("Mutton kola", 15);
        Item item4 = new Item("Fish roast", 18);
        Item item5 = new Item("Prawn golden fry", 20);
        Item item6 = new Item("Biriyani", 15);
        Item item7 = new Item("Butter roti", 15);
        Item item8 = new Item("Mutton masala", 20);
        Item item9 = new Item("Chocolate milk shake", 10);
        Item item10 = new Item("Vanilla milk shake", 8);

        restaurant.getMenu().add(item1);
        restaurant.getMenu().add(item2);
        restaurant.getMenu().add(item3);

        assertEquals(25, restaurant.totalOrderCost(restaurant.getMenu()));
    }
}