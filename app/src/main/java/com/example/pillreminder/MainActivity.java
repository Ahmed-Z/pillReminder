package com.example.pillreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "MainActivity";
    private EditText email;
    private EditText password;
    private Button loginButton;
    private  String currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        email = findViewById(R.id.emailField);
        password = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);
        databaseReference = database.getReference("message");

      mAuthListener = new FirebaseAuth.AuthStateListener() {
          @Override
          public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
              FirebaseUser user = firebaseAuth.getCurrentUser();
              if (user != null){
                  currentUser = user.getEmail();
              }
          }
      };

        loginButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             String emailString = email.getText().toString();
             String pwd = password.getText().toString();
             if (!emailString.equals("") && !pwd.equals("")){
                 mAuth.signInWithEmailAndPassword(emailString,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(!task.isSuccessful()) {
                             Toast.makeText(MainActivity.this, "Failed to sign in", Toast.LENGTH_LONG).show();
                         }
                         else{
                             if (currentUser.equals("medecin@gmail.com")){
                                 startActivity(new Intent(MainActivity.this,medecinDashboard.class));
                                 finish();
                             }
                             else{
                                 startActivity(new Intent(MainActivity.this,patientDashboard.class));
                                 finish();
                             }
                         }


                     }
                 });
             }
          }
      });

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
