package com.potdora.reciperequestor;

import java.util.LinkedList;

import com.potdora.controller.Ingredient;
import com.potdora.controller.Recipe;

class RecipeImpl implements Recipe {

    String name = "";

    LinkedList<Ingredient> ingredients = new LinkedList<>();

    RecipeImpl(String name, LinkedList<Ingredient> ingredients, String directions) {
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override
    public String getName() {
        return null;

    }

    @Override
    public LinkedList<Ingredient> getIngredientList() {
        return null;
    }

    @Override
    public String getURL() {
        return null;
    }

}