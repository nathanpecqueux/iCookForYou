package fr.univ_littoral.nathan.myapplication;

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

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    TextView textViewTitreRecette;
    TextView textViewNbPersonnes;
    TextView textViewDifficulte;
    TextView textViewTempsPrep;
    TextView textViewTotalEtape;

    TableLayout tableLayoutIngredients;
    TableRow row; // création d'un élément : ligne
    TextView tv1, tv2;
    List<String> col1 = new ArrayList<String>();
    List<String> col2 = new ArrayList<String>();
    List<String> etapes = new ArrayList<String>();

    private String[] ingredients = new String[]{
            "Pâtes", "Jambon", "Sel"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
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
                Intent intentAccueil = new Intent(RecipeActivity.this, ConnectionActivity.class);
                startActivity(intentAccueil);
                break;
            case R.id.menuRecetteProfil:
                Intent intentProfil = new Intent(RecipeActivity.this, ProfileActivity.class);
                startActivity(intentProfil);
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
                /*AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("A propos de ...")
                        .setMessage("ICookForYou\n\nApplication créée par :\n\nBomy François\nLebegue Clément\nLeblanc Alexandre\nPecqueux Nathan");
                builder.show();*/
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
        textViewTitreRecette.setText("Pâtes aux jambon");
        textViewNbPersonnes.setHint("Personnes : 4");
        textViewDifficulte.setHint("Difficulté : 5*");
        textViewTempsPrep.setHint("Temps : 10min");

        separateIngredients(ingredients);
        int compteur = ingredients.length;
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

        //Etapes
        etapes.add("Faites bouillir de l'eau salé");
        etapes.add("Une fois l'eau portée à ébullition, y plonger les pâtes");
        etapes.add("Attendre le temps indiqué sur la boite, pendant ce temps, couper le jambon en petit morceau");
        etapes.add("Une fois le temps écoulé, égoutter les pâtes et servir chaud");

        affichageEtapes();
    }

    public void affichageEtapes() {
        String totalEtape = new String();
        int temp;
        for (int i = 0; i < etapes.size(); i++) {
            temp = i + 1;
            totalEtape += "Etape " + temp + " :\n";
            totalEtape += "       " + etapes.get(i) + "\n\n";
        }
        textViewTotalEtape.setText(totalEtape);
    }

    public void separateIngredients(String[] ingredients) {
        for (int j = 0; j < ingredients.length; j++) {
            if (j % 2 == 0) {
                col1.add(ingredients[j]);
            } else {
                col2.add(ingredients[j]);
            }
        }
    }
}