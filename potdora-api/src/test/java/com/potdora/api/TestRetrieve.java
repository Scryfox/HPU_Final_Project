package com.potdora.api;

import org.junit.Test;

public class TestRetrieve {

    @Test
    public void testRecipeRetrieve() {
        System.out.println(Retrieve.randomRecipe().getName());
    }

}
