package com.example.maximtechnologytask2.models;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Payment extends Document {

    private String employee;
}
