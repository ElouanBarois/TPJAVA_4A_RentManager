package com.epf.rentmanager;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class ClientServiceTest {
    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientDao clientDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void findAll_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.clientDao.findAll()).thenThrow(DaoException.class);

        // Then
        assertThrows(ServiceException.class, () -> clientService.findAll());
    }

    @Test
    void delete_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        Client client = new Client(200, "MOI", "elou", "moi.elou@ex.com", LocalDate.of(2000,1,1)    );
        when(this.clientDao.delete(client)).thenThrow(DaoException.class);
        // Then
        assertThrows(ServiceException.class, () -> clientService.delete(client));
    }
    @Test
    void count_should_fail_when_dao_throws_exception() throws DaoException{
        //When
        when(this.clientDao.count()).thenThrow(DaoException.class);
        //Then
        assertThrows(ServiceException.class, () -> clientService.count());
    }


}
