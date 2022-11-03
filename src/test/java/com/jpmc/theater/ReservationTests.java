package com.jpmc.theater;

import com.jpmc.utils.TheaterUtils;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {
    @Test
    void givenCustomer_withoutAnyDiscount_shouldReturnTotalFee() {
        var showing = givenShowing(1,1);
        assertTrue(new Reservation(givenCustomer(), showing, 3).totalFee() == 37.5);
    }

    @Test
    void givenCustomer_whenShowing1stDayWithSpecialDiscount_shouldReturnTotalFee() {
        var showing = givenShowing(1,1);
        assertTrue(new Reservation(givenCustomer(), showing, 3).totalFeeWithDiscount() == 28.5);
    }

    @Test
    void givenCustomer_whenShowing2ndDayWithSpecialDiscount_shouldReturnTotalFee() {
        var showing = givenShowing(1,2);
        assertTrue(new Reservation(givenCustomer(), showing, 3).totalFeeWithDiscount() == 30);
    }

    @Test
    void givenCustomer_whenShowing2ndDay_shouldReturnTotalFee() {
        var showing = givenShowing(4,2);
        assertTrue(new Reservation(givenCustomer(), showing, 3).totalFeeWithDiscount() == 31.5);
    }

    @Test
    void givenCustomer_withoutDiscount_shouldReturnTotalFee() {
        var showing = givenShowing(4,5);
        assertTrue(new Reservation(givenCustomer(), showing, 3).totalFeeWithDiscount() == 37.5);
    }

    Showing givenShowing(int specialCode, int sequenceOfTheDay) {
        return new Showing(
                Movie.builder()
                        .title("Spider-Man: No Way Home")
                        .runningTime(Duration.ofMinutes(90))
                        .ticketPrice(12.5)
                        .specialCode(specialCode)
                        .build(),
                sequenceOfTheDay,
                TheaterUtils.stringToLocalDatetime("2016-03-04 10:30")
        );
    }

    Customer givenCustomer() {
        return Customer.builder()
                .id("unused-id")
                .name("Jone Doe")
                .build();
    }
}
