package api;

import dao.FitnessDao;
import model.Course;
import model.CourseWithTimeSchedule;
import model.TrainerWithCourseDescription;
import model.User;
import org.springframework.web.bind.annotation.*;
import services.HelloService;

import java.sql.SQLException;
import java.util.List;

@ResponseBody
@RequestMapping("/")
public class Controller {

    HelloService helloService;
    FitnessDao fitnessDao;

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    public void setFitnessDao(FitnessDao fitnessDao) {
        this.fitnessDao = fitnessDao;
    }

    @GetMapping("/message")
    public String printMessage(){
       return helloService.hello();
    }

    @GetMapping("/courses")
    public List<Course> searchCourses(@RequestParam String word, @RequestParam int price) throws SQLException {
        String description = "%"+word+"%";

        return fitnessDao.retrieveCourses(description,price);

    }

    @GetMapping("/trainers")
    public List<TrainerWithCourseDescription> searchTrainers(@RequestParam Integer price) throws SQLException {
       return fitnessDao.retrieveTrainers(price);

    }

    @GetMapping("/coursesTimeSchedule")
    public List<CourseWithTimeSchedule> searchCoursesWithTimeSchedule(@RequestParam String word) throws SQLException {
        String time = "%"+word+"%";

        return fitnessDao.retrieveCoursesWithTimeSchedule(time);

    }

    @GetMapping("/users")
    public List<User> searchUsers(@RequestParam String word) throws SQLException {
        String name = word+"%";

        return fitnessDao.retrieveUsers(name);

    }

    @PostMapping("/trainer")
    public int insertTrainer(@RequestParam Integer trainerId, @RequestParam Integer courseId,@RequestParam String name,
                             @RequestParam String email) throws SQLException {


        return fitnessDao.createTrainer(trainerId,courseId,name,email);

    }

    @PutMapping("/course")
    public int updateCourse(@RequestParam String description, @RequestParam Integer price) throws SQLException {


        return fitnessDao.updateCourse(description,price);

    }
}