package com.potdora.client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.potdora.api.Consolidate;
import com.potdora.api.Retrieve;
import com.potdora.controller.Ingredient;
import com.potdora.controller.Recipe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class GUIController {

    LinkedList<Recipe> recipes = new LinkedList<>();

    @FXML
    public TextField userName;

    @FXML
    public Label recipeNameGUI;

    @FXML
    public Button addButton;

    @FXML
    public Accordion recipesAccordion;

    @FXML
    public Button generateListButton;

    @FXML
    public void addRecipe() {

        Recipe newRecipe = null;
        try {
            newRecipe = Retrieve.randomRecipe();
        } catch (IOException IOe) {
            new Alert(AlertType.INFORMATION, "No more than 5 recipes can be displayed per minute").showAndWait();
        }

        String recipeName = newRecipe.getName();

        Label recipeNameGUI = new Label(recipeName);

        recipeNameGUI.setPrefHeight(23);

        recipeNameGUI.setPrefWidth(207);

        final Button deleteButton = new Button("Delete");
        final Button replaceButton = new Button("Replace");

        HBox recipeInternals = new HBox(recipeNameGUI, replaceButton, deleteButton);

        recipeInternals.setPrefHeight(0);
        recipeInternals.setPrefWidth(350);

        Label recipeDetailsGUI = new Label(ingredientString(newRecipe.getIngredientList()));

        AnchorPane recipeBody = new AnchorPane();
        recipeBody.getChildren().add(recipeDetailsGUI);

        TitledPane newRecipeGUI = new TitledPane(null, recipeBody);

        newRecipeGUI.setGraphic(recipeInternals);

        recipes.add(newRecipe);

        recipesAccordion.getPanes().add(newRecipeGUI);

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TitledPane currentPane = (TitledPane) deleteButton.getParent().getParent().getParent();
                String currentRecipeName = ((Label) ((HBox) currentPane.getGraphic()).getChildren().get(0)).getText();
                for (int i = 0; i < recipes.size(); i++) {
                    if (currentRecipeName.equals(recipes.get(i).getName())) {
                        recipes.remove(i);
                        break;
                    }
                }
                recipesAccordion.getPanes().remove(currentPane);
                System.out.println("Removed recipe");
            }
        });

        replaceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TitledPane currentPane = (TitledPane) replaceButton.getParent().getParent().getParent();

                Recipe newRecipe = null;

                try {
                    newRecipe = Retrieve.randomRecipe();
                } catch (IOException IOe) {
                    new Alert(AlertType.INFORMATION, "No more than 5 recipes can be displayed per minute")
                            .showAndWait();
                }

                String recipeName = newRecipe.getName();

                HBox currentBar = (HBox) currentPane.getGraphic();

                Label currentRecipeName = (Label) currentBar.getChildren().get(0);

                currentRecipeName.setText(recipeName);

                AnchorPane paneContent = (AnchorPane) currentPane.getContent();

                Label recipeDetails = (Label) paneContent.getChildren().get(0);

                recipeDetails.setText(ingredientString(newRecipe.getIngredientList()));

                System.out.println("Replaced recipe");
            }
        });

    }

    @FXML
    public void createShoppingList() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose were to save list");
        File saveFile = fileChooser.showSaveDialog(generateListButton.getScene().getWindow());

        if (saveFile == null) {
            return;
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(saveFile);

            // TODO: Generate stuff to write to file
            String ingredientList = Consolidate.generateShoppingList(recipes);

            fileWriter.append("***Ingredients*** \n\n");

            fileWriter.append(ingredientList);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void checkUserName() {
        System.out.println(userName.getText());
    }

    String ingredientString(LinkedList<Ingredient> ingredients) {

        String recipeDetails = "";

        recipeDetails += "***Ingredients***\n\n";

        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient curIngredient = ingredients.get(i);
            recipeDetails += "* " + curIngredient.getName() + ": " + curIngredient.getAmountInGrams() + "\n";
        }
        return recipeDetails;

    }

}