package model;

public class Course {

    private Integer courseId;
    private String name;
    private String description;
    private Integer price;

    public Course(Integer courseId, String name, String description, Integer price) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public Course(){

    }

    public Course(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public Course(String name) {
        this.name = name;

    }



    public Integer getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

