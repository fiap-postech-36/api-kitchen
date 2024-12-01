package br.com.kitchen.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class CancelOrderSteps {

    private String status;
    private String orderId;

    @Given("que eu tenho um pedido com o status {string}")
    public void givenOrderStatus(String status) {
        this.status = status;
    }

    @When("eu cancelo o pedido com o ID {string}")
    public void whenCancelOrder(String orderId) {
        this.orderId = orderId;
        // Simulação de cancelamento do pedido
        this.status = "CANCELADO";
    }

    @Then("o status do pedido deve ser {string}")
    public void thenOrderStatus(String expectedStatus) {
        Assertions.assertEquals(expectedStatus, this.status);
    }

    @Given("que não existe nenhum pedido com o ID {string}")
    public void givenNoOrderWithId(String orderId) {
        this.orderId = orderId;
        // Simulação de pedido inexistente
        this.status = null;
    }

    @When("eu tento cancelar o pedido com o ID {string}")
    public void whenTryCancelNonExistentOrder(String orderId) {
        // Simulação de falha ao tentar cancelar um pedido inexistente
        if (this.status == null) {
            throw new RuntimeException("Pedido não encontrado");
        }
    }

    @Then("uma exceção deve ser lançada informando que o pedido não foi encontrado")
    public void thenExceptionThrown() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            whenTryCancelNonExistentOrder(orderId);
        });
    }
}
