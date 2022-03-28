package model;

public class RabbitMessageRegistration implements RabbitMessage {

     private String messageType;
     private Registration messageContent;

    public RabbitMessageRegistration(String messageType, Registration messageContent) {
        this.messageType = messageType;
        this.messageContent = messageContent;
    }

    public RabbitMessageRegistration() {
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

    public void setMessageContent(Registration messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "RabbitMessageRegistration{" +
                "messageType='" + messageType + '\'' +
                ", messageContent=" + messageContent +
                '}';
    }
}
