package br.com.kitchen.application.usecase.kitchen.stepdefinitions;
import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.domain.gateway.OrderStatusControlGateway;
import br.com.kitchen.application.usecase.kitchen.UpdateOrderStatusCase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UpdateOrderStatusSteps {

    private final OrderStatusControlGateway orderStatusControlGateway = Mockito.mock(OrderStatusControlGateway.class);
    private final UpdateOrderStatusCase updateOrderStatusCase = new UpdateOrderStatusCase(orderStatusControlGateway);

    private OrderStatusControl existingOrderStatusControl;
    private OrderStatus result;

    @Given("an order exists in the system with ID {string} and current status {string}")
    public void anOrderExistsInTheSystemWithIDAndCurrentStatus(String orderId, String status) {
        existingOrderStatusControl = new OrderStatusControl(orderId, OrderStatus.valueOf(status));
        when(orderStatusControlGateway.findById(orderId))
            .thenReturn(Optional.of(existingOrderStatusControl));
    }

    @When("the system processes the order to the next step")
    public void theSystemProcessesTheOrderToTheNextStep() {
        result = updateOrderStatusCase.execute(new OrderStatusControlInput(existingOrderStatusControl.getOrderId()));
    }

    @Then("the order status should transition to {string}")
    public void theOrderStatusShouldTransitionTo(String expectedStatus) {
        existingOrderStatusControl.nextStepOrder();
        verify(orderStatusControlGateway, times(1)).findById(existingOrderStatusControl.getOrderId());
        verify(orderStatusControlGateway, times(1)).update(existingOrderStatusControl);
        assertEquals(OrderStatus.valueOf(expectedStatus), result);
    }
}