package fr.univ_littoral.nathan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.univ_littoral.nathan.myapplication.sampledata.Ingredient;

public class SelectIngredientActivity extends AppCompatActivity {

    private EditText filterText;
    Context context;
    private static final String URL_FOOD = "http://51.255.164.53/php/selectFood.php";

    private ArrayAdapter<String> listAdapter;
    ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ingredient);

        context = this;

        filterText = (EditText)findViewById(R.id.editText);

        ListView itemList = (ListView)findViewById(R.id.listView);

        findFood();

        //Liste l"ingrédient séléctionné et l'envoie à la layout d'ajout
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectIngredientActivity.this, AddIngredientActivity.class);
                String entry = (String) parent.getItemAtPosition(position);
                intent.putExtra("id",entry);
                startActivity(intent);
            }
        });

        filterText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SelectIngredientActivity.this.listAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void findFood() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_FOOD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            final ArrayList<String> listViewAdapterContent = new ArrayList<>();
                            final ArrayList<Ingredient> ingredientList = new ArrayList<>();

                            JSONArray array = new JSONArray(response);

                            // Get Recipe objects from data
                            for (int i = 0; i < array.length(); i++) {
                                Ingredient ingredient = new Ingredient();

                                JSONObject jsonIng = array.getJSONObject(i);
                                ingredient.name = jsonIng.getString("name");
                                ingredientList.add(ingredient);
                            }

                            for (Ingredient i : ingredientList) {
                                listViewAdapterContent.add(i.getName());
                            }

                            listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1, listViewAdapterContent){
                                public View getView(int position, View convertView, ViewGroup parent){
                                    View view = super.getView(position, convertView, parent);
                                    TextView tv = (TextView) view.findViewById(android.R.id.text1);
                                    tv.setTextColor(Color.WHITE);
                                    return view;
                                }
                            };
                            itemList = (ListView) findViewById(R.id.listView);

                            itemList.setAdapter(listAdapter);
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
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
