package com.example.mateu.gamesdraganddrop;

import android.content.ClipData;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActQuebraCabeca extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {

    private LinearLayout lLImgsQuebraCabeca;
    private GridLayout gridTabuleiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_quebra_cabeca);

        inicializaComponentes();

    }

    private void inicializaComponentes(){

        lLImgsQuebraCabeca = findViewById(R.id.lLImgsQuebraCabeca);

        gridTabuleiro = findViewById(R.id.tabuleiro);

        for(int i = lLImgsQuebraCabeca.getChildCount() - 1; i >= 0 ; i--){

            LinearLayout linearLayoutImgs = (LinearLayout) lLImgsQuebraCabeca.getChildAt(i);

            linearLayoutImgs.setOnDragListener(this);

            ImageView imgTab = (ImageView) linearLayoutImgs.getChildAt(0);

            imgTab.setTag(i);

            imgTab.setOnTouchListener(this);

            gridTabuleiro.getChildAt(i).setOnDragListener(this);

        }

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()){

            case DragEvent.ACTION_DROP:

                View view = (View) event.getLocalState();
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                LinearLayout conteiner = (LinearLayout) v;

                if(conteiner.getChildAt(0) != null && conteiner.getChildAt(0).getTag() == null &&
                        conteiner.getChildAt(1) == null || conteiner.getChildAt(0) == null) {

                    viewGroup.removeView(view);

                    conteiner.addView(view);

                }

                break;

            default: break;

        }

        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){

            ClipData data = ClipData.newPlainText("", "");

            View.DragShadowBuilder shandow = new View.DragShadowBuilder(v);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                v.startDragAndDrop(data, shandow, v, 1);
            else
                v.startDrag(data, shandow, v, 1);


            return true;

        }

        return false;
    }
}
