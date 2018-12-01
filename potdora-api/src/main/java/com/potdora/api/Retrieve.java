package com.potdora.api;

import java.util.LinkedList;

import com.potdora.controller.Ingredient;
import com.potdora.controller.Recipe;
import com.potdora.controller.ShoppingListBuilder;
import com.potdora.reciperequestor.Parser;
import com.potdora.reciperequestor.Requestor;

public class Retrieve {

    public static Recipe randomRecipe() throws Exception {

        String recipeJSON = Requestor.requestRecipes("chicken");
        LinkedList<Recipe> recipes = Parser.parseRecipes(recipeJSON);
        return recipes.get(0);

    }

    public static Recipe randomRecipe(String userName) throws Exception {

        // TODO: Base request string off of user preferences
        String recipeJSON = Requestor.requestRecipes("chicken");
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