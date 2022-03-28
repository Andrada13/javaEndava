package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import model.Payment;
import model.RabbitMessage;
import model.Registration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMessenger {
    //RabbitPayment rabbitPayment;

    //public void setRabbitPayment(RabbitPayment rabbitPayment) {
    //    this.rabbitPayment = rabbitPayment;
   // }

    static ObjectMapper objectMapper = new ObjectMapper();

    private static final String EXCHANGE_NAME = "fitnessExchange";
    private static final String EXCHANGE_NAME_PAYMENT = "paymentExchange";


    public static void messagePublisher(RabbitMessage rabbitMessage) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

           // RabbitMessage rabbitMessage = new RabbitMessage("registration",messageContent);
            String message =objectMapper.writeValueAsString(rabbitMessage);

            channel.basicPublish(EXCHANGE_NAME, "director", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");

        }
    }

    public void messagePublisherPayment(RabbitMessage rabbitMessage) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

           // RabbitMessage rabbitMessage = new RabbitMessage("payment",messageContent);
            String message =objectMapper.writeValueAsString(rabbitMessage);

            channel.basicPublish(EXCHANGE_NAME_PAYMENT, "payment", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");


           // rabbitPayment.waitingMessages(channel);

        }
    }

}
