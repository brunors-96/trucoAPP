package com.example.bruno.truco.model;

import com.google.firebase.auth.FirebaseUser;

public class User {
    private String id;
    private String nome;

    public User(FirebaseUser user) {
        this.id = user.getUid();
    }

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
