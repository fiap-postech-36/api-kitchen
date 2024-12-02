package br.com.kitchen.domain.core.domain.entities.stepdefinitions;

import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStatusControlSteps {

    private OrderStatusControl order;
    private LocalDateTime lastUpdateBeforeAction;

    @Given("um pedido com ID {string} é criado")
    public void criarPedido(String orderId) throws InterruptedException {
        order = new OrderStatusControl(orderId);
        lastUpdateBeforeAction = order.getLastUpdate();
        Thread.sleep(5);
    }

    @When("o status do pedido é avançado")
    public void avancarStatusPedido() {
        order.nextStepOrder();
    }

    @When("o pedido é cancelado")
    public void cancelarPedido() {
        order.cancel();
    }

    @Then("o status do pedido deve ser {string}")
    public void verificarStatusPedido(String expectedStatus) {
        assertEquals(OrderStatus.valueOf(expectedStatus), order.getOrderStatus());
    }

    @Then("a última atualização deve ser registrada")
    public void verificarUltimaAtualizacao() {
        assertNotNull(order.getLastUpdate());
        assertTrue(order.getLastUpdate().isAfter(lastUpdateBeforeAction));
    }

    @And("a última atualização deve ser registrada na criação")
    public void aultimaAtualizacaoDeveSerRegistradaNaCriacao() {
        assertNotNull(order.getLastUpdate());
        assertTrue(order.getLastUpdate().isEqual(lastUpdateBeforeAction));
    }
}
