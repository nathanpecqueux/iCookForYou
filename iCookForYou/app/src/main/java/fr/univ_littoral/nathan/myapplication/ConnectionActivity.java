package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_connexion,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
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
            case R.id.menuProfilQuitter:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
        return true;
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
