package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;

import java.time.LocalDate;
import java.util.Scanner;

public class ClientCLI {

    private ClientService clientService;

    public ClientCLI(ClientService clientService) {
        this.clientService = clientService;
    }

    public void createClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Création d'un client");
        long id=0;
        scanner.nextLine(); // Consume the newline character
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
        String naissanceStr = scanner.nextLine();
        LocalDate naissance = LocalDate.parse(naissanceStr);

        try {
            Client client = new Client(id, nom, prenom, email, naissance);
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
