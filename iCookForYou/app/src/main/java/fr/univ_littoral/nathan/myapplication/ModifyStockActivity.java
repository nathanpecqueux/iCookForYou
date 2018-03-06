package fr.univ_littoral.nathan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import fr.univ_littoral.nathan.myapplication.sampledata.ModifyAdapter;

public class ModifyStockActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView mListView;
    private Button valider;
    private Button annuler;
    private ImageButton poubelle;
    private static final String URL_FOOD = "http://51.255.164.53/php/selectFoodByUser.php";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_stock);
        context = this;

        valider=(Button) findViewById(R.id.buttonValider);
        annuler=(Button) findViewById(R.id.buttonAnnuler);
        poubelle=(ImageButton) findViewById(R.id.imageButtonPoubelle);

        valider.setOnClickListener(this);
        annuler.setOnClickListener(this);
        poubelle.setOnClickListener(this);

        // Get data to display
        final ArrayList<Ingredient> ingredientList = Ingredient.getIngredientsFromFile("ingredients.json", this);

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
                            System.out.println(array);

                            // Get Recipe objects from data
                            for(int i = 0; i < array.length(); i++) {
                                Ingredient ingredient = new Ingredient();

                                JSONObject jsonIng = array.getJSONObject(i);

                                ingredient.name = jsonIng.getString("nameFood");
                                ingredient.quantity = jsonIng.getString("quantity");
                                ingredient.unity = jsonIng.getString("nameUnit");

                                ingredientList.add(ingredient);

                            }
                            // Create adapter
                            ModifyAdapter adapter = new ModifyAdapter(context, ingredientList);


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
                System.out.println(mail);
                params.put("mail", mail);
                return params;
            }

        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonValider:
                /*





                    //REQUETE BDD POUR CHANGER LES VALEURS





                */
                Intent intentOk = new Intent( ModifyStockActivity.this, StockActivity.class);
                startActivity(intentOk);
                break;
            case R.id.buttonAnnuler:
                Intent intentAnnuler = new Intent( ModifyStockActivity.this, StockActivity.class);
                startActivity(intentAnnuler);
                break;
            case R.id.imageButtonPoubelle:
                /*





                    //ICI TU SUPPRIMES DANS LA BDD ET TU ACTUALISES OK ?!
                    //EST-CE-QUE JE ME SUIS BIEN FAIS COMPRENDRE OU TU VEUX UN DESSIN PEUT-ETRE ?





                 */
                break;
        }
    }
}
