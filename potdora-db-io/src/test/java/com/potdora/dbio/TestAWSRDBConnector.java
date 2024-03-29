package com.potdora.dbio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;

public class TestAWSRDBConnector {

    @Test
    public void testConnectToDB() {
        try {
            Connection conn = AWSRDBConnector.connectToDB();
            System.out.println("Successfully connected to database!");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO \"User\" (\"Name\") VALUES (?)");

            ps.setString(1, "TESTSQLUSER");

            ps.executeUpdate();
            ps.close();

            Statement testQueryStatement = conn.createStatement();

            ResultSet results = testQueryStatement.executeQuery("SELECT * FROM \"User\"");

            boolean foundInsertedName = false;

            while (results.next()) {
                String nextName = results.getString("Name");
                if (nextName.equals("TESTSQLUSER"))
                    foundInsertedName = true;
            }

            Assert.assertTrue(foundInsertedName);

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