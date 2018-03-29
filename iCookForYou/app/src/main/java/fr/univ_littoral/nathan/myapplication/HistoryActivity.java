package fr.univ_littoral.nathan.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import fr.univ_littoral.nathan.myapplication.sampledata.Ingredient;
import fr.univ_littoral.nathan.myapplication.sampledata.Recipe;
import fr.univ_littoral.nathan.myapplication.sampledata.RecipeAdapter;


public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables globales pour le layout xml
    private ListView mListView;
    private Button next;
    private String index = "5";
    Context context;

    private static final String URL_FOOD = "http://51.255.164.53/php/selectHistByUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        index="5";

        //On lie nos variables globales avec les id du layout xml
        next = (Button) findViewById(R.id.buttonNext2);
        //Listener du bouton
        next.setOnClickListener(this);
        context = this;

        //On appele la fonction pour afficher les recettes
        printRecipes();

    }

    //permet de creer le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_recette, menu);
        return true;
    }

    //gère les clics sur les éléments du menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //on retourne à l'accueil
            case R.id.menuRecetteAccueil:
                Intent intentAccueil = new Intent(HistoryActivity.this, HomeActivity.class);
                startActivity(intentAccueil);
                break;
            //on va vers le profil de l'utilsateur
            case R.id.menuRecetteProfil:
                Intent intentProfil = new Intent(HistoryActivity.this, ProfileActivity.class);
                startActivity(intentProfil);
                break;
            //on accède au stock de l'utilsateur
            case R.id.menuRecetteStock:
                Intent intentStock = new Intent(HistoryActivity.this, StockActivity.class);
                startActivity(intentStock);
                break;
            //Pop-up informations de l'application
            case R.id.menuRecetteAPropos:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.layout_propos);
                dialog.setCancelable(true);

                //le texte
                dialog.setTitle("A propos de l'application");
                TextView text = (TextView) dialog.findViewById(R.id.TextView01);
                text.setText("Application créée par :");
                TextView text2 = (TextView) dialog.findViewById(R.id.TextView02);
                text2.setText("Bomy François\nLebegue Clément\nLeblanc Alexandre\nPecqueux Nathan");
                TextView text3 = (TextView) dialog.findViewById(R.id.TextView03);
                text3.setText("Version 1.0,  02/02/2018");

                //l'image
                ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);
                img.setImageResource(R.drawable.logo_propos);

                dialog.show();
                break;
                //On se déconnecte de l'application
            case R.id.menuRecetteDéconnexion:
                Intent intent = new Intent(HistoryActivity.this,ConnectionActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    //gère les clics sur les boutons
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //permet d'afficher la suite de la liste
            case R.id.buttonNext2:
                printRecipes();
                break;
        }
    }

    //Récupération + affichage des recettes 
    private void printRecipes() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FOOD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            final ArrayList<Ingredient> ingredientList = new ArrayList<>();

                            JSONArray array = new JSONArray(response);
                            ArrayList<String> urls = new ArrayList<>();

                            // Get Recipe objects from data
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonIng = array.getJSONObject(i);
                                urls.add(jsonIng.getString("url"));
                            }
                            ArrayList<Recipe> recipesL = new ArrayList<>();

                            Recipe recipe = new Recipe();
                            try {
                                for (int i = 0; i < array.length(); i++) {
                                    Recipe.getHistoryList recipes = new Recipe.getHistoryList();
                                    recipes.execute(urls.get(i),"list");
                                    recipes.get();
                                    recipesL.add(recipe.resultRecipes.get(0));
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                            // Get data to display
                            //final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);
                            final ArrayList<Recipe> recipeList = recipesL;

                            // Create adapter
                            RecipeAdapter adapter = new RecipeAdapter(context, recipeList);

                            // Create list view
                            mListView = (ListView) findViewById(R.id.recipe_list_view);
                            mListView.setAdapter(adapter);

                            if(Integer.parseInt(index)!=15) {
                                index = String.valueOf(Integer.parseInt(index) + 5);
                            }else{
                                ViewGroup.LayoutParams params = next.getLayoutParams();
                                params.height = 0;
                                next.setLayoutParams(params);
                            }
                            scrollMyListViewToBottom();

                            // Set what happens when a list view item is clicked
                            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Recipe selectedRecipe = recipeList.get(position);

                                    Intent detailIntent = new Intent(context, RecipeActivity.class);
                                    detailIntent.putExtra("url", selectedRecipe.urlLink);
                                    detailIntent.putExtra("title", selectedRecipe.title);

                                    startActivity(detailIntent);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                String mail = getApplicationContext()
                        .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                        .getString("login", null);
                params.put("mail", mail);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void scrollMyListViewToBottom() {
        mListView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                mListView.setSelection(mListView.getCount()-7);
            }
        });
    }
}