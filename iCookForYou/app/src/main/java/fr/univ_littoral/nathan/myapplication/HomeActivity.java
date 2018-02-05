package fr.univ_littoral.nathan.myapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.univ_littoral.nathan.myapplication.sampledata.Recipe;

import static fr.univ_littoral.nathan.myapplication.sampledata.Recipe.getRecipesFromFile;

public class HomeActivity extends AppCompatActivity{

    private ListView mRecipeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecipeListView = (ListView) findViewById(R.id.recipe_list_view);
        final ArrayList<Recipe> recipeList = getRecipesFromFile("recipes.json", this);
        String[] listItems = new String[recipeList.size()];
        for(int i=0;i<recipeList.size();i++){
            Recipe recipe = recipeList.get(i);
            listItems[i] = recipe.title;
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1, listItems);
        mRecipeListView.setAdapter(adapter);
    }
}
