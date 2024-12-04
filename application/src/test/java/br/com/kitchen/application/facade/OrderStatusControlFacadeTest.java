package br.com.kitchen.application.facade;

import br.com.kitchen.application.infra.RabbitMQConfig;
import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.application.usecase.kitchen.CancelOrderStatusCase;
import br.com.kitchen.application.usecase.kitchen.CreateOrderStatusCase;
import br.com.kitchen.application.usecase.kitchen.GetAllOrderStatusCase;
import br.com.kitchen.application.usecase.kitchen.UpdateOrderStatusCase;
import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderStatusControlFacadeTest {

    @Mock
    private CreateOrderStatusCase createOrderStatusCase;

    @Mock
    private UpdateOrderStatusCase updateOrderStatusCase;

    @Mock
    private CancelOrderStatusCase cancelOrderStatusCase;

    @Mock
    private GetAllOrderStatusCase getAllOrderStatusCase;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private OrderStatusControlFacade orderStatusControlFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        OrderStatusControlInput input = mock(OrderStatusControlInput.class);

        orderStatusControlFacade.createOrder(input);

        verify(createOrderStatusCase, times(1)).execute(input);
    }

    @Test
    void testUpdateOrderWithoutNotification() {
        OrderStatusControlInput input = mock(OrderStatusControlInput.class);
        when(updateOrderStatusCase.execute(input)).thenReturn(OrderStatus.RECEIVED);

        orderStatusControlFacade.updateOrder(input);

        verify(updateOrderStatusCase, times(1)).execute(input);
        verifyNoInteractions(rabbitTemplate);
    }

    @Test
    void testUpdateOrderWithNotification() {
        OrderStatusControlInput input = mock(OrderStatusControlInput.class);
        when(input.orderId()).thenReturn("123");
        when(updateOrderStatusCase.execute(input)).thenReturn(OrderStatus.FINISHED);

        orderStatusControlFacade.updateOrder(input);

        verify(updateOrderStatusCase, times(1)).execute(input);
        verify(rabbitTemplate, times(1)).convertAndSend(
                RabbitMQConfig.KITCHEN_EXCHANGE_NAME,
                RabbitMQConfig.KITCHEN_ORDER_KEY_NAME,
                "123"
        );
    }

    @Test
    void testCancelOrder() {
        OrderStatusControlInput input = mock(OrderStatusControlInput.class);

        orderStatusControlFacade.cancelOrder(input);

        verify(cancelOrderStatusCase, times(1)).execute(input);
    }

    @Test
    void testGetAllOrder() {
        List<OrderStatusControl> expectedOrders = List.of(mock(OrderStatusControl.class));
        when(getAllOrderStatusCase.execute()).thenReturn(expectedOrders);

        List<OrderStatusControl> result = orderStatusControlFacade.getAllOrder();

        verify(getAllOrderStatusCase, times(1)).execute();
        assertEquals(expectedOrders, result);
    }
}