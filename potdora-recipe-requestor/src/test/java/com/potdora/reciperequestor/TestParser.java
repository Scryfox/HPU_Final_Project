package com.potdora.reciperequestor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestParser {

    String testJSON = "";

    @Before
    public void getTestJSON() throws IOException {
        String fileName = "/home/scryfox/Documents/Projects/Potdora/potdora-recipe-requestor/src/test/java/com/potdora/reciperequestor/testJSON.txt";

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        testJSON = content;
    }

    @Test
    public void testParserForStaticRecipes() {

        try {
            Parser newParser = new Parser();
            newParser.parseRecipes(testJSON);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    @Test
    public void testParserForRealRecipes() {
        String json = "";

        try {
            json = Requestor.requestRecipes("q=chicken");
            if (json.isEmpty()) {
                System.out.println("JIJIJIJIJIJIJJIJIJ");
            }
        } catch (Exception e) {
            System.err.println(e);

        }
        if (json.isEmpty()) {
            System.out.println("HEHEHEHEHEHEHEHEHEHEHEH");
        }

        Parser newParser = new Parser();
        newParser.parseRecipes(json);
    }

    @Test
    public void testFileLoader() {

        try {
            File removableWordsFile = new File(Parser.class.getResource("/removable_words.txt").getFile());
            BufferedReader br = new BufferedReader(new FileReader(removableWordsFile));
            String testLine = br.readLine();
            System.out.println(testLine);
            Assert.assertEquals("TEST", testLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}