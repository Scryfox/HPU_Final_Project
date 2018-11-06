package com.potdora.food2fork;

import org.junit.*;

public class TestRequestor {

    int responseCode = 0;

    @Before
    public void getStatus() {

        try {
            responseCode = Requestor.requestRecipes();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @Test
    public void testRequestorSuccess() {

        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void testRequestorFailure() {
        Assert.assertNotEquals(100, responseCode);
    }

}