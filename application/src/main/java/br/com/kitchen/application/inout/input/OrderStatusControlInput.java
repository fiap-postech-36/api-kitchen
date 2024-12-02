package br.com.kitchen.application.inout.input;

import jakarta.validation.constraints.NotNull;


public record OrderStatusControlInput(

        @NotNull(message = "order must be provided")
        String orderId
){}
