package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
import java.util.List;

public class ReservationService {
    private final ReservationDao reservationDao;

    public ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public long createReservation(Reservation reservation) throws ServiceException {
        try {
            return reservationDao.create(reservation);
        } catch (DaoException ex) {
            throw new ServiceException("Error creating reservation: " + ex.getMessage());
        }
    }

    public void deleteReservation(Reservation reservation) throws ServiceException {
        try {
            reservationDao.delete(reservation);
        } catch (DaoException ex) {
            throw new ServiceException("Error deleting reservation: " + ex.getMessage());
        }
    }

    public List<Reservation> findReservationsByClientId(long clientId) throws ServiceException {
        try {
            return reservationDao.findResaByClientId(clientId);
        } catch (DaoException ex) {
            throw new ServiceException("Error finding reservations by client ID: " + ex.getMessage());
        }
    }

    public List<Reservation> findReservationsByVehicleId(long vehicleId) throws ServiceException {
        try {
            return reservationDao.findResaByVehicleId(vehicleId);
        } catch (DaoException ex) {
            throw new ServiceException("Error finding reservations by vehicle ID: " + ex.getMessage());
        }
    }

    public List<Reservation> getAllReservations() throws ServiceException {
        try {
            return reservationDao.findAll();
        } catch (DaoException ex) {
            throw new ServiceException("Error finding all reservations: " + ex.getMessage());
        }
    }
}
