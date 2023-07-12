package com.example.maximtechnologytask2;

import com.example.maximtechnologytask2.models.Document;
import com.example.maximtechnologytask2.models.Invoice;
import com.example.maximtechnologytask2.models.Payment;
import com.example.maximtechnologytask2.models.Request;
import com.example.maximtechnologytask2.models.enums.DocumentType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application {

    public static List<Document> documents = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static String documentToString(Document document){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Номер: ").append(document.getNumber()).append("\n")
                .append("Пользователь: ").append(document.getUsername()).append("\n")
                .append("Сумма: ").append(document.getSum()).append("\n")
                .append("Дата: ").append(document.getDate()).append("\n");

        if (document.getType() == DocumentType.INVOICE) {
            stringBuilder.append("Валюта: ").append(((Invoice) document).getCurrency()).append("\n")
                    .append("Курс: ").append(((Invoice) document).getRate()).append("\n")
                    .append("Товар: ").append(((Invoice) document).getItem()).append("\n")
                    .append("Количество: ").append(((Invoice) document).getQuantity());
        } else if (document.getType() == DocumentType.REQUEST) {
            stringBuilder.append("Валюта: ").append(((Request) document).getCurrency()).append("\n")
                    .append("Курс: ").append(((Request) document).getRate()).append("\n")
                    .append("Контрактор: ").append(((Request) document).getContractor()).append("\n")
                    .append("Комиссия: ").append(((Request) document).getCommission());

        } else {
            stringBuilder.append("Сотрудник: ").append(((Payment) document).getEmployee());
        }

        return stringBuilder.toString();
    }

    public static String documentToDto(Document document){
        StringBuilder stringBuilder = new StringBuilder();

        if(document.getType() == DocumentType.INVOICE){
            stringBuilder.append("Накладная от ");
        }else if (document.getType() == DocumentType.REQUEST) {
            stringBuilder.append("Заявка на оплату от ");
        } else {
            stringBuilder.append("Платёжка от ");
        }

        stringBuilder.append(document.getDate())
                .append(" номер ")
                .append(document.getNumber())
                .append("\n");

        return stringBuilder.toString();
    }

    public static String documentsToString(){
        StringBuilder stringBuilder = new StringBuilder();

        for (Document document : documents){
            stringBuilder.append(documentToDto(document));
        }

        return stringBuilder.toString();
    }
}
