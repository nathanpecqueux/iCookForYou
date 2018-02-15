/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fr.univ_littoral.nathan.myapplication.sampledata;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Recipe {

    public String title;
    public String servings;
    public String difficulty;
    public String time;
    public String description;
    public String imageUrl;
    public String instructionUrl;
    public String label;
    public List<String> ingredientLines = new ArrayList<String>();
    public List<String> step = new ArrayList<String>();


    public static ArrayList<Recipe> getRecipesFromFile(String filename, Context context){
        final ArrayList<Recipe> recipeList = new ArrayList<>();

        try {
            // Load data
            String jsonString = loadJsonFromAsset("recipes.json", context);
            System.out.println(jsonString);
            if(jsonString!=null){
                JSONObject json = new JSONObject(jsonString);
                JSONArray recipes = json.getJSONArray("recipes");

                // Get Recipe objects from data
                for(int i = 0; i < recipes.length(); i++) {
                    Recipe recipe = new Recipe();

                    recipe.title = recipes.getJSONObject(i).getString("title");
                    recipe.servings = recipes.getJSONObject(i).getString("servings");
                    recipe.difficulty = recipes.getJSONObject(i).getString("difficulty");
                    recipe.time = recipes.getJSONObject(i).getString("time");
                    recipe.description = recipes.getJSONObject(i).getString("description");
                    recipe.imageUrl = recipes.getJSONObject(i).getString("image");
                    recipe.instructionUrl = recipes.getJSONObject(i).getString("url");
                    recipe.label = recipes.getJSONObject(i).getString("dietLabel");

                    JSONArray a = recipes.getJSONObject(i).getJSONArray("ingredientLines");
                    for (int j = 0; j < a.length(); j++) {
                        recipe.ingredientLines.add(a.getString(j));
                    }

                    JSONArray b = recipes.getJSONObject(i).getJSONArray("step");
                    for (int k = 0; k < b.length(); k++) {
                        recipe.step.add(b.getString(k));
                    }

                    recipeList.add(recipe);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipeList;
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
