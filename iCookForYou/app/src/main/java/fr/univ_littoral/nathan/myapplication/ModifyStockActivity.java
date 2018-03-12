package fr.univ_littoral.nathan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ModifyStockActivity extends AppCompatActivity implements View.OnClickListener{

    Button cancel;
    Button validateFoodModification;
    Button deleteFood;
    TextView selectedIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_stock);
        selectedIngredient = (TextView) findViewById(R.id.ingredientTextModify);
        deleteFood = (Button) findViewById(R.id.buttonDeleteFood);
        deleteFood.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.buttonCancelFoodModify);
        cancel.setOnClickListener(this);
        validateFoodModification = (Button) findViewById(R.id.buttonValidateFoodModify);
        validateFoodModification.setOnClickListener(this);
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
            case R.id.buttonDeleteFood:
                Intent deletefoodactivity = new Intent(ModifyStockActivity.this, StockActivity.class);
                startActivityForResult(deletefoodactivity, 1);
                break;
            case R.id.buttonCancelFoodModify:
                Intent cancelintent = new Intent();
                setResult(1, cancelintent);
                finish();
                break;
            case R.id.buttonValidateFoodModify:
                Intent validatefoodactivity = new Intent(ModifyStockActivity.this, StockActivity.class);
                startActivityForResult(validatefoodactivity, 1);
                break;
        }
    }
}
