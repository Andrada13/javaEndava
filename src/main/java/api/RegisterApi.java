package api;

import dao.RegisterCallBackHandlerDao;
import dao.RegistrationDao;
import exceptions.DataNotFoundException;
import model.Registration;
import org.apache.tomcat.jni.File;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import services.RegistrationService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

//@ResponseBody
@RequestMapping("/")
public class RegisterApi {

    RegistrationService registrationService;
    RegistrationDao registrationDao;
    RegisterCallBackHandlerDao registerCallBackHandlerDao;

    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void setRegistrationDao(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    public void setRegisterCallBackHandlerDao(RegisterCallBackHandlerDao registerCallBackHandlerDao) {
        this.registerCallBackHandlerDao = registerCallBackHandlerDao;
    }

    @PostMapping(path = "/newRegistration", consumes = "application/json")
    public ResponseEntity<String> insertNewRegistration(@RequestBody Registration registration) {

        try {
            registrationService.createNewRegistration(registration);
            return ResponseEntity.ok("Registration created.");
        } catch (DataNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

    }


    @GetMapping("/allRegistrations")
    public String writeAllRegistrations() throws IOException {

        Path p = Paths.get("C:", "Users", "OaNeagu", "OneDrive - ENDAVA", "Documents", "Registrations.csv");

        //Iterable<String> line = Arrays.asList("Registration_id," + "User_id," + "Trainer_id," + "Time " + "\n" + registrationDao.registrations().toString()
        //    .replace("[","").replace("]",""));


        List<String> registrations = new ArrayList<>();
        registrations.add("Registration_id,User_id,Trainer_id,Time ");
        registrations.addAll(registrationDao.registrations());

        Files.write(p, registrations, StandardOpenOption.APPEND);


        return p.toString();

    }

    @GetMapping("/registrationsDatetime")
    public String writeRegistrationsDatetime(@RequestParam String startTime, @RequestParam String endTime) throws IOException, SQLException {

        Path p = Paths.get("C:", "Users", "OaNeagu", "OneDrive - ENDAVA", "Documents", "RegistrationsDatetime.csv");

        //Iterable<String> line = Arrays.asList("Registration_id," + "User_id," + "Trainer_id," + "Time " + "\n" + registrationDao.registrations().toString()
        //    .replace("[","").replace("]",""));


        List<String> registrations = new ArrayList<>();
        registrations.add("Registration_id, User_name, Trainer_name, Course_name, Time ");
        registrations.addAll(registrationDao.retrieveRegistrationsByDatetime(startTime, endTime));

        Files.write(p, registrations);


        return p.toString();



    }

    @GetMapping("/writeRegistration")
    public void writeRegistration(@RequestParam String startTime, @RequestParam String endTime) throws IOException, SQLException {

        /*
        FileWriter fileWriter = new FileWriter("C:\\Users\\OaNeagu\\OneDrive - ENDAVA\\Documents\\Registration.csv");

       // List<String> registrations = new ArrayList<>();
       // registrations.add("Registration_id, User_name, Trainer_name, Course_name, Time");

        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write("Registration_id, User_name, Trainer_name, Course_name, Time");
            bufferedWriter.newLine();

            for (String registration : registrationDao.retrieveRegistrationsByDatetime(startTime, endTime)) {
                bufferedWriter.write(registration);
                bufferedWriter.newLine();
            }
        }


         */

        registerCallBackHandlerDao.printRegistration(startTime, endTime);
    }


}


