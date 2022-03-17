package api;

import dao.RegistrationDao;
import model.Registration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import services.RegistrationService;

import java.sql.SQLException;

@ResponseBody
@RequestMapping("/")
public class Register {

    RegistrationService registrationService;

    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(path = "/newRegistration", consumes="application/json")
    public int insertNewRegistration(@RequestBody Registration registration) throws SQLException {

        return registrationService.createNewRegistration(registration);

    }
}
