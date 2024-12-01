package br.com.kitchen.infra.exception;

public class OrderStatusControlNotFoundException extends RuntimeException {

    public OrderStatusControlNotFoundException(String message) {
        super(message);
    }
}
