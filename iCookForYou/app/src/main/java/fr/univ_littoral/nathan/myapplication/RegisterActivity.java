package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.List;
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
    List<User> userList;
    TextView erreur;

    private static final String URL_USERS = "http://51.255.164.53/php/registerUser.php";
    private static final String URL_USERALLERGY = "http://51.255.164.53/php/registerUserAllergy.php";
    private static final String URL_ALLERGY = "http://51.255.164.53/php/allergy.php";
    private static final String URL_DIET = "http://51.255.164.53/php/diet.php";
    private static final String URL_USERDIET = "http://51.255.164.53/php/registerUserDiet.php";
    private static final String URL_CONNEXION = "http://51.255.164.53/php/connexionUser.php";

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
        erreur=(TextView) findViewById(R.id.erreur);
        erreur.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_connexion,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){ case R.id.menuProfilAPropos:
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
            case R.id.menuProfilDéconnexion:
                Intent intent = new Intent(RegisterActivity.this,ConnectionActivity.class);
                startActivity(intent);
                break;
        }
        return true;
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
                    final String mail = String.valueOf(registerMail.getText());
                    userList = new ArrayList<>();
                    loadUser(new CallBack() {
                        @Override
                        public void onSuccess(List<User> userList) {
                            for (User u : userList) {
                                if (u.getMail().equals(mail)) {
                                    erreur.setText("Email déjà utilisé !");
                                    erreur.setTextColor(Color.RED);
                                    erreur.setVisibility(View.VISIBLE);
                                    return;
                                }
                            }
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
                            erreur.setText("Inscription réussi !");
                            erreur.setTextColor(Color.GREEN);
                            erreur.setVisibility(View.VISIBLE);
                            Intent intentRegister = new Intent();

                            intentRegister.putExtra("mail", registerMail.getText().toString());
                            intentRegister.putExtra("password", registerPassword.getText().toString());
                            setResult(2, intentRegister);

                            finish();
                        }
                        @Override
                        public void onFail(String msg) {
                            erreur.setText("Problème de connexion au serveur !");
                            erreur.setTextColor(Color.RED);
                            erreur.setVisibility(View.VISIBLE);
                        }
                    });
                    break;
                }
                erreur.setText("Erreur dans l'inscription");
                erreur.setTextColor(Color.RED);
                erreur.setVisibility(View.VISIBLE);
                break;
            case R.id.cancelButton:
                Intent intentRegister = new Intent();

                setResult(1, intentRegister);
                finish();
                break;
        }
    }

    private void loadUser(final CallBack onCallBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_CONNEXION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject user = array.getJSONObject(i);

                                userList.add(new User(
                                        user.getString("mail"),
                                        user.getString("password")
                                ));
                            }
                            onCallBack.onSuccess(userList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            onCallBack.onFail(e.toString());
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

    public interface CallBack {
        void onSuccess(List<User> userList);
        void onFail(String msg);
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
        return isValidPassword(registerPassword.getText().toString(), registerConfirmPassword.getText().toString());
    }

    public final static boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public final static boolean isValidPassword(String password, String passwordConfirm){
        return !TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordConfirm) && password.equals(passwordConfirm);
    }
}
