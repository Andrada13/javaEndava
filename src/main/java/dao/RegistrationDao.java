package dao;

import model.Registration;
import model.Trainer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class RegistrationDao {


    NamedParameterJdbcTemplate jdbcTemplate;
    String insertRegistrationSql;

    public void setInsertRegistrationSql(String insertRegistrationSql) {
        this.insertRegistrationSql = insertRegistrationSql;
    }

    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public int createRegistration(Registration registration) throws SQLException {

        Map<String,Object> map = new HashMap<>();

        Integer registrationId = registration.getRegistrationId();
        Integer userId = registration.getUserId();
        Integer trainerId = registration.getTrainerId();
        Timestamp timestamp = registration.getTimestamp();

        map.put("registrationId",registrationId);
        map.put("userId",userId);
        map.put("trainerId",trainerId);
        map.put("timestamp",timestamp);

        int rowNumber = jdbcTemplate.update(insertRegistrationSql,map);

        return rowNumber;

    }
}
