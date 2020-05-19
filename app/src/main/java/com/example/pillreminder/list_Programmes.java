package com.example.pillreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class list_Programmes extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceProgramme;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private programmeRecyclerAdapter programmeRecyclerAdapter;
    private List<Programme> ProgrammeList;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list__programmes);

    }

    @Override
    protected void onStart() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        ProgrammeList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        super.onStart();
        final String currentUserEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        final String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();



        databaseReference = database.getReference().child("Programme");
        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Programme programme = dataSnapshot.getValue(Programme.class);
                if (programme.getEmailPatient().equals(currentUserEmail) || programme.getMedecinId().equals(currentUserID)){
                    Log.d("TAG",programme.getEmailPatient());

                    ProgrammeList.add(programme);
                    programmeRecyclerAdapter = new programmeRecyclerAdapter(list_Programmes.this,ProgrammeList);
                    recyclerView.setAdapter(programmeRecyclerAdapter);
                    programmeRecyclerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}