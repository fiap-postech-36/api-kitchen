package br.com.kitchen.application.usecase.kitchen.stepdefinitions;

import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.application.usecase.kitchen.CreateOrderStatusCase;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.domain.gateway.OrderStatusControlGateway;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CreateOrderStatusSteps {

    private final OrderStatusControlGateway orderStatusControlGateway = Mockito.mock(OrderStatusControlGateway.class);
    private final CreateOrderStatusCase createOrderStatusCase = new CreateOrderStatusCase(orderStatusControlGateway);

    private OrderStatusControl orderStatusControl;

    @Given("a new order with ID {string}")
    public void aNewOrderWithID(String orderId) {
        orderStatusControl = new OrderStatusControl(orderId);
    }

    @When("the create order status is executed")
    public void theCreateOrderStatusIsExecuted() {
        createOrderStatusCase.execute(new OrderStatusControlInput(orderStatusControl.getOrderId()));
    }

    @Then("the order status should be saved successfully")
    public void theOrderStatusShouldBeSavedSuccessfully() {
        // Capture o argumento passado para o método save
        ArgumentCaptor<OrderStatusControl> captor = ArgumentCaptor.forClass(OrderStatusControl.class);
        verify(orderStatusControlGateway, times(1)).save(captor.capture());

        // Pegue o valor capturado e compare com o objeto esperado
        OrderStatusControl capturedOrderStatusControl = captor.getValue();

        assertEquals(orderStatusControl.getOrderId(), capturedOrderStatusControl.getOrderId());
    }
}