package com.potdora.controller;

import java.util.LinkedList;

public interface Recipe {

    String getName();

    LinkedList<Ingredient> getIngredientList();

    String getURL();

}