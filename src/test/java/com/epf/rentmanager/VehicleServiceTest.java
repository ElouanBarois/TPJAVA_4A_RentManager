package com.epf.rentmanager;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.epf.rentmanager.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ServiceException;

public class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleDao vehicleDao;

    @BeforeEach
    public void setUp() {MockitoAnnotations.initMocks(this);}
    @Test
    public void create_should_throw_exception_when_constructeur_is_empty() throws DaoException {
        // Given
        Vehicle vehicle = new Vehicle();
        vehicle.setNbPlaces(5);
        vehicle.setModele("Yaris");
        // When
        when(vehicleDao.create(vehicle)).thenThrow(DaoException.class);
        // Then
        assertThrows(ServiceException.class, () -> vehicleService.create(vehicle));
    }

    @Test
    public void create_should_throw_exception_when_nbPlaces_is_less_than_1() throws DaoException {
        // Given
        Vehicle vehicle = new Vehicle();
        vehicle.setConstructeur("Toyota");
        vehicle.setModele("Yaris");
        vehicle.setNbPlaces(0);
        // When
        when(vehicleDao.create(vehicle)).thenThrow(DaoException.class);
        // Then
        assertThrows(ServiceException.class, () -> vehicleService.create(vehicle));
    }

    @Test
    public void create_should_throw_exception_when_nbPlaces_is_greater_than_9() throws DaoException {
        // Given
        Vehicle vehicle = new Vehicle();
        vehicle.setConstructeur("Toyota");
        vehicle.setModele("Yaris");
        vehicle.setNbPlaces(10);
        // When
        when(vehicleDao.create(vehicle)).thenThrow(DaoException.class);
        // Then
        assertThrows(ServiceException.class, () -> vehicleService.create(vehicle));
    }
}
