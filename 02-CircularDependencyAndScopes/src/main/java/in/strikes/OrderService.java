package in.strikes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderService {

    private PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    void orderPlace(){
        paymentService.pay();
        //call here
        getOrderDetails(); //call here to avoid C/D
        System.out.println("Order place called...");



    }
    void getOrderDetails() {
        System.out.println("Order details called...");
    }
}
