package org.example.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.management.Query;

//@Component
//@Primary  -To gives more priority
//@Qualifier("upi")
public class UpiPayment implements PaymentService {
    @Override
    public void pay() {
        System.out.println("pay via upi..!");
    }
}
