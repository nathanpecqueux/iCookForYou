package fr.univ_littoral.nathan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by alexd on 16/01/2018.
 */


//Fonction qui gère la splash d'accueil ( logo + nom )
public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationContext()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("onecub", false)
                .putBoolean("isLoggedIn", false)
                .putString("login", null)
                .apply();
        Intent intent = new Intent(this, ConnectionActivity.class);
        startActivity(intent);
        finish();
    }
}
