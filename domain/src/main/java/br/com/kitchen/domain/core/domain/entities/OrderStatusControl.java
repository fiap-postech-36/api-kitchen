package br.com.kitchen.domain.core.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class OrderStatusControl implements Serializable {

    private String orderId;
    private OrderStatus orderStatus;
    private LocalDateTime lastUpdate;

    public OrderStatusControl(String orderId) {
        this.orderId = orderId;
        orderStatus = OrderStatus.getFromOrder(0);
        lastUpdate = LocalDateTime.now();
    }

    public void nextStepOrder() {
        this.orderStatus = this.orderStatus.getNext();
        this.lastUpdate = LocalDateTime.now();
    }

    public void cancel() {
        this.orderStatus = OrderStatus.getFromOrder(-1);
        this.lastUpdate = LocalDateTime.now();
    }

}
