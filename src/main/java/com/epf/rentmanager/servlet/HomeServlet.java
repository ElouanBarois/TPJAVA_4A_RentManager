package com.epf.rentmanager.servlet;

import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        try {
            int countV = vehicleService.count();
			request.setAttribute("countV", countV);
            int countR = reservationService.count();
            request.setAttribute("countR", countR);
            int countC = clientService.count();
            request.setAttribute("countC", countC);


        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);

	}

}
