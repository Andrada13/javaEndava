package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import model.RabbitMessagePayment;
import model.RabbitMessageRegistration;
import model.Registration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitPayment {

    static ObjectMapper objectMapper = new ObjectMapper();
    private static final String EXCHANGE_NAME = "paymentExchange";
    private static final String EXCHANGE_NAME_REGISTRATION = "fitnessExchange";

    PaymentService paymentService;
    RegistrationService registrationService;

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }



    @PostConstruct
    public void createChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        waitingMessages(channel);
        //waitingMessagesRegistration(channel);
    }


    public void waitingMessages(Channel channel) throws IOException, TimeoutException {
        String queueName = "payment.queue";

        channel.queueBind(queueName, EXCHANGE_NAME, "payment");

        System.out.println(" [*] Waiting for messages.");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            //String message = new String(delivery.getBody(), "UTF-8");
            byte[] messageBody = delivery.getBody();

           // System.out.println(" [x] Received '" + message + "'");
            try {
                paymentService.createNewPaymentFromRabbitMessage(messageBody);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });

    }


    public void waitingMessagesRegistration(Channel channel) throws IOException, TimeoutException {
        String queueName = "director.queue";

        channel.queueBind(queueName, EXCHANGE_NAME_REGISTRATION, "director");

        System.out.println(" [*] Waiting for messages.");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            //String message = new String(delivery.getBody(), "UTF-8");
            RabbitMessageRegistration message = objectMapper.readValue(delivery.getBody(), RabbitMessageRegistration.class);
            System.out.println(" [x] Received '" + message + "'");
            try {
                registrationService.createNewRegistration((Registration) message.getMessageContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
