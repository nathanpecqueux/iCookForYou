package fr.univ_littoral.nathan.myapplication.sampledata;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Recipe {

    public String title;
    public Document page;
    public String urlLink;
    public String imageUrl;
    public String servings;
    public String difficulty;
    public String time;
    public ArrayList<String> ingredientLines = new ArrayList<String>();
    public List<String> step = new ArrayList<String>();
    public static ArrayList<Recipe> resultRecipes;

    public Recipe() {
    }

    public Recipe(String name, String urlLink) {
        this.title = name;
        this.urlLink = urlLink;
    }

    public static ArrayList<Recipe> getRecipesFromFile(String filename, Context context) {
        final ArrayList<Recipe> recipeList = new ArrayList<>();

        try {
            // Load data
            String jsonString = loadJsonFromAsset("recipes.json", context);
            if (jsonString != null) {
                JSONObject json = new JSONObject(jsonString);
                JSONArray recipes = json.getJSONArray("recipes");

                // Get Recipe objects from data
                for (int i = 0; i < recipes.length(); i++) {
                    Recipe recipe = new Recipe();

                    recipe.title = recipes.getJSONObject(i).getString("title");
                    recipe.servings = recipes.getJSONObject(i).getString("servings");
                    recipe.difficulty = recipes.getJSONObject(i).getString("difficulty");
                    recipe.time = recipes.getJSONObject(i).getString("time");
                    recipe.imageUrl = recipes.getJSONObject(i).getString("image");

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
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    double parse(String ratio) {
        if (ratio.contains("/")) {
            String[] rat = ratio.split("/");
            return Double.parseDouble(rat[0]) / Double.parseDouble(rat[1]);
        } else {
            return Double.parseDouble(ratio);
        }
    }

    public void loadInformations(String infoToLoad) throws IOException {
        if (infoToLoad.equals("all")) {
            page = Jsoup.connect(urlLink).get();
            time = (page.getElementsByClass("title-2 recipe-infos__total-time__value").text());
            difficulty = "1";
            ArrayList<String> result = new ArrayList<>();

            for (int i = 0; i < page.getElementsByClass("ingredient").size(); i++) {
                @SuppressWarnings("unused")
                int index = 0;
                String ingredientName = page.getElementsByClass("ingredient").get(i).text();
                if (page.getElementsByClass("recipe-ingredient-qt").get(i).text().equals("")) {
                    result.add("1 " + ingredientName);
                } else {
                    float quantity = (float) parse((page.getElementsByClass("recipe-ingredient-qt").get(i).text()));
                    double decimale = quantity - (int) quantity;
                    if (decimale == 0) {
                        int q = (int) parse((page.getElementsByClass("recipe-ingredient-qt").get(i).text()));
                        result.add(q + " " + ingredientName);
                    } else {
                        result.add(quantity + " " + ingredientName);
                    }
                }
            }
            ingredientLines = result;

            servings = page.getElementsByClass("title-2 recipe-infos__quantity__value").text();

            String str[] = page.getElementsByClass("recipe-preparation__list").text().split("Etape");

            for (int y = 0; y < str.length; y++) {
                String s = "";
                for (int i = 3; i < str[y].length(); i++) {
                    s += str[y].charAt(i);
                }
                str[y] = s;
                step.add(s);
            }

            if (page.getElementById("af-diapo-desktop-0_img") != null) {
                imageUrl = page.getElementById("af-diapo-desktop-0_img").attr("src");
            } else {
                imageUrl = "";
            }
        } else if (infoToLoad.equals("list")) {
            page = Jsoup.connect(urlLink).get();
            ArrayList<String> result = new ArrayList<>();

            for (int i = 0; i < page.getElementsByClass("ingredient").size(); i++) {
                @SuppressWarnings("unused")
                int index = 0;
                String ingredientName = page.getElementsByClass("ingredient").get(i).text();
                if (page.getElementsByClass("recipe-ingredient-qt").get(i).text().equals("")) {
                    result.add("1 " + ingredientName);
                } else {
                    float quantity = (float) parse((page.getElementsByClass("recipe-ingredient-qt").get(i).text()));
                    double decimale = quantity - (int) quantity;
                    if (decimale == 0) {
                        int q = (int) parse((page.getElementsByClass("recipe-ingredient-qt").get(i).text()));
                        result.add(q + " " + ingredientName);
                    } else {
                        result.add(quantity + " " + ingredientName);
                    }
                }
            }
            ingredientLines = result;

            if (page.getElementById("af-diapo-desktop-0_img") != null) {
                imageUrl = page.getElementById("af-diapo-desktop-0_img").attr("src");
            } else {
                imageUrl = "";
            }
        }
    }

    public static class getRecipes extends AsyncTask<String, Void, Void> {
        //ArrayList<Recipe> resultRecipes = new ArrayList<>();

        String ingrédients = null;
        int index = 0;

        @Override
        protected Void doInBackground(String... params) {
            try {
                ingrédients = params[0];
                index = Integer.parseInt(params[2]);

                Document document = Jsoup.connect("http://www.marmiton.org/recettes/recherche.aspx?aqt=" + ingrédients).get();

                Element elementResultsList = document.getElementsByClass("recipe-results").first();
                Elements resultsElements = elementResultsList.getElementsByClass("recipe-card");

                resultRecipes = new ArrayList<>();

                for (int i = 0; i < index; i++) {
                    Elements currentRecipeElement = resultsElements.get(i).getElementsByClass("recipe-card__title");
                    String title = currentRecipeElement.first().ownText();
                    String urlLink = "http://www.marmiton.org" + resultsElements.get(i).attr("href");

                    Recipe r = new Recipe(title, urlLink);

                    r.loadInformations(params[1]);

                    resultRecipes.add(r);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class getRecipe extends AsyncTask<String, Void, Void> {
        //ArrayList<Recipe> resultRecipes = new ArrayList<>();

        @Override
        protected Void doInBackground(String... params) {
            try {
                resultRecipes = new ArrayList<>();

                String title = params[0];
                String url = params[1];
                Recipe r = new Recipe(title, url);

                r.loadInformations(params[2]);

                resultRecipes.add(r);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class getHistoryList extends AsyncTask<String, Void, Void> {
        //ArrayList<Recipe> resultRecipes = new ArrayList<>();

        @Override
        protected Void doInBackground(String... params) {
            try {
                resultRecipes = new ArrayList<>();

                String url = params[0];
                Document document = null;
                try {
                    document = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Element resultsElements = document.getElementsByClass("main-title").first();

                resultRecipes = new ArrayList<>();

                String title = resultsElements.ownText();

                Recipe r = new Recipe(title, url);

                r.loadInformations(params[1]);

                resultRecipes.add(r);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class getTop extends AsyncTask<String, Void, Void> {
        //ArrayList<Recipe> resultRecipes = new ArrayList<>();

        int index = 0;

        @Override
        protected Void doInBackground(String... params) {
            try {
                index = Integer.parseInt(params[0]);

                Document document = Jsoup.connect("http://www.marmiton.org/recettes/top-internautes.aspx").get();

                Element elementResultsList = document.getElementsByClass("m-lsting-recipe").first();
                Elements resultsElements = elementResultsList.getElementsByClass("item");

                resultRecipes = new ArrayList<>();

                for (int i = 0; i < index; i++) {
                    Elements currentRecipeElement = resultsElements.get(i).getElementsByClass("title");
                    String title = currentRecipeElement.first().ownText();
                    String urlLink = "http://www.marmiton.org" + resultsElements.get(i).attr("href");

                    Recipe r = new Recipe(title, urlLink);
                    //System.out.println(r);

                    r.loadInformations(params[1]);

                    resultRecipes.add(r);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return "Recipe [title=" + title + ", urlLink=" + urlLink + ", imageUrl=" + imageUrl + ", servings=" + servings
                + ", difficulty=" + difficulty + ", time=" + time + ", ingredientLines=" + ingredientLines + ", step="
                + step + "]";
    }

}