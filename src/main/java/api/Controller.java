package api;

import dao.FitnessDao;
import model.*;
import org.springframework.web.bind.annotation.*;
import services.HelloService;

import java.io.IOException;
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

    // what if we make this point receive a Trainer object?
    @PostMapping(path = "/newTrainer", consumes="application/json")
    public int insertTrainerWithBody(@RequestBody Trainer trainer) throws SQLException {


        return fitnessDao.createTrainerWithBody(trainer);

    }



    @PutMapping("/course")
    public int updateCourse(@RequestParam String description, @RequestParam Integer price) throws SQLException {


        return fitnessDao.updateCourse(description,price);

    }


}
