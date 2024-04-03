package com.epf.rentmanager.servlet;
import javax.servlet.ServletException;

import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.ReservationDTO;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
@WebServlet("/rents")
public class ReservationListServlet extends HttpServlet{
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
        List<ReservationDTO> reservationDTOList = new ArrayList<>();

        try {
            List<Reservation> reservations = reservationService.getAllReservations();
            for (Reservation reservation : reservations) {
                Vehicle vehicle = vehicleService.findById(reservation.getVehicleId());
                Client client = clientService.findById(reservation.getClientId());
                ReservationDTO reservationDTO = new ReservationDTO();
                reservationDTO.setId(reservation.getId());
                reservationDTO.setDebut(reservation.getDebut());
                reservationDTO.setFin(reservation.getFin());
                reservationDTO.setClientNom(client.getNom());
                reservationDTO.setClientPrenom(client.getPrenom());
                reservationDTO.setVehicleManufacturer(vehicle.getConstructeur());
                reservationDTO.setVehicleModele(vehicle.getModele());
                reservationDTOList.add(reservationDTO);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("reservations", reservationDTOList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reservationID = request.getParameter("id");
        long reservationLong = Long.parseLong(reservationID);
        try {
            reservationService.deleteReservation(reservationLong);
            response.sendRedirect(request.getContextPath() + "/rents");
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la suppression de la reservation (Servlet).");
        }
    }

}
