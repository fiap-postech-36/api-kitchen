package br.com.kitchen.application.integration;

import br.com.kitchen.application.facade.OrderStatusControlFacade;
import br.com.kitchen.application.infra.RabbitMQConfig;
import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.application.inout.output.OrderStatusControlOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final OrderStatusControlFacade orderStatusControlFacade;

    @RabbitListener(queues = RabbitMQConfig.ORDER_KITCHEN_QUEUE_NAME)
    public void receiveOrderMessage(String orderId) {
        orderStatusControlFacade.createOrder(new OrderStatusControlInput(orderId));
    }

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_QUEUE_NAME)
    public void receivePaymentMessage(String orderId) {
        orderStatusControlFacade.updateOrder(new OrderStatusControlInput(orderId));
    }
}
