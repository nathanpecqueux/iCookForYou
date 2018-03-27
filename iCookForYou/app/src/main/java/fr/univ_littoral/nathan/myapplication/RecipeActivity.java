package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import fr.univ_littoral.nathan.myapplication.sampledata.Recipe;
import fr.univ_littoral.nathan.myapplication.sampledata.RecipeAdapter;

public class RecipeActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewTitreRecette;
    TextView textViewNbPersonnes;
    TextView textViewDifficulte;
    TextView textViewTempsPrep;
    TextView textViewTotalEtape;
    ImageView imageRecipe;
    Button buttonRealiser;

    private static final String URL_HIST = "http://51.255.164.53/php/registerHistory.php";

    String title;
    String difficulty;
    String time;
    String servings;
    String imageUrl;
    String url;

    TableLayout tableLayoutIngredients;
    TableRow row; // création d'un élément : ligne
    TextView tv1, tv2;
    List<String> col1 = new ArrayList<String>();
    List<String> col2 = new ArrayList<String>();
    List<String> step = new ArrayList<String>();


    List<String> ingredientLines = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intentReceive=getIntent();
        url=intentReceive.getStringExtra("url");
        title=intentReceive.getStringExtra("title");

        Recipe r = new Recipe(title,url);

        Recipe.getRecipe recipes = new Recipe.getRecipe();

        recipes.execute(title, url, "all");

        try {
            recipes.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        difficulty=r.resultRecipes.get(0).difficulty;
        time=r.resultRecipes.get(0).time;
        servings=r.resultRecipes.get(0).servings;
        imageUrl=r.resultRecipes.get(0).imageUrl;
        ingredientLines=r.resultRecipes.get(0).ingredientLines;
        step=r.resultRecipes.get(0).step;

        imageRecipe=(ImageView) findViewById(R.id.imageRecipe);

        if(imageUrl.equals("")){

        }else{
            Picasso.with(this).load(imageUrl).resize(450,300).into(imageRecipe);
        }

        textViewTitreRecette = (TextView) findViewById(R.id.textViewTitreRecette);
        textViewNbPersonnes = (TextView) findViewById(R.id.textViewNbPersonnes);
        textViewDifficulte = (TextView) findViewById(R.id.textViewDifficulte);
        textViewTempsPrep = (TextView) findViewById(R.id.textViewTempsPrep);
        textViewTotalEtape = (TextView) findViewById(R.id.textViewTotalEtape);
        tableLayoutIngredients = (TableLayout) findViewById(R.id.TableLayoutIngredients);
        buttonRealiser=(Button) findViewById(R.id.buttonRealiser);

        buttonRealiser.setOnClickListener(this);
        codeBouchon();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_recette, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRecetteAccueil:
                Intent intentAccueil = new Intent(RecipeActivity.this, HomeActivity.class);
                startActivity(intentAccueil);
                break;
            case R.id.menuRecetteProfil:
                Intent intentProfil = new Intent(RecipeActivity.this, ProfileActivity.class);
                startActivity(intentProfil);
                break;
            case R.id.menuRecetteStock:
                Intent intentStock = new Intent(RecipeActivity.this, StockActivity.class);
                startActivity(intentStock);
                break;
            case R.id.menuRecetteAPropos:
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
            case R.id.menuRecetteDéconnexion:
                Intent intent = new Intent(RecipeActivity.this,ConnectionActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    public void codeBouchon() {
        textViewTitreRecette.setText(title);
        textViewNbPersonnes.setHint("Personnes :   "+servings);
        textViewDifficulte.setHint("Difficulté :   "+difficulty);
        textViewTempsPrep.setHint("Temps :   "+time);

        separateIngredients(ingredientLines);
        int compteur = ingredientLines.size();
        int i;

        //Remplissage tableaux ingrédients
        for (i = 0; i < col2.size(); i++) {
            row = new TableRow(this); // création d'une nouvelle ligne

            tv1 = new TextView(this); // création cellule
            tv1.setTextColor(Color.WHITE);
            tv1.setTextSize(17);
            tv1.setText(col1.get(i)); // ajout du texte
            tv1.setGravity(Gravity.CENTER); // centrage dans la cellule
            // adaptation de la largeur de colonne à l'écran :
            tv1.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            // idem 2ème cellule
            tv2 = new TextView(this);
            tv2.setTextColor(Color.WHITE);
            tv2.setTextSize(17);
            tv2.setText(col2.get(i));
            tv2.setGravity(Gravity.CENTER);
            tv2.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            // ajout des cellules à la ligne
            row.addView(tv1);
            row.addView(tv2);

            // ajout de la ligne au tableau
            tableLayoutIngredients.addView(row);
        }
        if (compteur % 2 != 0) {
            row = new TableRow(this); // création d'une nouvelle ligne

            tv1 = new TextView(this); // création cellule
            tv1.setTextColor(Color.WHITE);
            tv1.setTextSize(17);
            tv1.setText(col1.get(i)); // ajout du texte
            tv1.setGravity(Gravity.CENTER); // centrage dans la cellule
            // adaptation de la largeur de colonne à l'écran :
            tv1.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            tv2 = new TextView(this);
            tv2.setText(" ");
            tv2.setGravity(Gravity.CENTER); // centrage dans la cellule
            // adaptation de la largeur de colonne à l'écran :
            tv2.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            row.addView(tv1);
            row.addView(tv2);
            tableLayoutIngredients.addView(row);
        }

        affichageEtapes();
    }

    public void affichageEtapes() {
        String totalEtape = new String();
        int temp;
        for (int i = 1; i < step.size(); i++) {
            temp = i;
            totalEtape += "Etape " + temp + " :\n";
            totalEtape += "       " + step.get(i) + "\n\n";
        }
        textViewTotalEtape.setText(totalEtape);
    }

    public void separateIngredients(List<String> ingredients) {
        for (int j = 0; j < ingredients.size(); j++) {
            if (j % 2 == 0) {
                col1.add(ingredients.get(j));
            } else {
                col2.add(ingredients.get(j));
            }
        }
    }

    @Override
    public void onClick(View view) {
        saveHistory();
        Intent intentStock=new Intent(RecipeActivity.this,StockActivity.class);
        startActivity(intentStock);
    }

    public void saveHistory() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_HIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {}
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                String mail = getApplicationContext()
                        .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                        .getString("login", null);
                System.out.println("mail : "+mail+"\nurl : "+url+"\nhist : "+URL_HIST);
                params.put("mail", mail);
                params.put("url", url);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(RecipeActivity.this);
        requestQueue.add(stringRequest);
    }
}