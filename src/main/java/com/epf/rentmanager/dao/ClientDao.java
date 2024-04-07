package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.model.Client;


import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {

	private ClientDao() {}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";
	private static final String COUNT_CLIENT_QUERY = "SELECT COUNT(*) AS total FROM Client;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String CHECK_EMAIL_QUERY= "SELECT COUNT(*) FROM Client WHERE email = ?";

	public long create(Client client) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection(); PreparedStatement statement = connection.prepareStatement(CREATE_CLIENT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, client.getNom());
			statement.setString(2, client.getPrenom());
			statement.setString(3, client.getEmail());
			Date naissanceDate = Date.valueOf(client.getNaissance());
			statement.setDate(4, naissanceDate);
			int affectedRows = statement.executeUpdate();


			if (affectedRows == 0) {
				throw new DaoException("Creating client failed, no rows affected.");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getLong(1);
				} else {
					throw new DaoException("Creating client failed, no ID obtained.");
				}
			}
		} catch (SQLException ex) {
			throw new DaoException("Error creating client: " + ex.getMessage());
		}
	}

	public long delete(Client client) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_QUERY)) {
			statement.setLong(1, client.getId());
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DaoException("Deleting client failed, no rows affected.");
			}
			return affectedRows;
		} catch (SQLException ex) {
			throw new DaoException("Error deleting client: " + ex.getMessage());
		}
	}

	public Client findById(long id) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_QUERY)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String email = resultSet.getString("email");
				LocalDate naissance = resultSet.getDate("naissance").toLocalDate();
				return new Client(id, nom, prenom, email, naissance);

			} else {
				throw new DaoException("Client with id " + id + " not found.");

			}


		} catch (SQLException ex) {
			throw new DaoException("Error finding client by id: " + ex.getMessage());
		}
	}

	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();PreparedStatement statement = connection.prepareStatement(FIND_CLIENTS_QUERY)) {
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String email = resultSet.getString("email");
				LocalDate naissance = resultSet.getDate("naissance").toLocalDate();
				clients.add(new Client(id, nom, prenom, email, naissance));
			}
			statement.close();
			connection.close();
		} catch (SQLException ex) {
			throw new DaoException("Error finding all clients: " + ex.getMessage());
		}
		return clients;
	}
	public void update (Client client) throws DaoException{
		try (Connection connection = ConnectionManager.getConnection();PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT_QUERY)) {
			statement.setString(1, client.getNom());
			statement.setString(2, client.getPrenom());
			statement.setString(3, client.getEmail());
			statement.setDate(4, java.sql.Date.valueOf(client.getNaissance()));
			statement.setLong(5, client.getId());
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new DaoException("Updating client failed, no rows affected.");
			}

		} catch (SQLException ex) {
			throw new DaoException("Error updating client: " + ex.getMessage());
		}
	}
	public int count() throws DaoException{
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(COUNT_CLIENT_QUERY);
		) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("total");
			} else {
				throw new DaoException("No clients found in the database.");
			}
		} catch (SQLException e) {
			throw new DaoException("Error counting clients"+ e);
		}
	}
	public boolean emailExists(String email) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(CHECK_EMAIL_QUERY)) {
			statement.setString(1, email);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Error checking if email exists: " + e.getMessage());
		}
		return false;
	}

}
