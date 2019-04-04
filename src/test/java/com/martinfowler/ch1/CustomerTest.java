package com.martinfowler.ch1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomerTest {

    private Customer customer;

    @Test
    public void statement_noRentals() {
        givenCustomerWithRentals("Jack");
        String expected = "Rental Record for Jack\n" +
            "Amount owed is 0.0\n" +
            "You earned 0 frequent renter points";
        resultShouldBe(expected);
    }

    @Test
    public void statement_oneRegularRental() {
        Rental rental = createRental("Avengers: Infinity War", Movie.REGULAR, 3);
        givenCustomerWithRentals("Jack", rental);
        String expected = "Rental Record for Jack\n" +
            "\tAvengers: Infinity War\t3.5\n" +
            "Amount owed is 3.5\n" +
            "You earned 1 frequent renter points";
        resultShouldBe(expected);
    }

    @Test
    public void statement_oneNewReleaseRental() {
        Rental rental = createRental("Venom", Movie.NEW_RELEASE, 3);
        givenCustomerWithRentals("Jack", rental);
        String expected = "Rental Record for Jack\n" +
            "\tVenom\t9.0\n" +
            "Amount owed is 9.0\n" +
            "You earned 2 frequent renter points";
        resultShouldBe(expected);
    }

    @Test
    public void statement_oneChildrensRental() {
        Rental rental = createRental("Spider-Man: Into the Spider-Verse", Movie.CHILDRENS, 3);
        givenCustomerWithRentals("Jack", rental);
        String expected = "Rental Record for Jack\n" +
            "\tSpider-Man: Into the Spider-Verse\t1.5\n" +
            "Amount owed is 1.5\n" +
            "You earned 1 frequent renter points";
        resultShouldBe(expected);
    }

    @Test
    public void statement_manyRentals() {
        Rental rental1 = createRental("Spider-Man: Into the Spider-Verse", Movie.CHILDRENS, 6);
        Rental rental2 = createRental("Venom", Movie.NEW_RELEASE, 2);
        Rental rental3 = createRental("Avengers: Infinity War", Movie.REGULAR, 8);
        givenCustomerWithRentals("Jack", rental1, rental2, rental3);
        String expected = "Rental Record for Jack\n" +
            "\tSpider-Man: Into the Spider-Verse\t6.0\n" +
            "\tVenom\t6.0\n" +
            "\tAvengers: Infinity War\t11.0\n" +
            "Amount owed is 23.0\n" +
            "You earned 4 frequent renter points";
        resultShouldBe(expected);
    }

    private Rental createRental(String title, int priceCode, int daysRented) {
        Movie movie = new Movie(title, priceCode);
        return new Rental(movie, daysRented);
    }

    private void givenCustomerWithRentals(String name, Rental... rentals) {
        customer = new Customer(name);
        for (Rental rental : rentals) {
            customer.addRental(rental);
        }
    }

    private void resultShouldBe(String expected) {
        assertEquals(expected, customer.statement());
    }
}
