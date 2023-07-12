package com.example.maximtechnologytask2.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.maximtechnologytask2.MainApplication;
import com.example.maximtechnologytask2.models.Invoice;
import com.example.maximtechnologytask2.models.Request;
import com.example.maximtechnologytask2.models.enums.DocumentType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RequestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonOk;

    @FXML
    private TextField inputCommission;

    @FXML
    private TextField inputCurrency;

    @FXML
    private DatePicker inputDate;

    @FXML
    private TextField inputNumber;

    @FXML
    private TextField inputRate;

    @FXML
    private TextField inputSum;

    @FXML
    private TextField inputUsername;

    @FXML
    private TextField inputContractor;

    @FXML
    void initialize() {
        buttonOk.setOnAction(actionEvent -> {
            buttonOk.getScene().getWindow().hide();

            Request request = Request.builder()
                    .number(inputNumber.getText())
                    .username(inputUsername.getText())
                    .sum(Double.parseDouble(inputSum.getText()))
                    .date(inputDate.getValue())
                    .type(DocumentType.REQUEST)
                    .contractor(inputContractor.getText())
                    .currency(inputCurrency.getText())
                    .rate(Double.parseDouble(inputRate.getText()))
                    .commission(Double.parseDouble(inputCommission.getText()))
                    .build();

            MainApplication.documents.add(request);
        });
    }

}
