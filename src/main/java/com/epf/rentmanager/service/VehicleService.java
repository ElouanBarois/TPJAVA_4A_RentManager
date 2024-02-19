package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.model.Vehicle;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;

	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}

	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		return instance;
	}

	public long create(Vehicle vehicle) throws ServiceException {
		if (vehicle.getConstructeur().isEmpty()) {
			throw new ServiceException("Le constructeur du véhicule ne peut pas être vide.");
		}
		if (vehicle.getNbPlaces() <= 1) {
			throw new ServiceException("Le nombre de places du véhicule doit être supérieur à 1.");
		}
		try {
			return vehicleDao.create(vehicle);
		} catch (DaoException ex) {
			throw new ServiceException("Erreur lors de la création du véhicule.");
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		try {
			return vehicleDao.findById(id);
		} catch (DaoException ex) {
			throw new ServiceException("Erreur lors de la récupération du véhicule par son ID.");
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicleDao.findAll();
		} catch (DaoException ex) {
			throw new ServiceException("Erreur lors de la récupération de tous les véhicules.");
		}
	}
}
