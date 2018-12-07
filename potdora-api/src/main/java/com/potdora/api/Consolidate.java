package com.potdora.api;

import java.util.LinkedList;

import com.potdora.controller.Ingredient;
import com.potdora.controller.Recipe;

public class Consolidate {

    public static String generateShoppingList(LinkedList<Recipe> recipes) {
        String result = "";
        LinkedList<Ingredient> consolidatedIngredients = new LinkedList<>();

        for (int i = 0; i < recipes.size(); i++) {
            LinkedList<Ingredient> ingredients = recipes.get(i).getIngredientList();
            for (int j = 0; j < ingredients.size(); j++) {
                boolean exists = false;
                Ingredient curIngredient = ingredients.get(j);
                for (int k = 0; k < consolidatedIngredients.size(); k++) {
                    if (curIngredient.getName().equals(consolidatedIngredients.get(k).getName())) {
                        exists = true;
                        System.out.println(curIngredient.getName() + " already exists!");
                        System.out.println("Before: " + curIngredient.getAmountInGrams());
                        System.out.println("Before: " + consolidatedIngredients.get(k).getAmountInGrams());

                        consolidatedIngredients.get(k).setAmountInGrams(
                                consolidatedIngredients.get(k).getAmountInGrams() + curIngredient.getAmountInGrams());

                        System.out.println("After: " + consolidatedIngredients.get(k).getAmountInGrams());
                    }

                }
                if (!exists) {
                    consolidatedIngredients.add(curIngredient);
                }
            }
        }

        for (int i = 0; i < consolidatedIngredients.size(); i++) {
            result += consolidatedIngredients.get(i).getName() + " : "
                    + consolidatedIngredients.get(i).getAmountInGrams() + "\n\n";
        }

        return result;
    }

}