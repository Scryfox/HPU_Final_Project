package com.potdora.dbio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import com.potdora.controller.Recipe;

public class DBFunctions {

    public static boolean checkIfUserExists(String userName) throws Exception {

        Connection conn = AWSRDBConnector.connectToDB();

        Statement query = conn.createStatement();

        ResultSet results = query.executeQuery("SELECT \"Name\" FROM \"User\" WHERE \"Name\" LIKE '" + userName + "'");

        if (results.next()) {
            return true;
        }

        return false;

    }

    public static boolean checkIfRecipeExists(String recipeName) throws Exception {

        Connection conn = AWSRDBConnector.connectToDB();

        Statement query = conn.createStatement();

        ResultSet results = query.executeQuery("SELECT \"Name\" FROM \"Recipe\" WHERE \"Name\" = '" + recipeName + "'");

        if (results.next()) {
            System.out.println("Recipe is already in there!");
            return true;
        }

        return false;

    }

    public static boolean checkIfIngredientExists(String ingredientName) throws Exception {

        Connection conn = AWSRDBConnector.connectToDB();

        Statement query = conn.createStatement();

        ResultSet results = query
                .executeQuery("SELECT \"Name\" FROM \"Ingredient\" WHERE \"Name\" LIKE '" + ingredientName + "'");

        if (results.next()) {
            return true;
        }

        return false;

    }

    public static void addUser(String userName) throws Exception {
        if (checkIfUserExists(userName)) {
            return;
        }
        Connection conn = AWSRDBConnector.connectToDB();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO \"User\" (\"Name\") VALUES (?)");

        ps.setString(1, userName);

        ps.executeUpdate();
        ps.close();
    }

    public static void addRecipe(String recipeName) throws Exception {
        if (checkIfRecipeExists(recipeName)) {
            System.out.println("RECIPE HAS ALREADY BEEN ADDED");
            return;
        }
        System.out.println("RECIPE HAS NOT BEEN ADDED YET");
        Connection conn = AWSRDBConnector.connectToDB();

        PreparedStatement ps = conn.prepareStatement(
                "Insert into \"Recipe\" (\"API_ID\", \"Name\") values ((Select \"ID\" from \"API\" where \"API\".\"Name\" = 'Edamame'), ?)");

        ps.setString(1, recipeName);

        ps.executeUpdate();
        ps.close();
    }

    public static void addIngredient(String ingredientName) throws Exception {
        if (checkIfIngredientExists(ingredientName)) {
            return;
        }
        Connection conn = AWSRDBConnector.connectToDB();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO \"Ingredient\" (\"Name\") VALUES (?)");

        ps.setString(1, ingredientName);

        ps.executeUpdate();
        ps.close();
    }

    public static LinkedList<String> getUserPreferences(String userName) throws Exception {
        LinkedList<String> userPreferences = new LinkedList<>();

        Connection conn = AWSRDBConnector.connectToDB();

        ResultSet results = conn.createStatement().executeQuery(
                "Select \"Name\" from \"Ingredient\" join \"IngredientPreferences\" on \"Ingredient\".\"ID\" = \"IngredientPreferences\".\"Ingredient_ID\"");

        while (results.next()) {
            userPreferences.add(results.getString("Name"));
        }

        return userPreferences;

    }

    public static void saveUserPreferences(Recipe likedRecipe, String userName) throws Exception {

        Connection conn = AWSRDBConnector.connectToDB();

        // Save user's like for recipe
        conn.createStatement()
                .executeUpdate("Insert into \"Likes\" values ((Select \"ID\" from \"User\" where \"User\".\"Name\" = '"
                        + userName + "'), (Select \"ID\" from \"Recipe\" where \"Recipe\".\"Name\" = '"
                        + likedRecipe.getName() + "'))");

        for (int i = 0; i < likedRecipe.getIngredientList().size(); i++) {
            String preferredIngredient = likedRecipe.getIngredientList().get(i).getName();

            // Save user's preferences for ingredients
            conn.createStatement().executeUpdate(
                    "Insert into \"IngredientPreferences\" values ((Select \"ID\" from \"User\" where \"User\".\"Name\" = '"
                            + userName + "'), (Select \"ID\" from \"Ingredient\" where \"Ingredient\".\"Name\" = '"
                            + preferredIngredient + "'))");
        }
    }

}