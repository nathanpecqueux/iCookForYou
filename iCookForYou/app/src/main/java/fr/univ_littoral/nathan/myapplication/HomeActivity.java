package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import fr.univ_littoral.nathan.myapplication.sampledata.Recipe;
import fr.univ_littoral.nathan.myapplication.sampledata.RecipeAdapter;


public class HomeActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Context context = this;

        // Get data to display
        final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);

        // Create adapter
        RecipeAdapter adapter = new RecipeAdapter(this, recipeList);

        // Create list view
        mListView = (ListView) findViewById(R.id.recipe_list_view);
        mListView.setAdapter(adapter);

        // Set what happens when a list view item is clicked
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe selectedRecipe = recipeList.get(position);

                Intent detailIntent = new Intent(context, RecipeActivity.class);
                detailIntent.putExtra("title", selectedRecipe.title);
                detailIntent.putExtra("servings", selectedRecipe.servings);
                detailIntent.putExtra("time", selectedRecipe.time);
                detailIntent.putExtra("difficulty", selectedRecipe.difficulty);
                detailIntent.putExtra("imageUrl", selectedRecipe.imageUrl);
                detailIntent.putExtra("ingredientLines", (ArrayList<String>)selectedRecipe.ingredientLines);
                detailIntent.putExtra("step", (ArrayList<String>)selectedRecipe.step);

                startActivity(detailIntent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_liste_recette,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuListProfil:
                Intent intentProfil = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intentProfil);
                break;
            case R.id.menuListStock:
                /*Intent intentStock = new Intent(HomeActivity.this, StockActivity.class);
                startActivity(intentStock);*/
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
                /*AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("A propos de ...")
                        .setMessage("ICookForYou\n\nApplication créée par :\n\nBomy François\nLebegue Clément\nLeblanc Alexandre\nPecqueux Nathan");
                builder.show();*/
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
}