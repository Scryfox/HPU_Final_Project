package com.potdora.controller;

import java.util.LinkedList;

public interface Recipe {

    String getName();

    LinkedList<IngredientInterface> getIngredientList();

    String getDirections();

}