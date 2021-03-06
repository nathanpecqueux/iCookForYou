package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class IndexActivity extends Activity implements View.OnClickListener {

    //Variables globales pour le layout xml
    Button buttonCestParti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //On lie nos variables globales avec les id du layout xml
        buttonCestParti=(Button) findViewById(R.id.buttonCestParti);

        buttonCestParti.setOnClickListener(this);
    }

    //Permet de créer le menu de la layout connexion
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_index, menu);
        return true;
    }

    //Fonction qui permet de gérer les clics sur les éléments du menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //Accès au profil
            case R.id.menuIndexProfil:
                Intent intentAccueil = new Intent(IndexActivity.this, ProfileActivity.class);
                startActivity(intentAccueil);
                break;
                //accès au stock
            case R.id.menuIndexStock:
                Intent intentStock = new Intent(IndexActivity.this, StockActivity.class);
                startActivity(intentStock);
                break;

                //Affiche une pop-up avec les informations de l'application
            case R.id.menuIndexAPropos:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.layout_propos);
                dialog.setCancelable(true);
                //there are a lot of settings, for dialog, check them all out!
                dialog.setTitle("A propos de l'application");
                //set up text
                TextView text = (TextView) dialog.findViewById(R.id.TextView01);
                text.setText("Application créée par :");
                TextView text2 = (TextView) dialog.findViewById(R.id.TextView02);
                text2.setText("Bomy François\nLebegue Clément\nLeblanc Alexandre\nPecqueux Nathan");
                TextView text3 = (TextView) dialog.findViewById(R.id.TextView03);
                text3.setText("Version 1.0,  02/02/2018");

                //set up image view
                ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);
                img.setImageResource(R.drawable.logo_propos);

                dialog.show();
                break;
                //Deconnexion
            case R.id.menuIndexDéconnexion:
                Intent intent = new Intent(IndexActivity.this,ConnectionActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent intentOneCub = new Intent(IndexActivity.this,OneCubActivity.class);
        startActivity(intentOneCub);
    }
}
