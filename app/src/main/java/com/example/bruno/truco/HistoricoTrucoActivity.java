package com.example.bruno.truco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bruno.truco.adapter.PartidaAdapter;
import com.example.bruno.truco.model.Partida;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoricoTrucoActivity extends AppCompatActivity {
    private TextView textEmail;
    private Button buttonAtualizar;
    private Button buttonMenu;
    private ProgressBar progressPartidas;
    private ListView listView;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private String uid;

    private List<Partida> partidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_truco);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        textEmail = findViewById(R.id.textEmail);

        if (currentUser != null) {
            uid = currentUser.getUid().toString();
            String email = currentUser.getEmail();

            textEmail.setText("Bem vindo " + email);
        }

        buttonAtualizar = findViewById(R.id.buttonAtualizar);
        buttonMenu = findViewById(R.id.buttonMenu);
        listView = findViewById(R.id.listView);
        progressPartidas = findViewById(R.id.progressPartidas);
        partidas = new ArrayList<>();
    }

    @Override
    protected void onStart(){
        super.onStart();
        carregarPartidas();
    }

    public void carregarPartidas(){
        Query mQuery = databaseReference.child("partidas").orderByChild("id")
                .limitToFirst(50);

        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!partidas.isEmpty())
                    partidas.clear();

                progressPartidas.setVisibility(ProgressBar.VISIBLE);

                for(DataSnapshot partidaSnapshot : dataSnapshot.getChildren()){
                    partidas.add(partidaSnapshot.getValue(Partida.class));
                }

                ArrayAdapter<Partida> adapter = new PartidaAdapter(HistoricoTrucoActivity.this,R.layout.partida_item,partidas);

                listView.setAdapter(adapter);
                progressPartidas.setVisibility(ProgressBar.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void voltarMenu(View view) {
        Intent intent = new Intent(HistoricoTrucoActivity.this, Menu.class);
        startActivity(intent);
    }

    public void atualizarHistorico(View view){
        carregarPartidas();
    }
}
