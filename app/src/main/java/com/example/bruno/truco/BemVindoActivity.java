package com.example.bruno.truco;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bruno.truco.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;

public class BemVindoActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editNome;
    private EditText editEmail;
    private ImageView imageView;

    private FirebaseAuth firebaseAuth;

    private FirebaseUser firebaseUser;
    private StorageReference storageReference;
    private ArrayList<String> photos;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        textView = findViewById(R.id.textView);
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        imageView = findViewById(R.id.imageSelected);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(firebaseAuth.getUid().toString());
        if (firebaseUser != null) {
            String sEmail = firebaseUser.getEmail();
            textView.setText("Bem Vindo: "+sEmail);
        }
        else {
            textView.setText("email nulo");
        }

        storageReference = FirebaseStorage.getInstance().getReference();
        photos = new ArrayList<>();
    }

    public void photoPickerFunction(View view){
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                imageView.setImageURI(Uri.parse(photos.get(0)));
            }
        }
    }

    private void resetForm(){
        photos.clear();
        imageView.setImageResource(0);
    }

    public void sendPhotoFunction(View view) {
        if(photos.size() > 0){
            Uri file = Uri.fromFile(new File(photos.get(0)));
            StorageReference photoRef = storageReference.child("images");
            Atualizar();
            resetForm();
            photoRef.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(BemVindoActivity.this, "Arquivo Enviado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(BemVindoActivity.this, "Falha ao enviar arquivo.", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(this, "Nenhum arquivo carregado.", Toast.LENGTH_SHORT).show();
        }


    }

    public void Atualizar (){
        User user = mapper();
        databaseReference.setValue(user);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(BemVindoActivity.this, "Dados Salvos com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(BemVindoActivity.this, "Erro ao Salvar dados", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public User mapper(){

        String sNome = editNome.getText().toString();

        User user = new User(firebaseAuth.getCurrentUser());

        user.setNome(sNome);

        return user;
    }
}