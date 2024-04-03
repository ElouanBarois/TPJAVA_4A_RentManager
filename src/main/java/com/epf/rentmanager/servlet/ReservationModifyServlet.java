package com.epf.rentmanager.servlet;

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.ReservationDTO;

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
import java.util.List;

@WebServlet("/rents/modify")
public class ReservationModifyServlet extends HttpServlet{
    @Autowired
    ReservationService reservationService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ClientService clientService;
    private long reservationLong;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reservationID = request.getParameter("id");
        reservationLong = Long.parseLong(reservationID);
        Reservation reservation = null;
        ReservationDTO reservationDTO = new ReservationDTO();
        List<Vehicle> vehicleList = null;
        List<Client> clientList = null;
        Client client = null;
        Vehicle vehicle =null;
        try {
            reservation = reservationService.findReservationById(reservationLong);
            vehicleList = vehicleService.findAll();
            clientList = clientService.findAll();
            client = clientService.findById(reservation.getClientId());
            vehicle = vehicleService.findById(reservation.getVehicleId());
            reservationDTO.setId(reservation.getId());
            reservationDTO.setDebut(reservation.getDebut());
            reservationDTO.setFin(reservation.getFin());
            reservationDTO.setClientNom(client.getNom());
            reservationDTO.setClientPrenom(client.getPrenom());
            reservationDTO.setVehicleManufacturer(vehicle.getConstructeur());
            reservationDTO.setVehicleModele(vehicle.getModele());
        } catch (ServiceException ex) {
            throw new RuntimeException(ex);
        }
        request.setAttribute("vehicles", vehicleList);
        request.setAttribute("clients", clientList);
        request.setAttribute("reservation", reservation);
        request.setAttribute("reservationDTO", reservationDTO);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/modify.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringClientID = request.getParameter("client");
        String stringVehicleID = request.getParameter("car");
        String stringDebut = request.getParameter("begin");
        String stringFin = request.getParameter("end");
        long clientLong = Long.parseLong(stringClientID);
        long vehicleLong = Long.parseLong(stringVehicleID);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate debut = LocalDate.parse(stringDebut, formatter);
        LocalDate fin = LocalDate.parse(stringFin, formatter);
        Reservation reservation= null;


        try {
            reservation = reservationService.findReservationById(reservationLong);
            reservation.setClientId(clientLong);
            reservation.setVehicleId(vehicleLong);
            reservation.setDebut(debut);
            reservation.setFin(fin);
            reservationService.update(reservation);
            response.sendRedirect(request.getContextPath() + "/rents");

        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de mise à jour de la réservation.");
        }
    }
}
