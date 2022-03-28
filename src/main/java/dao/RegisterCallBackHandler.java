package dao;

import model.Course;
import model.Registration;
import model.RegistrationDatetime;
import model.Trainer;
import model.User;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RegisterCallBackHandler implements RowCallbackHandler {

    BufferedWriter bufferedWriter;

    public RegisterCallBackHandler(String filePath) throws IOException {

        FileWriter fileWriter = new FileWriter(filePath);
        bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Registration_id, User_name, Trainer_name, Course_name, Time");
        bufferedWriter.newLine();
    }

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        int registrationId = rs.getInt("registration_id");
        String trainerName = rs.getString("trainer_name");
        String userName = rs.getString("user_name");
        String courseName = rs.getString("course_name");
        Timestamp timestamp = rs.getTimestamp("registration_datetime");

        Registration registration= new Registration(registrationId,timestamp);
        User user = new User(userName);
        Trainer trainer = new Trainer(trainerName);
        Course course = new Course(courseName);

        RegistrationDatetime registrationDatetime = new RegistrationDatetime(registration,trainer,course,user);


        try {
            bufferedWriter.write(registrationDatetime.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        bufferedWriter.close();
    }
}
