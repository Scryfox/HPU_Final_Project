package com.potdora.dbio;

import java.sql.Connection;
import java.sql.DriverManager;

public class AWSRDBConnector {

    // Driver String
    static String driver = "org.postgresql.Driver";

    // Database specifics
    static String hostName = "potdora-web-database.cdsgon0i3cmo.us-west-2.rds.amazonaws.com";
    static String dbName = "potdora_web_database";
    static int dbPort = 5432;
    static String userName = "Scryfox";
    static String password = "mario242";

    static Connection connectToDB() throws Exception {

        // Set the driver for the DB
        Class.forName(driver);

        String jdbcUrl = "jdbc:postgresql://" + hostName + ":" + dbPort + "/" + dbName + "?user=" + userName
                + "&password=" + password;

        Connection con = DriverManager.getConnection(jdbcUrl);

        return con;

    }

}