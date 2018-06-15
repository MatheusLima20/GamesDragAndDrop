package com.example.mateu.gamesdraganddrop;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mateu.gamesdraganddrop.Tabuleiro.Tabuleiro;

public class ActQuebraCabecaSlider extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {

    private Tabuleiro tab;
    private GridLayout gridTabuleiro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_quebra_cabeca_slider);
        setTitle(R.string.quebra_cabeca_slider);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        inicializaAtributos();

    }

    private void inicializaAtributos(){

        int [] imgs = {

                R.drawable.img1_slider,
                R.drawable.img2_slider,
                R.drawable.img3_slider,
                R.drawable.img4_slider,
                R.drawable.img5_slider,
                R.drawable.img6_slider,
                R.drawable.img7_slider,
                R.drawable.img8_slider

        };

        tab = new Tabuleiro(imgs);

        gridTabuleiro = findViewById(R.id.gridTabuleiroSlider);

        for(int i = gridTabuleiro.getChildCount() - 1; i >= 0 ; i--){

            LinearLayout linearLayoutImgs = (LinearLayout) gridTabuleiro.getChildAt(i);

            linearLayoutImgs.setTag(i);

            ImageView img = (ImageView) linearLayoutImgs.getChildAt( 1);

            if(img != null)
                img.setOnTouchListener(this);

            linearLayoutImgs.setOnDragListener(this);

        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){

            ClipData data = ClipData.newPlainText("", "");

            View.DragShadowBuilder shandow = new View.DragShadowBuilder(null);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                v.startDragAndDrop(data, shandow, v, 1);
            else
                v.startDrag(data, shandow, v, 1);


            return true;

        }

        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()){

            case DragEvent.ACTION_DROP:

                View view = (View) event.getLocalState();
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                LinearLayout conteiner = (LinearLayout) v;

                if(conteiner.getChildAt(0).getTag() == null &&
                        conteiner.getChildAt(1) == null && verificaProximoCampo(
                                Integer.parseInt(viewGroup.getTag().toString()),
                                Integer.parseInt(conteiner.getTag().toString())
                )) {

                    viewGroup.removeView(view);

                    conteiner.addView(view);

                }

                break;

            default: break;

        }

        return true;
    }

    public void voltar(View view) {

        Intent i = new Intent(this, ActMain.class);

        startActivity(i);

    }

    public void verificar(View view) {

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

            if(linearLayout.getChildAt(1) != null)
                posicaoImg = Integer.parseInt(linearLayout.getChildAt(1).getTag().toString());

            if(i != amountChild -1 && posicaoImg == null)
                return false;

            if(posicaoImg != null && tab.getImagem(posicaoImg) != tab.getImagem(i))
                return false;



        }

        return acertouTodas;

    }

    private boolean verificaProximoCampo(int tag, int tag2){

        if((tag + 1) == tag2 || (tag - 1) == tag2 ||
                (tag + 3) == tag2 || (tag - 3) == tag2
                )
            return true;

        return false;

    }

}
