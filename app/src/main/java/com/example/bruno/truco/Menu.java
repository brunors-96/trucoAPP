package com.example.bruno.truco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void trucoActivity(View view){
        Toast.makeText(Menu.this, "Partida iniciada", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Menu.this, TrucoActivity.class);
        startActivity(intent);
    }

    public void historyActivity(View view){
        Intent intent = new Intent(Menu.this, HistoricoTrucoActivity.class);
        startActivity(intent);
    }

    public void editActivity(View view){
        Intent intent = new Intent(Menu.this, BemVindoActivity.class);
        startActivity(intent);
    }
}
