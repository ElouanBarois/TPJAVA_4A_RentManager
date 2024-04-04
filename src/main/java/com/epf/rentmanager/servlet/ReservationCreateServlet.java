package com.epf.rentmanager.servlet;
import javax.servlet.ServletException;

import com.epf.rentmanager.dao.ReservationDao;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.Period;
@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet{
    @Autowired
    ReservationService reservationService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ClientService clientService;
    List<Vehicle> vehicleList = null;
    List<Client> clientList = null;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        List<Reservation> ReservationList;
        boolean disponible = true;
        boolean disponible2 = true;
        try {
            ReservationList = reservationService.findReservationsByVehicleId(vehicleid);


            for (Reservation reservationSearch : ReservationList) {
                LocalDate StartTest = reservationSearch.getDebut();
                LocalDate EndTest = reservationSearch.getFin();
                if (!fin.isBefore(StartTest) && !EndTest.isBefore(debut)) {
                    disponible = false;
                    break;
                }
            }

            ReservationList.add(reservation);
            ReservationList.sort(Comparator.comparing(Reservation::getDebut));
            int totalConsecutiveDays = 0;
            boolean isThirtyDaysLong =false;
            LocalDate lastEnd = null;
            for (Reservation reservationSearch : ReservationList){
                LocalDate StartTest = reservationSearch.getDebut();
                LocalDate EndTest = reservationSearch.getFin();
                if (lastEnd != null && lastEnd.plusDays(1).until(StartTest).getDays() > 0) {
                    if (totalConsecutiveDays >= 30) {
                        isThirtyDaysLong = true;
                        break;
                    }
                    totalConsecutiveDays = 0;
                }
                int days = StartTest.until(EndTest.plusDays(1)).getDays();
                totalConsecutiveDays += days;
                lastEnd = EndTest;
            }
            if(isThirtyDaysLong){
                disponible2=false;
            }
            if (disponible && disponible2) {
                reservationService.createReservation(reservation);
                response.sendRedirect(request.getContextPath() + "/rents");
            }else {
                String message= "Les dates selectionnées pour ce véhicule sont déjà réservées.";
                String message2= "Il faut au moins 1 jour de pause entre la nouvelle réservation et la précédente. (30 jours d'affilé)";
                if (!disponible){
                    request.setAttribute("errorMessageDatesChoisies", message);
                }else{
                    request.setAttribute("errorMessageDatesChoisies", message2);
                }
                request.setAttribute("vehicles", vehicleList);
                request.setAttribute("clients", clientList);
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création de la réservation. (servlet)");
        }
    }
}
