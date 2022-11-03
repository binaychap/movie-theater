package com.jpmc.theater;

import com.jpmc.utils.TheaterUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        var theater = Theater.builder()
                .provider(TheaterUtils.provider)
                .schedule(TheaterUtils.givenShowSchedule()).build();
        var john = Customer.builder().name("John Doe").id("id-12345").build();
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 50);
    }

    @Test
    void givenCustomer_whenReserve4TicketWithSpecialDiscount_shouldReturnTotalFee() {
        var theater = Theater.builder()
                .provider(TheaterUtils.provider)
                .schedule(TheaterUtils.givenShowSchedule()).build();
        var john = Customer.builder().name("John Doe").id("id-12345").build();
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFeeWithDiscount(), 37.5);
    }

    @Test
    void givenCustomer_whenReserve4TicketWithNoShow_shouldThrowIllegalStateException() {
        var theater = Theater.builder()
                .provider(TheaterUtils.provider)
                .schedule(TheaterUtils.givenShowSchedule()).build();
        var john = Customer.builder().name("John Doe").id("id-12345").build();
        assertThrows(IllegalStateException.class, ()-> theater.reserve(john, -2, 4));
    }

    @Test
    void printMovieSchedule() {
        var theater = Theater.builder()
                .provider(TheaterUtils.provider)
                .schedule(TheaterUtils.givenShowSchedule()).build();
        theater.printSchedule();
    }

    @Test
    void printMovieSchedule_json_format() {
        var theater = Theater.builder()
                .provider(TheaterUtils.provider)
                .schedule(TheaterUtils.givenShowSchedule()).build();
        theater.printScheduleJSONFormat();
    }
}
