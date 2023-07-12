package com.example.maximtechnologytask2.models;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Request extends Document{

    private String contractor;
    private String currency;
    private double rate;
    private double commission;
}
