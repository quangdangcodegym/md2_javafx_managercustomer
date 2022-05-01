package com.codegym.managercustomer.utils;

import com.codegym.managercustomer.logger.Log;
import com.codegym.managercustomer.model.UserAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.logging.Level;

public class Utils {

    public  static  ObservableList<UserAccount> listAccounts;

    public static void init() throws IOException {
        my_log = new Log("log.txt");
        my_log.logger.setLevel(Level.ALL);
        listAccounts = getUserList();
    }
    public static Long getIDPresent() {
        return Long.valueOf(listAccounts.size());
    }

    public static Log my_log ;

    public static boolean editUserById(Long id, UserAccount user) {
        for (int i = 0; i < listAccounts.size(); i++) {
            if (listAccounts.get(i).getId() == id) {
                listAccounts.get(i).setEmail(user.getEmail());
                listAccounts.get(i).setUserName(user.getUserName());
                listAccounts.get(i).setFirstName(user.getFirstName());
                listAccounts.get(i).setLastName(user.getLastName());
                return true;
            }
        }
        return false;
    }
    public static ObservableList<UserAccount> getUserList() {

        UserAccount user1 = new UserAccount(1L, "smith", "smith@gmail.com", //
                "Susan", "Smith", true);
        UserAccount user2 = new UserAccount(2L, "mcneil", "mcneil@gmail.com", //
                "Anne", "McNeil", true);
        UserAccount user3 = new UserAccount(3L, "white", "white@gmail.com", //
                "Kenvin", "White", false);
        UserAccount user4 = new UserAccount(4L, "smith", "smith@gmail.com", //
                "Susan", "Smith", true);
        UserAccount user5 = new UserAccount(5L, "mcneil", "mcneil@gmail.com", //
                "Anne", "McNeil", true);
        UserAccount user6 = new UserAccount(6L, "white", "white@gmail.com", //
                "Kenvin", "White", false);
        UserAccount user7 = new UserAccount(7L, "smith", "smith@gmail.com", //
                "Susan", "Smith", true);
        UserAccount user8 = new UserAccount(8L, "mcneil", "mcneil@gmail.com", //
                "Anne", "McNeil", true);
        UserAccount user9 = new UserAccount(9L, "white", "white@gmail.com", //
                "Kenvin", "White", false);

        ObservableList<UserAccount> list = FXCollections.observableArrayList(user1, user2, user3, user4, user5, user6, user7, user8, user9);
        ObservableList<UserAccount> ls = FXCollections.observableArrayList();
        return list;
    }
}
