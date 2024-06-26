package com.epf.rentmanager.service;

import java.util.List;
import java.util.Objects;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
	private VehicleDao vehicleDao;
	private VehicleService(VehicleDao vehicleDao){
		this.vehicleDao = vehicleDao;
	}

	public long create(Vehicle vehicle) throws ServiceException {
		vehicle = validateVehicle(vehicle);
		try {
			return vehicleDao.create(vehicle);
		} catch (DaoException ex) {
			throw new ServiceException("Erreur lors de la création du véhicule."+ ex.getMessage());
		}
	}
	public Vehicle validateVehicle(Vehicle vehicle) throws ServiceException {
		if (Objects.isNull(vehicle.getConstructeur()) || vehicle.getConstructeur().isEmpty()) {
			throw new ServiceException("Le constructeur du véhicule ne peut pas être vide.");
		}
		if (Objects.isNull(vehicle.getModele()) || vehicle.getModele().isEmpty()) {
			throw new ServiceException("Le modèle du véhicule ne peut pas être vide.");
		}
		int nbPlaces = vehicle.getNbPlaces();
		if (nbPlaces <= 1 || nbPlaces > 9) {
			throw new ServiceException("Le nombre de places du véhicule doit être compris entre 2 et 9.");
		}
		return vehicle;
	}

	public Vehicle findById(long id) throws ServiceException {
		try {
			return vehicleDao.findById(id);
		} catch (DaoException ex) {
			throw new ServiceException("Erreur lors de la récupération du véhicule par son ID." + ex.getMessage());
		}
	}
	public int count() throws ServiceException {
		try{
			return vehicleDao.count();
		} catch (DaoException ex) {
			throw new ServiceException("Erreur lors du calcul du nombre de véhicules." + ex.getMessage());
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicleDao.findAll();
		} catch (DaoException ex) {
			throw new ServiceException("Erreur lors de la récupération de tous les véhicules."+ex.getMessage());
		}
	}
	public void delete(Vehicle vehicle) throws ServiceException {
		try {
			vehicleDao.delete(vehicle);
		} catch (DaoException e) {
			throw new ServiceException("Error while deleting client with ID "+e.getMessage());
		}
	}
	public void update(Vehicle vehicle) throws ServiceException{
		vehicle = validateVehicle(vehicle);
		try {
			vehicleDao.update(vehicle);
		} catch (DaoException e) {
			throw new ServiceException("Error while updating vehicle with ID "+ e.getMessage());
		}

	}

}
