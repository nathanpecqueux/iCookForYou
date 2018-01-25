package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPasswordActivity extends Activity implements View.OnClickListener{

    Button recoverButton;
    Button cancelButton;
    EditText recoverMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        recoverButton=(Button) findViewById(R.id.recoverPasswordButton);
        cancelButton=(Button) findViewById(R.id.cancelButtonForgot);
        recoverMail=(EditText) findViewById(R.id.recoverMail);
        recoverButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.recoverPasswordButton:
                if(isValidEmail(recoverMail.getText().toString())){
                    //Envoi d'un mail de recovery
                    System.out.println("Success !");
                    break;
                }
                System.out.println("Mail non trouvé dans la BDD");
                break;
            case R.id.cancelButtonForgot:
                finish();
                break;
        }
    }

    public final static boolean isValidEmail(String email){
        //Besoin d'ajouter une vérification du mail dans la BDD et d'un utilisateur qui match
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
