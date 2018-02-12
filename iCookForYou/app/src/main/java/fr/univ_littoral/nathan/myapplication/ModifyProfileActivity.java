package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifyProfileActivity extends Activity implements View.OnClickListener{



    //Variables pour Pop-up
    AlertDialog.Builder builder;
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

    //Tableaux répertoriant les diet de l'utilisateur
    String[] allergies ={"Allergie 1","Allergie 2","Allergie 3"};
    boolean[] checkedAllergies=new boolean[allergies.length];
    ArrayList<String> userAllergy=new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profil);

        //Initialisation
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


        //On récupère l'intent
        Intent intentFromProfileActivity=getIntent();

        //On initialise tout les champs du profil avec les données de l'intent
        initializeModifyProfile(intentFromProfileActivity);
    }


    //Fonction qui initialise les champs du profil avec les données de l'intent
    public void initializeModifyProfile(Intent intentFromProfileActivity){
        editTextNom.setText(intentFromProfileActivity.getStringExtra("Nom"));
        editTextPrenom.setText(intentFromProfileActivity.getStringExtra("Prenom"));
        editTextMdp.setText(intentFromProfileActivity.getStringExtra("Mdp"));
        editTextVerifMdp.setText(intentFromProfileActivity.getStringExtra("Mdp"));
        editTextMail.setText(intentFromProfileActivity.getStringExtra("Mail"));

        boolean temp=(intentFromProfileActivity.getBooleanExtra("Vegan",false));
        if(temp==true){
            checkboxVegan.setChecked(true);
        }else{
            checkboxVegan.setChecked(false);
        }
        temp=(intentFromProfileActivity.getBooleanExtra("Vegetarian",false));
        if(temp==true){
            checkboxVegetarian.setChecked(true);
        }else{
            checkboxVegetarian.setChecked(false);
        }
        temp=(intentFromProfileActivity.getBooleanExtra("NoGluten",false));
        if(temp==true){
            checkboxNoGluten.setChecked(true);
        }else{
            checkboxNoGluten.setChecked(false);
        }

        userAllergy=intentFromProfileActivity.getStringArrayListExtra("Allergy");
        refreshTextViewAllergy();


        checkedAllergies=intentFromProfileActivity.getBooleanArrayExtra("AllergyId");
    }



    //Fonction qui gère les clics sur les boutons
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.buttonAllergy:
                createBuilder();
                ad.show();
                break;
            case R.id.buttonValiderProfil:
                if(editTextMdp.getText().toString().equals(editTextVerifMdp.getEditableText().toString())) {
                    Intent intentModifyProfileActivity = new Intent();
                    intentModifyProfileActivity.putExtra("NomModif", editTextNom.getText().toString());
                    intentModifyProfileActivity.putExtra("PrenomModif", editTextPrenom.getText().toString());
                    intentModifyProfileActivity.putExtra("MdpModif", editTextMdp.getText().toString());
                    intentModifyProfileActivity.putExtra("MailModif", editTextMail.getText().toString());

                    intentModifyProfileActivity.putExtra("VeganModif", checkboxVegan.isChecked());
                    intentModifyProfileActivity.putExtra("VegetarianModif", checkboxVegetarian.isChecked());
                    intentModifyProfileActivity.putExtra("NoGlutenModif", checkboxNoGluten.isChecked());

                    intentModifyProfileActivity.putExtra("AllergyModif", userAllergy);
                    intentModifyProfileActivity.putExtra("AllergyIdModif", checkedAllergies);
                    setResult(2, intentModifyProfileActivity);
                    finish();
                }else {
                    Context context=getApplicationContext();
                    CharSequence text="Les mots de passe sont différents";
                    int duration =Toast.LENGTH_SHORT;
                    Toast toast=Toast.makeText(context,text,duration);
                    toast.show();
                }
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


    //Permet de rafraichir le tableau des diet de l'utilisateur
    public ArrayList<String> refreshUserAllergy(){
        userAllergy.clear();
        for(int i=0; i<checkedAllergies.length;i++){
            if(checkedAllergies[i]==true){
                userAllergy.add(allergies[i]);
            }
        }
        return userAllergy;
    }

    //Rafraichit le TextView des diet de l'utilisateur
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

    //Créer le builder pour la pop-up des diet
    public void createBuilder(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Sélectionnez vos diet");
        builder.setItems(allergies,null);


        builder.setPositiveButton("Valider",null);
        builder.setMultiChoiceItems(allergies, checkedAllergies, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean checked) {
                userAllergy=refreshUserAllergy();
                refreshTextViewAllergy();
            }
        });
        ad=builder.create();
    }
}