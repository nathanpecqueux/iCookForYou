package fr.univ_littoral.nathan.myapplication.sampledata;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by alexd on 05/02/2018.
 */

public class Ingredient {
    public String name;
    public String quantity;
    public String unity;

    public static ArrayList<Ingredient> getIngredientsFromFile(String filename, Context context) {
        final ArrayList<Ingredient> ingredientsList = new ArrayList<>();

        try {
            // Load data
            String jsonString = loadJsonFromAsset("ingredients.json", context);
            if (jsonString != null) {
                JSONObject json = new JSONObject(jsonString);
                JSONArray ingredients = json.getJSONArray("ingredients");

                // Get Recipe objects from data
                for (int i = 0; i < ingredients.length(); i++) {
                    Ingredient ingredient = new Ingredient();

                    ingredient.name = ingredients.getJSONObject(i).getString("name");
                    System.out.println(ingredient.name);
                    ingredient.quantity = ingredients.getJSONObject(i).getString("quantity");
                    ingredient.unity = ingredients.getJSONObject(i).getString("unity");

                    ingredientsList.add(ingredient);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ingredientsList;
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
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
