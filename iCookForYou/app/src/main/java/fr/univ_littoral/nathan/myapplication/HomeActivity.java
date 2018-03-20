package fr.univ_littoral.nathan.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
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


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private ImageButton imageButtonPlats;
    private LinearLayout onec;
    private View view;
    private ProgressBar loading;
    Context context;

    private static final String URL_FOOD = "http://51.255.164.53/php/selectFoodByUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loading = (ProgressBar) findViewById(R.id.progressBar2);
        load(view);

        onec = (LinearLayout) findViewById(R.id.onec);
        onec.setOnClickListener(this);
        view = (View) findViewById(R.id.view);
        context = this;

        Boolean onecub = getApplicationContext()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                .getBoolean("onecub", false);

        if (onecub == true) {
            ViewGroup.LayoutParams params = onec.getLayoutParams();
            params.height = 0;
            onec.setLayoutParams(params);
            view.setVisibility(View.INVISIBLE);
        } else {
            ViewGroup.LayoutParams params = onec.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            onec.setLayoutParams(params);
        }

        printRecipes();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_liste_recette, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuListProfil:
                Intent intentProfil = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intentProfil);
                break;
            case R.id.menuListStock:
                Intent intentStock = new Intent(HomeActivity.this, StockActivity.class);
                startActivity(intentStock);
                break;
            case R.id.menuListAPropos:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.layout_propos);
                dialog.setCancelable(true);
                //there are a lot of settings, for dialog, check them all out!
                dialog.setTitle("A propos de l'application");
                //set up text
                TextView text = (TextView) dialog.findViewById(R.id.TextView01);
                text.setText("Application créée par :");
                TextView text2 = (TextView) dialog.findViewById(R.id.TextView02);
                text2.setText("Bomy François\nLebegue Clément\nLeblanc Alexandre\nPecqueux Nathan");
                TextView text3 = (TextView) dialog.findViewById(R.id.TextView03);
                text3.setText("Version 1.0,  02/02/2018");

                //set up image view
                ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);
                img.setImageResource(R.drawable.logo_propos);

                dialog.show();
                break;
            case R.id.menuListQuit:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent intentVotreStock = new Intent(HomeActivity.this, OneCubActivity.class);
        startActivity(intentVotreStock);
    }

    public void load(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if(loading.getProgress()==100){
                            loading.setProgress(0);
                        }
                        Thread.sleep(50);
                        loading.incrementProgressBy(10);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void printRecipes() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FOOD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            final ArrayList<Ingredient> ingredientList = new ArrayList<>();

                            JSONArray array = new JSONArray(response);
                            String ingrédients = "";

                            // Get Recipe objects from data
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonIng = array.getJSONObject(i);
                                ingrédients += jsonIng.getString("nameFood") + " ";
                            }

                            Recipe recipe = new Recipe();

                            Recipe.getRecipes recipes = new Recipe.getRecipes();

                            recipes.execute(ingrédients, "list");

                            try {
                                ViewGroup.LayoutParams params = loading.getLayoutParams();
                                params.height = 0;
                                loading.setLayoutParams(params);
                                recipes.get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }

                            // Get data to display
                            //final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);
                            final ArrayList<Recipe> recipeList = recipe.resultRecipes;

                            // Create adapter
                            RecipeAdapter adapter = new RecipeAdapter(context, recipeList);

                            // Create list view
                            mListView = (ListView) findViewById(R.id.recipe_list_view);
                            mListView.setAdapter(adapter);

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
}