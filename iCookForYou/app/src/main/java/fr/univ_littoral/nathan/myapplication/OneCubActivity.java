package fr.univ_littoral.nathan.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

public class OneCubActivity extends Activity implements View.OnClickListener{
    private Button buttonOui;
    private Button buttonNon;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_cub);

        buttonOui = (Button) findViewById(R.id.buttonOui);
        buttonNon = (Button) findViewById(R.id.buttonNon);


        buttonOui.setOnClickListener(this);
        buttonNon.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOui:
                download(v);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10000);
                            Intent intentRecettes=new Intent(OneCubActivity.this,StockActivity.class);
                            startActivity(intentRecettes);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.buttonNon:
                Intent intentAccueil = new Intent( OneCubActivity.this, HomeActivity.class);
                startActivity(intentAccueil);
                break;
        }
    }

    public void download(View view){
        progress = new ProgressDialog(OneCubActivity.this);
        progress.setMax(100);
        progress.setMessage("En cours de récupération");
        progress.setTitle("Récupération de vos listes de courses");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progress.getProgress() <= progress
                            .getMax()) {
                        Thread.sleep(100);
                        handle.sendMessage(handle.obtainMessage());
                        if (progress.getProgress() == progress
                                .getMax()) {
                            progress.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress.incrementProgressBy(1);
        }
    };

}
