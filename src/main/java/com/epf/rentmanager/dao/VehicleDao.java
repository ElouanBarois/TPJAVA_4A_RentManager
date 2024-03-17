package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;

import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {
	private VehicleDao() {}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle( constructeur,modele, nb_places) VALUES(?, ?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur,modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur,modele, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLE_QUERY = "SELECT COUNT(*) AS total FROM Vehicle;";
	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur=?, modele=?, nb_places=? WHERE id=?;";



	public long create(Vehicle vehicle) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection(); PreparedStatement statement = connection.prepareStatement(CREATE_VEHICLE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, vehicle.getConstructeur());
			statement.setString(2, vehicle.getModele());
			statement.setInt(3, vehicle.getNbPlaces());
			int affectedRows = statement.executeUpdate();


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
			if (resultSet.next()) {
				String constructeur = resultSet.getString("constructeur");
				String modele = resultSet.getString("modele");
				int nbplaces = resultSet.getInt("nb_places");
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

			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				String constructeur = resultSet.getString("constructeur");
				String modele = resultSet.getString("modele");
				int nbplaces = resultSet.getInt("nb_places");
				vehicles.add(new Vehicle(id, constructeur, modele, nbplaces));
			}
		} catch (SQLException ex) {
			throw new DaoException("Error finding all vehicles: " + ex.getMessage());
		}
		return vehicles;
	}
	public void update (Vehicle vehicle) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_VEHICLE_QUERY)) {
			statement.setString(1,vehicle.getConstructeur());
			statement.setString(2,vehicle.getModele());
			statement.setInt(3,vehicle.getNbPlaces());
			statement.setLong(4,vehicle.getId());
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DaoException("Updating vehicle failed, no rows affected.");
			}

		} catch (SQLException ex) {
			throw new DaoException("Error updating vehicle (DAO): " + ex.getMessage());
		}
	}
	public int count() throws DaoException {

		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(COUNT_VEHICLE_QUERY);
			 ) {
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt("total");
			} else {
				throw new DaoException("No vehicles found in the database.");
			}

		} catch (SQLException e) {
			throw new DaoException("Error counting vehicles"+ e);
		}
	}
		
}
	


