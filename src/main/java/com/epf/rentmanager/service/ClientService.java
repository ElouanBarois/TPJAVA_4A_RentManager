package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;

public class ClientService {
	private ClientDao clientDao;
	public static ClientService instance;

	public ClientService() {
		this.clientDao = ClientDao.getInstance();
	}

	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}

		return instance;
	}


	public long create(Client client) throws ServiceException {
		if (client.getNom().isEmpty() || client.getPrenom().isEmpty()) {
			throw new ServiceException("Le nom et le prénom du client ne peuvent pas être vides.");
		}

		// Convertir le nom de famille en majuscules
		client.setNom(client.getNom().toUpperCase());

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
			throw new ServiceException("Error while deleting client with ID "+ e.getMessage());
		}
	}
}
