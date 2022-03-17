package model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Registration {

    private Integer registrationId;
    private Integer userId;
    private Integer trainerId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp timestamp;

    public Registration(Integer registrationId, Integer userId, Integer trainerId, Timestamp timestamp) {
        this.registrationId = registrationId;
        this.userId = userId;
        this.trainerId = trainerId;
        this.timestamp = timestamp;
    }

    public Registration(){

    }
    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationId=" + registrationId +
                ", userId=" + userId +
                ", trainerId=" + trainerId +
                ", timestamp=" + timestamp +
                '}';
    }
}
