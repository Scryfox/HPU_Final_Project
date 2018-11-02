package com.potdora.controller;

import java.util.LinkedList;

public interface ShoppingList {

    LinkedList<Recipe> getRecipes();

    LinkedList<Ingredient> getAllIngredients();

}