package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.univ_littoral.nathan.myapplication.sampledata.Recipe;
import fr.univ_littoral.nathan.myapplication.sampledata.RecipeAdapter;

public class RecipeActivity extends AppCompatActivity {

    TextView textViewTitreRecette;
    TextView textViewNbPersonnes;
    TextView textViewDifficulte;
    TextView textViewTempsPrep;
    TextView textViewTotalEtape;
    ImageView imageRecipe;

    String title;
    String difficulty;
    String time;
    String servings;
    String imageUrl;

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

        final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);

        RecipeAdapter adapter = new RecipeAdapter(this, recipeList);

        Intent intentReceive=getIntent();
        title=intentReceive.getStringExtra("title");
        difficulty=intentReceive.getStringExtra("difficulty");
        time=intentReceive.getStringExtra("time");
        servings=intentReceive.getStringExtra("servings");
        imageUrl=intentReceive.getStringExtra("imageUrl");
        ingredientLines=intentReceive.getStringArrayListExtra("ingredientLines");
        step=intentReceive.getStringArrayListExtra("step");

        imageRecipe=(ImageView) findViewById(R.id.imageRecipe);
        Picasso.with(this).load(imageUrl).resize(300,200).into(imageRecipe);


        textViewTitreRecette = (TextView) findViewById(R.id.textViewTitreRecette);
        textViewNbPersonnes = (TextView) findViewById(R.id.textViewNbPersonnes);
        textViewDifficulte = (TextView) findViewById(R.id.textViewDifficulte);
        textViewTempsPrep = (TextView) findViewById(R.id.textViewTempsPrep);
        textViewTotalEtape = (TextView) findViewById(R.id.textViewTotalEtape);
        tableLayoutIngredients = (TableLayout) findViewById(R.id.TableLayoutIngredients);

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
                /*Intent intentStock = new Intent(RecipeActivity.this, StockActivity.class);
                startActivity(intentStock);
                break;*/
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
            case R.id.menuRecetteQuitter:
                Intent intentQuitter = new Intent(Intent.ACTION_MAIN);
                intentQuitter.addCategory(Intent.CATEGORY_HOME);
                intentQuitter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentQuitter);
                break;
        }
        return true;
    }

    public void codeBouchon() {
        textViewTitreRecette.setText(title);
        textViewNbPersonnes.setHint("Personnes :"+servings);
        textViewDifficulte.setHint("Difficulté :"+difficulty);
        textViewTempsPrep.setHint("Temps :"+time+"min");

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
        for (int i = 0; i < step.size(); i++) {
            temp = i + 1;
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
}