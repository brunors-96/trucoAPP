package com.example.bruno.truco;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    private EditText editLogin;
    private EditText editSenha;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editLogin = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        mAuth = FirebaseAuth.getInstance();
    }

    public void logarActivity(View view){
        String sLogin = editLogin.getText().toString();
        String sSenha = editSenha.getText().toString();
        mAuth.signInWithEmailAndPassword(sLogin, sSenha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();

                           Intent intent = new Intent(MainActivity.this, Menu.class);
                           startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Erro ao logar.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void registerActivity(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
