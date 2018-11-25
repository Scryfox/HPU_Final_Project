package com.potdora.reciperequestor;

import com.potdora.controller.Ingredient;

class IngredientImpl implements Ingredient {

    String name = "";

    double amountInGrams = 0;

    IngredientImpl(String name, double amountInGrams) {
        this.name = name;
        this.amountInGrams = amountInGrams;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getAmountInGrams() {
        return amountInGrams;
    }

}