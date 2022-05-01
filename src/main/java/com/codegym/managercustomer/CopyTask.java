package com.codegym.managercustomer;

import java.io.File;
import java.util.Random;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

// Copy all file in C:/Windows
public class CopyTask extends Task<String> {
    ObservableList<File> copiedFiles;

    public void setCopiedFiles(ObservableList<File> copiedFiles) {
        this.copiedFiles = copiedFiles;
    }

    @Override
    protected String call() throws Exception {

        File dir = new File("C:/Windows");
        File[] files = dir.listFiles();
        int count = files.length;



        //List<File> copied = new ArrayList<File>();
        int i = 0;
        for (File file : files) {
            if (file.isFile()) {
                this.copy(file);
                //copied.add(file);
                copiedFiles.addAll(file);
            }
            i++;
            this.updateProgress(i, count);
            //this.updateValue(copied);
            //this.updateValue(copiedFiles);
        }
       // return copiedFiles;
        return "";
    }

    private void copy(File file) throws Exception {
        this.updateMessage("Copying: " + file.getAbsolutePath());
        Random random = new Random();
        int r =  random.nextInt(5000 - 500) + 500;
        System.out.println("random: " + r);
        Thread.sleep(r);
    }

    private void copyFile(File file) throws InterruptedException {
        this.updateMessage("Copying: " + file.getAbsolutePath());
        Random random = new Random();
        int r =  random.nextInt(5000 - 500) + 500;
        System.out.println("random: " + r);
        Thread.sleep(r);
    }

}