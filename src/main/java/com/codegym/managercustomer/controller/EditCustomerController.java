package com.codegym.managercustomer.controller;

import com.codegym.managercustomer.Main;
import com.codegym.managercustomer.model.UserAccount;
import com.codegym.managercustomer.utils.Utils;
import javafx.beans.value.ObservableObjectValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {
    public TextField idfUserName;
    public TextField idfEmail;
    public TextField idfFirstName;
    public TextField idfLastName;

    public ObservableObjectValue<UserAccount> userAccount;
    private Stage stage;

    public void setStage(Stage stage, ObservableObjectValue<UserAccount> user) {
        this.stage = stage;
        this.userAccount = user;

        idfUserName.setText(userAccount.get().getUserName());
        idfEmail.setText(userAccount.get().getEmail());
        idfFirstName.setText(userAccount.get().getFirstName());
        idfLastName.setText(userAccount.get().getLastName());
    }

    public EditCustomerController() {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnSave(ActionEvent actionEvent) throws IOException {
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

    public void itemClickedAddCustomer(ActionEvent actionEvent) {
    }
}
