package com.example.mateu.gamesdraganddrop.Tabuleiro;


import com.example.mateu.gamesdraganddrop.R;

public class Tabuleiro {

    private final int[] IMAGENS = new int[] {

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
            R.drawable.img16,

    };

    public final int TAMANHOTABULEIRO = IMAGENS.length;


    public int getImagem(int posicao){

        return IMAGENS[posicao];

    }


}
