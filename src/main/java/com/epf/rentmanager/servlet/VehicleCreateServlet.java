package com.epf.rentmanager.servlet;

import javax.servlet.ServletException;

import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.servlet.VehicleListServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cars/create")
public class VehicleCreateServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String manufacturer = request.getParameter("manufacturer");
        String modele = request.getParameter("modele");
        int seats = Integer.parseInt(request.getParameter("seats"));
        Vehicle vehicle = new Vehicle(0, manufacturer, modele, seats);
        try {
            vehicleService.create(vehicle);
            response.sendRedirect(request.getContextPath() + "/cars");
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création du véhicule.");
        }
    }

}
