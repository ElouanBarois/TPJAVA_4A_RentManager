package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;

import java.util.Scanner;

public class VehicleCLI {

    private VehicleService vehicleService;

    public VehicleCLI(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    public VehicleCLI() {
        this.vehicleService = new VehicleService();
    }

    public void createVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Création d'un véhicule");
        long id=0;
        System.out.print("Constructeur : ");
        String constructeur = scanner.nextLine();
        System.out.print("Modèle : ");
        String modele = scanner.nextLine();
        System.out.print("Nombre de places : ");
        int nbPlaces = scanner.nextInt();

        try {
            Vehicle vehicle = new Vehicle(id, constructeur, modele, nbPlaces);
            vehicleService.create(vehicle);
            System.out.println("Véhicule créé avec succès !");
        } catch (ServiceException e) {
            System.out.println("Erreur lors de la création du véhicule : " + e.getMessage());
        }
    }


    public void listVehicles() {
        System.out.println("Liste des véhicules : ");
        try {
            vehicleService.findAll().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Erreur lors de la récupération de la liste des véhicules : " + e.getMessage());
        }
    }
    public void deleteVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Suppression d'un véhicule");
        System.out.print("ID du véhicule à supprimer : ");
        long vehicleId = scanner.nextLong();

        try {
            Vehicle vehicle = vehicleService.findById(vehicleId);
            vehicleService.delete(vehicle);
            System.out.println("Véhicule supprimé avec succès !");
        } catch (ServiceException e) {
            System.out.println("Erreur lors de la suppression du véhicule : " + e.getMessage());
        }
    }


}
