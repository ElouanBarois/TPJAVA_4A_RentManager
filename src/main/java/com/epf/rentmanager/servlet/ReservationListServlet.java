package com.epf.rentmanager.servlet;
import javax.servlet.ServletException;

import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.util.List;
@WebServlet("/rents")
public class ReservationListServlet extends HttpServlet{
    @Autowired
    ReservationService reservationService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reservation> reservationList = null;
        try {
            reservationList = reservationService.getAllReservations();
            System.out.println(reservationList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("reservations", reservationList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
    }




}
