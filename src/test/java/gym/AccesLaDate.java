package gym;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class AccesLaDate {
    @Test
    public void readUsingStatement() throws SQLException {
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/database_service", "root", "Vampir1234");
        Connection connection = dataSource.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select name,email,address from users");

        resultSet.next();
        String name = resultSet.getString("name");

        System.out.println(name);

    }

    @Test
    public void readUsingPreparedStatement() throws SQLException {
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/database_service", "root", "Vampir1234");
        Connection connection = dataSource.getConnection();

        String sqlWithParam = "select name,email,address from users where name=?";
        PreparedStatement statement = connection.prepareStatement(sqlWithParam);
        statement.setString(1, "George");

        // String sqlWithParam = "select name,email,address from users where name=:nameParam";

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        String address = resultSet.getString("address");


        System.out.println(address);

    }

    @Test
    public void readUsingJdbcTemplate() throws SQLException {
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/database_service", "root", "Vampir1234");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sqlWithParam = "select name,email,address from users where name=?";
        //  PreparedStatement statement = connection.prepareStatement(sqlWithParam);
        //   statement.setString(1,"George");

        String[] parameters = {"George"};
        int[] parameterTypes = {Types.VARCHAR};
        String address = jdbcTemplate.query(sqlWithParam, parameters, parameterTypes,

                new ResultSetExtractor<String>() {

                    @Override
                    public String extractData(ResultSet rs) throws SQLException, DataAccessException {

                        rs.next();
                        return rs.getString("address");

                    }
                }
        );
        System.out.println(address);

    }

    @Test
    public void readUsingJdbcTemplateLambda() throws SQLException {
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/database_service", "root", "Vampir1234");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sqlWithParam = "select name,email,address from users where name=?";
        //  PreparedStatement statement = connection.prepareStatement(sqlWithParam);
        //   statement.setString(1,"George");

        String[] parameters = {"George"};
        int[] parameterTypes = {Types.VARCHAR};
        String address = jdbcTemplate.query(sqlWithParam, parameters, parameterTypes,

                (rs) -> {

                    rs.next();
                    return rs.getString("address");

                }
        );
        System.out.println(address);

    }

    @Test
    public void readUsingJdbcTemplateRowMapper() throws SQLException {
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/database_service", "root", "Vampir1234");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sqlWithParam = "select name,email,address from users where name=?";
        //  PreparedStatement statement = connection.prepareStatement(sqlWithParam);
        //   statement.setString(1,"George");

        String[] parameters = {"George"};
        int[] parameterTypes = {Types.VARCHAR};
        List<String> addresses = jdbcTemplate.query(sqlWithParam, parameters, parameterTypes,

                (rs, rowNo) -> rs.getString("address")
        );
        System.out.println(addresses);

    }

    @Test
    public void readUsingJdbcTemplateRowMapper1() throws SQLException {
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/database_service", "root", "Vampir1234");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sqlWithParam = "select name,email,address from users where name=?";
        //  PreparedStatement statement = connection.prepareStatement(sqlWithParam);
        //   statement.setString(1,"George");

        String[] parameters = {"George"};
        int[] parameterTypes = {Types.VARCHAR};
        List<Trainer> addresses = jdbcTemplate.query(sqlWithParam, parameters, parameterTypes,

                (rs, rowNo) -> {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String email = rs.getString("email");

                    Trainer trainer = new Trainer(name, address, email);
                    return trainer;

                }

        );
        System.out.println(addresses);

    }

    class Trainer {
        String name;
        String address;
        String email;

        public Trainer(String name, String address, String email) {
            this.name = name;
            this.address = address;
            this.email = email;
        }
    }

/*
    @Test
    public void readUsingJdbcTemplateRowMapper2() throws SQLException {
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/database_service", "root", "Vampir1234");
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sqlWithParam = "select course_id,name,description,price from course where description like :description and price> :priceLimit";

        Map<String, Object> map = new HashMap();
        map.put("description", "%stress%");
        map.put("priceLimit", 100);

        List<Course> courses = jdbcTemplate.query(sqlWithParam, map,

                (rs, rowNo) -> {
                    int id = rs.getInt("course_id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int price = rs.getInt("price");

                    Course course = new Course(id, name, description, price);
                    return course;

                }

        );
        System.out.println(courses);

    }
/*
    class Course {
        Integer courseId;
        String name;
        String description;
        Integer price;

        public Course(Integer courseId, String name, String description, Integer price) {
            this.courseId = courseId;
            this.name = name;
            this.description = description;
            this.price = price;


        }

        @Override
        public String toString() {
            return "Course{" +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", price='" + price + '\'' +
                    '}';
        }
    }

/*
    @Test
    public void retrieveTrainers() throws SQLException {
        //DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/database_service","root","Vampir1234");
        String sqlWithParam = "select t.name, t.email, c.name, c.description,c.price\n" +
                "                    from trainer t\n" +
                "                    join course c on t.course_id=c.course_id\n" +
                "                    where c.price>:priceLimit";

        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/database_service", "root", "Vampir1234");
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);


        Map<String, Object> map = new HashMap();
        map.put("priceLimit", 100);

        List<Request> requests = jdbcTemplate.query(sqlWithParam, map,

                (rs, rowNo) -> {
                    String trainerName = rs.getString("name");
                    String description = rs.getString("description");
                    String email = rs.getString("email");
                    String courseName = rs.getString("name");


                    model.Trainer trainer = new model.Trainer(trainerName,email);
                    model.Course course = new model.Course(courseName,description);

                    Request request = new Request(trainer, course);
                    return request;
                }
        );
        System.out.println(requests);
    }
*/
}

