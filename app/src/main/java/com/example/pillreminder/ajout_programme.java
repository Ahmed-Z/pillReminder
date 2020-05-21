package com.example.pillreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class ajout_programme extends AppCompatActivity {

    private Button saveButton;
    private Spinner spinnerField;
    private Spinner spinnerField2;
    private EditText programField;
    private EditText emailField;
    private EditText medicamentField;
    private TextView dateDebutField;
    private TextView dateFinField;
    private DatePickerDialog.OnDateSetListener mDateListener1;
    private DatePickerDialog.OnDateSetListener mDateListener2;
    private String dateDebut;
    private String dateFin;
    private TextView timeField1;
    private TextView timeField2;
    private TextView timeField3;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private String medecinId;
    private EditText descriptionField;
    private EditText quantiteField;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_programme);
        database = FirebaseDatabase.getInstance();
        saveButton = findViewById(R.id.deleteButton);
        programField = findViewById(R.id.programField);
        emailField = findViewById(R.id.emailPatientField);
        medicamentField = findViewById(R.id.medicamentField);
        dateDebutField = findViewById(R.id.datedebutField);
        dateFinField = findViewById(R.id.datefinField);
        spinnerField = findViewById(R.id.spinnerField);
        spinnerField2 = findViewById(R.id.spinnerField2);
        timeField1 = findViewById(R.id.timeField1);
        timeField2 = findViewById(R.id.timeField2);
        timeField3 = findViewById(R.id.timeField3);
        mAuth = FirebaseAuth.getInstance();
        descriptionField = findViewById(R.id.descriptionField);
        database = FirebaseDatabase.getInstance();
        quantiteField = findViewById(R.id.quantiteField);
        Calendar cal = Calendar.getInstance();
        final int hour = cal.get(Calendar.HOUR_OF_DAY);
        final int minute = cal.get(Calendar.MINUTE);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    medecinId = user.getUid();
                    mAuth.removeAuthStateListener(mAuthListener);
                }
            }
        };

        timeField1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ajout_programme.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeField1.setText(hourOfDay + ":" + minute);
                    }
                },hour,minute,android.text.format.DateFormat.is24HourFormat(ajout_programme.this));
                timePickerDialog.show();
            }
        });

        timeField2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ajout_programme.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeField2.setText(hourOfDay + ":" + minute);
                    }
                },hour,minute,android.text.format.DateFormat.is24HourFormat(ajout_programme.this));
                timePickerDialog.show();
            }
        });

        timeField3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ajout_programme.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeField3.setText(hourOfDay + ":" + minute);
                    }
                },hour,minute,android.text.format.DateFormat.is24HourFormat(ajout_programme.this));
                timePickerDialog.show();
            }
        });

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(ajout_programme.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.nbrefois));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerField.setAdapter(myAdapter);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(ajout_programme.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.quantité));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerField2.setAdapter(myAdapter2);


        dateDebutField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(ajout_programme.this, android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        mDateListener1,
                        year,month,day
                        );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dateFinField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(ajout_programme.this, android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        mDateListener2,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                dateDebut = dayOfMonth + "/"+ month + "/"+year;
                dateDebutField.setText(dateDebut);
            }
        };
        mDateListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                dateFin = dayOfMonth + "/"+ month + "/"+year;
                dateFinField.setText(dateFin);
            }
        };

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nomProgramme = programField.getText().toString().trim();
                final String email = emailField.getText().toString().trim();
                final String medicament = medicamentField.getText().toString().trim();
                final String dateDebut = dateDebutField.getText().toString().trim();
                final String dateFin = dateFinField.getText().toString().trim();
                final String foisParJour = spinnerField.getSelectedItem().toString().trim();
                final String q = quantiteField.getText().toString().trim();
                final String quantité =  q + " " + spinnerField2.getSelectedItem().toString().trim();
                final  String time1 = timeField1.getText().toString();
                final  String time2 = timeField2.getText().toString();
                final  String time3 = timeField3.getText().toString();
                final String description  = descriptionField.getText().toString().trim();

                if(!TextUtils.isEmpty(nomProgramme) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(medicament) && !TextUtils.isEmpty(dateDebut) &&
                        !TextUtils.isEmpty(dateFin) &&!TextUtils.isEmpty(time1) &&!TextUtils.isEmpty(q) ){
                    Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("email").equalTo(email);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null){
                                databaseReference = database.getReference().child("Programme");
                                DatabaseReference currentPatientDb = databaseReference.child((nomProgramme+"-"+medecinId));

                                Map<String,String> dataToSave = new HashMap<>();
                                dataToSave.put("medecinId",medecinId);
                                dataToSave.put("nomProgramme",nomProgramme);
                                dataToSave.put("emailPatient",email);
                                dataToSave.put("medicament",medicament);
                                dataToSave.put("dateDebut",dateDebut);
                                dataToSave.put("dateFin",dateFin);
                                dataToSave.put("prise1",time1);
                                dataToSave.put("prise2",time2);
                                dataToSave.put("prise3",time3);
                                dataToSave.put("description",description);
                                dataToSave.put("foisParJour",foisParJour);
                                dataToSave.put("quantité",quantité);
                                currentPatientDb.setValue(dataToSave);
                                Toast.makeText(ajout_programme.this, "Programme Ajouté", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ajout_programme.this, medecinDashboard.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(ajout_programme.this, "Patient n'existe pas", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });

                }
                else{
                    Toast.makeText(ajout_programme.this, "Information missing or invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

}
