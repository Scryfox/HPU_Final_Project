package com.potdora.client;

import java.util.LinkedList;

import com.potdora.api.Retrieve;
import com.potdora.controller.Recipe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class GUIController {

    LinkedList<TitledPane> recipes = new LinkedList<>();

    @FXML
    public TextField userName;

    @FXML
    public Label recipeNameGUI;

    @FXML
    public Button addButton;

    @FXML
    public Accordion recipesAccordion;

    @FXML
    public void addRecipe() {

        Recipe newRecipe = null;
        newRecipe = Retrieve.randomRecipe();

        String recipeName = newRecipe.getName();

        Label recipeNameGUI = new Label(recipeName);

        recipeNameGUI.setPrefHeight(23);

        recipeNameGUI.setPrefWidth(207);

        final Button deleteButton = new Button("Delete");

        HBox recipeInternals = new HBox(recipeNameGUI, new Button("Replace"), deleteButton);

        recipeInternals.setPrefHeight(0);
        recipeInternals.setPrefWidth(350);

        AnchorPane recipeBody = new AnchorPane();

        TitledPane newRecipeGUI = new TitledPane(null, recipeBody);

        newRecipeGUI.setGraphic(recipeInternals);

        recipes.add(newRecipeGUI);

        recipesAccordion.getPanes().add(newRecipeGUI);

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TitledPane currentPane = (TitledPane) deleteButton.getParent().getParent().getParent();
                recipesAccordion.getPanes().remove(currentPane);
                System.out.println("Removed recipe");
            }
        });

    }

    @FXML
    public void createShoppingList() {

    }

    @FXML
    public void checkUserName() {
        System.out.println(userName.getText());
    }

}