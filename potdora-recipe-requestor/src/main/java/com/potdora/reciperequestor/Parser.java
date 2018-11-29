package com.potdora.reciperequestor;

import java.util.LinkedList;

import com.potdora.controller.Ingredient;
import com.potdora.controller.Recipe;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {

    public static LinkedList<Recipe> parseRecipes(String jsonString) {

        JSONArray hits = new JSONObject(jsonString).getJSONArray("hits");

        LinkedList<Recipe> recipes = new LinkedList<>();

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

            recipes.add(new RecipeImpl(name, ingredients, url));

        }

        return recipes;
    }

}