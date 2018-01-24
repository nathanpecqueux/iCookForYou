package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends Activity implements View.OnClickListener{

    static String[] allergies={"arachides","fruits à écales","lait de vache","oeufs","poissons","fruits de mer","soya","blé","graines de sésame"};
    static String[] diets={"Végétarien","Vegan","Sans gluten"};
    boolean[] checkedAllergiesItems=new boolean[allergies.length];
    boolean[] checkedDietItems=new boolean[diets.length];
    AlertDialog adAllergies;
    AlertDialog adDiet;
    Button allergiesButton;
    Button dietButton;
    Button registerButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        allergiesButton=(Button) findViewById(R.id.registeredallergies);
        dietButton=(Button) findViewById(R.id.registereddiet);
        registerButton=(Button) findViewById(R.id.register);
        cancelButton=(Button) findViewById(R.id.cancel);
        allergiesButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        dietButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.registeredallergies:
                alertAllergies();
                adAllergies.show();
                break;
            case R.id.registereddiet:
                alertDiet();
                adDiet.show();
                break;
            case R.id.register:

                break;
            case R.id.cancel:
                finish();
                break;
        }
    }

    public void alertAllergies(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Selectionnez vos allergies");

        builder.setNegativeButton("Ok", null);
        builder.setMultiChoiceItems(allergies, checkedAllergiesItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getApplicationContext(), allergies[which], Toast.LENGTH_SHORT).show();
            }
        });
        adAllergies=builder.create();
    }

    public void alertDiet(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Selectionnez vos régimes alimentaires");

        builder.setNegativeButton("Ok", null);
        builder.setMultiChoiceItems(diets, checkedDietItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getApplicationContext(), diets[which], Toast.LENGTH_SHORT).show();
            }
        });
        adDiet=builder.create();
    }
}
