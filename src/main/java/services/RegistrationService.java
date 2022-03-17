package services;

import dao.RegistrationDao;
import model.HelloWorld;
import model.Registration;

import java.sql.SQLException;

public class RegistrationService {

    RegistrationDao registrationDao;

    public void setRegistrationDao(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    public int createNewRegistration(Registration registration) throws SQLException {

       return registrationDao.createRegistration(registration);

    }
}
