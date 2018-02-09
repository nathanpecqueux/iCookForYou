package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends Activity implements View.OnClickListener{

    static String[] allergies={"arachides","fruits à écales","lait de vache","oeufs","poissons","fruits de mer","soya","blé","graines de sésame"};
    static String[] diets={"Végétarien","Vegan","Sans gluten"};
    boolean[] checkedAllergiesItems=new boolean[allergies.length];
    boolean[] checkedDietItems=new boolean[diets.length];
    AlertDialog adAllergies;
    AlertDialog adDiet;
    Button allergiesButton;
    Button dietButton;
    Button registerButton;
    Button cancelButton;
    EditText registerName;
    EditText registerFirstName;
    EditText registerMail;
    EditText registerPassword;
    EditText registerConfirmPassword;

    //private static final String URL_USERS = "http://192.168.5.46/MyApi/Api.php";
    private static final String URL_USERS = "http://51.255.164.53/php/registerUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
                adAllergies.show();
                break;
            case R.id.registeredDiet:
                alertDiet();
                adDiet.show();
                break;
            case R.id.registerButton:
                if(checkRegister()){
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

                            params.put("lastName", String.valueOf(registerName.getText()));
                            params.put("firstName", String.valueOf(registerFirstName.getText()));
                            params.put("mail", String.valueOf(registerMail.getText()));
                            params.put("password", String.valueOf(registerPassword.getText()));

                            return params;
                        }

                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                    requestQueue.add(stringRequest);

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

    public void alertAllergies(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Selectionnez vos allergies");

        builder.setNegativeButton("Annuler",null);
        builder.setPositiveButton("Confirmer", null);
        builder.setMultiChoiceItems(allergies, checkedAllergiesItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getApplicationContext(), allergies[which], Toast.LENGTH_SHORT).show();
            }
        });
        adAllergies=builder.create();
    }

    public void alertDiet(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Selectionnez vos régimes alimentaires");

        builder.setNegativeButton("Annuler",null);
        builder.setPositiveButton("Confirmer", null);
        builder.setMultiChoiceItems(diets, checkedDietItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getApplicationContext(), diets[which], Toast.LENGTH_SHORT).show();
            }
        });
        adDiet=builder.create();
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
