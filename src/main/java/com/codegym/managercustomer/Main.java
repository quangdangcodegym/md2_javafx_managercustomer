package com.codegym.managercustomer;

import com.codegym.managercustomer.controller.ListCustomerController;
import com.codegym.managercustomer.utils.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;

public class Main extends Application {
    public static final String MAIN_CUSTOMERMANAGER = "CustomerManager";
    @Override
    public void start(Stage stage) throws IOException {

        /*Log my_log = new Log("log.txt");
        my_log.logger.setLevel(Level.ALL);

        my_log.logger.info("Info message");
        my_log.logger.warning("warning message");
        my_log.logger.severe("Servere message");*/
//        FXMLLoader fxmlLoader = new FXMLLoader(CustomerManager.class.getResource("listcustomer.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//        Utils.init();
//        ListCusController listCusController = new ListCusController(stage);
//        System.out.println(MAIN_CUSTOMERMANAGER);


        Utils.init();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("listcustomer.fxml"));

        Parent root = fxmlLoader.load();
        ((ListCustomerController) fxmlLoader.getController()).setStage(stage);


        Scene scene = new Scene(root);
        stage.setTitle("Customer Manager");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
        System.out.println("Avavavav");
    }
}