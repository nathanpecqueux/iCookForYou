package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends Activity implements View.OnClickListener{

    static String[] allergy;
    static String[] diet;
    boolean[] checkedAllergyItems;
    boolean[] checkedDietItems;
    AlertDialog addAllergy;
    AlertDialog addDiet;
    Button allergiesButton;
    Button dietButton;
    Button registerButton;
    Button cancelButton;
    EditText registerName;
    EditText registerFirstName;
    EditText registerMail;
    EditText registerPassword;
    EditText registerConfirmPassword;

    private static final String URL_USERS = "http://51.255.164.53/php/registerUser.php";
    private static final String URL_USERALLERGY = "http://51.255.164.53/php/registerUserAllergy.php";
    private static final String URL_ALLERGY = "http://51.255.164.53/php/allergy.php";
    private static final String URL_DIET = "http://51.255.164.53/php/diet.php";
    private static final String URL_USERDIET = "http://51.255.164.53/php/registerUserDiet.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setAllergy();
        setDiet();
        allergiesButton=(Button) findViewById(R.id.registeredAllergies);
        dietButton=(Button) findViewById(R.id.registeredDiet);
        registerButton=(Button) findViewById(R.id.registerButton);
        cancelButton=(Button) findViewById(R.id.cancelButton);
        registerName=(EditText) findViewById(R.id.registeredName);
        registerFirstName=(EditText) findViewById(R.id.registeredFirstName);
        registerMail=(EditText) findViewById(R.id.registeredMail);
        registerPassword=(EditText) findViewById(R.id.registeredPassword);
        registerConfirmPassword=(EditText) findViewById(R.id.registeredConfirmPassword);
        allergiesButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        dietButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.registeredAllergies:
                alertAllergies();
                addAllergy.show();
                break;
            case R.id.registeredDiet:
                alertDiet();
                addDiet.show();
                break;
            case R.id.registerButton:
                if(checkRegister()){
                    saveUser();
                    ArrayList<String> allergyIndex = new ArrayList<>();
                    for(int i = 0; i< checkedAllergyItems.length; i++){
                        if(checkedAllergyItems[i]==true){
                            allergyIndex.add((i+1)+"");
                        }
                    }
                    for(String i : allergyIndex) {
                        saveAllergy(String.valueOf(registerMail.getText()),i);
                    }
                    ArrayList<String> dietIndex = new ArrayList<>();
                    for(int i = 0; i< checkedDietItems.length; i++){
                        if(checkedDietItems[i]==true){
                            dietIndex.add((i+1)+"");
                        }
                    }
                    for(String i : dietIndex) {
                        saveDiet(String.valueOf(registerMail.getText()),i);
                    }
                    System.out.println("Inscription réussie");
                    break;
                }
                System.out.println("Inscription échouée");
                break;
            case R.id.cancelButton:
                finish();
                break;
        }
    }

    public void saveUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_USERS,
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
                params.put("lastName", String.valueOf(registerName.getText()));
                params.put("firstName", String.valueOf(registerFirstName.getText()));
                params.put("mail", String.valueOf(registerMail.getText()));
                params.put("password", String.valueOf(registerPassword.getText()));

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        requestQueue.add(stringRequest);
    }

    public void saveAllergy(String mail, String i) {
        final String index = i;
        final String email = mail;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_USERALLERGY,
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
                params.put("mail",email);
                params.put("allergy",index);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        requestQueue.add(stringRequest);
    }

    public void setAllergy() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ALLERGY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            allergy = new String[array.length()];
                            checkedAllergyItems = new boolean[allergy.length];

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject jsonAllergy = array.getJSONObject(i);

                                allergy[i]=jsonAllergy.getString("name");
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

    public void saveDiet(String mail, String i) {
        final String index = i;
        final String email = mail;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_USERDIET,
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
                params.put("mail",email);
                params.put("diet",index);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        requestQueue.add(stringRequest);
    }

    public void setDiet() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DIET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            diet = new String[array.length()];
                            checkedDietItems = new boolean[diet.length];

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject jsonAllergy = array.getJSONObject(i);

                                diet[i]=jsonAllergy.getString("name");
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

    public void alertAllergies(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Selectionnez vos diet");

        builder.setNegativeButton("Annuler",null);
        builder.setPositiveButton("Confirmer", null);
        builder.setMultiChoiceItems(allergy, checkedAllergyItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getApplicationContext(), allergy[which], Toast.LENGTH_SHORT).show();
            }
        });
        addAllergy =builder.create();
    }

    public void alertDiet(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Selectionnez vos régimes alimentaires");

        builder.setNegativeButton("Annuler",null);
        builder.setPositiveButton("Confirmer", null);
        builder.setMultiChoiceItems(diet, checkedDietItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getApplicationContext(), diet[which], Toast.LENGTH_SHORT).show();
            }
        });
        addDiet =builder.create();
    }

    public boolean checkRegister(){
        if(registerName.getText().toString().equals("")){
            return false;
        }
        if(registerFirstName.getText().toString().equals("")){
            return false;
        }
        if(!isValidEmail(registerMail.getText().toString())){
            return false;
        }
        if(!isValidPassword(registerPassword.getText().toString(),registerConfirmPassword.getText().toString())){
            return false;
        }
        return true;
    }

    public final static boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public final static boolean isValidPassword(String password, String passwordConfirm){
        return !TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordConfirm) && password.equals(passwordConfirm);
    }
}
