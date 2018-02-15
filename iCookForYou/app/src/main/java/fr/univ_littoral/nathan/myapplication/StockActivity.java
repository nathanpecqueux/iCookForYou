package fr.univ_littoral.nathan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import fr.univ_littoral.nathan.myapplication.sampledata.Ingredient;
import fr.univ_littoral.nathan.myapplication.sampledata.IngredientAdapter;
import fr.univ_littoral.nathan.myapplication.sampledata.Recipe;
import fr.univ_littoral.nathan.myapplication.sampledata.RecipeAdapter;

public class StockActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        final Context context = this;

        // Get data to display
        final ArrayList<Ingredient> ingredientList = Ingredient.getIngredientsFromFile("ingredients.json", this);

        // Create adapter
        IngredientAdapter adapter = new IngredientAdapter(this, ingredientList);

        // Create list view
        mListView = (ListView) findViewById(R.id.ingredients_list_view);
        mListView.setAdapter(adapter);

        // Set what happens when a list view item is clicked
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ingredient selectedIngredient = ingredientList.get(position);

                Intent detailIntent = new Intent(context, Recipe.class);
                detailIntent.putExtra("name", selectedIngredient.name);
                detailIntent.putExtra("quantity", selectedIngredient.quantity);
                detailIntent.putExtra("unity", selectedIngredient.unity);

                startActivity(detailIntent);
            }

        });
    }
}
