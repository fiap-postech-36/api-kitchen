package br.com.kitchen.application.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.kitchen.infra.exception.OrderStatusControlNotFoundException;
import org.junit.jupiter.api.Test;

public class ApiErrorExceptionTest {

    @Test
    void testPaymentNotFoundExceptionCode() {
        OrderStatusControlNotFoundException exception = new OrderStatusControlNotFoundException("Payment with ID 123 not found.");
        assertEquals("Payment with ID 123 not found.", exception.getMessage());
    }

    @Test
    void testPaymentNotFoundExceptionMessage() {
        String expectedMessage = "Payment with ID 123 not found.";

        OrderStatusControlNotFoundException exception = new OrderStatusControlNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testPaymentNotFoundExceptionIsThrown() {
        String expectedMessage = "Payment with ID 123 not found.";
        OrderStatusControlNotFoundException exception = assertThrows(OrderStatusControlNotFoundException.class, () -> {
            throw new OrderStatusControlNotFoundException(expectedMessage);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }
}

