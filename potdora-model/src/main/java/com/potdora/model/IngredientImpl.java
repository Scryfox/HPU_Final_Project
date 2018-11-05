package com.potdora.model;

import com.potdora.controller.Ingredient;

public class IngredientImpl implements Ingredient {

    private String name;
    private double amountInGrams;
    private double gramsPerLiter;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getAmountInGrams() {
        return this.amountInGrams;
    }

}