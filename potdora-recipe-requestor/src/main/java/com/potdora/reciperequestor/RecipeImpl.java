package com.potdora.reciperequestor;

import java.util.LinkedList;

import com.potdora.controller.Ingredient;
import com.potdora.controller.Recipe;

class RecipeImpl implements Recipe {

    String name = "";

    LinkedList<Ingredient> ingredients = new LinkedList<>();

    String url = "";

    RecipeImpl(String name, LinkedList<Ingredient> ingredients, String url) {
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override
    public String getName() {
        return name;

    }

    @Override
    public LinkedList<Ingredient> getIngredientList() {
        return ingredients;
    }

    @Override
    public String getURL() {
        return url;
    }

}