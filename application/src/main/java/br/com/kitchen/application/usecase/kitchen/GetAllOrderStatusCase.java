package br.com.kitchen.application.usecase.kitchen;

import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.domain.gateway.OrderStatusControlGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllOrderStatusCase {

    private final OrderStatusControlGateway orderStatusControlGateway;

    public List<OrderStatusControl> execute() {
        return orderStatusControlGateway.findAll().stream().toList();
    }
}
