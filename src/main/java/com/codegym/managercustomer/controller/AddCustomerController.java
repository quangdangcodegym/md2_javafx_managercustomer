package com.codegym.managercustomer.controller;

import com.codegym.managercustomer.Main;
import com.codegym.managercustomer.utils.Utils;
import com.codegym.managercustomer.model.UserAccount;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustomerController {

    public static final String CONTROLLER_ADDCUSTOMER ="AddCustomerLayoutController ";
    public static final String CONTROLLER_ADDCUSTOMER_SAVE ="btnSave() ";
    private Stage stage;
    @FXML
    private TextField idfUserName;

    @FXML
    public TextField idfEmail;
    @FXML
    public TextField idfFirstName;
    @FXML
    public TextField idfLastName;
    public ObservableObjectValue<UserAccount> userAccount;

    public void setStage(Stage stage) {
        this.stage = stage;
        UserAccount user = new UserAccount();
        userAccount = new SimpleObjectProperty<>(user);

    }
    public void itemClickedAddCustomer(ActionEvent actionEvent) {
    }

    public void btnSave(ActionEvent actionEvent) throws IOException {
        System.out.println(CONTROLLER_ADDCUSTOMER + CONTROLLER_ADDCUSTOMER_SAVE);
        userAccount.get().setUserName(idfUserName.getText());
        userAccount.get().setLastName(idfLastName.getText());
        userAccount.get().setEmail(idfEmail.getText());
        userAccount.get().setFirstName(idfFirstName.getText());
        userAccount.get().setId(Utils.getIDPresent());

        Utils.listAccounts.add(userAccount.get());
//        LayoutCustomerController listCusController = new LayoutCustomerController();
//        listCusController.setStage(stage);


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("listcustomer.fxml"));

        Parent root = fxmlLoader.load();
        ((ListCustomerController) fxmlLoader.getController()).setStage(stage);


        Scene scene = new Scene(root);
        stage.setTitle("Customer Manager");
        stage.setScene(scene);
        stage.show();
    }
}
