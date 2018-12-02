package com.potdora.reciperequestor;

import java.util.LinkedList;

import com.potdora.controller.Ingredient;
import com.potdora.controller.Recipe;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {

    public static LinkedList<Recipe> parseRecipes(String jsonString) {

        System.out.println("PARSING RECIPES");

        if (jsonString.isEmpty()) {
            System.out.println("EMPTY STRING PASSED TO PARSER");
        }

        JSONArray hits = new JSONObject(jsonString).getJSONArray("hits");

        LinkedList<Recipe> recipes = new LinkedList<>();

        System.out.println("Hits: " + hits.length());

        for (int i = 0; i < hits.length(); i++) {
            LinkedList<Ingredient> ingredients = new LinkedList<>();

            JSONObject recipeJSON = ((JSONObject) ((JSONObject) hits.get(i)).get("recipe"));

            String name = recipeJSON.getString("label");

            String url = recipeJSON.getString("url");

            JSONArray ingredientsJSON = recipeJSON.getJSONArray("ingredients");

            for (int j = 0; j < ingredientsJSON.length(); j++) {
                JSONObject ingredientJsonObject = (JSONObject) ingredientsJSON.get(j);
                ingredients.add(new IngredientImpl(ingredientJsonObject.getString("text"),
                        ingredientJsonObject.getDouble("weight")));
            }

            RecipeImpl nextRecipe = new RecipeImpl(name, ingredients, url);

            recipes.add(nextRecipe);

        }

        System.out.println("RECIPES: " + recipes.size());

        for (int i = 0; i < recipes.size(); i++) {
            System.out.println(recipes.get(i).getName());
        }

        return recipes;
    }

}