package com.jpmc.theater;

import com.jpmc.utils.TheaterUtils;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void specialMovieWith20PercentDiscount() {
        var spiderMan = givenMovie();
        var givenShowing = givenShowing(spiderMan, "2016-03-04 16:30");
        assertEquals(10, spiderMan.calculateTicketPrice(givenShowing));
        System.out.println(Duration.ofMinutes(90));
    }

    @Test
    void givenMovieShow_whenShowBetween11To4PM_shouldReturn25PercentDiscount() {
        var spiderMan = givenMovie();
        var givenShowing = givenShowing(spiderMan, "2016-03-04 13:30");
        assertEquals(9.375, spiderMan.calculateTicketPrice(givenShowing));
        System.out.println(Duration.ofMinutes(90));
    }


    Showing givenShowing(Movie spiderMan, String showTime) {
        return Showing.builder()
                .movie(spiderMan)
                .sequenceOfTheDay(5)
                .showStartTime(TheaterUtils.stringToLocalDatetime(showTime))
                .build();
    }

    Movie givenMovie() {
        return Movie.builder()
                .title("Spider-Man: No Way Home")
                .runningTime(Duration.ofMinutes(90))
                .ticketPrice(12.5)
                .specialCode(1)
                .build();
    }
}
