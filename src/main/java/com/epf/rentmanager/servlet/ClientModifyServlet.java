package com.epf.rentmanager.servlet;

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.ReservationDTO;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/users/modify")
public class ClientModifyServlet extends HttpServlet {
    @Autowired
    ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientID = request.getParameter("id");
        long clientLong = Long.parseLong(clientID);
        Client client = null;
        try {
            client = clientService.findById(clientLong);

            } catch (ServiceException ex) {
            throw new RuntimeException(ex);
        }

        request.setAttribute("client", client);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/modify.jsp").forward(request, response);

    }
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String Nom = request.getParameter("last_name");
            String Prenom = request.getParameter("first_name");
            String email = request.getParameter("email");
            String Stringnaissance = request.getParameter("Naissance");
            String clientID = request.getParameter("id");
            long clientLong = Long.parseLong(clientID);
            Client client = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate naissance = LocalDate.parse(Stringnaissance, formatter);
            try {
                client = clientService.findById(clientLong);
                client.setNom(Nom);
                client.setPrenom(Prenom);
                client.setEmail(email);
                client.setNaissance(naissance);
                clientService.update(client);
                response.sendRedirect(request.getContextPath() + "/users");

            } catch (ServiceException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de mise à jour du client.");
            }
        }


}
