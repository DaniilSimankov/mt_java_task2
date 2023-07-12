package com.example.maximtechnologytask2.controllers;

import com.example.maximtechnologytask2.MainApplication;
import com.example.maximtechnologytask2.models.Document;
import com.example.maximtechnologytask2.models.Invoice;
import com.example.maximtechnologytask2.models.Payment;
import com.example.maximtechnologytask2.models.Request;
import com.example.maximtechnologytask2.models.enums.DocumentType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class MainController {

    private int countModified;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox boxCheckBox;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonInvoice;

    @FXML
    private Button buttonLoad;

    @FXML
    private Button buttonPayment;

    @FXML
    private Button buttonRequest;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonView;

    @FXML
    private Button buttonDelete;

    @FXML
    private Label report;

    @FXML
    void initialize() {

        buttonInvoice.setOnAction(actionEvent -> {
            openNewScene("invoice.fxml");

            if (countModified != MainApplication.documents.size()) {
                updateLabelAndCheckBox();
                countModified++;
            }
        });

        buttonPayment.setOnAction(actionEvent -> {
            openNewScene("payment.fxml");
            if (countModified != MainApplication.documents.size()) {
                updateLabelAndCheckBox();
                countModified++;

            }
        });

        buttonRequest.setOnAction(actionEvent -> {
            openNewScene("request.fxml");

            if (countModified != MainApplication.documents.size()) {
                updateLabelAndCheckBox();
                countModified++;
            }
        });

        buttonView.setOnAction(actionEvent -> {
            System.out.println(MainApplication.documents);

            openNewScene("view.fxml");
        });

        buttonSave.setOnAction(actionEvent -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Сохранение");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Текстовые документы (*.txt)", "*.txt"),
                    new FileChooser.ExtensionFilter("Все файлы", "*"));

            File file = fileChooser.showSaveDialog(new Stage());

            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                Document document = MainApplication.documents.get(MainApplication.documents.size() - 1);
                writer.println(MainApplication.documentToString(document));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        buttonLoad.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Открытие");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Текстовые документы (*.txt)", "*.txt"),
                    new FileChooser.ExtensionFilter("Все файлы", "*"));

            File file = fileChooser.showOpenDialog(new Stage());

            if (file != null) {

                try (FileReader fileReader = new FileReader(file);
                     BufferedReader reader = new BufferedReader(fileReader)) {

                    Map<String, Object> fields = new HashMap<>();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(": ");
                        if (parts.length < 2)
                            throw new IOException();
                        fields.put(parts[0], parts[1]);
                    }
                    if (!fields.isEmpty()) {
                        if (fields.containsKey("Сотрудник")) {
                            Payment payment = Payment.builder()
                                    .number((String) fields.get("Номер"))
                                    .username((String) fields.get("Пользователь"))
                                    .sum(Double.parseDouble((String) fields.get("Сумма")))
                                    .date(LocalDate.parse((String) fields.get("Дата")))
                                    .employee((String) fields.get("Сотрудник"))
                                    .type(DocumentType.PAYMENT)
                                    .build();

                            MainApplication.documents.add(payment);

                        } else if (fields.containsKey("Комиссия")) {
                            Request request = Request.builder()
                                    .number((String) fields.get("Номер"))
                                    .username((String) fields.get("Пользователь"))
                                    .sum(Double.parseDouble((String) fields.get("Сумма")))
                                    .date((LocalDate) fields.get("Дата"))
                                    .currency((String) fields.get("Валюта"))
                                    .rate(Double.parseDouble((String) fields.get("Курс")))
                                    .commission(Double.parseDouble((String) fields.get("Комиссия")))
                                    .contractor((String) fields.get("Контрактор"))
                                    .type(DocumentType.REQUEST)
                                    .build();

                            MainApplication.documents.add(request);
                        } else {
                            Invoice invoice = Invoice.builder()
                                    .number((String) fields.get("Номер"))
                                    .username((String) fields.get("Пользователь"))
                                    .sum(Double.parseDouble((String) fields.get("Сумма")))
                                    .date(LocalDate.parse((String) fields.get("Дата")))
                                    .currency((String) fields.get("Валюта"))
                                    .rate(Double.parseDouble((String) fields.get("Курс")))
                                    .item((String) fields.get("Товар"))
                                    .quantity(Double.parseDouble((String) fields.get("Количество")))
                                    .type(DocumentType.INVOICE)
                                    .build();

                            MainApplication.documents.add(invoice);
                        }
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }

            if (countModified != MainApplication.documents.size()) {
                updateLabelAndCheckBox();
                countModified++;
            }
        });

        buttonDelete.setOnAction(actionEvent -> {
            Set<String> setCheckBox = boxCheckBox.getChildren().stream().filter(child -> ((CheckBox)child).isSelected()).map(node -> ((CheckBox)node).getText()).collect(Collectors.toSet());
            MainApplication.documents.removeIf(document -> setCheckBox.contains(document.getNumber()));
            boxCheckBox.getChildren().removeIf(checkBox -> setCheckBox.contains(((CheckBox)checkBox).getText()));

            if (countModified != MainApplication.documents.size()) {
                report.setText(MainApplication.documentsToString());
            }
            countModified = countModified - setCheckBox.size();
        });

        buttonExit.setOnAction(actionEvent -> {
            buttonExit.getScene().getWindow().hide();
        });
    }

    private void updateLabelAndCheckBox() {
        report.setText(MainApplication.documentsToString());
        createCheckBox(MainApplication.documents.get(MainApplication.documents.size() - 1).getNumber());
    }

    public void openNewScene(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    private void createCheckBox(String name) {
        CheckBox checkBox = new CheckBox(name);
        checkBox.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
        boxCheckBox.getChildren().add(checkBox);
    }

}
