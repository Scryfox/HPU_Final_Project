package com.potdora.reciperequestor;

import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

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

        assertNotEquals("", response);

    }

    @Test
    public void testRequestorFailure() {
    }

}