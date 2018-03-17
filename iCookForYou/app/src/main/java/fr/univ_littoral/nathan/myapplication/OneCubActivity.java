package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import fr.univ_littoral.nathan.myapplication.sampledata.Ingredient;
import fr.univ_littoral.nathan.myapplication.sampledata.IngredientAdapter;

public class OneCubActivity extends Activity implements View.OnClickListener{
    private Button buttonOui;
    private Button buttonNon;
    private TextView erreur;
    private EditText mail;
    private ProgressDialog progress;

    private static final String URL_ONECUB = "http://51.255.164.53/php/registerOnecub.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_cub);

        buttonOui = (Button) findViewById(R.id.buttonOui);
        buttonNon = (Button) findViewById(R.id.buttonNon);
        erreur = (TextView) findViewById(R.id.erreur);
        mail = (EditText) findViewById(R.id.editTextMail);

        buttonOui.setOnClickListener(this);
        buttonNon.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOui:
                if(checkMail()==false){
                    erreur.setVisibility(View.VISIBLE);
                }else {
                    getApplicationContext()
                            .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                            .edit()
                            .putBoolean("onecub", true)
                            .apply();
                    erreur.setVisibility(View.INVISIBLE);
                    download(v);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1500);
                                Intent intentRecettes = new Intent(OneCubActivity.this, StockActivity.class);
                                startActivity(intentRecettes);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                break;
            case R.id.buttonNon:
                Intent intentAccueil = new Intent( OneCubActivity.this, HomeActivity.class);
                startActivity(intentAccueil);
                break;
        }
    }

    public void download(View view){
        progress = new ProgressDialog(OneCubActivity.this);
        progress.setMax(100);
        progress.setMessage("En cours de récupération");
        progress.setTitle("Récupération de vos listes de courses");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progress.getProgress() <= progress
                            .getMax()) {
                        Thread.sleep(100);
                        handle.sendMessage(handle.obtainMessage());
                        if (progress.getProgress() == progress
                                .getMax()) {
                            saveFood();
                            progress.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress.incrementProgressBy(5);
        }
    };

    public void saveFood() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ONECUB,
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

                String mail = getApplicationContext()
                        .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                        .getString("login", null);
                params.put("mail", mail);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(OneCubActivity.this);
        requestQueue.add(stringRequest);
    }

    public boolean checkMail() {
        String m = String.valueOf(mail.getText());
        return Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", m);
    }
}
