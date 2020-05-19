package com.example.pillreminder;

public class Programme {
    private String medecinId;
    private String nomProgramme;
    private String emailPatient;
    private String medicament;
    private String dateDebut;
    private String dateFin;
    private String prise1;
    private String prise2;
    private String prise3;
    private String description;
    private String foisParJour;
    private String quantité;

    public Programme() {
    }

    public Programme(String medecinId, String nomProgramme, String emailPatient, String medicament, String dateDebut, String dateFin, String prise1, String prise2, String prise3, String description, String foisParJour, String quantité) {
        this.medecinId = medecinId;
        this.nomProgramme = nomProgramme;
        this.emailPatient = emailPatient;
        this.medicament = medicament;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prise1 = prise1;
        this.prise2 = prise2;
        this.prise3 = prise3;
        this.description = description;
        this.foisParJour = foisParJour;
        this.quantité = quantité;
    }

    public String getFoisParJour() {
        return foisParJour;
    }

    public void setFoisParJour(String foisParJour) {
        this.foisParJour = foisParJour;
    }

    public String getQuantité() {
        return quantité;
    }

    public void setQuantité(String quantité) {
        this.quantité = quantité;
    }

    public String getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(String medecinId) {
        this.medecinId = medecinId;
    }

    public String getNomProgramme() {
        return nomProgramme;
    }

    public void setNomProgramme(String nomProgramme) {
        this.nomProgramme = nomProgramme;
    }

    public String getEmailPatient() {
        return emailPatient;
    }

    public void setEmailPatient(String emailPatient) {
        this.emailPatient = emailPatient;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getPrise1() {
        return prise1;
    }

    public void setPrise1(String prise1) {
        this.prise1 = prise1;
    }

    public String getPrise2() {
        return prise2;
    }

    public void setPrise2(String prise2) {
        this.prise2 = prise2;
    }

    public String getPrise3() {
        return prise3;
    }

    public void setPrise3(String prise3) {
        this.prise3 = prise3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
