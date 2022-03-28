package dao;

import exceptions.DataNotFoundException;
import model.Course;
import model.Payment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    String insertPaymentSql;
    String countUserIdSql;
    String coursesNameSql;
    String insertErronatedPaymentsSql;

    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void setCountUserIdSql(String countUserIdSql) {
        this.countUserIdSql = countUserIdSql;
    }

    public void setInsertPaymentSql(String insertPaymentSql) {
        this.insertPaymentSql = insertPaymentSql;
    }

    public void setCoursesNameSql(String coursesNameSql) {
        this.coursesNameSql = coursesNameSql;
    }

    public void setInsertErronatedPaymentsSql(String insertErronatedPaymentsSql) {
        this.insertErronatedPaymentsSql = insertErronatedPaymentsSql;
    }

    public void createPayment(Payment payment) throws DataNotFoundException {

        Map<String,Object> map = new HashMap<>();

        Timestamp paymentTimestamp = payment.getPaymentTimestamp();
        Integer userId = payment.getUserId();
        String orgaccno = payment.getOrgaccno();
        String benaccno = payment.getBenaccno();
        Integer amountSettlement = payment.getAmountSettlement();
        String courseName = payment.getCourseName();


        map.put("paymentTimestamp",paymentTimestamp);
        map.put("userId",userId);
        map.put("orgaccno",orgaccno);
        map.put("benaccno",benaccno);
        map.put("amountSettlement", amountSettlement);
        map.put("courseName", courseName);

        jdbcTemplate.update(insertPaymentSql, map);

    }

    public void insertErronatedPayments(String messageText, String errorMessage) throws DataNotFoundException{

        Map<String,Object> map = new HashMap<>();


        map.put("payload",messageText);
        map.put("errorCause",errorMessage);


        jdbcTemplate.update(insertErronatedPaymentsSql, map);

    }

    public int validateUserId(Integer userId) throws DataNotFoundException {

        Map<String,Object> map = new HashMap<>();

        map.put("userId", userId);


        return jdbcTemplate.queryForObject(countUserIdSql, map, Integer.class);


    }

    public List<Course> validateCourses(String courseName) throws DataNotFoundException{

        Map<String, Object> map = new HashMap();
        map.put("name", courseName);

        List<Course> courses = jdbcTemplate.query(coursesNameSql, map,

                (rs, rowNo) -> {
                    String name = rs.getString("name");

                    Course course = new Course(name);
                    return course;
                }
        );
        return courses;
    }

}
