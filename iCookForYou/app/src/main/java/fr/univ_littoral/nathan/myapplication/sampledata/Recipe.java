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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
    public static ArrayList<Recipe> resultRecipes = new ArrayList<>();

    public Recipe(String name, String urlLink) {
        this.title = name;
        this.urlLink = urlLink;
    }

    public static ArrayList<Recipe> getRecipesFromFile(String filename, Context context){
        final ArrayList<Recipe> recipeList = new ArrayList<>();

        try {
            // Load data
            String jsonString = loadJsonFromAsset("recipes.json", context);
            if(jsonString!=null){
                JSONObject json = new JSONObject(jsonString);
                JSONArray recipes = json.getJSONArray("recipes");

                // Get Recipe objects from data
                for(int i = 0; i < recipes.length(); i++) {
                    Recipe recipe = new Recipe(null,null);

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
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    public void loadInformations() throws IOException {
        page = Jsoup.connect(this.getUrlLink()).get();

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
                result.add(quantity + " " + ingredientName);
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

        if(page.getElementById("af-diapo-desktop-0_img") != null) {
            imageUrl = page.getElementById("af-diapo-desktop-0_img").attr("src");
        }else {
            imageUrl="";
        }
    }

    double parse(String ratio) {
        if (ratio.contains("/")) {
            String[] rat = ratio.split("/");
            return Double.parseDouble(rat[0]) / Double.parseDouble(rat[1]);
        } else {
            return Double.parseDouble(ratio);
        }
    }

    public ArrayList<Recipe> search(String keyword)throws IOException {
        ArrayList<Recipe> resultRecipes = new ArrayList<>();

        Document document = Jsoup.connect("http://www.marmiton.org/recettes/recherche.aspx?aqt=" + "jambon").get();

        Element elementResultsList = document.getElementsByClass("recipe-results").first();
        Elements resultsElements = elementResultsList.getElementsByClass("recipe-card");

        for (Element e : resultsElements) {
            Elements currentRecipeElement = e.getElementsByClass("recipe-card__title");
            String title = currentRecipeElement.first().ownText();
            String urlLink = "http://www.marmiton.org" + e.attr("href");

            Recipe r = new Recipe(title, urlLink);

            r.loadInformations();

            resultRecipes.add(r);

        }
        return resultRecipes;
    }

    public static class getRecipes extends AsyncTask<String, Void, Void> {
        //ArrayList<Recipe> resultRecipes = new ArrayList<>();

        String ingrédients = null;

        @Override
        protected Void doInBackground(String... params) {
            try {
                ingrédients = params[0];

                Document document = Jsoup.connect("http://www.marmiton.org/recettes/recherche.aspx?aqt=" + ingrédients).get();

                Element elementResultsList = document.getElementsByClass("recipe-results").first();
                Elements resultsElements = elementResultsList.getElementsByClass("recipe-card");

                for (Element e : resultsElements) {
                    Elements currentRecipeElement = e.getElementsByClass("recipe-card__title");
                    String title = currentRecipeElement.first().ownText();
                    String urlLink = "http://www.marmiton.org" + e.attr("href");

                    Recipe r = new Recipe(title, urlLink);

                    r.loadInformations();

                    resultRecipes.add(r);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public ArrayList<Recipe> getResultRecipes() {
        return resultRecipes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Document getPage() {
        return page;
    }

    public void setPage(Document page) {
        this.page = page;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(ArrayList<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public List<String> getStep() {
        return step;
    }

    public void setStep(List<String> step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "Recipe [title=" + title + ", urlLink=" + urlLink + ", imageUrl=" + imageUrl + ", servings=" + servings
                + ", difficulty=" + difficulty + ", time=" + time + ", ingredientLines=" + ingredientLines + ", step="
                + step + "]";
    }

}
