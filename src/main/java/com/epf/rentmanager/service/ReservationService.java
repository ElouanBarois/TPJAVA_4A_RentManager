package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService {
    private final ReservationDao reservationDao;

    private ReservationService(ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }

    public long createReservation(Reservation reservation) throws ServiceException {
        try {
            return reservationDao.create(reservation);
        } catch (DaoException ex) {
            throw new ServiceException("Error creating reservation (service): " + ex.getMessage());
        }
    }

    public long deleteReservation(long id) throws ServiceException {
        try {
            return reservationDao.delete(id);
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
    public Reservation findReservationById (long id) throws ServiceException {
        try {
            return reservationDao.findResaById(id);
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
    public int count() throws ServiceException {
        try{
            return reservationDao.count();
        } catch (DaoException ex) {
            throw new ServiceException("Erreur lors du calcul du nombre de reservations." + ex.getMessage());
        }
    }
    public void update(Reservation reservation) throws ServiceException {
        try {
            reservationDao.update(reservation);
        } catch (DaoException e) {
            throw new ServiceException("Error while updating reservation with ID "+ e.getMessage());
        }
    }

}
