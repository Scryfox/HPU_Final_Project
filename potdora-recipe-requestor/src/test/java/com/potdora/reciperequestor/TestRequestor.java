package com.potdora.reciperequestor;

import org.junit.*;

public class TestRequestor {

    String response = "";

    @Before
    public void getStatus() {

        try {
            response = Requestor.requestRecipes("q=chicken");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @Test
    public void testRequestorSuccess() {

    }

    @Test
    public void testRequestorFailure() {
    }

}