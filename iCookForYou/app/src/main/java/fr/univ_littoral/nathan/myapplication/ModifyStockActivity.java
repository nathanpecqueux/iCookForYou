package fr.univ_littoral.nathan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

public class ModifyStockActivity extends AppCompatActivity implements View.OnClickListener {

    Button cancel;
    Button validateFoodModification;
    Button deleteFood;
    TextView unit;
    TextView qtt;
    TextView selectedIngredient;
    private static final String URL_UNIT = "http://51.255.164.53/php/selectUnitByFood.php";
    private static final String URL_QUANTITY = "http://51.255.164.53/php/selectQuantityByFood.php";
    private static final String URL_UPDATEQTT = "http://51.255.164.53/php/updateQuantityFood.php";
    private static final String URL_DELETE = "http://51.255.164.53/php/deleteQuantityFood.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_stock);

        selectedIngredient = (TextView) findViewById(R.id.ingredientTextModify);
        deleteFood = (Button) findViewById(R.id.buttonDeleteFood);
        unit = (TextView) findViewById(R.id.unityTextModify);
        qtt = (TextView) findViewById(R.id.quantityIngredientTextModify);
        deleteFood.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.buttonCancelFoodModify);

        cancel.setOnClickListener(this);
        validateFoodModification = (Button) findViewById(R.id.buttonValidateFoodModify);
        validateFoodModification.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.getStringExtra("id") == null) {
            selectedIngredient.setText("");
        } else {
            selectedIngredient.setText(intent.getStringExtra("id").toString());
        }

        findUnit();

        findQuantity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDeleteFood:
                deleteFood();
                Intent deletefoodactivity = new Intent(ModifyStockActivity.this, StockActivity.class);
                startActivityForResult(deletefoodactivity, 1);
                break;
            case R.id.buttonCancelFoodModify:
                Intent cancelintent = new Intent();
                setResult(1, cancelintent);
                finish();
                break;
            case R.id.buttonValidateFoodModify:
                updateQuantity();
                Intent validatefoodactivity = new Intent(ModifyStockActivity.this, StockActivity.class);
                startActivityForResult(validatefoodactivity, 1);
                break;
        }
    }

    private void findUnit() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UNIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            JSONObject jsonIng = array.getJSONObject(0);

                            unit.setText(jsonIng.getString("name"));
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
                String nameFood = (String) selectedIngredient.getText();
                params.put("nameFood", nameFood);
                return params;
            }

        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void findQuantity() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_QUANTITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            JSONObject jsonIng = array.getJSONObject(0);

                            qtt.setText(jsonIng.getString("name"));
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
                String nameFood = (String) selectedIngredient.getText();
                String mail = getApplicationContext()
                        .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                        .getString("login", null);
                params.put("mail", mail);
                params.put("nameFood", nameFood);
                return params;
            }

        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void updateQuantity() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATEQTT,
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

                // Paramètres de l'utilisateurs pour la première requête !
                params.put("quantity", String.valueOf(qtt.getText()));
                params.put("mail", mail);
                params.put("nameFood", String.valueOf(selectedIngredient.getText()));

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(ModifyStockActivity.this);
        requestQueue.add(stringRequest);
    }

    public void deleteFood() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DELETE,
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

                // Paramètres de l'utilisateurs pour la première requête !
                params.put("mail", mail);
                params.put("nameFood", (String)selectedIngredient.getText());

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(ModifyStockActivity.this);
        requestQueue.add(stringRequest);
    }
}
