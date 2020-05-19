package com.example.pillreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class medecinDashboard extends AppCompatActivity {
    private Button buttonAjoutMalade;
    private Button buttonAjoutProgramme;
    private Button buttonGererPatient;
    private Button buttonGererProgramme;
    private Button localiserButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medecin_activity);
        mAuth = FirebaseAuth.getInstance();
        buttonAjoutMalade = findViewById(R.id.buttonAjoutMalade);
        buttonAjoutProgramme = findViewById(R.id.buttonAjoutProgramme);
        buttonGererPatient = findViewById(R.id.buttonGererPatient);
        localiserButton = findViewById(R.id.localiserButton);
        buttonGererProgramme = findViewById(R.id.buttonGererProgramme);

        buttonGererPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medecinDashboard.this, list_patients.class));
            }
        });

        buttonAjoutMalade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medecinDashboard.this,ajout_malade.class));
            }
        });

        buttonAjoutProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medecinDashboard.this,ajout_programme.class));
            }
        });
        buttonGererProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medecinDashboard.this,list_Programmes.class));
            }
        });
        localiserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medecinDashboard.this,MapsActivity.class));
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_signout){
            mAuth.signOut();
            startActivity(new Intent(medecinDashboard.this,MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
