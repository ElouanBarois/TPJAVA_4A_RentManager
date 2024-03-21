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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@WebServlet("/cars/details")
public class VehicleDetailsServlet extends HttpServlet{
    @Autowired
    ClientService clientService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleID = request.getParameter("id");
        long vehicleLong = Long.parseLong(vehicleID);
        Client client = null;
        Vehicle vehicle = null;
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>();
        List<Client> clientList = new ArrayList<>();
        Set<Long> uniqueClientIds = new HashSet<>();
        int numberOfUniqueClientIds = 0;
        try {
            vehicle = vehicleService.findById(vehicleLong);
            reservationList = reservationService.findReservationsByVehicleId(vehicleLong);
            for (Reservation reservation : reservationList) {
                Client clientR = clientService.findById(reservation.getClientId());
                Long clientId = reservation.getClientId();
                uniqueClientIds.add(clientId);
                ReservationDTO reservationDTO = new ReservationDTO();
                reservationDTO.setId(reservation.getId());
                reservationDTO.setDebut(reservation.getDebut());
                reservationDTO.setFin(reservation.getFin());
                reservationDTO.setClientNom(clientR.getNom());
                reservationDTO.setClientPrenom(clientR.getPrenom());
                reservationDTOList.add(reservationDTO);
            }
            for (Long uniqueClientID : uniqueClientIds) {
                client = clientService.findById(uniqueClientID);
                clientList.add(client);
            }
            numberOfUniqueClientIds = uniqueClientIds.size();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("nbClient", numberOfUniqueClientIds);
        request.setAttribute("clients", clientList);
        request.setAttribute("vehicle", vehicle);

        request.setAttribute("reservationsR", reservationDTOList);
        request.setAttribute("reservations", reservationList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp").forward(request, response);

    }

}
