package dao;

import exceptions.DataNotFoundException;
import model.Course;
import model.Registration;
import model.RegistrationDatetime;
import model.Trainer;
import model.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationDao {


    NamedParameterJdbcTemplate jdbcTemplate;
    String insertRegistrationSql;
    String countUserIdSql;
    String countTrainerIdSql;
    String selectRegistrationsSql;
    String selectDatetimeRegistrationsSql;


    public void setSelectRegistrationsSql(String selectRegistrationsSql) {
        this.selectRegistrationsSql = selectRegistrationsSql;
    }

    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void setInsertRegistrationSql(String insertRegistrationSql) {
        this.insertRegistrationSql = insertRegistrationSql;
    }

    public void setCountUserIdSql(String countUserIdSql) {
        this.countUserIdSql = countUserIdSql;
    }

    public void setCountTrainerIdSql(String countTrainerIdSql) {
        this.countTrainerIdSql = countTrainerIdSql;
    }

    public void setSelectDatetimeRegistrationsSql(String selectDatetimeRegistrationsSql) {
        this.selectDatetimeRegistrationsSql = selectDatetimeRegistrationsSql;
    }

    public void createRegistration(Registration registration) throws DataNotFoundException {

        Map<String,Object> map = new HashMap<>();

        Integer registrationId = registration.getRegistrationId();
        Integer userId = registration.getUserId();
        Integer trainerId = registration.getTrainerId();
        Timestamp timestamp = registration.getTimestamp();

        map.put("registrationId",registrationId);
        map.put("userId",userId);
        map.put("trainerId",trainerId);
        map.put("timestamp",timestamp);

        jdbcTemplate.update(insertRegistrationSql, map);




    }

    public int validateUserId(Integer userId) throws DataNotFoundException {

        Map<String,Object> map = new HashMap<>();

        map.put("userId", userId);


        return jdbcTemplate.queryForObject(countUserIdSql, map, Integer.class);


    }

    public int validateTrainerId(Integer trainerId) throws DataNotFoundException{

        Map<String,Object> map = new HashMap<>();

        map.put("trainerId", trainerId);

        return jdbcTemplate.queryForObject(countTrainerIdSql, map, Integer.class);

    }


    public List<String> registrations(){

        Map<String, Object> map = new HashMap();

        List<String> registrations = jdbcTemplate.query(selectRegistrationsSql, map,

                (rs, rowNo) -> {
                    int registrationId = rs.getInt("registration_id");
                    int userId = rs.getInt("user_id");
                    int trainerId = rs.getInt("trainer_id");
                    Timestamp timestamp = rs.getTimestamp("registration_datetime");

                    Registration registration= new Registration(registrationId,userId,trainerId,timestamp);
                    return registration.toString();
                }
        );
        return registrations;

    }

    public List<String> retrieveRegistrationsByDatetime(String startTime, String endTime) throws DataNotFoundException {

        Map<String, Object> map = new HashMap();
        map.put("startTime", startTime);
        map.put("endTime", endTime);


        List<String> registrations = jdbcTemplate.query(selectDatetimeRegistrationsSql, map,

                (rs, rowNo) -> {
                    Integer registrationId = rs.getInt("registration_id");
                    String userName = rs.getString("user_name");
                    String trainerName = rs.getString("trainer_name");
                    String courseName = rs.getString("course_name");
                    Timestamp timestamp = rs.getTimestamp("registration_datetime");


                    Registration registration= new Registration(registrationId,timestamp);
                    User user = new User(userName);
                    Trainer trainer = new Trainer(trainerName);
                    Course course = new Course(courseName);

                    RegistrationDatetime registrationDatetime = new RegistrationDatetime(registration,trainer,course,user);

                    return registrationDatetime.toString();
                }
        );
        return registrations;
    }

}
