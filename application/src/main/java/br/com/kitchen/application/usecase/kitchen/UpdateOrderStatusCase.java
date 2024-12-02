package br.com.kitchen.application.usecase.kitchen;

import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.domain.gateway.OrderStatusControlGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderStatusCase {

    private final OrderStatusControlGateway orderStatusControlGateway;

    public OrderStatus execute(OrderStatusControlInput orderStatusControlInput) {
        OrderStatusControl orderStatusControl = orderStatusControlGateway.findById(String.valueOf(orderStatusControlInput.orderId())).orElseThrow(RuntimeException::new);
        orderStatusControl.nextStepOrder();
        orderStatusControlGateway.update(orderStatusControl);
        return orderStatusControl.getOrderStatus();
    }
}
