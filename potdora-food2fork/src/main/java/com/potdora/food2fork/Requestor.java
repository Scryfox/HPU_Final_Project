package com.potdora.food2fork;

import java.net.*;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Map;
import java.util.HashMap;

public class Requestor {

    public static int requestRecipes() throws Exception {

        // Build the request
        URL url = new URL("http://example.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        int status = 0;
        try {
            con.setRequestMethod("GET");

            // Sets request headers
            con.setRequestProperty("Content-Type", "application/json");

            // Set timeouts to 5 seconds each
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            // Get the status code
            status = con.getResponseCode();

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } finally {

            // Close the connection
            con.disconnect();
        }

        return status;

    }

}