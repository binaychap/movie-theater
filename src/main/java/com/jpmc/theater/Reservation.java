package com.jpmc.theater;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    public double totalFee() {
        return showing.getMovieFee() * audienceCount;
    }

    public double totalFeeWithDiscount() {
        return showing.calculateFee(audienceCount);
    }
}