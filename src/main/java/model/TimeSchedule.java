package model;

public class TimeSchedule {

    private Integer scheduleId;
    private String scheduleTime;


    public TimeSchedule(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    @Override
    public String toString() {
        return "TimeSchedule{" +
                ", scheduleTime='" + scheduleTime + '\'' +
                '}';
    }
}
