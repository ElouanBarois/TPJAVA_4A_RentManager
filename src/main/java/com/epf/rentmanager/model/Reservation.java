package com.epf.rentmanager.model;
import java.time.LocalDate;
public class Reservation {
    private long id;
    private long clientid;
    private long vehicleid;
    private java.time.LocalDate debut;
    private java.time.LocalDate fin;

    public Reservation(long id, long clientid, long vehicleid, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.clientid = clientid;
        this.vehicleid = vehicleid;
        this.debut = debut;
        this.fin = fin;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public long getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(int vehicleid) {
        this.vehicleid = vehicleid;
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
        return "Reservation{" +
                "id=" + id +
                ", clientid=" + clientid +
                ", vehicleid=" + vehicleid +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }
}
