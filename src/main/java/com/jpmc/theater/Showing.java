package com.jpmc.theater;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
@Value
@Builder(toBuilder = true)
public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public double calculateFee(int audienceCount) {
        return movie.calculateTicketPrice(this) * audienceCount;
    }
}
