package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConnectionActivity extends Activity implements View.OnClickListener{

    Button registerButton;
    Button connectionButton;
    EditText connectionMail;
    EditText connectionPassword;
    TextView forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        registerButton=(Button) findViewById(R.id.gotoregisterButton);
        connectionButton=(Button) findViewById(R.id.connectionButton);
        connectionMail=(EditText) findViewById(R.id.connectionMail);
        connectionPassword=(EditText) findViewById(R.id.connectionPassword);
        forgotPassword=(TextView) findViewById(R.id.forgotPassword);
        registerButton.setOnClickListener(this);
        connectionButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gotoregisterButton:
                Intent registerActivity = new Intent(ConnectionActivity.this, RegisterActivity.class);
                startActivity(registerActivity);
                break;
            case R.id.connectionButton:

                break;
            case R.id.forgotPassword:
                Intent forgotPasswordActivity = new Intent(ConnectionActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotPasswordActivity);
                break;
        }
    }
}
