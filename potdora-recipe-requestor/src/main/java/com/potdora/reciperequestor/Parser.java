package com.potdora.reciperequestor;

import java.util.LinkedList;

import com.potdora.controller.Recipe;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {

    public static LinkedList<Recipe> parseRecipes(String jsonString) {

        JSONArray hits = new JSONObject(jsonString).getJSONArray("hits");

        LinkedList<Recipe> recipes = new LinkedList<>();

        for (int i = 0; i < hits.length(); i++) {
            JSONObject recipeJSON = ((JSONObject) ((JSONObject) hits.get(i)).get("recipe"));

            String name = recipeJSON.getString("label");

            System.out.println(name);
        }

        return recipes;
    }

}