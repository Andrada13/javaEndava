package model;

public class RegistrationDatetime {
    Registration registration;
    Trainer trainer;
    Course course;
    User user;

    public RegistrationDatetime(Registration registration, Trainer trainer, Course course, User user) {
        this.registration = registration;
        this.trainer = trainer;
        this.course = course;
        this.user = user;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return registration.getRegistrationId() +","+ user.getName()+ ","+ trainer.getName() + "," + course.getName()+","
                + registration.getTimestamp();

    }
}
