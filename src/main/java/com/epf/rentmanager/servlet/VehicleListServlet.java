package com.epf.rentmanager.servlet;
import javax.servlet.ServletException;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cars")
public class VehicleListServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vehicle> vehicleList = null;
        try {
            vehicleList = vehicleService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("vehicles", vehicleList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleID = request.getParameter("id");
        long vehicleLong = Long.parseLong(vehicleID);
        try {
            Vehicle vehicle = vehicleService.findById(vehicleLong);
            vehicleService.delete(vehicle);
            response.sendRedirect(request.getContextPath() + "/cars");
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la suppression du vehicule (Servlet).");
        }
    }
}