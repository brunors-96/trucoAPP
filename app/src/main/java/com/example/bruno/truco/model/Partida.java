package com.example.bruno.truco.model;

public class Partida {
    private String id;
    private String jogador1;
    private String jogador2;
    private boolean vitoria;

    public Partida() {
    }

    public Partida(String id, String jogador1, String jogador2, boolean vitoria) {
        this.id = id;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.vitoria = vitoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJogador1() {
        return jogador1;
    }

    public void setJogador1(String jogador1) {
        this.jogador1 = jogador1;
    }

    public String getJogador2() {
        return jogador2;
    }

    public void setJogador2(String jogador2) {
        this.jogador2 = jogador2;
    }

    public boolean isVitoria() {
        return vitoria;
    }

    public void setVitoria(boolean vitoria) {
        this.vitoria = vitoria;
    }
}
