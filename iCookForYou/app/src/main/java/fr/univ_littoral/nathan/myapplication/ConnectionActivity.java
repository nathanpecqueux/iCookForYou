package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_connexion,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuProfilAPropos:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("A propos de ...")
                        .setMessage("ICookForYou\n\nApplication créée par :\n\nBomy François\nLebegue Clément\nLeblanc Alexandre\nPecqueux Nathan");
                builder.show();
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
        switch(v.getId()){
            case R.id.gotoregisterButton:
                Intent registerActivity = new Intent(ConnectionActivity.this, RegisterActivity.class);
                startActivity(registerActivity);
                break;
            case R.id.connectionButton:
                Intent homeActivity = new Intent(ConnectionActivity.this, HomeActivity.class);
                startActivity(homeActivity);
                break;
            case R.id.forgotPassword:
                Intent forgotPasswordActivity = new Intent(ConnectionActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotPasswordActivity);
                break;
        }
    }
}
