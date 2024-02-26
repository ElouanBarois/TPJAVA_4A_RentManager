package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.epf.rentmanager.model.Reservation;

import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ReservationDao {

	private static ReservationDao instance = null;

	private ReservationDao() {
	}

	public static ReservationDao getInstance() {
		if (instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";

	public long create(Reservation reservation) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection(); PreparedStatement statement = connection.prepareStatement(CREATE_RESERVATION_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setLong(1, reservation.getClientId());
			statement.setLong(2, reservation.getVehicleId());
			// Convert LocalDate to java.sql.Date
			Date debutDate = Date.valueOf(reservation.getDebut());
			statement.setDate(3, debutDate);
			Date finDate = Date.valueOf(reservation.getDebut());
			statement.setDate(4, finDate);
			int affectedRows = statement.executeUpdate();
			statement.close();
			connection.close();

			if (affectedRows == 0) {
				throw new DaoException("Creating reservation failed, no rows affected.");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getLong(1);
				} else {
					throw new DaoException("Creating reservation failed, no ID obtained.");
				}
			}
		} catch (SQLException ex) {
			throw new DaoException("Error reservation vehicle: " + ex.getMessage());
		}
	}

	public long delete(Reservation reservation) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION_QUERY)) {
			preparedStatement.setLong(1, reservation.getId());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la suppression d'une reservation."+e);
		}
	}
	public long delete(long id) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION_QUERY)) {
			preparedStatement.setLong(1, id);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la suppression d'une reservation."+ e);
		}
	}


	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection(); PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY)) {
			statement.setLong(1, clientId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				long vehicleId = resultSet.getLong("vehicleId");
				LocalDate debutDate = resultSet.getDate("debutDate").toLocalDate();
				LocalDate finDate = resultSet.getDate("finDate").toLocalDate();
				reservations.add(new Reservation(id, clientId, vehicleId, debutDate, finDate));
			}
			statement.close();
			connection.close();

		} catch (SQLException ex) {
			throw new DaoException("Error finding reservations by clientId: " + ex.getMessage());
		}
		return reservations;
	}

	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection(); PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY)) {
			statement.setLong(1, vehicleId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				long clientId = resultSet.getLong("clientId");
				LocalDate debutDate = resultSet.getDate("debutDate").toLocalDate();
				LocalDate finDate = resultSet.getDate("finDate").toLocalDate();
				reservations.add(new Reservation(id, clientId, vehicleId, debutDate, finDate));
			}
			statement.close();
			connection.close();

		} catch (SQLException ex) {
			throw new DaoException("Error finding reservations by clientId: " + ex.getMessage());
		}
		return reservations;
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection(); PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_QUERY)) {
			ResultSet resultSet = statement.executeQuery();
			statement.close();
			connection.close();
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				long clientId = resultSet.getLong("clientId");
				long vehicleId = resultSet.getLong("vehicleId");
				LocalDate debutDate = resultSet.getDate("debutDate").toLocalDate();
				LocalDate finDate = resultSet.getDate("finDate").toLocalDate();
				reservations.add(new Reservation(id, clientId, vehicleId, debutDate, finDate));
			}
		} catch (SQLException ex) {
			throw new DaoException("Error finding all reservations: " + ex.getMessage());
		}
		return reservations;
	}


}
