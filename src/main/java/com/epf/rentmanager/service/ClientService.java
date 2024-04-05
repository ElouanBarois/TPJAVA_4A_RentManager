package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	private ClientDao clientDao;

	private ClientService(ClientDao clientDao){
		this.clientDao = clientDao;
	}

	public long create(Client client) throws ServiceException {
		client = validateClient(client);
		client.setNom(client.getNom().toUpperCase());
		client = validateEmailnotused(client);
		try {
			return clientDao.create(client);
		} catch (DaoException ex) {
			throw new ServiceException("Erreur lors de la création du client."+ ex.getMessage());
		}

	}

	public Client findById(long id) throws ServiceException {
		try {
			return clientDao.findById(id);
		} catch (DaoException ex) {
			throw new ServiceException("Erreur lors de la récupération du client par son ID."+ ex.getMessage());
		}
	}
	public int count() throws ServiceException {
		try{
			return clientDao.count();
		} catch (DaoException ex) {
			throw new ServiceException("Erreur lors du calcul du nombre de clients (DAO)." + ex.getMessage());
		}
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return clientDao.findAll();
		} catch (DaoException ex) {
			throw new ServiceException("Erreur lors de la récupération de tous les clients."+ ex.getMessage());
		}
	}
	public void delete(Client client) throws ServiceException {
		try {
			clientDao.delete(client);
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors de la suppression du client "+ e.getMessage());
		}
	}

	public void update(Client client) throws ServiceException {
		client = validateClient(client);
		client.setNom(client.getNom().toUpperCase());
		try {
			clientDao.update(client);
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors de la mise à jour du client "+ e.getMessage());
		}
	}
	public boolean emailExists(String email) throws ServiceException{
		try {
			return clientDao.emailExists(email);
		} catch (DaoException e) {
			throw new ServiceException("L'email existe déjà."+ e.getMessage());
		}
	}
	public Client validateClient(Client client) throws ServiceException {
		if (client == null) {
			throw new ServiceException("Client object is null");
		}
		if (client.getNom() == null || client.getNom().isEmpty() || client.getNom().length()<3 ){
			throw new ServiceException("Le nom du client est vide ou inférieur à 3 caractères.");
		}
		if (client.getPrenom() == null || client.getPrenom().isEmpty() || client.getPrenom().length()<3) {
			throw new ServiceException("Le prénom du client est vide ou inférieur à 3 caractères.");
		}
		LocalDate dateNaissance = client.getNaissance();
		LocalDate currentDate = LocalDate.now();
		int age = Period.between(dateNaissance, currentDate).getYears();
		if (age < 18) {
			throw new ServiceException("Le client doit être âgé de 18 ans ou plus.");
		}
		if (!isValidEmail(client.getEmail())) {
			throw new ServiceException("Format d'email invalide.");
		}
		return client;
	}
	private boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		return pattern.matcher(email).matches();
	}
	public Client validateEmailnotused(Client client) throws ServiceException{
		//On sépare de la validation car cette fois il ne faut pas vérifier dans la méthode update
		if (emailExists(client.getEmail())){
			throw new ServiceException("L'email existe déjà.");
		}
		return client;
	}
}
