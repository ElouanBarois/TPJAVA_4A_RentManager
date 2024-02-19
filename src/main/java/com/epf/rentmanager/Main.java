package com.epf.rentmanager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.ui.cli.ClientCLI;
public class Main {

    public static void main(String[] args) {
        ClientCLI clientCLI = new ClientCLI();
        clientCLI.listClients(); // Example usage of a method from ClientCLI
        clientCLI.createClient();

    }
}
