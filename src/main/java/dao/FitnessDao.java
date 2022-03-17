package dao;

import model.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FitnessDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    String searchCoursesSql;
    String searchTrainersSql;
    String searchCoursesWithTimeScheduleSql;
    String searchUsersSql;
    String insertTrainerSql;
    String updateCourseSql;
    String insertNewTrainerSql;


    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void setSearchCoursesSql(String searchCoursesSql){
        this.searchCoursesSql = searchCoursesSql;
    }
    public void setSearchTrainersSql(String searchTrainersSql){
        this.searchTrainersSql = searchTrainersSql;
    }
    public void setSearchCoursesWithTimeScheduleSql(String searchCoursesWithTimeScheduleSql){this.searchCoursesWithTimeScheduleSql = searchCoursesWithTimeScheduleSql;}

    public void setSearchUsersSql(String searchUsersSql){
        this.searchUsersSql = searchUsersSql;
    }

    public void setInsertTrainerSql(String insertTrainerSql){
        this.insertTrainerSql = insertTrainerSql;
    }

    public void setUpdateCourseSql(String updateCourseSql){
        this.updateCourseSql = updateCourseSql;
    }

    public void setInsertNewTrainerSql(String insertNewTrainerSql){
        this.insertNewTrainerSql = insertNewTrainerSql;
    }



    //@Transactional
    public int createTrainer(Integer trainerId, Integer courseId, String name, String email){

        Map<String,Object> map = new HashMap<>();
        map.put("trainerId",trainerId);
        map.put("courseId",courseId);
        map.put("name",name);
        map.put("email",email);

        int rowNumber = jdbcTemplate.update(insertTrainerSql,map);

        return rowNumber;

    }



    public int createTrainerWithBody(Trainer trainer) throws SQLException {

        Map<String,Object> map = new HashMap<>();


        Integer courseId = trainer.getCourseId();
        String trainerName = trainer.getName();
        String email = trainer.getEmail();

        map.put("courseId",courseId);
        map.put("name",trainerName);
        map.put("email",email);

        int rowNumber = jdbcTemplate.update(insertNewTrainerSql,map);

        return rowNumber;

    }

    public int updateCourse(String description, Integer price){

        Map<String,Object> map = new HashMap<>();
        map.put("description",description);
        map.put("price",price);

        int rowNumber = jdbcTemplate.update(updateCourseSql,map);

        return rowNumber;

    }

    public List<Course> retrieveCourses(String givenWord, int givenPrice) throws SQLException {
        //DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/database_service","root","Vampir1234");
        //String sqlWithParam = "select course_id,name,description,price from course where description like :description and price> :priceLimit";

        Map<String, Object> map = new HashMap();
        map.put("description", givenWord);
        map.put("priceLimit", givenPrice);

        List<Course> courses = jdbcTemplate.query(searchCoursesSql, map,

                (rs, rowNo) -> {
                    int id = rs.getInt("course_id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int price = rs.getInt("price");

                    Course course = new Course(id, name, description, price);
                    return course;
                }
        );
        return courses;
    }



    public List<TrainerWithCourseDescription> retrieveTrainers(int givenPrice) throws SQLException {
        //DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/database_service","root","Vampir1234");
        //String sqlWithParam = "select course_id,name,description,price from course where description like :description and price> :priceLimit";

        Map<String, Object> map = new HashMap();
        map.put("priceLimit", givenPrice);

        List<TrainerWithCourseDescription> trainersWithCourseDescription = jdbcTemplate.query(searchTrainersSql, map,

                (rs, rowNo) -> {

                    String trainerName = rs.getString("trainer_name");
                    String email = rs.getString("email");
                    String courseName = rs.getString("course_name");
                    String description = rs.getString("description");



                    Trainer trainer = new Trainer(trainerName,email);
                    Course course = new Course(courseName,description);

                    TrainerWithCourseDescription trainerWithCourseDescription = new TrainerWithCourseDescription(trainer,course);


                    return trainerWithCourseDescription;
                }
        );
       return trainersWithCourseDescription;
    }

    public List<CourseWithTimeSchedule> retrieveCoursesWithTimeSchedule(String givenWord) throws SQLException {

        Map<String, Object> map = new HashMap();
        map.put("scheduleTime", givenWord);

        List<CourseWithTimeSchedule> coursesWithTimeSchedules = jdbcTemplate.query(searchCoursesWithTimeScheduleSql, map,

                (rs, rowNo) -> {

                    String courseName = rs.getString("name");
                    String description = rs.getString("description");
                    String time = rs.getString("schedule_time");


                    Course course = new Course(courseName,description);
                    TimeSchedule timeSchedule = new TimeSchedule(time);

                    CourseWithTimeSchedule courseWithTimeSchedule = new CourseWithTimeSchedule(course, timeSchedule);


                    return courseWithTimeSchedule;
                }
        );
        return coursesWithTimeSchedules;
    }

    public List<User> retrieveUsers(String givenWord) throws SQLException {

        Map<String, Object> map = new HashMap();
        map.put("name", givenWord);


        List<User> users = jdbcTemplate.query(searchUsersSql, map,

                (rs, rowNo) -> {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String address = rs.getString("address");

                    User user = new User(name,email,address);
                    return user;
                }
        );
        return users;
    }
}
