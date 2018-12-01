package com.potdora.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GUIController {

    @FXML
    public TextField userName;

    @FXML
    public void createShoppingList() {

    }

    @FXML
    public void checkUserName() {
        System.out.println(userName.getText());
    }

}