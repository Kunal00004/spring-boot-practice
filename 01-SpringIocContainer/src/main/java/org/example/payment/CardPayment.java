package org.example.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component
//@Primary  -To gives more priority
//@Qualifier("cp")
public class CardPayment implements PaymentService {
    @Override
    public void pay() {
        System.out.println("pay via card...!");
    }
}
