package dao;

import model.Course;
import model.Registration;
import model.RegistrationDatetime;
import model.Trainer;
import model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class RegisterCallBackHandlerDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    String selectDatetimeRegistrationsSql;



    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void setSelectDatetimeRegistrationsSql(String selectDatetimeRegistrationsSql) {
        this.selectDatetimeRegistrationsSql = selectDatetimeRegistrationsSql;
    }


    public void printRegistration( String startDate, String endDate) throws SQLException, IOException {

        Map<String,Object> map = new HashMap();
        map.put("startTime",startDate);
        map.put("endTime",endDate);

        RegisterCallBackHandler registerCallBackHandler = new RegisterCallBackHandler("C:\\Users\\OaNeagu\\OneDrive - ENDAVA\\Documents\\Registration.csv");
        jdbcTemplate.query(selectDatetimeRegistrationsSql,map,
                registerCallBackHandler
        );

        registerCallBackHandler.close();
    }
}
