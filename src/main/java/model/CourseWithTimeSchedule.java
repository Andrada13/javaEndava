package model;

public class CourseWithTimeSchedule{

    private Course course;
    private TimeSchedule timeSchedule;

    public CourseWithTimeSchedule(Course course, TimeSchedule timeSchedule) {
        this.course = course;
        this.timeSchedule = timeSchedule;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public TimeSchedule getTimeSchedule() {
        return timeSchedule;
    }

    public void setTimeSchedule(TimeSchedule timeSchedule) {
        this.timeSchedule = timeSchedule;
    }

    @Override
    public String toString() {
        return "CourseWithTimeSchedule{" +
                "course=" + course +
                ", timeSchedule=" + timeSchedule +
                '}';
    }


}
