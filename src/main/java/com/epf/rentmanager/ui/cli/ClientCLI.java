package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.util.Scanner;

public class ClientCLI {}
    /*
    private ClientService clientService;

    public ClientCLI(ClientService clientService) {
        this.clientService = clientService;
    }

    public ClientCLI() {
        this.clientService = new ClientService();
    }


    public void createClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Création d'un client");

        // Consume the newline character
        scanner.nextLine();

        // Prompt for user input
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
        String naissanceStr = scanner.nextLine();

        // Validate email format using regex pattern
        Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
        if (!emailPattern.matcher(email).matches()) {
            System.out.println("Erreur : Format d'email invalide.");
            return;
        }

        // Validate date format and parse it to LocalDate
        LocalDate naissance;
        try {
            naissance = LocalDate.parse(naissanceStr);
        } catch (Exception e) {
            System.out.println("Erreur : Format de date invalide. Utilisez le format AAAA-MM-JJ.");
            return;
        }

        // Check for empty fields
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || naissanceStr.isEmpty()) {
            System.out.println("Erreur : Veuillez saisir tous les champs.");
            return;
        }

        try {
            // Create client object and call service to create it
            Client client = new Client(0, nom, prenom, email, naissance);
            clientService.create(client);
            System.out.println("Client créé avec succès !");
        } catch (ServiceException e) {
            System.out.println("Erreur lors de la création du client : " + e.getMessage());
        }
    }


    public void listClients() {
        System.out.println("Liste des clients : ");
        try {
            clientService.findAll().forEach(System.out::println);
        } catch (ServiceException e) {
            System.out.println("Erreur lors de la récupération de la liste des clients : " + e.getMessage());
        }
    }

    public void deleteClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Suppression d'un client");
        System.out.print("ID du client à supprimer : ");
        long clientId = scanner.nextLong();

        try {
            Client client = clientService.findById(clientId);
            clientService.delete(client);
            System.out.println("Client supprimé avec succès !");
        } catch (ServiceException e) {
            System.out.println("Erreur lors de la suppression du client : " + e.getMessage());
        }
    }
}

*/