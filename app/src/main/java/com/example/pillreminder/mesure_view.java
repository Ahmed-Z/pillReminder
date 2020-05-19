package com.example.pillreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mesure_view extends AppCompatActivity {
    private EditText temperatureField;
    private EditText pressionArtérielleField;
    private EditText rythmeCardiaqueField;
    private EditText poidsField;
    private EditText glycémieField;
    private Button saveButton;
    private String patientId;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String patientEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mesure_view);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    patientId = user.getUid();
                    patientEmail = user.getEmail();
                    mAuth.removeAuthStateListener(mAuthListener);
                }
            }
        };

        temperatureField = findViewById(R.id.temperatureField);
        pressionArtérielleField = findViewById(R.id.pressionArtérielleField);
        rythmeCardiaqueField = findViewById(R.id.rythmeCardiaqueField);
        poidsField = findViewById(R.id.poidsField);
        glycémieField = findViewById(R.id.glycémieField);
        saveButton = findViewById(R.id.deleteButton);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(temperatureField.getText().toString())){
                    databaseReference = database.getReference().child("Users").child(patientId).child("temperature");
                    databaseReference.setValue(temperatureField.getText().toString());
                    Log.d("TAG",temperatureField.getText().toString());
                    if (Float.parseFloat(temperatureField.getText().toString()) > 37.5){
                        Log.d("TAG","temperature elevééééé");
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setData(Uri.parse("mailto:"));
                        String to = "ahmed.veno@gmail.com";
                        intent.putExtra(Intent.EXTRA_EMAIL,to);
                        intent.putExtra(Intent.EXTRA_SUBJECT,"TEMPERATUE ELEVEE ");
                        intent.putExtra(Intent.EXTRA_TEXT, "Attention température élevée du patient ayant l'email "+ patientEmail + " : " + temperatureField.getText().toString());
                        intent.setType("message/rfc822");
                        Intent chooser = Intent.createChooser(intent,"Send Email");
                        startActivity(Intent.createChooser(intent,"Send Email"));
                        Log.d("TAG","message envoyé");
                    }
                }
                if (!TextUtils.isEmpty(pressionArtérielleField.getText().toString())){
                    databaseReference = database.getReference().child("Users").child(patientId).child("pressionArtérielle");
                    databaseReference.setValue(pressionArtérielleField.getText().toString());
                }
                if (!TextUtils.isEmpty(rythmeCardiaqueField.getText().toString())){
                    databaseReference = database.getReference().child("Users").child(patientId).child("rythmeCardiaque");
                    databaseReference.setValue(rythmeCardiaqueField.getText().toString());
                }
                if (!TextUtils.isEmpty(poidsField.getText().toString())){
                    databaseReference = database.getReference().child("Users").child(patientId).child("poids");
                    databaseReference.setValue(poidsField.getText().toString());
                }
                if (!TextUtils.isEmpty(glycémieField.getText().toString())){
                    databaseReference = database.getReference().child("Users").child(patientId).child("glycémie");
                    databaseReference.setValue(glycémieField.getText().toString());
                }
                Toast.makeText(mesure_view.this, "Informations enregistrées", Toast.LENGTH_LONG).show();
                startActivity(new Intent(mesure_view.this,patientDashboard.class));
                finish();


            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
