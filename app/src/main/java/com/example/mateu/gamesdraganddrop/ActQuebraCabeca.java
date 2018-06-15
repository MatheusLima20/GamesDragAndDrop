package com.example.mateu.gamesdraganddrop;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mateu.gamesdraganddrop.Tabuleiro.Tabuleiro;

public class ActQuebraCabeca extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {

    private Tabuleiro tabuleiro;
    private LinearLayout lLImgsQuebraCabeca;
    private GridLayout gridTabuleiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_quebra_cabeca);
        setTitle("Quebra Cabeça");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        inicializaAtributos();

    }

    private void inicializaAtributos(){

        int[] imgs = {
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3,
                R.drawable.img4,
                R.drawable.img5,
                R.drawable.img6,
                R.drawable.img7,
                R.drawable.img8,
                R.drawable.img9,
                R.drawable.img10,
                R.drawable.img11,
                R.drawable.img12,
                R.drawable.img13,
                R.drawable.img14,
                R.drawable.img15,
                R.drawable.img16
        };

        tabuleiro = new Tabuleiro(imgs);

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


    public void btnVerifiarQb(View view) {

        String acertou = (verificaTabuleiro())? "Parabéns Você Acertou!" : "Que pena, Você Errou!";

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Aviso");
        alert.setMessage(acertou);
        alert.setNeutralButton("Ok", null);
        alert.show();

    }

    private boolean verificaTabuleiro(){

        int amountChild = gridTabuleiro.getChildCount();

        boolean acertouTodas = true;

        for(int i = 0; i < amountChild; i++) {

            LinearLayout linearLayout = (LinearLayout) gridTabuleiro.getChildAt(i);

            Integer posicaoImg = null;

            if(linearLayout.getChildCount() > 1)
                posicaoImg = Integer.parseInt(linearLayout.getChildAt(1).getTag().toString());


            if (posicaoImg == null)
                return false;

            if(tabuleiro.getImagem(posicaoImg) != tabuleiro.getImagem(i))
                return false;



        }

        return acertouTodas;

    }

    public void btnVoltar(View view) {

        Intent i = new Intent(this, ActMain.class);
        startActivity(i);

    }
}
