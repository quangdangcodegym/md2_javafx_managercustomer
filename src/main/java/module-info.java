module com.codegym.managercustomer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.codegym.managercustomer to javafx.fxml;
    exports com.codegym.managercustomer;


    opens com.codegym.managercustomer.controller to javafx.fxml;
    exports com.codegym.managercustomer.controller;

    opens com.codegym.managercustomer.model to java.base;
    exports com.codegym.managercustomer.model;
}