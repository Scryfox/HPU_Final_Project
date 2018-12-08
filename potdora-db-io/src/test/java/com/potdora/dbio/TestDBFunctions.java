package com.potdora.dbio;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDBFunctions {

    @Before
    public void setupDB() {
        try {
            Connection conn = AWSRDBConnector.connectToDB();
            System.out.println("Successfully connected to database!");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"User\" (\"Name\") VALUES (?)");

            ps.setString(1, "TESTSQLUSER");

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testCheckIfUserExists() {
        try {
            Assert.assertTrue(DBFunctions.checkIfUserExists("TESTSQLUSER"));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @After
    public void cleanupDB() {
        try {
            Connection conn = AWSRDBConnector.connectToDB();
            PreparedStatement delps = conn
                    .prepareStatement("DELETE FROM \"User\" WHERE \"User\".\"Name\" = 'TESTSQLUSER'");

            delps.executeUpdate();
            delps.close();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

}