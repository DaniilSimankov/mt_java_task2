package com.example.maximtechnologytask2.models;

import com.example.maximtechnologytask2.models.enums.DocumentType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
public class Document {
    protected Long id;
    protected String number;
    protected String username;
    protected double sum;
    protected LocalDate date;
    protected DocumentType type;

}
