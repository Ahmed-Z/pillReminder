package com.example.pillreminder;

public class Patient {
    private String nom;
    private String prenom;
    private String email;
    private String age;
    private String id;
    private String maladie;
    private String docteurId;
    private String temperature;
    private String pressionArtérielle;
    private String rythmeCardiaque;
    private String poids;
    private String glycémie;
    private String latitude;
    private String longitude;

    public Patient() {
    }


    public Patient(String nom, String prenom, String email, String age, String maladie) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.maladie = maladie;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaladie() {
        return maladie;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public String getDocteurId() {
        return docteurId;
    }

    public void setDocteurId(String docteurId) {
        this.docteurId = docteurId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressionArtérielle() {
        return pressionArtérielle;
    }

    public void setPressionArtérielle(String pressionArtérielle) {
        this.pressionArtérielle = pressionArtérielle;
    }

    public String getRythmeCardiaque() {
        return rythmeCardiaque;
    }

    public void setRythmeCardiaque(String rythmeCardiaque) {
        this.rythmeCardiaque = rythmeCardiaque;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getGlycémie() {
        return glycémie;
    }

    public void setGlycémie(String glycémie) {
        this.glycémie = glycémie;
    }
}

