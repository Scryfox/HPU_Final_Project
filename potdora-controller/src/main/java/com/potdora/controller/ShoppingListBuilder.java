package com.potdora.controller;

import java.util.LinkedList;

public class ShoppingListBuilder {

    public static LinkedList<Ingredient> consolidateIngredients(LinkedList<Recipe> recipes) {

        LinkedList<Ingredient> ingredients = new LinkedList<>();

        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);

            for (int j = 0; j < recipe.getIngredientList().size(); j++) {
                Ingredient curIngredient = recipe.getIngredientList().get(j);
                boolean newIngredient = true;

                for (int k = 0; k < ingredients.size(); k++) {
                    if (ingredients.get(k).getName() == curIngredient.getName()) {
                        ingredients.get(k).setAmountInGrams(
                                ingredients.get(k).getAmountInGrams() + curIngredient.getAmountInGrams());
                        newIngredient = false;
                    }
                }
                if (newIngredient) {
                    ingredients.add(curIngredient);
                }
            }
        }

        return ingredients;

    }

}