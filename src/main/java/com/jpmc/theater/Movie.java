package com.jpmc.theater;

import lombok.Builder;
import lombok.Value;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

@Value
@Builder(toBuilder = true)
public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay(), showing.getShowStartTime());
    }

    private double getDiscount(int showSequence, LocalDateTime showStartTime) {
        double specialDiscount = decide(showSequence);
        double sequenceDiscount = getSpecialDiscount(showStartTime);
        // biggest discount wins
        return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
    }



    double getSpecialDiscount(LocalDateTime showStartTime) {
        double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }
        int hour = showStartTime.getHour();
        int minute = showStartTime.getMinute();
        hour = hour == 16 ? hour + minute : hour;

        if (between(hour, 11, 16)) {
            specialDiscount = ticketPrice * 0.25;
        }
        return specialDiscount;
    }

    boolean between(int i, int minValue, int maxValue) {
        if (i >= minValue && i <= maxValue)
            return true;
        else
            return false;
    }
    enum Show {
        FIRST {
            @Override
            public int apply() {
                return 3;
            }
        },
        SECOND {
            @Override
            public int apply() {
                return 2;
            }
        },
        SEVENTH {
            @Override
            public int apply() {
                return 1;
            }
        },
        DEFAULT {
            @Override
            public int apply() {
                return 0;
            }
        };

        public abstract int apply();

    }

    public double decide(int showSequence) {
        var hasValidDiscountSequence = validDiscount().contains(showSequence) ? showSequence : 0;
        Show show = Show.valueOf(getMap().get(hasValidDiscountSequence));
        return show.apply();
    }

    public Set<Integer> validDiscount() {
        return Set.of(1, 2, 7);
    }

    public Map<Integer, String> getMap() {
        return Map.of(1, "FIRST", 2, "SECOND", 7, "SEVENTH",
                0, "DEFAULT");
    }
}