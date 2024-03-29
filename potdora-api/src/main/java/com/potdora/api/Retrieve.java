package com.potdora.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Random;

import com.potdora.controller.Ingredient;
import com.potdora.controller.Recipe;
import com.potdora.controller.ShoppingListBuilder;
import com.potdora.dbio.DBFunctions;
import com.potdora.reciperequestor.Parser;
import com.potdora.reciperequestor.Requestor;

public class Retrieve {

    public static Recipe randomRecipe() throws IOException {

        System.out.println("RANDOM RECIPE INBOUND WITHOUT NAME");

        String recipeJSON = "";

        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(Retrieve.class.getResourceAsStream("/queries.txt")));

            LinkedList<String> queries = new LinkedList<>();

            String nextLine = "";

            while (true) {
                nextLine = br.readLine();
                if (nextLine == null) {
                    break;
                }
                queries.add(nextLine);
            }

            Random rand = new Random();

            int randomNum = rand.nextInt(queries.size());

            recipeJSON = Requestor.requestRecipes("q=" + queries.get(randomNum));

        } catch (IOException e) {
            System.err.println("Site can't be reached");
            throw new IOException();
        } catch (Exception e) {
            System.err.println("Requesting Recipes Failed");
            System.err.println(e);
            System.exit(1);
        }

        if (recipeJSON.isEmpty()) {
            System.err.println("No Recipes found!");
            System.exit(1);
        }

        Parser recipeParser = new Parser();

        LinkedList<Recipe> recipes = recipeParser.parseRecipes(recipeJSON);

        System.out.println("RECIPES PARSED");

        if (recipes.isEmpty()) {
            System.out.println("There are no recipes");
        }

        Random rand = new Random();

        return recipes.get(rand.nextInt(recipes.size()));

    }

    public static Recipe randomRecipe(String userName) throws Exception {

        System.out.println("RANDOM RECIPE INBOUND WITH NAME");

        String recipeJSON = "";

        LinkedList<String> queries = new LinkedList<>();

        try {

            InputStream preferences = Retrieve.class.getResourceAsStream("/queries.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(preferences));

            String nextLine = "";

            while (true) {
                nextLine = br.readLine();
                if (nextLine == null) {
                    break;
                }
                queries.add(nextLine);
            }

            LinkedList<String> userPrefs = new LinkedList<>();
            if (!userName.isEmpty()) {
                // Get user's preferences and add to list of options
                userPrefs = DBFunctions.getUserPreferences(userName);
            }

            queries.addAll(userPrefs);

            Random rand = new Random();

            int randomNum = rand.nextInt(queries.size());

            recipeJSON = Requestor.requestRecipes("q=" + queries.get(randomNum));

        } catch (

        IOException e) {
            System.err.println("Site can't be reached");
            throw new IOException();
        } catch (Exception e) {
            System.err.println("Requesting Recipes Failed");
            System.err.println(e);
            System.exit(1);
        }

        if (recipeJSON.isEmpty()) {
            System.err.println("No Recipes found!");
            System.exit(1);
        }

        Parser recipeParser = new Parser();

        LinkedList<Recipe> recipes = recipeParser.parseRecipes(recipeJSON);

        System.out.println("RECIPES PARSED");

        if (recipes.isEmpty()) {
            System.out.println("There are no recipes");
        }

        Random rand = new Random();

        Recipe randomRecipe = recipes.get(rand.nextInt(recipes.size()));

        // Add recipe to database
        DBFunctions.addRecipe(randomRecipe.getName());

        for (int i = 0; i < randomRecipe.getIngredientList().size(); i++) {
            // Add recipe ingredients to database
            DBFunctions.addIngredient(randomRecipe.getIngredientList().get(i).getName());
        }

        return randomRecipe;

    }

    public static String generateShoppingList(LinkedList<Recipe> recipes) {

        String shoppingList = "";

        shoppingList += "Shopping List \n";

        shoppingList += "\n\n";

        LinkedList<Ingredient> ingredients = ShoppingListBuilder.consolidateIngredients(recipes);

        for (int i = 0; i < recipes.size(); i++) {
            Recipe currentRecipe = recipes.get(i);

            shoppingList += "Recipe: " + currentRecipe.getName() + "\n\n";

        }

        for (int j = 0; j < ingredients.size(); j++) {
            Ingredient curIngredient = ingredients.get(j);

            shoppingList += curIngredient.getName() + curIngredient.getAmountInGrams();
            shoppingList += "\n";
        }

        return shoppingList;

    }

}