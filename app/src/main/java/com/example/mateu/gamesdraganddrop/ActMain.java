package com.example.mateu.gamesdraganddrop;

import android.content.Intent;
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

        findViewById(R.id.btnQbCabeca).setOnClickListener(this);

        findViewById(R.id.btnQbSlider).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        Button btn = (Button) v;

        if(btn.getId() == R.id.btnQbCabeca){

            Intent intent = new Intent(this, ActQuebraCabeca.class);

            startActivity(intent);

        }else{

            Intent intent = new Intent(this, ActQuebraCabecaSlider.class);

            startActivity(intent);

        }

    }
}
