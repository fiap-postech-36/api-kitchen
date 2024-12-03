package br.com.kitchen.infra.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderStatusControlNotFoundExceptionTest {

    @Test
    public void testConstructor_WithMessage() {
        String expectedMessage = "Pagamento não encontrado.";

        OrderStatusControlNotFoundException exception = new OrderStatusControlNotFoundException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage(), "A mensagem da exceção não corresponde.");
    }

    @Test
    public void testThrowException_WithMessage() {
        String expectedMessage = "Pagamento não encontrado.";

        OrderStatusControlNotFoundException exception = assertThrows(OrderStatusControlNotFoundException.class, () -> {
            throw new OrderStatusControlNotFoundException(expectedMessage);
        });
        assertEquals(expectedMessage, exception.getMessage(), "A mensagem da exceção não corresponde.");
    }
}
