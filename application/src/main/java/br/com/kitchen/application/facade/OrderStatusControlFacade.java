package br.com.kitchen.application.facade;

import br.com.kitchen.application.infra.RabbitMQConfig;
import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.application.usecase.kitchen.CancelOrderStatusCase;
import br.com.kitchen.application.usecase.kitchen.CreateOrderStatusCase;
import br.com.kitchen.application.usecase.kitchen.GetAllOrderStatusCase;
import br.com.kitchen.application.usecase.kitchen.UpdateOrderStatusCase;
import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderStatusControlFacade {

    private final CreateOrderStatusCase createOrderStatusCase;
    private final UpdateOrderStatusCase updateOrderStatusCase;
    private final CancelOrderStatusCase cancelOrderStatusCase;
    private final GetAllOrderStatusCase getAllOrderStatusCase;
    private final RabbitTemplate rabbitTemplate;

    public void createOrder(final OrderStatusControlInput orderStatusControlInput) {
        createOrderStatusCase.execute(orderStatusControlInput);
    }

    public void updateOrder(final OrderStatusControlInput orderStatusControlInput) {
        OrderStatus orderStatus = updateOrderStatusCase.execute(orderStatusControlInput);
        if (orderStatus.equals(OrderStatus.FINISHED)) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.KITCHEN_EXCHANGE_NAME, RabbitMQConfig.KITCHEN_ORDER_KEY_NAME, orderStatusControlInput.orderId());
        }
    }

    public void cancelOrder(final OrderStatusControlInput orderStatusControlInput) {
        cancelOrderStatusCase.execute(orderStatusControlInput);
    }

    public List<OrderStatusControl> getAllOrder() {
        return getAllOrderStatusCase.execute();
    }
}
