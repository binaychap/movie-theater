package com.jpmc.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jpmc.theater.LocalDateProvider;
import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TheaterUtils {
    public static LocalDateProvider provider = LocalDateProvider.singleton();
    private static ObjectMapper objectMapper = null;

    public static ObjectMapper mapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        return objectMapper;
    }

    public static LocalDateTime stringToLocalDatetime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return localDateTime;
    }

    public static List<Showing> givenShowSchedule() {

        var spiderMan = Movie.builder()
                .title("Spider-Man: No Way Home")
                .runningTime(Duration.ofMinutes(90))
                .ticketPrice(12.5)
                .specialCode(1)
                .build();
        var turningRed = Movie.builder()
                .title("Turning Red")
                .runningTime(Duration.ofMinutes(85))
                .ticketPrice(11)
                .specialCode(0)
                .build();
        var theBatMan = Movie.builder()
                .title("The Batman")
                .runningTime(Duration.ofMinutes(95))
                .ticketPrice(9)
                .specialCode(0)
                .build();

        return List.of(
                Showing.builder().movie(turningRed).sequenceOfTheDay(1)
                        .showStartTime(LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0)))
                        .build(),
                Showing.builder().movie(spiderMan).sequenceOfTheDay(2)
                        .showStartTime(LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0)))
                        .build(),
                Showing.builder().movie(theBatMan).sequenceOfTheDay(3)
                        .showStartTime(LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50)))
                        .build(),
                Showing.builder().movie(turningRed).sequenceOfTheDay(4)
                        .showStartTime(LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30)))
                        .build(),
                Showing.builder().movie(spiderMan).sequenceOfTheDay(5)
                        .showStartTime(LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10)))
                        .build(),
                Showing.builder().movie(theBatMan).sequenceOfTheDay(6)
                        .showStartTime(LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50)))
                        .build(),
                Showing.builder().movie(turningRed).sequenceOfTheDay(7)
                        .showStartTime(LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30)))
                        .build(),
                Showing.builder().movie(spiderMan).sequenceOfTheDay(8)
                        .showStartTime(LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10)))
                        .build(),
                Showing.builder().movie(theBatMan).sequenceOfTheDay(9)
                        .showStartTime(LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
                        .build()
        );
    }
}
