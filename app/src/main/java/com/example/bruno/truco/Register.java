package com.example.bruno.truco;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText editEmail;
    private EditText editSenha;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editEmail  = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void register(View v){
        String sEmail = editEmail.getText().toString();
        String sSenha = editSenha.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(sEmail, sSenha).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Registrado com Sucesso",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Register.this, "Erro ao Registrar",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}