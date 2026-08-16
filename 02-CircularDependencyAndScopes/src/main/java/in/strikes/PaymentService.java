package in.strikes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
//    @Autowired             //to avoid circular dependency
//    private OrderService orderService;
//    PaymentService(OrderService orderService){
//        this.orderService = orderService;
//    }
    void pay(){
        System.out.println("Payment service called...");
        //Not its responsibility
//        orderService.getOrderDetails();        // C/D occur
    }
}
