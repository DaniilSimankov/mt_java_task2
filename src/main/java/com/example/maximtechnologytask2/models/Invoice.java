package com.example.maximtechnologytask2.models;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Invoice extends Document{

    private String currency;
    private double rate;
    private String item;
    private double quantity;
}
