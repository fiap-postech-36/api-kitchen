package br.com.kitchen.application.usecase.kitchen.stepdefinitions;

import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.application.usecase.kitchen.CreateOrderStatusCase;
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

public class CreateOrderStatusCaseSteps {

    @Mock
    private OrderStatusControlGateway orderStatusControlGateway;

    @InjectMocks
    private CreateOrderStatusCase createOrderStatusCase;

    private OrderStatusControlInput orderStatusControlInput;
    private OrderStatusControl orderStatusControl;

    @Given("que eu tenha um pedido sem ID")
    public void que_eu_tenha_um_pedido_com_ID() {
        MockitoAnnotations.openMocks(this);
        orderStatusControlInput = new OrderStatusControlInput("123");

        orderStatusControl = new OrderStatusControl("123");

        when(orderStatusControlGateway.save(any(OrderStatusControl.class))).thenReturn(Optional.ofNullable(orderStatusControl));
    }

    @When("eu crio o pedido")
    public void eu_crio_o_status_para_o_pedido_com_ID() {
        createOrderStatusCase.execute(orderStatusControlInput);
    }

    @Then("o pedido deve ser salvo com o status {string}")
    public void o_pedido_deve_ser_salvo_com_o_status(String expectedStatus) {
        assertEquals(OrderStatus.valueOf(expectedStatus), orderStatusControl.getOrderStatus());
        verify(orderStatusControlGateway, times(1)).save(any(OrderStatusControl.class));
    }
}