package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.ReservationDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
public class ReservationService {
    private final ReservationDao reservationDao;

    private ReservationService(ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }

    public void createReservation(Reservation reservation) throws ServiceException {
        reservation= validateReservation(reservation);
        try {
             reservationDao.create(reservation);
        } catch (DaoException ex) {
            throw new ServiceException("Error creating reservation (service): " + ex.getMessage());
        }
    }

    public void deleteReservation(long id) throws ServiceException {
        try {
             reservationDao.delete(id);
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

    public Reservation validateReservation(Reservation reservation) throws ServiceException{
        List<Reservation> ReservationList = findReservationsByVehicleId(reservation.getVehicleId());
        LocalDate debut = reservation.getDebut();
        LocalDate fin = reservation.getFin();
        for (Reservation reservationSearch : ReservationList) {
            LocalDate StartTest = reservationSearch.getDebut();
            LocalDate EndTest = reservationSearch.getFin();
            if (!fin.isBefore(StartTest) && !EndTest.isBefore(debut)) {
                throw new ServiceException("Les dates selectionnées pour ce véhicule sont déjà réservées.");
            }
        }
        long daysBetween = ChronoUnit.DAYS.between(debut, fin);
        if (daysBetween > 7) {
            throw new ServiceException("La réservation ne peut pas dépasser 7 jours.");
        }
        ReservationList.add(reservation);
        int totalConsecutiveDays = 0;
        boolean isThirtyDaysLong =false;
        LocalDate lastEnd = null;
        for (Reservation reservationSearch : ReservationList){
            LocalDate StartTest = reservationSearch.getDebut();
            LocalDate EndTest = reservationSearch.getFin();
            if (lastEnd != null && lastEnd.plusDays(1).until(StartTest).getDays() > 0) {
                if (totalConsecutiveDays >= 30) {
                    isThirtyDaysLong = true;
                    break;
                }
                totalConsecutiveDays = 0;
            }
            int days = StartTest.until(EndTest.plusDays(1)).getDays();
            totalConsecutiveDays += days;
            lastEnd = EndTest;
        }
        if(isThirtyDaysLong){
            throw new ServiceException("Il faut au moins 1 jour de pause entre la nouvelle réservation et la précédente. (30 jours d'affilé)");
        }
        return reservation;

    }

}
