package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class OneCubActivity extends Activity implements View.OnClickListener{
    private Button buttonOui;
    private Button buttonNon;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_cub);

        buttonOui = (Button) findViewById(R.id.buttonOui);
        buttonNon = (Button) findViewById(R.id.buttonNon);
        spinner = (ProgressBar) findViewById(R.id.progressBar);

        spinner.incrementProgressBy(10);

        buttonOui.setOnClickListener(this);
        buttonNon.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOui:
                spinner.setVisibility(View.VISIBLE);
                break;
            case R.id.buttonNon:
                Intent intentAccueil = new Intent( OneCubActivity.this, HomeActivity.class);
                startActivity(intentAccueil);
                break;
        }
    }
}
