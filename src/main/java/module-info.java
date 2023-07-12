module com.example.maximtechnologytask2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires lombok;

    opens com.example.maximtechnologytask2 to javafx.fxml;
    exports com.example.maximtechnologytask2;
    opens com.example.maximtechnologytask2.controllers to javafx.fxml;
}