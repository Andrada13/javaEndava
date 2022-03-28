package api;

import model.Registration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;


//@ResponseBody
@RequestMapping("/")
public class ValidatingRegisterApi {

    @PostMapping(path = "/addOrRejectRegistration", consumes="application/json")
    public ResponseEntity<String> insertNewRegistration(@RequestBody Registration registration) throws SQLException {
        try {
            createRegistration();
            return ResponseEntity.ok("Registration created.");
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    private void createRegistration() throws Exception {
        boolean generateException = Math.random() < 0.5;
        if (generateException) {
            throw new Exception("There's something wrong in your request!!!");
        }


        // ok if we got here
    }
}