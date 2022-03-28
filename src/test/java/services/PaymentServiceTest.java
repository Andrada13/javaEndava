package services;


import dao.PaymentDao;

import exceptions.DataNotFoundException;
import model.Course;
import model.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PaymentServiceTest {


    @Mock
    PaymentDao paymentDao;

    @InjectMocks
    PaymentService paymentService;


    @Captor
    ArgumentCaptor<Payment> paymentCaptor;

    @Test
    public void createPaymentTestSuccessful() {

        when(paymentDao.validateCourses("Riding")).thenReturn(getListOfCourses());
        when(paymentDao.validateUserId(10)).thenReturn(1);

        paymentService.createNewPayment(getPayment());

        // verify(paymentDao).createPayment(any(Payment.class)); //argument matcher
        verify(paymentDao).createPayment(paymentCaptor.capture());
        Payment captorValue = paymentCaptor.getValue();
        assertEquals("123445", captorValue.getOrgaccno());

    }


    @Test
    public void createPaymentTestIncorrectUserId() {
        //when(paymentDao.validateCourses("Riding")).thenReturn(getListOfCourses());
        when(paymentDao.validateUserId(10)).thenReturn(0);

        Assertions.assertThrows(DataNotFoundException.class,
                () -> paymentService.createNewPayment(getPayment()));


        // verify(paymentDao).createPayment(any(Payment.class)); //argument matcher
        // verify(paymentDao).createPayment(paymentCaptor.capture());
        verify(paymentDao).insertErronatedPayments(getPayment().toString(), "User not found");
        // Payment captorValue = paymentCaptor.getValue();
        //assertEquals("123445",captorValue.getOrgaccno());

    }

    @Test
    public void createPaymentTestIncorrectCourseName() {


        when(paymentDao.validateUserId(10)).thenReturn(1);
        when(paymentDao.validateCourses("Riding")).thenReturn(Collections.emptyList());


        Assertions.assertThrows(DataNotFoundException.class,
                () -> paymentService.createNewPayment(getPayment()));

        verify(paymentDao).insertErronatedPayments(getPayment().toString(), "Course not found");

    }

    private List<Course> getListOfCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(2, "Yoga", "abc", 12));

        return courses;
    }

    private Payment getPayment() {
        Payment payment = new Payment();
        payment.setUserId(10);
        payment.setCourseName("Riding");
        payment.setOrgaccno("123445");
        payment.setAmountSettlement(10);
        payment.setBenaccno("23567");
        payment.setPaymentTimestamp(Timestamp.valueOf("2020-10-23 12:20:10"));

        return payment;
    }

}


