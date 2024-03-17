package com.epf.rentmanager.servlet;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.service.ServiceException;
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
@WebServlet("/cars/modify")

public class VehicleModifyServlet extends HttpServlet{
    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleID = request.getParameter("id");
        long vehicleLong = Long.parseLong(vehicleID);
        Vehicle vehicle = null;
        try {
            vehicle = vehicleService.findById(vehicleLong);

        } catch (ServiceException ex) {
            throw new RuntimeException(ex);
        }
        request.setAttribute("vehicle", vehicle);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/modify.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String constructeur = request.getParameter("manufacturer");
        String modele = request.getParameter("modele");
        int seats = Integer.parseInt(request.getParameter("seats"));
        String vehicleID = request.getParameter("id");
        long vehicleId = Long.parseLong(vehicleID);
        Vehicle vehicle = null;

        try {
            vehicle = vehicleService.findById(vehicleId);
            vehicle.setConstructeur(constructeur);
            vehicle.setModele(modele);
            vehicle.setNbPlaces(seats);
            vehicleService.update(vehicle);
            response.sendRedirect(request.getContextPath() + "/cars");

        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de mise à jour du vehicle.");
        }
    }


}
