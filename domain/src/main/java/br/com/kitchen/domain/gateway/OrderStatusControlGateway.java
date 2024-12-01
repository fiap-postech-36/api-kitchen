package br.com.kitchen.domain.gateway;


import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;

import java.util.Collection;
import java.util.Optional;

public interface OrderStatusControlGateway {

    Optional<OrderStatusControl> save(final OrderStatusControl orderStatusControl);
    Optional<OrderStatusControl> update(final OrderStatusControl orderStatusControl);
    Optional<OrderStatusControl> findById(final String id);
    Collection<OrderStatusControl> findAll();

}
