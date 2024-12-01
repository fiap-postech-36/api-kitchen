package br.com.kitchen.infra.gateways;

import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.domain.gateway.OrderStatusControlGateway;
import br.com.kitchen.infra.entity.OrderStatusControlEntity;
import br.com.kitchen.infra.exception.OrderStatusControlNotFoundException;
import br.com.kitchen.infra.mapper.OrderStatusControlMapper;
import br.com.kitchen.infra.repository.OrderStatusControlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class OrderStatusControlGatewayImpl implements OrderStatusControlGateway {


    private final OrderStatusControlRepository orderStatusControlRepository;

    public OrderStatusControlGatewayImpl(OrderStatusControlRepository orderStatusControlRepository) {
        this.orderStatusControlRepository = orderStatusControlRepository;
    }

    private final OrderStatusControlMapper mapper = OrderStatusControlMapper.INSTANCE;

    @Override
    public Optional<OrderStatusControl> save(final OrderStatusControl orderStatusControl) {
        final var orderStatusControlEntity = mapper.orderStatusControlToOrderStatusControlEntity(orderStatusControl);
        return Optional.ofNullable(mapper.orderStatusControlEntityToOrderStatusControl(orderStatusControlRepository.save(orderStatusControlEntity)));
    }

    @Override
    public Optional<OrderStatusControl> update(final OrderStatusControl newOrderStatusControl) {
        return save(newOrderStatusControl);
    }

    @Override
    public Optional<OrderStatusControl> findById(final String id) {
        OrderStatusControlEntity orderStatusControlEntity = orderStatusControlRepository.findById(id).orElseThrow(() -> new OrderStatusControlNotFoundException("OrderStatusControl not found"));
        return Optional.ofNullable(mapper.orderStatusControlEntityToOrderStatusControl(orderStatusControlEntity));
    }

    @Override
    public Collection<OrderStatusControl> findAll() {
        return orderStatusControlRepository.findAll().stream()
                .map(mapper::orderStatusControlEntityToOrderStatusControl)
                .toList();
    }
}
