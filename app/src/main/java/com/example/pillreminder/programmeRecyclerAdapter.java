package com.example.pillreminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class programmeRecyclerAdapter extends RecyclerView.Adapter<programmeRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Programme> programmeList;

    public programmeRecyclerAdapter(Context context, List<Programme> programmeList) {
        this.context = context;
        this.programmeList = programmeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.programme_row,parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Programme programme = programmeList.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getEmail().equals(programme.getEmailPatient())){
            holder.deleteButton.setVisibility(View.GONE);
        }
        holder.medecinId.setText(programme.getMedecinId());
        holder.nomProgramme.setText(programme.getNomProgramme());
        holder.emailPatient.setText(programme.getEmailPatient());
        holder.medicament.setText(programme.getMedicament());
        holder.dateDebut.setText(programme.getDateDebut());
        holder.dateFin.setText(programme.getDateFin());
        holder.prise1.setText(programme.getPrise1());
        holder.prise2.setText(programme.getPrise2());
        holder.prise3.setText(programme.getPrise3());
        holder.description.setText(programme.getDescription());
        holder.foisParJour.setText(programme.getFoisParJour());
        holder.quantité.setText(programme.getQuantité());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Programme").child(programme.getNomProgramme()+"-"+programme.getMedecinId()).removeValue();
                context.startActivity(new Intent(context,list_Programmes.class));
                Toast.makeText(context, "Programme supprimé", Toast.LENGTH_LONG).show();
                ((Activity)context).finish();


            }
        });
    }

    @Override
    public int getItemCount() {
        return programmeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView medecinId;
        public TextView nomProgramme;
        public TextView emailPatient;
        public TextView medicament;
        public TextView dateDebut;
        public TextView dateFin;
        public TextView prise1;
        public TextView prise2;
        public TextView prise3;
        public TextView description;
        public TextView foisParJour;
        public TextView quantité;
        public Button deleteButton;



        public ViewHolder(@NonNull View view, Context ctx) {
            super(view);

            context = ctx;
            medecinId = (TextView) view.findViewById(R.id.medecinId);
            nomProgramme = (TextView) view.findViewById(R.id.nomProgramme);
            emailPatient = (TextView) view.findViewById(R.id.emailPatient);
            dateDebut = (TextView) view.findViewById(R.id.dateDebut);
            dateFin = (TextView) view.findViewById(R.id.dateFin);
            prise1 = (TextView) view.findViewById(R.id.prise1);
            prise2 = (TextView) view.findViewById(R.id.prise2);
            prise3 = (TextView) view.findViewById(R.id.prise3);
            description = (TextView) view.findViewById(R.id.description);
            foisParJour = (TextView) view.findViewById(R.id.foisParJour);
            quantité = (TextView) view.findViewById(R.id.quantité);
            medicament = (TextView) view.findViewById(R.id.medicament);
            deleteButton = (Button) view.findViewById(R.id.deleteButton);

        }
    }
}
