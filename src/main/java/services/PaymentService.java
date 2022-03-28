package services;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PaymentDao;
import exceptions.DataNotFoundException;
import model.Course;
import model.Payment;
import model.RabbitMessagePayment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class PaymentService {

    PaymentDao paymentDao;


    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }


   // @Transactional
    public void createNewPaymentFromRabbitMessage(byte[] messageBody) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        RabbitMessagePayment rabbitMessagePayment = null;
        boolean ok=true;
        try {
            //RabbitMessagePayment message = rabbitMessagePayment;
            rabbitMessagePayment = objectMapper.readValue(messageBody, RabbitMessagePayment.class);

        }catch(Exception e){
            ok=false;
        }


        if(ok) {
            Payment payment = (Payment) rabbitMessagePayment.getMessageContent();
            createNewPayment(payment);
        }else{
            paymentDao.insertErronatedPayments(new String(messageBody),"Parsing Error");
            throw new Exception("Syntax error");
        }

    }


   // @Transactional
    public void createNewPayment(Payment payment) {

        //Payment payment = (Payment) rabbitMessagePayment.getMessageContent();


        List<Course> courses = paymentDao.validateCourses(payment.getCourseName());

            if (paymentDao.validateUserId(payment.getUserId()) == 0) {
                paymentDao.insertErronatedPayments(payment.toString(), "User not found");
                throw new DataNotFoundException("User with id " + payment.getUserId() + " was not found");
            } else if (courses.size() == 0) {
                paymentDao.insertErronatedPayments(payment.toString(), "Course not found");
                throw new DataNotFoundException("Course " + payment.getCourseName() + " not found");
            } else {
                paymentDao.createPayment(payment);
                // RabbitMessagePayment rabbitMessage = new RabbitMessagePayment("payment",payment);
                //rabbitMessenger.messagePublisherPayment(rabbitMessage);

            }
        }
    }
