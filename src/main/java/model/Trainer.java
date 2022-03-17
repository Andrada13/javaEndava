package model;

public class Trainer {
       private String name;
       private String email;
       private Integer courseId;


    public Trainer(String name, String email, Integer courseId) {
        this.name = name;
        this.email = email;
        this.courseId = courseId;
    }

    public Trainer(){

    }

    public Trainer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}

