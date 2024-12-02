package br.com.kitchen.infra.entity.stepfunction;

import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.infra.entity.OrderStatusControlEntity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderStatusControlEntitySteps {

    private OrderStatusControlEntity orderStatusControlEntity;

    @Given("que eu criei uma OrderStatusControlEntity com o ID {string}, status {string} e data de atualização {string}")
    public void que_eu_criei_uma_OrderStatusControlEntity_com_o_ID_status_e_data_de_atualização(String orderId, String orderStatus, String lastUpdate) {
        this.orderStatusControlEntity = OrderStatusControlEntity.builder()
                .orderId(orderId)
                .orderStatus(OrderStatus.valueOf(orderStatus))
                .lastUpdate(LocalDate.parse(lastUpdate))
                .build();
    }

    @Then("a OrderStatusControlEntity deve ter o ID {string}")
    public void a_OrderStatusControlEntity_deve_ter_o_ID(String orderId) {
        assertEquals(orderId, orderStatusControlEntity.getOrderId());
    }

    @Then("a OrderStatusControlEntity deve ter o status {string}")
    public void a_OrderStatusControlEntity_deve_ter_o_status(String orderStatus) {
        assertEquals(OrderStatus.valueOf(orderStatus), orderStatusControlEntity.getOrderStatus());
    }

    @Then("a OrderStatusControlEntity deve ter a data de atualização {string}")
    public void a_OrderStatusControlEntity_deve_ter_a_data_de_atualização(String lastUpdate) {
        assertEquals(LocalDate.parse(lastUpdate), orderStatusControlEntity.getLastUpdate());
    }

    @When("eu atualizo a OrderStatusControlEntity para ter o status {string} e data de atualização {string}")
    public void eu_atualizo_a_OrderStatusControlEntity_para_ter_o_status_e_data_de_atualização(String orderStatus, String lastUpdate) {
        orderStatusControlEntity.setOrderStatus(OrderStatus.valueOf(orderStatus));
        orderStatusControlEntity.setLastUpdate(LocalDate.parse(lastUpdate));
    }
}