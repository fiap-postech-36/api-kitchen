package br.com.kitchen.application.usecase.kitchen.stepdefinitions;

import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.application.usecase.kitchen.CancelOrderStatusCase;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.domain.gateway.OrderStatusControlGateway;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CancelOrderStatusSteps {

    @Autowired
    private CancelOrderStatusCase cancelOrderStatusCase;

    private final OrderStatusControlGateway mockGateway = Mockito.mock(OrderStatusControlGateway.class);
    private OrderStatusControl orderStatusControl;

    @Given("an existing order with ID {string} and status {string}")
    public void anExistingOrderWithIDAndStatus(String orderId, String status) {
        orderStatusControl = new OrderStatusControl("111");

        Mockito.when(mockGateway.findById(orderId)).thenReturn(Optional.of(orderStatusControl));
    }

    @When("the cancel order status is executed")
    public void theCancelOrderStatusIsExecuted() {
        cancelOrderStatusCase = new CancelOrderStatusCase(mockGateway);
        cancelOrderStatusCase.execute(new OrderStatusControlInput("123")); // Mock input
    }

    @Then("the order status should be {string}")
    public void theOrderStatusShouldBe(String expectedStatus) {
        assertEquals(expectedStatus, orderStatusControl.getOrderStatus().name());
        Mockito.verify(mockGateway).update(orderStatusControl);
    }
}