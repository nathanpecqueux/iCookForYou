package fr.univ_littoral.nathan.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.plus.model.people.Person;

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
    private Button next;
    private ImageView onec;
    private String index = "5";
    private Button hist;
    private Button top;
    private Button buttonPlacard;
    private Switch buttonDiet;
    private LinearLayout layoutVide;
    Context context;

    //ProgressBar
    private  ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private int progressBarStatusB = 0;
    private Handler progressBarbHandler = new Handler();
    private long fileSize = 0;

    private static final String URL_FOOD = "http://51.255.164.53/php/selectFoodByUser.php";
    private static final String URL_DIET = "http://51.255.164.53/php/selectIdDietUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        index="5";

        next = (Button) findViewById(R.id.buttonNext);
        next.setOnClickListener(this);
        hist = (Button) findViewById(R.id.buttonHistory);
        hist.setOnClickListener(this);
        top = (Button) findViewById(R.id.buttonPop);
        top.setOnClickListener(this);
        buttonDiet = (Switch) findViewById(R.id.buttonDiet);
        buttonDiet.setOnClickListener(this);
        buttonPlacard = (Button) findViewById(R.id.buttonPlacard);
        buttonPlacard.setOnClickListener(this);
        onec = (ImageView) findViewById(R.id.onecub);
        onec.setOnClickListener(this);
        layoutVide = (LinearLayout) findViewById(R.id.layoutVide);

        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Chargement de vos recettes");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        progressBarStatus = 0;
        progressBarStatusB = 0;

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {
                    progressBarStatus = downloadFile();

                    progressBarbHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }

                if (progressBarStatusB == 100) {
                    progressBar.dismiss();
                }
            }
        }).start();

        context = this;

        Boolean onecub = getApplicationContext()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                .getBoolean("onecub", false);

        if (onecub == true) {
            ViewGroup.LayoutParams params = onec.getLayoutParams();
            params.width = 0;
            onec.setLayoutParams(params);
        } else {
            ViewGroup.LayoutParams params = onec.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            onec.setLayoutParams(params);
        }
        System.out.println("create home");

        findDiet();
    }

    public int downloadFile() {
        while (fileSize <= 1000000) {
            fileSize++;

            if (fileSize == 100000) {
                return 10;
            }else if (fileSize == 200000) {
                return 20;
            }else if (fileSize == 300000) {
                return 30;
            }else if (fileSize == 400000) {
                return 40;
            }else if (fileSize == 500000) {
                return 50;
            }else if (fileSize == 700000) {
                return 70;
            }else if (fileSize == 800000) {
                return 80;
            }else if (fileSize == 900000) {
                return 90;
            }
        }
        if(progressBarStatusB==100){
            return 100;
        }
        return 99;
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
            case R.id.menuListDéconnexion:
                Intent intent = new Intent(HomeActivity.this,ConnectionActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.onecub:
                Intent intentVotreStock = new Intent(HomeActivity.this, OneCubActivity.class);
                startActivity(intentVotreStock);
                break;
            case R.id.buttonNext:
                printRecipes();
                break;
            case R.id.buttonHistory:
                Intent history = new Intent(HomeActivity.this, HistoryActivity.class);
                startActivity(history);
                break;
            case R.id.buttonPop:
                Intent top = new Intent(HomeActivity.this, TopActivity.class);
                startActivity(top);
                break;
            case R.id.buttonDiet:
                findDiet();
                break;
            case R.id.buttonPlacard:
                Intent intentStock = new Intent(HomeActivity.this, StockActivity.class);
                startActivity(intentStock);
                break;
        }
    }

    private void printRecipes() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FOOD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            String[] diets = String.valueOf(buttonDiet.getText()).split("&");
                            for (int i=0;i<diets.length;i++) {
                                diets[i] = diets[i].replace(" ","").toLowerCase().replace("é","e");
                            }

                            final ArrayList<Ingredient> ingredientList = new ArrayList<>();

                            JSONArray array = new JSONArray(response);
                            String ingrédients = "";
                            if(array.length()==0) {
                                ViewGroup.LayoutParams params = layoutVide.getLayoutParams();
                                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                                layoutVide.setLayoutParams(params);
                            }else{
                                ViewGroup.LayoutParams params = layoutVide.getLayoutParams();
                                params.height = 0;
                                layoutVide.setLayoutParams(params);
                            }
                            if(buttonDiet.isChecked()) {
                                // Get Recipe objects from data
                                for (int i = 0; i < array.length(); i++) {
                                    int x = 0;
                                    JSONObject jsonIng = array.getJSONObject(i);
                                    for (String diet : diets) {
                                        if (diet.equals(jsonIng.getString("excluded"))) {
                                            x = 1;
                                        }
                                    }
                                    if (x == 0) {
                                        ingrédients += jsonIng.getString("nameFood") + " ";
                                    }
                                }
                            }else{
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jsonIng = array.getJSONObject(i);
                                    ingrédients += jsonIng.getString("nameFood") + " ";
                                }
                            }

                            System.out.println(ingrédients);

                            Recipe recipe = new Recipe();

                            Recipe.getRecipes recipes = new Recipe.getRecipes();

                            recipes.execute(ingrédients, "list", index);

                            try {
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
        progressBarStatusB=100;
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

    private void findDiet() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DIET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            buttonDiet.setText("");

                            if(array.length()==0) {
                                buttonDiet.setVisibility(View.INVISIBLE);
                            }
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject diet = array.getJSONObject(i);
                                int id = diet.getInt("idDiet");
                                if (id == 1) {
                                    buttonDiet.setText("Végan");
                                } else if (id == 2) {
                                    buttonDiet.setText("Végétarien");
                                } else if (id == 3) {
                                    if(buttonDiet.getText().equals("")){
                                        buttonDiet.setText("Sans gluten");
                                    }else{
                                        buttonDiet.setText(buttonDiet.getText()+" & Sans gluten");
                                    }
                                }
                            }

                            printRecipes();
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == 1) {
                System.out.println("coucou");
                findDiet();
            }
        }
    }

}