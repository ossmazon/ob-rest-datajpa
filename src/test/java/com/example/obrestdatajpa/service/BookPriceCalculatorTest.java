package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {

        //configuration
        Book book = new Book(1L,
                "The hobbot",
                "Tolkien",
                1000,
                49.99,
                LocalDate.now(),
                true);
        BookPriceCalculator calculator= new BookPriceCalculator();

        // execute test
        double price = calculator.calculatePrice(book);
        System.out.println(price);

        //assertions
        assertTrue(price>0);

    }
}