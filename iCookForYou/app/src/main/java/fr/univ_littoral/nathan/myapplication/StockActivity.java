package fr.univ_littoral.nathan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

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

import fr.univ_littoral.nathan.myapplication.sampledata.Ingredient;
import fr.univ_littoral.nathan.myapplication.sampledata.IngredientAdapter;
import fr.univ_littoral.nathan.myapplication.sampledata.Recipe;
import fr.univ_littoral.nathan.myapplication.sampledata.RecipeAdapter;

public class StockActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private static final String URL_FOOD = "http://51.255.164.53/php/selectFoodByUser.php";
    Context context;
    Button addFood;
    Button modifyStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        context = this;

        addFood = (Button) findViewById(R.id.addingredient);
        addFood.setOnClickListener(this);
        modifyStock = (Button) findViewById(R.id.modifIngredient);
        modifyStock.setOnClickListener(this);

        findFood();
    }


    private void findFood() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FOOD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            final ArrayList<Ingredient> ingredientList = new ArrayList<>();

                            JSONArray array = new JSONArray(response);

                            // Get Recipe objects from data
                            for (int i = 0; i < array.length(); i++) {
                                Ingredient ingredient = new Ingredient();

                                JSONObject jsonIng = array.getJSONObject(i);

                                ingredient.name = jsonIng.getString("nameFood");
                                ingredient.quantity = jsonIng.getString("quantity");
                                ingredient.unity = jsonIng.getString("nameUnit");

                                ingredientList.add(ingredient);

                            }
                            // Create adapter
                            IngredientAdapter adapter = new IngredientAdapter(context, ingredientList);


                            // Create list view
                            mListView = (ListView) findViewById(R.id.ingredients_list_view);
                            mListView.setAdapter(adapter);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addingredient:
                Intent addingredientactivity = new Intent(StockActivity.this, AddIngredientActivity.class);
                startActivityForResult(addingredientactivity, 1);
                break;
            case R.id.modifIngredient:
                Intent modifingredientactivity = new Intent(StockActivity.this, ModifyStockActivity.class);//Mettre l'activité de François
                startActivityForResult(modifingredientactivity, 1);
                break;
        }
    }
}
