package com.potdora.reciperequestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

import com.potdora.controller.Ingredient;
import com.potdora.controller.Recipe;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {

    public Parser() {

    }

    public LinkedList<Recipe> parseRecipes(String jsonString) {

        if (jsonString.isEmpty()) {
            System.out.println("EMPTY STRING PASSED TO PARSER");
        }

        JSONArray hits = new JSONObject(jsonString).getJSONArray("hits");

        LinkedList<Recipe> recipes = new LinkedList<>();

        System.out.println("Hits: " + hits.length());
        LinkedList<String> removableWords = new LinkedList<>();

        try {

            System.out.println("URL for file is : " + this.getClass().getResource("/removable_words.txt"));

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(getClass().getResourceAsStream("/removable_words.txt")));

            String nextLine = "";

            while (true) {
                nextLine = br.readLine();
                if (nextLine == null) {
                    break;
                }
                removableWords.add(nextLine);
            }

        } catch (Exception e) {
            System.err.println("PROBLEM LOADING WORDS FILE");
            e.printStackTrace();
        }

        for (int i = 0; i < removableWords.size(); i++) {
            System.out.println("***" + removableWords.get(i) + "***");
        }

        for (int i = 0; i < hits.length(); i++) {
            LinkedList<Ingredient> ingredients = new LinkedList<>();

            JSONObject recipeJSON = ((JSONObject) ((JSONObject) hits.get(i)).get("recipe"));

            String name = recipeJSON.getString("label");

            String url = recipeJSON.getString("url");

            JSONArray ingredientsJSON = recipeJSON.getJSONArray("ingredients");

            for (int j = 0; j < ingredientsJSON.length(); j++) {
                JSONObject ingredientJsonObject = (JSONObject) ingredientsJSON.get(j);
                String[] rawIngredientNameParts = ingredientJsonObject.getString("text").split(" ");
                String processedIngredientName = "";
                int startOfGoodInput = 1;
                for (int k = 1; k < rawIngredientNameParts.length; k++) {
                    boolean done = false;
                    for (int l = 0; l < removableWords.size(); l++) {
                        if (rawIngredientNameParts[k].equals(removableWords.get(l))) {
                            startOfGoodInput++;
                            break;
                        }
                        done = true;
                    }
                    if (done)
                        break;
                }

                processedIngredientName = String.join(" ",
                        Arrays.copyOfRange(rawIngredientNameParts, startOfGoodInput, rawIngredientNameParts.length));

                ingredients.add(new IngredientImpl(processedIngredientName, ingredientJsonObject.getDouble("weight")));
            }

            RecipeImpl nextRecipe = new RecipeImpl(name, ingredients, url);

            recipes.add(nextRecipe);

        }

        return recipes;
    }

}