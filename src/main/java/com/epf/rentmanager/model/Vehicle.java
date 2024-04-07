package com.epf.rentmanager.model;

public class Vehicle {
    private long id;
    private String constructeur;
    private String modele;
    private int nbPlaces;


    public Vehicle(long id, String constructeur, String modele, int nbPlaces) {
        this.id = id;
        this.constructeur = constructeur;
        this.modele = modele;
        this.nbPlaces = nbPlaces;
    }
    public Vehicle(String constructeur, String modele, int nbPlaces) {
        this.constructeur = constructeur;
        this.modele = modele;
        this.nbPlaces = nbPlaces;
    }

    public Vehicle() {

    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", constructeur='" + constructeur + '\'' +
                ", modele='" + modele + '\'' +
                ", nbPlaces=" + nbPlaces +
                '}';
    }
}

