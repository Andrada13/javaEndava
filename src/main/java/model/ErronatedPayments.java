package model;

public class ErronatedPayments {

    String payload;
    String errorCause;


    public ErronatedPayments(String payload, String errorCause) {
        this.payload = payload;
        this.errorCause = errorCause;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(String errorCause) {
        this.errorCause = errorCause;
    }


}
