package fr.univ_littoral.nathan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OneCubActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonOui;
    private Button buttonNon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_cub);

        buttonOui = (Button) findViewById(R.id.buttonOui);
        buttonNon = (Button) findViewById(R.id.buttonNon);

        buttonOui.setOnClickListener(this);
        buttonNon.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOui:
                //Page internet OneCub
                break;
            case R.id.buttonNon:
                Intent intentAccueil = new Intent( OneCubActivity.this, HomeActivity.class);
                startActivity(intentAccueil);
                break;
        }
    }
}
