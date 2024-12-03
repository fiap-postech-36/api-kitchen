package br.com.kitchen.domain.core.domain.entities.stepdefinitions;

import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStatusSteps {

    private int orderValue;
    private OrderStatus currentStatus;
    private OrderStatus resultStatus;

    @Given("the order value is {int}")
    public void theOrderValueIs(int order) {
        this.orderValue = order;
    }

    @When("I call getFromOrder")
    public void iCallGetFromOrder() {
        this.resultStatus = OrderStatus.getFromOrder(orderValue);
    }

    @Then("the OrderStatus should be {word}")
    public void theOrderStatusShouldBe(String expectedStatusName) {
        if ("null".equals(expectedStatusName)) {
            assertNull(resultStatus);
        } else {
            assertNotNull(resultStatus);
            assertEquals(OrderStatus.valueOf(expectedStatusName), resultStatus);
        }
    }

    @Given("the current OrderStatus is {word}")
    public void theCurrentOrderStatusIs(String statusName) {
        this.currentStatus = OrderStatus.valueOf(statusName);
    }

    @When("I call getNext")
    public void iCallGetNext() {
        this.resultStatus = currentStatus.getNext();
    }

    @Then("the next OrderStatus should be {word}")
    public void theNextOrderStatusShouldBe(String expectedStatusName) {
        if ("null".equals(expectedStatusName)) {
            assertNull(resultStatus);
        } else {
            assertNotNull(resultStatus);
            assertEquals(OrderStatus.valueOf(expectedStatusName), resultStatus);
        }
    }
}
