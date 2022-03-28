package model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Payment  {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp paymentTimestamp;
    private Integer userId;
    private String orgaccno;
    private String benaccno;
    private Integer amountSettlement;
    private String courseName;

    public Timestamp getPaymentTimestamp() {
        return paymentTimestamp;
    }

    public void setPaymentTimestamp(Timestamp paymentTimestamp) {
        this.paymentTimestamp = paymentTimestamp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrgaccno() {
        return orgaccno;
    }

    public void setOrgaccno(String orgaccno) {
        this.orgaccno = orgaccno;
    }

    public String getBenaccno() {
        return benaccno;
    }

    public void setBenaccno(String benaccno) {
        this.benaccno = benaccno;
    }

    public Integer getAmountSettlement() {
        return amountSettlement;
    }

    public void setAmountSettlement(Integer amountSettlement) {
        this.amountSettlement = amountSettlement;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "{" +
                "paymentTimestamp=" + paymentTimestamp +
                ", userId=" + userId +
                ", orgaccno='" + orgaccno + '\'' +
                ", benaccno='" + benaccno + '\'' +
                ", amountSettlement=" + amountSettlement +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
