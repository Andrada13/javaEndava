package model;

public class RabbitMessagePayment implements RabbitMessage {

     private String messageType;
     private Payment messageContent;

    public RabbitMessagePayment(String messageType, Payment messageContent) {
        this.messageType = messageType;
        this.messageContent = messageContent;
    }

    public RabbitMessagePayment() {
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Object getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(Payment messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "{" +
                "messageType='" + messageType + '\'' +
                ", messageContent=" + messageContent+
                '}';
    }
}
