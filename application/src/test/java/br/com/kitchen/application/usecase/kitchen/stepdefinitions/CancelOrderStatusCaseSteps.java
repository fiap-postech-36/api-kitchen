package br.com.kitchen.application.usecase.kitchen.stepdefinitions;

import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.application.usecase.kitchen.CancelOrderStatusCase;
import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.domain.gateway.OrderStatusControlGateway;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CancelOrderStatusCaseSteps {

    @Mock
    private OrderStatusControlGateway orderStatusControlGateway;

    @InjectMocks
    private CancelOrderStatusCase cancelOrderStatusCase;

    private OrderStatusControlInput orderStatusControlInput;
    private OrderStatusControl orderStatusControl;

    @Given("que eu tenha um pedido com ID {string}")
    public void que_eu_tenha_um_pedido_com_ID_e_status(String orderId) {
        MockitoAnnotations.openMocks(this);
        orderStatusControl = new OrderStatusControl(orderId);

        orderStatusControlInput = new OrderStatusControlInput(orderId);

        when(orderStatusControlGateway.findById(orderId)).thenReturn(Optional.of(orderStatusControl));
    }

    @When("eu cancelo o pedido com ID {string}")
    public void eu_cancelo_o_pedido_com_ID(String orderId) {
        cancelOrderStatusCase.execute(orderStatusControlInput);
    }

    @Then("o pedido deve estar com o status {string}")
    public void o_pedido_deve_estar_com_o_status(String expectedStatus) {
        assertEquals(OrderStatus.valueOf(expectedStatus), orderStatusControl.getOrderStatus());

        verify(orderStatusControlGateway, times(1)).update(orderStatusControl);
    }
}