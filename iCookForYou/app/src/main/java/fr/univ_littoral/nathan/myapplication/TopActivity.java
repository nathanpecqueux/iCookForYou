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

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import fr.univ_littoral.nathan.myapplication.sampledata.Recipe;
import fr.univ_littoral.nathan.myapplication.sampledata.RecipeAdapter;


public class TopActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private Button next;
    private ImageView onec;
    private String index = "5";
    private Button hist;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        index = "5";
        context = this;

        next = (Button) findViewById(R.id.buttonNext2);
        next.setOnClickListener(this);

        printRecipes();

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
                Intent intentAccueil = new Intent(TopActivity.this, HomeActivity.class);
                startActivity(intentAccueil);
                break;
            case R.id.menuRecetteProfil:
                Intent intentProfil = new Intent(TopActivity.this, ProfileActivity.class);
                startActivity(intentProfil);
                break;
            case R.id.menuRecetteStock:
                Intent intentStock = new Intent(TopActivity.this, StockActivity.class);
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
            case R.id.menuRecetteQuitter:
                Intent intentQuitter = new Intent(Intent.ACTION_MAIN);
                intentQuitter.addCategory(Intent.CATEGORY_HOME);
                intentQuitter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentQuitter);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonNext2:
                printRecipes();
                break;
        }
    }

    private void printRecipes() {

        Recipe recipe = new Recipe();

        Recipe.getTop recipes = new Recipe.getTop();

        recipes.execute(index, "list");

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

        for (Recipe r : recipe.resultRecipes

             ) {
            System.out.println(r);
        }

        // Create adapter
        RecipeAdapter adapter = new RecipeAdapter(context, recipeList);

        // Create list view
        mListView = (ListView) findViewById(R.id.recipe_list_view);
        mListView.setAdapter(adapter);

        if (Integer.parseInt(index) != 15) {
            index = String.valueOf(Integer.parseInt(index) + 5);
        } else {
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
    }

    private void scrollMyListViewToBottom() {
        mListView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                mListView.setSelection(mListView.getCount() - 7);
            }
        });
    }
}