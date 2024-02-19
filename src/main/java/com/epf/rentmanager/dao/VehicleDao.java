package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;

import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection(); PreparedStatement statement = connection.prepareStatement(CREATE_VEHICLE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, vehicle.getConstructeur());
			statement.setString(2, vehicle.getModele());
			statement.setInt(3, vehicle.getNbPlaces());
			int affectedRows = statement.executeUpdate();
			statement.close();
			connection.close();

			if (affectedRows == 0) {
				throw new DaoException("Creating vehicle failed, no rows affected.");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getLong(1);
				} else {
					throw new DaoException("Creating vehicle failed, no ID obtained.");
				}
			}
		} catch (SQLException ex) {
			throw new DaoException("Error creating vehicle: " + ex.getMessage());
		}
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();PreparedStatement statement = connection.prepareStatement(DELETE_VEHICLE_QUERY)) {
			statement.setLong(1, vehicle.getId());
			int affectedRows = statement.executeUpdate();
			statement.close();
			connection.close();
			if (affectedRows == 0) {
				throw new DaoException("Deleting vehicle failed, no rows affected.");
			}
			return affectedRows;
		} catch (SQLException ex) {
			throw new DaoException("Error deleting vehicle: " + ex.getMessage());
		}
	}

	public Vehicle findById(long id) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();PreparedStatement statement = connection.prepareStatement(FIND_VEHICLE_QUERY)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			statement.close();
			connection.close();
			if (resultSet.next()) {
				String constructeur = resultSet.getString("constructeur");
				String modele = resultSet.getString("modele");
				int nbplaces = resultSet.getInt("nbplaces");
				return new Vehicle(id, constructeur, modele, nbplaces);
			} else {
				throw new DaoException("Vehicle with id " + id + " not found.");
			}

		} catch (SQLException ex) {
			throw new DaoException("Error finding vehicle by id: " + ex.getMessage());
		}
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();PreparedStatement statement = connection.prepareStatement(FIND_VEHICLES_QUERY)) {
			ResultSet resultSet = statement.executeQuery();
			statement.close();
			connection.close();
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				String constructeur = resultSet.getString("constructeur");
				String modele = resultSet.getString("modele");
				int nbplaces = resultSet.getInt("nbplaces");
				vehicles.add(new Vehicle(id, constructeur, modele, nbplaces));
			}
		} catch (SQLException ex) {
			throw new DaoException("Error finding all vehicles: " + ex.getMessage());
		}
		return vehicles;
	}
		
}
	


