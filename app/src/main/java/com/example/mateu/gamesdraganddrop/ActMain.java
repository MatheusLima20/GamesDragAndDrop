package com.example.mateu.gamesdraganddrop;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActMain extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        findViewById(R.id.btnCabeca).setOnClickListener(this);

        findViewById(R.id.btnQbSlider).setOnClickListener(this);

        findViewById(R.id.btnAbout).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        Button btn = (Button) v;

        if(btn.getId() == R.id.btnCabeca){

            Intent intent = new Intent(this, ActQuebraCabeca.class);

            startActivity(intent);

        }else if (btn.getId() == R.id.btnQbSlider){

            Intent intent = new Intent(this, ActQuebraCabecaSlider.class);

            startActivity(intent);

        }else {

            Intent intent = new Intent(this, ActAbout.class);

            startActivity(intent);

        }

    }
}
