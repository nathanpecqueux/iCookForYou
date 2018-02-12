package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;

public class ConnectionActivity extends Activity implements View.OnClickListener {

    Button registerButton;
    Button connectionButton;
    EditText connectionMail;
    EditText connectionPassword;
    TextView forgotPassword;
    TextView erreur;

    //private static final String URL_USERS = "http://192.168.5.46/MyApi/Api.php";
    private static final String URL_USERS = "http://51.255.164.53/php/connexionUser.php";

    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        registerButton = (Button) findViewById(R.id.gotoregisterButton);
        connectionButton = (Button) findViewById(R.id.connectionButton);
        connectionMail = (EditText) findViewById(R.id.connectionMail);
        connectionPassword = (EditText) findViewById(R.id.connectionPassword);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        erreur = (TextView) findViewById(R.id.erreur);
        registerButton.setOnClickListener(this);
        connectionButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        erreur.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gotoregisterButton:
                Intent registerActivity = new Intent(ConnectionActivity.this, RegisterActivity.class);
                startActivity(registerActivity);
                break;
            case R.id.connectionButton:
                final String mail = String.valueOf(connectionMail.getText());
                final String password = String.valueOf(connectionPassword.getText());
                userList = new ArrayList<>();
                loadUser(new CallBack() {
                    @Override
                    public void onSuccess(List<User> userList) {
                        for (User u : userList) {
                            if (u.getMail().equals(mail) && u.getPassword().equals(password)) {
                                // Connection Correct :)
                                getApplicationContext()
                                        .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                                        .edit()
                                        .putBoolean("isLoggedIn", true)
                                        .putString("login", u.getMail())
                                        .apply();

                                System.out.println("Envoie vers l'intention HomeActivity !!!!!");

                                // Récupérer login :
                                String myString = getApplicationContext()
                                        .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                                        .getString("login", null);
                                System.out.println("login stocké dans l'appli : "+myString);

//                                Intent homeActivity = new Intent(ConnectionActivity.this, HomeActivity.class);
//                                startActivity(homeActivity);
                                return;
                            }
                        }
                        erreur.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFail(String msg) {
                        System.out.println("Problème de connexion au serveur !");
                    }
                });
                break;
            case R.id.forgotPassword:
                Intent forgotPasswordActivity = new Intent(ConnectionActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotPasswordActivity);
                break;
        }
    }

    private void loadUser(final CallBack onCallBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_USERS,
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

}
