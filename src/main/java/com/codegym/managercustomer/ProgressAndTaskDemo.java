package com.codegym.managercustomer;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.codegym.managercustomer.utils.Utils;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ProgressAndTaskDemo extends Application {

    private CopyTask copyTask;
    private ObservableList<File> copiedFiles;

    @Override
    public void start(Stage primaryStage) {
        copiedFiles = FXCollections.observableArrayList();

        final Label label = new Label("Copy files:");
        final ProgressBar progressBar = new ProgressBar(0);
        final ProgressIndicator progressIndicator = new ProgressIndicator(0);

        final Button startButton = new Button("Start");
        final Button cancelButton = new Button("Cancel");

        final Label statusLabel = new Label();
        statusLabel.setMinWidth(250);
        statusLabel.setTextFill(Color.BLUE);


        // Start Button.
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startButton.setDisable(true);
                progressBar.setProgress(0);
                progressIndicator.setProgress(0);
                cancelButton.setDisable(false);

                // Tạo một nhiệm vụ.
                copyTask = new CopyTask();

                // Hủy bỏ kết nối thuộc tính progress
                progressBar.progressProperty().unbind();

                // Kết nối thuộc tính progress.
                progressBar.progressProperty().bind(copyTask.progressProperty());

                // Hủy bỏ kết nối thuộc tính progress
                progressIndicator.progressProperty().unbind();

                // Kết nối thuộc tính progress.
                progressIndicator.progressProperty().bind(copyTask.progressProperty());

                // Hủy bỏ các kết nối thuộc tính text của Label.
                statusLabel.textProperty().unbind();

                // Kết nối thuộc tính text của Label
                // với thuộc tính message của Task
                statusLabel.textProperty().bind(copyTask.messageProperty());

                // Khi nhiệm vụ hoàn thành
                /*copyTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
                        new EventHandler<WorkerStateEvent>() {

                            @Override
                            public void handle(WorkerStateEvent t) {
                                List<File> copied = copyTask.getValue();
                                statusLabel.textProperty().unbind();
                                statusLabel.setText("Copied: " + copied.size());
                            }
                        });
                copyTask.valueProperty().addListener((observable, oldValue, newValue) -> {
                    Utils.my_log.logger.warning("value");
                });
                copyTask.progressProperty().addListener((observable, oldValue, newValue) -> {
                    //Utils.my_log.logger.info("progress");
                });
                  */

                copiedFiles.addListener((ListChangeListener<? super File>) c -> {
                    //Utils.my_log.logger.warning("Size: " + copiedFiles.get(copiedFiles.size()-1).getName());

                    System.out.println(copiedFiles.size());
                });




                // Start the Task.
                // Thực thi nhiệm vụ
                copyTask.setCopiedFiles(copiedFiles);
                new Thread(copyTask).start();

            }
        });


        // Cancel
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startButton.setDisable(false);
                cancelButton.setDisable(true);
                copyTask.cancel(true);
                progressBar.progressProperty().unbind();
                progressIndicator.progressProperty().unbind();
                statusLabel.textProperty().unbind();
                //
                progressBar.setProgress(0);
                progressIndicator.setProgress(0);
            }
        });

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);

        root.getChildren().addAll(label, progressBar, progressIndicator, //
                statusLabel, startButton, cancelButton);

        Scene scene = new Scene(root, 500, 120, Color.WHITE);
        primaryStage.setTitle("ProgressBar & ProgressIndicator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        Utils.init();
        Application.launch(args);
    }

}