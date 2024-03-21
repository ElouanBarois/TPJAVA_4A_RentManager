package com.epf.rentmanager.servlet;

import javax.servlet.ServletException;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/create")
public class ClientCreateServlet extends HttpServlet{
    @Autowired
    ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> emailList= new ArrayList<String>();
        List<Client> clientList=null;
        String email = null;
        try {
            clientList =clientService.findAll();
            for (Client client : clientList) {
                email = client.getEmail();
                emailList.add(email);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
                StringBuilder jsonArray = new StringBuilder("[");
        for (String email1 : emailList) {
            jsonArray.append("\"").append(email1).append("\",");
        }
        if (!emailList.isEmpty()) {
            jsonArray.deleteCharAt(jsonArray.length() - 1);
        }
        jsonArray.append("]");
        request.setAttribute("emails", jsonArray.toString());
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Nom = request.getParameter("last_name");
        String Prenom = request.getParameter("first_name");
        String email = request.getParameter("email");
        String Stringnaissance = request.getParameter("Naissance");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate naissance = LocalDate.parse(Stringnaissance, formatter);
        try {
            Client client = new Client(0, Nom, Prenom, email, naissance);
            clientService.create(client);
            response.sendRedirect(request.getContextPath() + "/users");
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création du client.");
        }
    }

}
