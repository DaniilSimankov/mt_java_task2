package com.example.maximtechnologytask2.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.maximtechnologytask2.MainApplication;
import com.example.maximtechnologytask2.models.Invoice;
import com.example.maximtechnologytask2.models.Payment;
import com.example.maximtechnologytask2.models.enums.DocumentType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PaymentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonOk;

    @FXML
    private DatePicker inputDate;

    @FXML
    private TextField inputEmployee;

    @FXML
    private TextField inputNumber;

    @FXML
    private TextField inputSum;

    @FXML
    private TextField inputUsername;

    @FXML
    void initialize() {
        buttonOk.setOnAction(actionEvent -> {
            buttonOk.getScene().getWindow().hide();

            Payment payment = Payment.builder()
                    .number(inputNumber.getText())
                    .username(inputUsername.getText())
                    .sum(Double.parseDouble(inputSum.getText()))
                    .date(inputDate.getValue())
                    .type(DocumentType.PAYMENT)
                    .employee(inputEmployee.getText())
                    .build();

            MainApplication.documents.add(payment);
        });
    }

}
