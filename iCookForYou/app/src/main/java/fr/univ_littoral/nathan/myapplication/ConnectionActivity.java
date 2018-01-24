package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnectionActivity extends Activity implements View.OnClickListener{

    Button registerButton;
    Button connectionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        registerButton=(Button) findViewById(R.id.gotoregister);
        connectionButton=(Button) findViewById(R.id.connection);
        registerButton.setOnClickListener(this);
        connectionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gotoregister:
                Intent registerActivity = new Intent(ConnectionActivity.this, RegisterActivity.class);
                startActivity(registerActivity);
                break;
            case R.id.connection:

                break;
        }
    }
}
