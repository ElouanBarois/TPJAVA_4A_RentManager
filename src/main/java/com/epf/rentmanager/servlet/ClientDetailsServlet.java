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

@WebServlet("/users/details")
public class ClientDetailsServlet extends HttpServlet {
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


        String clientID = request.getParameter("id");
        long clientLong = Long.parseLong(clientID);
        Client client = null;
        Vehicle vehicle = null;
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>();
        List<Vehicle> vehicleList = new ArrayList<>();
        Set<Long> uniqueVehicleIds = new HashSet<>();
        int numberOfUniqueVehicleIds = 0;
        try {
            client = clientService.findById(clientLong);
            reservationList = reservationService.findReservationsByClientId(clientLong);
            for (Reservation reservation : reservationList) {
                Vehicle vehicleR = vehicleService.findById(reservation.getVehicleId());
                Long vehicleId = reservation.getVehicleId();
                uniqueVehicleIds.add(vehicleId);
                ReservationDTO reservationDTO = new ReservationDTO();
                reservationDTO.setId(reservation.getId());
                reservationDTO.setDebut(reservation.getDebut());
                reservationDTO.setFin(reservation.getFin());
                reservationDTO.setVehicleManufacturer(vehicleR.getConstructeur());
                reservationDTO.setVehicleModele(vehicleR.getModele());
                reservationDTOList.add(reservationDTO);
            }
            for (Long uniqueVehicleId : uniqueVehicleIds) {
                vehicle = vehicleService.findById(uniqueVehicleId);
                vehicleList.add(vehicle);
            }


            numberOfUniqueVehicleIds = uniqueVehicleIds.size();



        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("nbVehicle", numberOfUniqueVehicleIds);
        request.setAttribute("vehicles", vehicleList);
        request.setAttribute("client", client);

        request.setAttribute("reservationsR", reservationDTOList);
        request.setAttribute("reservations", reservationList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);

    }


}
