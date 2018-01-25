package fr.univ_littoral.nathan.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifyProfileActivity extends AppCompatActivity implements View.OnClickListener{

    //Variables pour afficher la liste des allergies
    AlertDialog.Builder builder;
    String [] allergies ={"Allergie 1","Allergie 2"};
    boolean[] checkedAllergies=new boolean[allergies.length];
    AlertDialog ad;

    //Variables xml

    TextView editTextNom=null;
    TextView editTextPrenom=null;
    TextView editTextMdp=null;
    TextView editTextVerifMdp=null;
    TextView editTextMail=null;

    android.support.v7.widget.AppCompatCheckBox checkboxVegan=null;
    android.support.v7.widget.AppCompatCheckBox checkboxVegetarian=null;
    android.support.v7.widget.AppCompatCheckBox checkboxNoGluten=null;


    Button buttonAllergy=null;
    Button buttonValiderProfil=null;

    TextView textViewAllergy=null;


    //Tableau de String répértoriant les allergies de l'utilisateur
    ArrayList<String> userAllergy=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profil);
        builder=new AlertDialog.Builder(this);

        editTextNom=(TextView) findViewById(R.id.editTextNom);
        editTextPrenom=(TextView) findViewById(R.id.editTextPrenom);
        editTextMdp=(TextView) findViewById(R.id.editTextMdp);
        editTextVerifMdp=(TextView) findViewById(R.id.editTextVerifMdp);
        editTextMail=(TextView) findViewById(R.id.editTextMail);

        checkboxVegan=(android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxVegan);
        checkboxVegetarian=(android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxVegetarian);
        checkboxNoGluten=(android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxNoGluten);

        buttonAllergy=(Button) findViewById(R.id.buttonAllergy);
        buttonValiderProfil=(Button) findViewById(R.id.buttonValiderProfil);

        textViewAllergy=(TextView) findViewById(R.id.textViewAllergy);

        buttonAllergy.setOnClickListener(this);
        buttonValiderProfil.setOnClickListener(this);
        checkboxVegan.setOnClickListener(this);
        checkboxVegetarian.setOnClickListener(this);
    }



    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.buttonAllergy:
                createBuilder();
                ad.show();
                break;
            case R.id.buttonValiderProfil:

                break;
            case R.id.checkboxVegan:
                if(checkboxVegetarian.isChecked()){
                    checkboxVegetarian.setChecked(false);
                }
                break;
            case R.id.checkboxVegetarian:
                if(checkboxVegan.isChecked()){
                    checkboxVegan.setChecked(false);
                }
                break;
        }
}

    public ArrayList<String> refreshUserAllergy(){
        userAllergy.clear();
        for(int i=0; i<checkedAllergies.length;i++){
            if(checkedAllergies[i]==true){
                userAllergy.add(allergies[i]);
            }
        }
        return userAllergy;
    }

    public void refreshTextViewAllergy(){
        //Remplissage du String pour le TextView
        String temp=new String();
        for(int i=0; i<userAllergy.size();i++){
            if(i==userAllergy.size()-1){
                temp+=userAllergy.get(i)+".";
            }else{
                temp+=userAllergy.get(i)+", ";
            }
        }
        textViewAllergy.setText(temp);
    }


    public void createBuilder(){
        builder.setTitle("Sélectionnez vos allergies");
        builder.setItems(allergies,null);

        builder.setPositiveButton("Valider",null);
        builder.setMultiChoiceItems(allergies, checkedAllergies, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean checked) {
                Toast.makeText(getApplicationContext(),allergies[which],Toast.LENGTH_SHORT);
                userAllergy=refreshUserAllergy();
                refreshTextViewAllergy();
            }
        });
        ad=builder.create();
    }
}