package br.com.kitchen.infra.gateway;

import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.infra.entity.OrderStatusControlEntity;
import br.com.kitchen.infra.exception.OrderStatusControlNotFoundException;
import br.com.kitchen.infra.gateways.OrderStatusControlGatewayImpl;
import br.com.kitchen.infra.mapper.OrderStatusControlMapper;
import br.com.kitchen.infra.repository.OrderStatusControlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderStatusControlGatewayImplTest {

    @Mock
    private OrderStatusControlRepository orderStatusControlRepository;

    @Mock
    private OrderStatusControlMapper mapper;

    @InjectMocks
    private OrderStatusControlGatewayImpl paymentGateway;

    private OrderStatusControl orderStatusControl;
    private OrderStatusControlEntity orderStatusControlEntity;

    @BeforeEach
    void setUp() {
        orderStatusControl = new OrderStatusControl("123");


        orderStatusControlEntity = OrderStatusControlEntity.builder()
                .orderId("123")
                .build();
    }

    @Test
    void testSave() {
        when(orderStatusControlRepository.save(Mockito.any())).thenReturn(orderStatusControlEntity);

        Optional<OrderStatusControl> result = paymentGateway.save(orderStatusControl);

        assertTrue(result.isPresent());
        assertEquals(orderStatusControl.getOrderId(), result.get().getOrderId());
    }

    @Test
    void testUpdate() {
        when(orderStatusControlRepository.save(Mockito.any())).thenReturn(orderStatusControlEntity);

        Optional<OrderStatusControl> result = paymentGateway.update(orderStatusControl);

        assertTrue(result.isPresent());
        assertEquals(orderStatusControl.getOrderId(), result.get().getOrderId());
        assertEquals(OrderStatus.CREATED, orderStatusControl.getOrderStatus());
    }

    @Test
    void testFindById() {
        String id = "payment-id";
        when(orderStatusControlRepository.findById(id)).thenReturn(Optional.of(orderStatusControlEntity));

        Optional<OrderStatusControl> result = paymentGateway.findById(id);

        assertTrue(result.isPresent());
        assertEquals(orderStatusControl.getOrderId(), result.get().getOrderId());
        verify(orderStatusControlRepository).findById(id);
    }

    @Test
    void testFindById_PaymentNotFoundException() {
        String id = "payment-id";
        when(orderStatusControlRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(OrderStatusControlNotFoundException.class, () -> paymentGateway.findById(id));
        verify(orderStatusControlRepository).findById(id);
    }

    @Test
    void testFindAll() {
        when(orderStatusControlRepository.findAll()).thenReturn(List.of(orderStatusControlEntity));

        Collection<OrderStatusControl> result = paymentGateway.findAll();

        assertEquals(1, result.size());
        assertFalse(result.contains(orderStatusControl));
        verify(orderStatusControlRepository).findAll();
    }
}

