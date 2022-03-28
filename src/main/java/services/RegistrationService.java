package services;

import dao.RegistrationDao;

import exceptions.DataNotFoundException;
import model.RabbitMessageRegistration;
import model.Registration;

public class RegistrationService {

    RegistrationDao registrationDao;

    public void setRegistrationDao(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    public void createNewRegistration(Registration registration) {

        if(registrationDao.validateUserId(registration.getUserId())==0) {
            throw new DataNotFoundException("User with id " + registration.getUserId() + " was not found");
        }else if(registrationDao.validateTrainerId(registration.getTrainerId())==0) {
            throw new DataNotFoundException("Trainer with id " + registration.getTrainerId() + " was not found");
        }else{
                registrationDao.createRegistration(registration);
               // RabbitMessageRegistration rabbitMessage = new RabbitMessageRegistration("registration",registration);
               // RabbitMessenger.messagePublisher(rabbitMessage);
        }

    }
}
