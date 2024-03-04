package com.epf.rentmanager.servlet;
import javax.servlet.ServletException;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.servlet.VehicleListServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cars/create")
public class VehicleCreateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Dispatcher la requête vers la JSP de création de véhicule
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des paramètres du formulaire
        String manufacturer = request.getParameter("manufacturer");
        String modele = request.getParameter("modele");
        int seats = Integer.parseInt(request.getParameter("seats"));

        // Création d'une instance de Vehicle avec les données soumises par l'utilisateur
        Vehicle vehicle = new Vehicle(0,manufacturer, modele, seats);

        // Appel du service pour insérer le véhicule dans la base de données
        VehicleService vehicleService = new VehicleService();
        try {
            vehicleService.create(vehicle);
            // Redirection vers une page de confirmation ou une autre page appropriée
            response.sendRedirect(request.getContextPath() + "/cars");


        } catch (ServiceException e) {
            // Gestion des erreurs
            e.printStackTrace();
            // Vous pouvez rediriger vers une page d'erreur ou afficher un message d'erreur approprié
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création du véhicule.");
        }
    }

}
