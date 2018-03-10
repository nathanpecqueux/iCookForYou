package fr.univ_littoral.nathan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddIngredientActivity extends AppCompatActivity implements View.OnClickListener{

    Button cancel;
    Button validateFood;
    Button selectFood;
    TextView selectedIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);
        selectedIngredient = (TextView) findViewById(R.id.ingredientTextAdd);
        selectFood = (Button) findViewById(R.id.selectIngredient);
        selectFood.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.buttonCancelFood);
        cancel.setOnClickListener(this);
        validateFood = (Button) findViewById(R.id.buttonValidateFood);
        validateFood.setOnClickListener(this);
        Intent intent = getIntent();
        if(intent.getStringExtra("id")==null){
            selectedIngredient.setText("");
        }else{
            selectedIngredient.setText(intent.getStringExtra("id").toString());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectIngredient:
                Intent registerActivity = new Intent(AddIngredientActivity.this, SelectIngredientActivity.class);
                startActivityForResult(registerActivity, 1);
                break;
            case R.id.buttonCancelFood:
                Intent cancelintent = new Intent();
                setResult(1, cancelintent);
                finish();
                break;
            case R.id.buttonValidateFood:
                Intent validatefoodactivity = new Intent(AddIngredientActivity.this, SelectIngredientActivity.class);
                startActivityForResult(validatefoodactivity, 1);
                break;
        }
    }
}
