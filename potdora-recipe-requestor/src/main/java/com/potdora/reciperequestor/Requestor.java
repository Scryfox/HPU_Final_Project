package com.potdora.reciperequestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Requestor {

    static String apiID = "f01e1f45";
    static String apiKey = "5dd82603bde909385aeb3b383b3da7a1";

    public static String requestRecipes(String parameterString) throws Exception {

        // Build the request
        URL url = new URL(
                "https://api.edamam.com/search?app_id=" + apiID + "&app_key=" + apiKey + "&" + parameterString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        int status = 0;

        StringBuffer content = new StringBuffer();

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
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } finally {

            // Close the connection
            con.disconnect();
        }

        return content.toString();

    }

}