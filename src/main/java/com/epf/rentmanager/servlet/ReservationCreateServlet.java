package com.epf.rentmanager.servlet;
import javax.servlet.ServletException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;

import com.epf.rentmanager.servlet.ReservationListServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.epf.rentmanager.model.ReservationDTO;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.service.ClientService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet{
    @Autowired
    ReservationService reservationService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vehicle> vehicleList = null;
        List<Client> clientList = null;

        try {
            vehicleList = vehicleService.findAll();
            clientList = clientService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("vehicles", vehicleList);
        request.setAttribute("clients", clientList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringVehicleid = request.getParameter("car");
        String stringClientid = request.getParameter("client");
        String stringDebut = request.getParameter("begin");
        String stringFin = request.getParameter("end");
        long vehicleid= Long.parseLong(stringVehicleid);
        long clientid= Long.parseLong(stringClientid);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate debut = LocalDate.parse(stringDebut, formatter);
        LocalDate fin = LocalDate.parse(stringFin, formatter);
        Reservation reservation = new Reservation(0,clientid,vehicleid,debut,fin);

        // Appel du service pour insérer le véhicule dans la base de données

        try {
            reservationService.createReservation(reservation);
            response.sendRedirect(request.getContextPath() + "/rents");


        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création de la réservation. (servlet)");
        }
    }




}
