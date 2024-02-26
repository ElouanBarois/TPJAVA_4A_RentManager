package com.epf.rentmanager.ui.cli;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.utils.IOUtils;

public class ReservationCLI {

    private final ReservationService reservationService;

    public ReservationCLI() {
        this.reservationService = new ReservationService();
    }
    public ReservationCLI(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public void createReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Création d'une réservation");

        System.out.print("ID du client : ");
        long clientId = scanner.nextLong();
        System.out.print("ID du véhicule : ");
        long vehicleId = scanner.nextLong();
        System.out.print("Date de début (AAAA-MM-JJ) : ");
        String debutStr = scanner.next();
        LocalDate debut = LocalDate.parse(debutStr);
        System.out.print("Date de fin (AAAA-MM-JJ) : ");
        String finStr = scanner.next();
        LocalDate fin = LocalDate.parse(finStr);

        try {
            Reservation reservation = new Reservation(0, clientId, vehicleId, debut, fin);
            reservationService.createReservation(reservation);
            System.out.println("Réservation créée avec succès !");
        } catch (ServiceException e) {
            System.out.println("Erreur lors de la création de la réservation : " + e.getMessage());
        }
    }

    public void listAllReservations() {
        try {
            List<Reservation> reservations = reservationService.getAllReservations();
            if (reservations.isEmpty()) {
                System.out.println("Aucune réservation trouvée.");
            } else {
                System.out.println("Liste des réservations : ");
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            }
        } catch (ServiceException e) {
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }
    }

    public void listReservationsByClientId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Liste des réservations pour un client donné");
        System.out.print("ID du client : ");
        long clientId = scanner.nextLong();

        try {
            List<Reservation> reservations = reservationService.findReservationsByClientId(clientId);
            if (reservations.isEmpty()) {
                System.out.println("Aucune réservation trouvée pour ce client.");
            } else {
                System.out.println("Liste des réservations pour le client " + clientId + " : ");
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            }
        } catch (ServiceException e) {
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }
    }

    public void listReservationsByVehicleId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Liste des réservations pour un véhicule donné");
        System.out.print("ID du véhicule : ");
        long vehicleId = scanner.nextLong();

        try {
            List<Reservation> reservations = reservationService.findReservationsByVehicleId(vehicleId);
            if (reservations.isEmpty()) {
                System.out.println("Aucune réservation trouvée pour ce véhicule.");
            } else {
                System.out.println("Liste des réservations pour le véhicule " + vehicleId + " : ");
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            }
        } catch (ServiceException e) {
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }
    }

    private static void deleteReservation() {
        long id = IOUtils.readInt("Id de la reservation: ");

        ReservationService reservationService = ReservationService.getInstance();
        long mes = 0;
        try {
            mes = reservationService.deleteReservation(id);
        } catch (ServiceException e) {
            System.out.println(e);
        }
        System.out.println("Un vehicule a ete suprimer : " + mes);
    }
}
