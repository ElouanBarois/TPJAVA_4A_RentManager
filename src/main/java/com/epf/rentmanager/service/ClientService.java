package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

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
			throw new ServiceException("Error while deleting client with ID "+ e.getMessage());
		}
	}

	public void update(Client client) throws ServiceException {
		try {
			clientDao.update(client);
		} catch (DaoException e) {
			throw new ServiceException("Error while updating client with ID "+ e.getMessage());
		}
	}
}
