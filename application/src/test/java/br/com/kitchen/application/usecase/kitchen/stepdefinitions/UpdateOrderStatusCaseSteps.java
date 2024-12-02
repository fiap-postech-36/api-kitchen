package br.com.kitchen.application.usecase.kitchen.stepdefinitions;

import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.application.usecase.kitchen.UpdateOrderStatusCase;
import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.domain.gateway.OrderStatusControlGateway;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UpdateOrderStatusCaseSteps {

    @Mock
    private OrderStatusControlGateway orderStatusControlGateway;

    @InjectMocks
    private UpdateOrderStatusCase updateOrderStatusCase;

    private OrderStatusControl orderStatusControl;
    private OrderStatus updatedOrderStatus;

    @Given("que o pedido com ID {string} tem o status {string}")
    public void que_o_pedido_com_id_tem_o_status(String orderId, String status) {
        MockitoAnnotations.openMocks(this);

        // Criando o pedido mockado com o status inicial
        orderStatusControl = new OrderStatusControl(orderId);
        orderStatusControl.setOrderStatus(OrderStatus.valueOf(status));

        // Mockando o comportamento do gateway
        when(orderStatusControlGateway.findById(orderId)).thenReturn(java.util.Optional.of(orderStatusControl));
    }

    @When("eu atualizo o status do pedido")
    public void eu_atualizo_o_status_do_pedido() {
        // Criando a entrada para o caso de uso
        OrderStatusControlInput input = new OrderStatusControlInput(orderStatusControl.getOrderId());

        // Executando o caso de uso para atualizar o status
        updatedOrderStatus = updateOrderStatusCase.execute(input);
    }

    @Then("o status do pedido deve ser {string}")
    public void o_status_do_pedido_deve_ser(String expectedStatus) {
        assertNotNull(updatedOrderStatus);
        assertEquals(OrderStatus.valueOf(expectedStatus), updatedOrderStatus);

        verify(orderStatusControlGateway, times(1)).findById(orderStatusControl.getOrderId());

        verify(orderStatusControlGateway, times(1)).update(orderStatusControl);
    }
}