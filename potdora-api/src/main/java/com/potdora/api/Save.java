package com.potdora.api;

import com.potdora.controller.Recipe;
import com.potdora.dbio.DBFunctions;

public class Save {

    public static void saveLike(String userName, Recipe recipe) throws Exception {

        if (userName.isEmpty()) {
            throw new Exception();
        }

        DBFunctions.saveUserPreferences(recipe, userName);

    }

}