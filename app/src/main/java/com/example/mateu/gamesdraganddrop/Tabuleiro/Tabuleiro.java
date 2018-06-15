package com.example.mateu.gamesdraganddrop.Tabuleiro;

public class Tabuleiro {

    private int[] imgs;

    public int tamanhoTabuleiro;

    public Tabuleiro (int [] imgs){

        this.imgs = imgs;

        this.tamanhoTabuleiro = this.imgs.length;

    }

    public int getImagem(int posicao){

        return imgs[posicao];

    }


}
