package com.potdora.api;

import java.util.LinkedList;
import java.util.Random;

import com.potdora.controller.Ingredient;
import com.potdora.controller.Recipe;
import com.potdora.controller.ShoppingListBuilder;
import com.potdora.reciperequestor.Parser;
import com.potdora.reciperequestor.Requestor;

public class Retrieve {

    public static Recipe randomRecipe() {

        System.out.println("RANDOM RECIPE INBOUND");

        String recipeJSON = "";

        try {
            recipeJSON = Requestor.requestRecipes("q=chicken");
        } catch (Exception e) {
            System.err.println("Requesting Recipes Failed");
            System.err.println(e);
            System.exit(1);
        }

        if (recipeJSON.isEmpty()) {
            System.err.println("No Recipes found!");
            System.exit(1);
        }

        LinkedList<Recipe> recipes = Parser.parseRecipes(recipeJSON);

        System.out.println("RECIPES PARSED");

        if (recipes.isEmpty()) {
            System.out.println("There are no recipes");
        }

        Random rand = new Random();

        return recipes.get(rand.nextInt(recipes.size()));

    }

    public static Recipe randomRecipe(String userName) throws Exception {

        // TODO: Base request string off of user preferences
        String recipeJSON = Requestor.requestRecipes("q=chicken");
        LinkedList<Recipe> recipes = Parser.parseRecipes(recipeJSON);
        return recipes.get(0);

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