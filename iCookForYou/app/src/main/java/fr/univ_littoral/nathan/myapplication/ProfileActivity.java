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
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables xml
    TextView textViewNom = null;
    TextView textViewPrenom = null;
    TextView textViewMdp = null;
    TextView textViewMail = null;
    ArrayList<String> userA;

    android.support.v7.widget.AppCompatCheckBox checkboxVegan = null;
    android.support.v7.widget.AppCompatCheckBox checkboxVegetarian = null;
    android.support.v7.widget.AppCompatCheckBox checkboxNoGluten = null;

    private static final String URL_USERS = "http://51.255.164.53/php/selectUserById.php";
    private static final String URL_ALLERGY = "http://51.255.164.53/php/selectNameAllergyUser.php";
    private static final String URL_DIET = "http://51.255.164.53/php/selectIdDietUser.php";

    Button buttonModifierProfil = null;

    TextView textViewAllergy = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //On lie nos variables globales avec les id du layout xml
        textViewNom = (TextView) findViewById(R.id.textViewNom);
        textViewPrenom = (TextView) findViewById(R.id.textViewPrenom);
        textViewMdp = (TextView) findViewById(R.id.textViewMdp);
        textViewMail = (TextView) findViewById(R.id.textViewMail);

        checkboxVegan = (android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxVegan);
        checkboxVegetarian = (android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxVegetarian);
        checkboxNoGluten = (android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxNoGluten);

        buttonModifierProfil = (Button) findViewById(R.id.buttonModifierProfil);

        textViewAllergy = (TextView) findViewById(R.id.textViewAllergy);

        buttonModifierProfil.setOnClickListener(this);

        userA = new ArrayList<String>();

        findUser();
        findAllergy();
        findDiet();

    }

    //créer le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profil, menu);
        return true;
    }

    //gères les clics sur le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //renvoie à la page de recette
            case R.id.menuProfilAccueil:
                Intent intentAccueil = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intentAccueil);
                break;
                //renvoie page stock
            case R.id.menuProfilStock:
                Intent intentStock = new Intent(ProfileActivity.this, StockActivity.class);
                startActivity(intentStock);
                break;
                //pop-up informations sur l'appli
            case R.id.menuProfilAPropos:
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
                //deconnexion
            case R.id.menuProfilDéconnexion:
                Intent intent = new Intent(ProfileActivity.this,ConnectionActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    public void setTextViewUserAllergy() {
        String temp = "";
        for (int i = 0; i < userA.size(); i++) {
            if (i == userA.size() - 1) {
                temp += userA.get(i);
            } else {
                temp += userA.get(i) + ", ";
            }
        }
        textViewAllergy.setText(temp);
    }

    //gère clics sur le bouton, redirection vers page de modification de profil avec envoie des données de l'utilisateur
    @Override
    public void onClick(View view) {
        Intent intentModifyProfileActivity = new Intent(ProfileActivity.this, ModifyProfileActivity.class);
        intentModifyProfileActivity.putExtra("Nom", textViewNom.getText().toString());
        intentModifyProfileActivity.putExtra("Prenom", textViewPrenom.getText().toString());
        intentModifyProfileActivity.putExtra("Mdp", textViewMdp.getText().toString());
        intentModifyProfileActivity.putExtra("Mail", textViewMail.getText().toString());

        intentModifyProfileActivity.putExtra("Vegan", checkboxVegan.isChecked());
        intentModifyProfileActivity.putExtra("Vegetarian", checkboxVegetarian.isChecked());
        intentModifyProfileActivity.putExtra("NoGluten", checkboxNoGluten.isChecked());

        intentModifyProfileActivity.putExtra("Allergy", userA);
        startActivity(intentModifyProfileActivity);
    }

    private void findUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_USERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            JSONObject user = array.getJSONObject(0);

                            User u = new User(
                                    user.getString("lastName"),
                                    user.getString("firstName"),
                                    user.getString("mail"),
                                    user.getString("password")
                            );

                            textViewNom.setText(u.getLastName());
                            textViewPrenom.setText(u.getFirstName());
                            textViewMail.setText(u.getMail());
                            textViewMdp.setText(u.getPassword());
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

    private void findAllergy() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ALLERGY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject allergy = array.getJSONObject(i);
                                userA.add(allergy.getString("name"));
                            }
                            setTextViewUserAllergy();
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

    private void findDiet() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DIET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject diet = array.getJSONObject(i);
                                int id = diet.getInt("idDiet");
                                if (id == 1) {
                                    checkboxVegan.setChecked(true);
                                } else if (id == 2) {
                                    checkboxVegetarian.setChecked(true);
                                } else if (id == 3) {
                                    checkboxNoGluten.setChecked(true);
                                }
                            }
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

}
