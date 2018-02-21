package fr.univ_littoral.nathan.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class ModifyProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables pour Pop-up
    AlertDialog.Builder builder;
    AlertDialog ad;

    //Variables xml
    TextView editTextNom = null;
    TextView editTextPrenom = null;
    TextView editTextMdp = null;
    TextView editTextVerifMdp = null;
    TextView editTextMail = null;

    android.support.v7.widget.AppCompatCheckBox checkboxVegan = null;
    android.support.v7.widget.AppCompatCheckBox checkboxVegetarian = null;
    android.support.v7.widget.AppCompatCheckBox checkboxNoGluten = null;
    String mail="";

    private static final String URL_ALLERGY = "http://51.255.164.53/php/allergy.php";
    private static final String URL_UPDATENOM = "http://51.255.164.53/php/updateLastName.php";
    private static final String URL_UPDATEPRENOM = "http://51.255.164.53/php/updateFirstName.php";
    private static final String URL_UPDATEPASSWORD = "http://51.255.164.53/php/updatePassword.php";
    private static final String URL_UPDATEDIET = "http://51.255.164.53/php/updateDiet.php";
    private static final String URL_UPDATEALLERGY = "http://51.255.164.53/php/updateAllergy.php";

    Button buttonAllergy = null;
    Button buttonValiderProfil = null;

    TextView textViewAllergy = null;

    //Tableaux répertoriant les allergies de l'utilisateur
    static String[] allergies = {};

    boolean[] checkedAllergies;
    ArrayList<String> userAllergy = new ArrayList<String>();
    ArrayList<String> userAllergyOld = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profil);

        //Initialisation
        builder = new AlertDialog.Builder(this);

        editTextNom = (TextView) findViewById(R.id.editTextNom);
        editTextPrenom = (TextView) findViewById(R.id.editTextPrenom);
        editTextMdp = (TextView) findViewById(R.id.editTextMdp);
        editTextVerifMdp = (TextView) findViewById(R.id.editTextVerifMdp);
        editTextMail = (TextView) findViewById(R.id.editTextMail);

        checkboxVegan = (android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxVegan);
        checkboxVegetarian = (android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxVegetarian);
        checkboxNoGluten = (android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxNoGluten);

        buttonAllergy = (Button) findViewById(R.id.buttonAllergy);
        buttonValiderProfil = (Button) findViewById(R.id.buttonValiderProfil);

        textViewAllergy = (TextView) findViewById(R.id.textViewAllergy);

        buttonAllergy.setOnClickListener(this);
        buttonValiderProfil.setOnClickListener(this);
        checkboxVegan.setOnClickListener(this);
        checkboxVegetarian.setOnClickListener(this);

        //On récupère l'intent
        Intent intentFromProfileActivity = getIntent();
        //On initialise tout les champs du profil avec les données de l'intent
        initializeModifyProfile(intentFromProfileActivity);

        setAllergy();
    }

    public void update(Intent intentFromProfileActivity) {
        mail = getApplicationContext()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                .getString("login", null);
        if (!(editTextNom.equals(intentFromProfileActivity.getStringExtra("Nom")))) {
            updateNom();
        }
        if (!(editTextPrenom.equals(intentFromProfileActivity.getStringExtra("Prenom")))) {
            updatePrenom();
        }
        if (!(editTextMdp.equals(intentFromProfileActivity.getStringExtra("Mdp")))) {
            updateMdp();
        }

        updateD(intentFromProfileActivity);

        updateA();
    }

    public void updateD(Intent intentFromProfileActivity) {
        // Action = "del" or "add"
        //updateDiet(String idDiet, String mail, String action);
        if (checkboxVegan.isChecked() != intentFromProfileActivity.getBooleanExtra("Vegan", false)) {
            if(intentFromProfileActivity.getBooleanExtra("Vegan", false) == true ){
                updateDiet("1", "del");
            }else{
                updateDiet("1", "add");
            }
        }
        if (checkboxVegetarian.isChecked() != intentFromProfileActivity.getBooleanExtra("Vegetarian", false)) {
            if(intentFromProfileActivity.getBooleanExtra("Vegetarian", false) == true ){
                updateDiet("2", "del");
            }else{
                updateDiet("2", "add");
            }
        }
        if (checkboxNoGluten.isChecked() != intentFromProfileActivity.getBooleanExtra("NoGluten", false)) {
            if(intentFromProfileActivity.getBooleanExtra("NoGluten", false) == true ){
                updateDiet("3", "del");
            }else{
                updateDiet("3", "add");
            }
        }
    }

    public void updateDiet(final String idDiet, final String action) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATEDIET,
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

                // Paramètres de l'utilisateurs pour la première requête !
                params.put("idDiet", idDiet);
                params.put("action", action);
                params.put("mail", mail);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(ModifyProfileActivity.this);
        requestQueue.add(stringRequest);
    }

    public void updateA() {
        String all = String.valueOf(textViewAllergy.getText())
                .replace(".", "");
        String[] allParts = all.split(", ");

        for (String a : userAllergyOld) {
            String changement = "del";
            for (String aP : allParts) {
                if (a.equals(aP)) {
                    changement = "non";
                }
            }
            if (changement.equals("del")) {
                updateAllergy(String.valueOf(userAllergyOld.indexOf(a)+1), "del");
            }
        }
        for (int i=0;i<allParts.length;i++) {
            String changement = "add";
            for (String a : userAllergyOld) {
                if (a.equals(allParts[i])) {
                    changement = "non";
                }
            }
            if (changement.equals("add")) {
                updateAllergy(String.valueOf(i+1), "add");
            }
        }
    }

    public void updateAllergy(final String idAllergy, final String action) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATEALLERGY,
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

                // Paramètres de l'utilisateurs pour la première requête !
                params.put("idAllergy", idAllergy);
                params.put("action", action);
                params.put("mail", mail);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(ModifyProfileActivity.this);
        requestQueue.add(stringRequest);
    }

    public void setAllergy() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ALLERGY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String all = String.valueOf(textViewAllergy.getText())
                                    .replace(".", "");
                            String[] allParts = all.split(", ");

                            JSONArray array = new JSONArray(response);

                            allergies = new String[array.length()];
                            checkedAllergies = new boolean[allergies.length];

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonAllergy = array.getJSONObject(i);

                                allergies[i] = jsonAllergy.getString("name");
                                for (String strP : allParts) {
                                    if (allergies[i].equals(strP)) {
                                        checkedAllergies[i] = true;
                                    }
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
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void updateNom() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATENOM,
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

                // Paramètres de l'utilisateurs pour la première requête !
                params.put("lastName", String.valueOf(editTextNom.getText()));
                params.put("mail", mail);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(ModifyProfileActivity.this);
        requestQueue.add(stringRequest);
    }

    public void updatePrenom() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATEPRENOM,
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

                // Paramètres de l'utilisateurs pour la première requête !
                params.put("firstName", String.valueOf(editTextPrenom.getText()));
                params.put("mail", mail);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(ModifyProfileActivity.this);
        requestQueue.add(stringRequest);
    }

    public void updateMdp() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATEPASSWORD,
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

                // Paramètres de l'utilisateurs pour la première requête !
                params.put("password", String.valueOf(editTextMdp.getText()));
                params.put("mail", mail);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(ModifyProfileActivity.this);
        requestQueue.add(stringRequest);
    }

    //Fonction qui initialise les champs du profil avec les données de l'intent
    public void initializeModifyProfile(Intent intentFromProfileActivity) {
        editTextNom.setText(intentFromProfileActivity.getStringExtra("Nom"));
        editTextPrenom.setText(intentFromProfileActivity.getStringExtra("Prenom"));
        editTextMdp.setText(intentFromProfileActivity.getStringExtra("Mdp"));
        editTextVerifMdp.setText(intentFromProfileActivity.getStringExtra("Mdp"));
        editTextMail.setText(intentFromProfileActivity.getStringExtra("Mail"));

        boolean temp = (intentFromProfileActivity.getBooleanExtra("Vegan", false));
        if (temp == true) {
            checkboxVegan.setChecked(true);
        } else {
            checkboxVegan.setChecked(false);
        }
        temp = (intentFromProfileActivity.getBooleanExtra("Vegetarian", false));
        if (temp == true) {
            checkboxVegetarian.setChecked(true);
        } else {
            checkboxVegetarian.setChecked(false);
        }
        temp = (intentFromProfileActivity.getBooleanExtra("NoGluten", false));
        if (temp == true) {
            checkboxNoGluten.setChecked(true);
        } else {
            checkboxNoGluten.setChecked(false);
        }

        userAllergy = intentFromProfileActivity.getStringArrayListExtra("Allergy");
        for(String s :userAllergy){
            userAllergyOld.add(s);
        }
        refreshTextViewAllergy();
    }

    //Fonction qui gère les clics sur les boutons
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAllergy:
                createBuilder();
                ad.show();
                break;
            case R.id.buttonValiderProfil:
                if (editTextMdp.getText().toString().equals(editTextVerifMdp.getEditableText().toString())) {
                    Intent intentFromProfileActivity = getIntent();
                    update(intentFromProfileActivity);
                    /*Intent intentModifyProfileActivity = new Intent();
                    intentModifyProfileActivity.putExtra("NomModif", editTextNom.getText().toString());
                    intentModifyProfileActivity.putExtra("PrenomModif", editTextPrenom.getText().toString());
                    intentModifyProfileActivity.putExtra("MdpModif", editTextMdp.getText().toString());
                    intentModifyProfileActivity.putExtra("MailModif", editTextMail.getText().toString());

                    intentModifyProfileActivity.putExtra("VeganModif", checkboxVegan.isChecked());
                    intentModifyProfileActivity.putExtra("VegetarianModif", checkboxVegetarian.isChecked());
                    intentModifyProfileActivity.putExtra("NoGlutenModif", checkboxNoGluten.isChecked());

                    intentModifyProfileActivity.putExtra("AllergyModif", userAllergy);
                    intentModifyProfileActivity.putExtra("AllergyIdModif", checkedAllergies);
                    setResult(2, intentModifyProfileActivity);*/
                    Intent intentAccueil=new Intent(ModifyProfileActivity.this, HomeActivity.class);
                    startActivity(intentAccueil);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Les mots de passe sont différents";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                break;
            case R.id.checkboxVegan:
                if (checkboxVegetarian.isChecked()) {
                    checkboxVegetarian.setChecked(false);
                }
                break;
            case R.id.checkboxVegetarian:
                if (checkboxVegan.isChecked()) {
                    checkboxVegan.setChecked(false);
                }
                break;
        }
    }

    //Permet de rafraichir le tableau des diet de l'utilisateur
    public ArrayList<String> refreshUserAllergy() {
        userAllergy.clear();
        for (int i = 0; i < checkedAllergies.length; i++) {
            if (checkedAllergies[i] == true) {
                userAllergy.add(allergies[i]);
            }
        }
        return userAllergy;
    }

    //Rafraichit le TextView des diet de l'utilisateur
    public void refreshTextViewAllergy() {
        //Remplissage du String pour le TextView
        String temp = new String();
        for (int i = 0; i < userAllergy.size(); i++) {
            if (i == userAllergy.size() - 1) {
                temp += userAllergy.get(i) + ".";
            } else {
                temp += userAllergy.get(i) + ", ";
            }
        }
        textViewAllergy.setText(temp);
    }

    //Créer le builder pour la pop-up des diet
    public void createBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sélectionnez vos diet");
        builder.setItems(allergies, null);


        builder.setPositiveButton("Valider", null);
        builder.setMultiChoiceItems(allergies, checkedAllergies, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean checked) {
                userAllergy = refreshUserAllergy();
                refreshTextViewAllergy();
            }
        });
        ad = builder.create();
    }
}