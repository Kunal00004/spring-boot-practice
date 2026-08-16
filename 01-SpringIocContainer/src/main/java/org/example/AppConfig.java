package org.example;

import in.strikes.CartService;
import org.example.payment.CardPayment;
import org.example.payment.PaymentService;
import org.example.payment.UpiPayment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("org.example")
public class AppConfig {
    @Bean
    public User getUser() {               //user class bean
        return new User("kunal",22);
    }

    @Bean
    public CartService cartService() {       // CartService class bean(jar file class)
        return new CartService();
    }

    @Bean
    @Qualifier("cp")
    public PaymentService createCardPayment() {        //cardPayment bean
        return new CardPayment();
    }

    @Bean
    @Qualifier("upi")
    public PaymentService createUpiPayment() {      //UpiPayment bean
        return new UpiPayment();
    }

    @Bean
    public OrderService createOrderService(@Qualifier("cp")PaymentService paymentService) {   //OrderService bean with constructor
        return new OrderService(paymentService);
    }
//    @Bean
//    public OrderService createOrderService() {        without constructor
//        return new OrderService();
//    }

}
