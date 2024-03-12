package com.epf.rentmanager.model;
import java.time.LocalDate;
public class ReservationDTO {
    private long id;
    private String clientNom;
    private String clientPrenom;
    private String vehicleManufacturer;
    private String vehicleModele;
    private LocalDate debut;
    private LocalDate fin;

    public ReservationDTO(long id, String clientNom, String clientPrenom, String vehicleManufacturer, String vehicleModele, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.clientNom = clientNom;
        this.clientPrenom = clientPrenom;
        this.vehicleManufacturer = vehicleManufacturer;
        this.vehicleModele = vehicleModele;
        this.debut = debut;
        this.fin = fin;
    }
    public ReservationDTO() {
        // Empty constructor
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }

    public String getClientPrenom() {
        return clientPrenom;
    }

    public void setClientPrenom(String clientPrenom) {
        this.clientPrenom = clientPrenom;
    }

    public String getVehicleManufacturer() {
        return vehicleManufacturer;
    }

    public void setVehicleManufacturer(String vehicleManufacturer) {
        this.vehicleManufacturer = vehicleManufacturer;
    }

    public String getVehicleModele() {
        return vehicleModele;
    }

    public void setVehicleModele(String vehicleModele) {
        this.vehicleModele = vehicleModele;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", clientNom='" + clientNom + '\'' +
                ", clientPrenom='" + clientPrenom + '\'' +
                ", vehicleManufacturer='" + vehicleManufacturer + '\'' +
                ", vehicleModele='" + vehicleModele + '\'' +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }
}