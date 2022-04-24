package com.codegym.managercustomer.controller;

import com.codegym.managercustomer.utils.Utils;
import com.codegym.managercustomer.Main;
import com.codegym.managercustomer.model.UserAccount;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListCustomerController implements Initializable {
    @FXML
    private BorderPane idBorderPaneContent;
    private Stage stage;

    TableView<UserAccount> table = new TableView<UserAccount>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    public ListCustomerController() {

    }
    public void setStage(Stage stage) {
        this.stage = stage;

        initDataTable();

    }

    public void initDataTable() {
        // Tạo cột UserName (Kiểu dữ liệu String)
        TableColumn<UserAccount, String> userNameCol //
                = new TableColumn<UserAccount, String>("User Name");

        // Tạo cột Email (Kiểu dữ liệu String)
        TableColumn<UserAccount, String> emailCol//
                = new TableColumn<UserAccount, String>("Email");

        // Tạo cột FullName (Kiểu dữ liệu String)
        TableColumn<UserAccount, String> fullNameCol//
                = new TableColumn<UserAccount, String>("Full Name");


        // Tạo 2 cột con cho cột FullName
        TableColumn<UserAccount, String> firstNameCol//
                = new TableColumn<UserAccount, String>("First Name");

        TableColumn<UserAccount, String> lastNameCol //
                = new TableColumn<UserAccount, String>("Last Name");

        // Thêm 2 cột con vào cột FullName
        fullNameCol.getColumns().addAll(firstNameCol, lastNameCol);

        // Active Column
        TableColumn<UserAccount, Boolean> activeCol//
                = new TableColumn<UserAccount, Boolean>("Active");


        // Định nghĩa cách để lấy dữ liệu cho mỗi ô.
        // Lấy giá trị từ các thuộc tính của UserAccount.
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));


        // Sét xắp xếp theo userName
        userNameCol.setSortType(TableColumn.SortType.DESCENDING);
        lastNameCol.setSortable(false);

        table.setRowFactory( tv -> {
            TableRow<UserAccount> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    //MyType rowData = row.getItem();

                    UserAccount rowData = row.getItem();

                    ObservableObjectValue<UserAccount> userAccount = new SimpleObjectProperty<>(rowData);

                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("editcustomer.fxml"));
                    Parent root = null;


                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ((EditCustomerController) loader.getController()).setStage(stage, userAccount);
                    Scene scene = new Scene(root);



                    stage.setTitle("Edit user");
                    stage.setScene(scene);
                    stage.show();


                }
            });
            return row ;
        });

        // Hiển thị các dòng dữ liệu

        ObservableList<UserAccount> list = Utils.listAccounts;
        table.setItems(list);

        table.getColumns().addAll(userNameCol, emailCol, firstNameCol, lastNameCol, activeCol);

        MenuBar menuBar = new MenuBar();


        // Tạo các Menu
        Menu fileMenu = new Menu("Customer");
        //Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");


        // Tạo các MenuItem
        MenuItem listItems = new MenuItem("List");
        MenuItem newItem = new MenuItem("New");

        //MenuItem copyItem = new MenuItem("Copy");
        //MenuItem pasteItem = new MenuItem("Paste");

        // Thêm các MenuItem vào Menu.
        fileMenu.getItems().addAll(listItems, newItem);
        //editMenu.getItems().addAll(copyItem, pasteItem);

        // Them su kien
        newItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                /*FXMLLoader loader = new FXMLLoader(CustomerManager.class.getResource("addcustomer.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //((DetailCustomerController) loader.getController()).setModel(model);

                ((AddCustomerController) loader.getController()).setStage(stage);
                Scene scene = new Scene(root);

                stage.setTitle("Add user");
                stage.setScene(scene);
                stage.show();*/
            }
        });

        // Thêm các Menu vào MenuBar
        menuBar.getMenus().addAll(fileMenu, helpMenu);


        addButtonToTable();
        VBox root = new VBox();

        root.getChildren().add(menuBar);
        root.getChildren().add(table);


        stage.setTitle("TableView (o7planning.org)");
        Scene scene = new Scene(root, 900 , 900);

        idBorderPaneContent.setCenter(table);
        stage.setScene(scene);
        stage.show();
    }

    private void addButtonToTable() {
        TableColumn<UserAccount, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<UserAccount, Void>, TableCell<UserAccount, Void>> cellFactory = new Callback<TableColumn<UserAccount, Void>, TableCell<UserAccount, Void>>() {
            @Override
            public TableCell<UserAccount, Void> call(final TableColumn<UserAccount, Void> param) {
                final TableCell<UserAccount, Void> cell = new TableCell<UserAccount, Void>() {
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {

                            HBox rootBox = new HBox();

                            rootBox.setSpacing(10);
                            rootBox.setPadding(new Insets(15,20, 10,10));


                            Button btnE = new Button("E");
                            btnE.setOnAction(event -> {
                                UserAccount rowData = getTableView().getItems().get(getIndex());

                                ObservableObjectValue<UserAccount> userAccount = new SimpleObjectProperty<>(rowData);

                                FXMLLoader loader = new FXMLLoader(Main.class.getResource("editcustomer.fxml"));
                                Parent root = null;


                                try {
                                    root = loader.load();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                ((EditCustomerController) loader.getController()).setStage(stage, userAccount);
                                Scene scene = new Scene(root);



                                stage.setTitle("Edit user");
                                stage.setScene(scene);
                                stage.show();
                            });
                            Button btnD = new Button("D");
                            btnD.setOnAction(event -> {
                                UserAccount data = getTableView().getItems().get(getIndex());
                                System.out.println("selectedData: " + data);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Delete Items");
                                alert.setHeaderText("Results:");
                                alert.setContentText("Connect to the database successfully!");

                                alert.showAndWait();
                            });



                            rootBox.getChildren().addAll(btnE, btnD);
                            setGraphic(rootBox);

                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table.getColumns().add(colBtn);

    }
    private void showAlertWithHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Test Connection");
        alert.setHeaderText("Results:");
        alert.setContentText("Connect to the database successfully!");

        alert.showAndWait();
    }

    public void itemClickedAddCustomer(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("addcustomer.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //((DetailCustomerController) loader.getController()).setModel(model);

        ((AddCustomerController) loader.getController()).setStage(stage);
        Scene scene = new Scene(root);

        stage.setTitle("Add user");
        stage.setScene(scene);
        stage.show();
    }
}
