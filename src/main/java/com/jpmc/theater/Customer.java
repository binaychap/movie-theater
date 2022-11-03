package com.jpmc.theater;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Customer {
    private String name;
    private String id;
}