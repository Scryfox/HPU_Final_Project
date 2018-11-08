package com.potdora.dbio;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestAWSRDBConnector {

    @Test
    public void testConnectToDB() {
        try {
            AWSRDBConnector.connectToDB();
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

}