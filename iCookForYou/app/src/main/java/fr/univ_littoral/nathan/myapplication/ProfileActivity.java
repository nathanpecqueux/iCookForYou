package fr.univ_littoral.nathan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables xml
    TextView textViewNom =null;
    TextView textViewPrenom =null;
    TextView textViewMdp =null;
    TextView textViewMail =null;

    android.support.v7.widget.AppCompatCheckBox checkboxVegan=null;
    android.support.v7.widget.AppCompatCheckBox checkboxVegetarian=null;
    android.support.v7.widget.AppCompatCheckBox checkboxNoGluten=null;

    Button buttonModifierProfil=null;

    TextView textViewAllergy=null;

    //Tableau répertoriant les allergies de l'utilisateur
    ArrayList<String> userAllergy=new ArrayList<String>();
    int nombreAllergy =3;
    boolean[] checkedAllergies=new boolean[nombreAllergy];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewNom =(TextView) findViewById(R.id.textViewNom);
        textViewPrenom =(TextView) findViewById(R.id.textViewPrenom);
        textViewMdp =(TextView) findViewById(R.id.textViewMdp);
        textViewMail =(TextView) findViewById(R.id.textViewMail);

        checkboxVegan=(android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxVegan);
        checkboxVegetarian=(android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxVegetarian);
        checkboxNoGluten=(android.support.v7.widget.AppCompatCheckBox) findViewById(R.id.checkboxNoGluten);

        buttonModifierProfil=(Button) findViewById(R.id.buttonModifierProfil);

        textViewAllergy=(TextView) findViewById(R.id.textViewAllergy);

        buttonModifierProfil.setOnClickListener(this);

        userAllergy.add("Allergie 1");
        checkedAllergies[0]=true;

        refreshProfil();
    }

    public void refreshProfil(){
        setTextViewUserAllergy();
        checkboxVegan.setChecked(true);
    }

    public void setTextViewUserAllergy() {
        String temp=new String();
        for(int i=0; i<userAllergy.size();i++){
            if(i==userAllergy.size()-1) {
                temp += userAllergy.get(i);
            }else{
                temp+=userAllergy.get(i)+", ";
            }
        }
        textViewAllergy.setText(temp);
    }


    @Override
    public void onClick(View view) {
        Intent intentModifyProfileActivity=new Intent(ProfileActivity.this,ModifyProfileActivity.class);
        intentModifyProfileActivity.putExtra("Nom",textViewNom.getHint().toString());
        intentModifyProfileActivity.putExtra("Prenom",textViewPrenom.getHint().toString());
        intentModifyProfileActivity.putExtra("Mdp",textViewMdp.getHint().toString());
        intentModifyProfileActivity.putExtra("Mail",textViewMail.getHint().toString());

        intentModifyProfileActivity.putExtra("Vegan",checkboxVegan.isChecked());
        intentModifyProfileActivity.putExtra("Vegetarian",checkboxVegetarian.isChecked());
        intentModifyProfileActivity.putExtra("NoGluten",checkboxNoGluten.isChecked());

        intentModifyProfileActivity.putExtra("Allergy", userAllergy);
        intentModifyProfileActivity.putExtra("AllergyId", checkedAllergies);
        startActivity(intentModifyProfileActivity);
    }
}
