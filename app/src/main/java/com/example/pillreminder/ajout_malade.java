package com.example.pillreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ajout_malade extends AppCompatActivity {
    private EditText emailField;
    private EditText passwordField;
    private EditText nomField;
    private EditText prenomField;
    private EditText ageField;
    private EditText maladieField;
    private String id;
    private FirebaseAuth mAuth;
    private String currentUser;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private Button saveButton;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_malade);
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("Users");
        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        nomField = findViewById(R.id.nomField);
        prenomField = findViewById(R.id.prenomField);
        maladieField = findViewById(R.id.maladieField);
        ageField = findViewById(R.id.ageField);
        saveButton = findViewById(R.id.deleteButton);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    currentUser = user.getUid();
                    mAuth.removeAuthStateListener(mAuthListener);
                }
            }
        };


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });


    }

    private void createNewAccount() {
        final String email,password,nom,prenom,maladie,ageString;
        email = emailField.getText().toString().trim();
        password = passwordField.getText().toString().trim();
        nom = nomField.getText().toString().trim();
        prenom = prenomField.getText().toString().trim();
        maladie = maladieField.getText().toString().trim();
        ageString = ageField.getText().toString().trim();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(nom) && !TextUtils.isEmpty(prenom)
                && !TextUtils.isEmpty(maladie) && !TextUtils.isEmpty(ageString)){
            mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    if (authResult != null){
                        //age = Integer.parseInt(ageString);
                        id = mAuth.getCurrentUser().getUid();
                        Map<String,String> dataToSave = new HashMap<>();

                        DatabaseReference currentUserDb = mDatabaseReference.child((id));
                        dataToSave.put("nom",nom);
                        dataToSave.put("id",id);
                        dataToSave.put("prenom",prenom);
                        dataToSave.put("email",email);
                        dataToSave.put("age",ageString);
                        dataToSave.put("maladie",maladie);
                        dataToSave.put("docteurId",currentUser);
                        dataToSave.put("temperature","inconnu");
                        dataToSave.put("pressionArtérielle","inconnu");
                        dataToSave.put("rythmeCardiaque","inconnu");
                        dataToSave.put("poids","inconnu");
                        dataToSave.put("glycémie","inconnu");
                        dataToSave.put("latitude","inconnu");
                        dataToSave.put("longitude","inconnu");
                        currentUserDb.setValue(dataToSave);

                        Toast.makeText(ajout_malade.this, "Patient enregistré", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ajout_malade.this, medecinDashboard.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(ajout_malade.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }

            });
        }
        else{
            Toast.makeText(ajout_malade.this, "Information missing or invalid", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
