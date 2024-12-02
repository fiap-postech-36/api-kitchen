package br.com.kitchen.application.inout.output;

import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderStatusControlOutput(

        String orderId,

        OrderStatus status) {
}
