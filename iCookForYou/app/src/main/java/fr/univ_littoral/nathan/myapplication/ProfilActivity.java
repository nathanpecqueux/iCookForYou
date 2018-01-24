package fr.univ_littoral.nathan.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener{

    Button boutonAllergie;

    AlertDialog.Builder builder;
    static String [] allergies ={"Allergie 1","Allergie 2"};
    boolean[] checkedAllergies=new boolean[allergies.length];

    AlertDialog ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        builder=new AlertDialog.Builder(this);

        boutonAllergie=(Button) findViewById(R.id.boutonAllergie);
        boutonAllergie.setOnClickListener(this);
    }

    public void onClick(View view) {
        createBuilder();
        ad.show();
    }

    public void createBuilder(){
        builder.setTitle("Sélectionnez vos allergies");
        builder.setItems(allergies,null);

        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Valider",null);
        builder.setMultiChoiceItems(allergies, checkedAllergies, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean checked) {
                Toast.makeText(getApplicationContext(),allergies[which],Toast.LENGTH_SHORT).show();
            }
        });
        ad=builder.create();
    }
}
