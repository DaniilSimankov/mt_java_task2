package com.example.maximtechnologytask2.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.maximtechnologytask2.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label report;

    @FXML
    private Button buttonOk;

    @FXML
    void initialize() {
        report.setText(MainApplication.documentToString(MainApplication.documents.get(MainApplication.documents.size()-1)));

        buttonOk.setOnAction(actionEvent -> {
            buttonOk.getScene().getWindow().hide();
        });
    }

}
