package com.example.pillreminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class patientRecyclerAdapter extends RecyclerView.Adapter<patientRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Patient> patientList;

    public patientRecyclerAdapter(Context context, List<Patient> patientList) {
        this.context = context;
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_row,parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Patient patient = patientList.get(position);
        holder.nom.setText(patient.getNom());
        holder.age.setText(patient.getAge());
        holder.maladie.setText(patient.getMaladie());
        holder.prenom.setText(patient.getPrenom());
        holder.Email.setText(patient.getEmail());
        holder.idPatient.setText(patient.getId());
        holder.docteurId.setText(patient.getDocteurId());
        holder.temperature.setText(patient.getTemperature());
        holder.pressionArtérielle.setText(patient.getPressionArtérielle());
        holder.rythmeCardiaque.setText(patient.getRythmeCardiaque());
        holder.poids.setText(patient.getPoids());
        holder.glycémie.setText(patient.getGlycémie());

       holder.deleteButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               holder.mDatabase.getInstance().getReference("Users").child(patient.getId()).removeValue();
               context.startActivity(new Intent(context,list_patients.class));
               Toast.makeText(context, "Patient supprimé", Toast.LENGTH_LONG).show();
               ((Activity)context).finish();
           }
       });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        public TextView age;
        public TextView maladie;
        public TextView prenom;
        public TextView Email;
        public TextView idPatient;
        public TextView docteurId;
        public TextView temperature;
        public TextView pressionArtérielle;
        public TextView rythmeCardiaque;
        public TextView poids;
        public TextView glycémie;
        public Button deleteButton;
        public FirebaseDatabase mDatabase;


        public ViewHolder(@NonNull View view, Context ctx) {
            super(view);

            context = ctx;
            nom = (TextView) view.findViewById(R.id.nomPatient);
            prenom = (TextView) view.findViewById(R.id.prenomPatient);
            Email = (TextView) view.findViewById(R.id.emailPatient);
            age = (TextView) view.findViewById(R.id.agePatient);
            idPatient = (TextView) view.findViewById(R.id.idPatient);
            maladie = (TextView) view.findViewById(R.id.maladiePatient);
            docteurId = (TextView) view.findViewById(R.id.docteurId);
            temperature = (TextView) view.findViewById(R.id.temperature);
            pressionArtérielle = (TextView) view.findViewById(R.id.pressionArtérielle);
            rythmeCardiaque = (TextView) view.findViewById(R.id.rythmeCardiaque);
            poids = (TextView) view.findViewById(R.id.poids);
            glycémie = (TextView) view.findViewById(R.id.pressionArtérielle);
            deleteButton = (Button) view.findViewById(R.id.deleteButton);

        }
    }
}
