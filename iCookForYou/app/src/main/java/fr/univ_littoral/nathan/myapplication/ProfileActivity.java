package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.List;
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

    Button buttonModifierProfil = null;

    TextView textViewAllergy = null;

    //Tableau répertoriant les allergies de l'utilisateur
    ArrayList<String> userAllergy = new ArrayList<String>();
    int nombreAllergy = 3;
    boolean[] checkedAllergies = new boolean[nombreAllergy];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        //userAllergy.add("Allergie 1");
        //checkedAllergies[0] = true;
        userA = new ArrayList<String>();

        findAllergy();

        //checkboxVegan.setChecked(true);
        //refreshProfil();

        findUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuProfilAccueil:
                Intent intentAccueil = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intentAccueil);
                break;
            case R.id.menuProfilStock:
                /*Intent intentStock = new Intent(ProfileActivity.this, StockActivity.class);
                startActivity(intentStock);
                break;*/
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
                /*AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("A propos de ...")
                        .setMessage("ICookForYou\n\nApplication créée par :\n\nBomy François\nLebegue Clément\nLeblanc Alexandre\nPecqueux Nathan");
                builder.show();*/
                break;
            case R.id.menuProfilQuitter:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
        return true;
    }
//
//    public void refreshProfil() {
//        setTextViewUserAllergy();
//    }

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

        intentModifyProfileActivity.putExtra("Allergy", userAllergy);
        intentModifyProfileActivity.putExtra("AllergyId", checkedAllergies);
        startActivityForResult(intentModifyProfileActivity, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            textViewNom.setText(data.getStringExtra("NomModif"));
            textViewPrenom.setText(data.getStringExtra("PrenomModif"));
            textViewMdp.setText(data.getStringExtra("MdpModif"));
            textViewMail.setText(data.getStringExtra("MailModif"));

            boolean temp = (data.getBooleanExtra("VeganModif", false));
            if (temp == true) {
                checkboxVegan.setChecked(true);
            } else {
                checkboxVegan.setChecked(false);
            }
            temp = (data.getBooleanExtra("VegetarianModif", false));
            if (temp == true) {
                checkboxVegetarian.setChecked(true);
            } else {
                checkboxVegetarian.setChecked(false);
            }
            temp = (data.getBooleanExtra("NoGlutenModif", false));
            if (temp == true) {
                checkboxNoGluten.setChecked(true);
            } else {
                checkboxNoGluten.setChecked(false);
            }
            userAllergy = data.getStringArrayListExtra("AllergyModif");

            setTextViewUserAllergy();
        }
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
                params.put("mail",mail);
                return params;
            }

        };

        Volley.newRequestQueue(this).add(stringRequest);
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
                params.put("mail",mail);
                return params;
            }

        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

}
