package services;


import dao.RegistrationDao;
import exceptions.DataNotFoundException;

import model.Registration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RegistrationServiceTest {


    @Mock
    RegistrationDao registrationDao;

    @InjectMocks
    RegistrationService registrationService;


    @Test
    public void createRegistrationTestSuccessful() throws Exception {

        when(registrationDao.validateTrainerId(12)).thenReturn(1);
        when(registrationDao.validateUserId(10)).thenReturn(1);

        registrationService.createNewRegistration(getRegistration());

        verify(registrationDao).createRegistration(any());

    }

    @Test
    public void createRegistrationTestIncorrectUserId() {

        when(registrationDao.validateUserId(10)).thenReturn(0);

        Assertions.assertThrows(DataNotFoundException.class,
                () -> registrationService.createNewRegistration(getRegistration()));

    }

    @Test
    public void createRegistrationTestIncorrectTrainerId() {

        when(registrationDao.validateTrainerId(13)).thenReturn(0);

        Assertions.assertThrows(DataNotFoundException.class,
                () -> registrationService.createNewRegistration(getRegistration()));

    }

    private Registration getRegistration() {

        Registration registration = new Registration();

        registration.setUserId(10);
        registration.setTrainerId(12);
        registration.setTimestamp(Timestamp.valueOf("2020-10-23 12:20:10"));

        return registration;
    }

}


