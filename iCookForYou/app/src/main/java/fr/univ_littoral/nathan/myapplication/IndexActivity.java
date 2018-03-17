package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IndexActivity extends Activity implements View.OnClickListener {

    Button buttonCestParti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        buttonCestParti=(Button) findViewById(R.id.buttonCestParti);

        buttonCestParti.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intentOneCub = new Intent(IndexActivity.this,OneCubActivity.class);
        startActivity(intentOneCub);
    }
}
