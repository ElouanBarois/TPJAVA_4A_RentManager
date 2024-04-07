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
    private Long vehicleId;

    public ReservationDTO(long id, String clientNom, String clientPrenom, String vehicleManufacturer, String vehicleModele, LocalDate debut, LocalDate fin, Long vehicleId) {
        this.id = id;
        this.clientNom = clientNom;
        this.clientPrenom = clientPrenom;
        this.vehicleManufacturer = vehicleManufacturer;
        this.vehicleModele = vehicleModele;
        this.debut = debut;
        this.fin = fin;
        this.vehicleId = vehicleId;
    }
    public ReservationDTO() {
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

    public Long getVehicleId(){return vehicleId;}
    public void setVehicleId(Long vehicleId){this.vehicleId = vehicleId;}

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
                ", vehicleId=" + vehicleId +
                '}';
    }
}