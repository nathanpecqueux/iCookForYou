package fr.univ_littoral.nathan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class AddIngredientActivity extends AppCompatActivity implements View.OnClickListener {

    Button cancel;
    Button validateFood;
    Button selectFood;
    TextView selectedIngredient;
    TextView unit;
    EditText quantity;
    private static final String URL_UNIT = "http://51.255.164.53/php/selectUnitByFood.php";
    private static final String URL_SAVESTOCK = "http://51.255.164.53/php/registerStock.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        selectedIngredient = (TextView) findViewById(R.id.ingredientTextAdd);
        unit = (TextView) findViewById(R.id.unityTextAdd);

        selectFood = (Button) findViewById(R.id.selectIngredient);
        selectFood.setOnClickListener(this);

        quantity = (EditText) findViewById(R.id.quantityIngredientText);


        cancel = (Button) findViewById(R.id.buttonCancelFood);
        cancel.setOnClickListener(this);

        validateFood = (Button) findViewById(R.id.buttonValidateFood);
        validateFood.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.getStringExtra("id") == null) {
            selectedIngredient.setText("");
        } else {
            selectedIngredient.setText(intent.getStringExtra("id").toString());
        }

        findUnit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectIngredient:
                Intent registerActivity = new Intent(AddIngredientActivity.this, SelectIngredientActivity.class);
                startActivityForResult(registerActivity, 1);
                break;
            case R.id.buttonCancelFood:
                Intent cancelintent = new Intent();
                setResult(1, cancelintent);
                finish();
                break;
            case R.id.buttonValidateFood:
                saveStock();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Intent validatefoodactivity = new Intent(AddIngredientActivity.this, StockActivity.class);
                startActivity(validatefoodactivity);
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

    public void saveStock() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVESTOCK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                String mail = getApplicationContext()
                        .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                        .getString("login", null);

                // Paramètres de l'utilisateurs pour la première requête !
                params.put("nameFood", String.valueOf(selectedIngredient.getText()));
                params.put("quantityFood", String.valueOf(quantity.getText()));
                params.put("mail", String.valueOf(mail));

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(AddIngredientActivity.this);
        requestQueue.add(stringRequest);
    }
}
