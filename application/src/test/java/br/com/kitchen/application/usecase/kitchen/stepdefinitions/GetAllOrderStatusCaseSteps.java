package br.com.kitchen.application.usecase.kitchen.stepdefinitions;

import br.com.kitchen.application.usecase.kitchen.GetAllOrderStatusCase;
import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.domain.gateway.OrderStatusControlGateway;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class GetAllOrderStatusCaseSteps {

    @Mock
    private OrderStatusControlGateway orderStatusControlGateway;

    @InjectMocks
    private GetAllOrderStatusCase getAllOrderStatusCase;

    private List<OrderStatusControl> orderStatusControls;

    @Given("que existam pedidos com status {string} e {string}")
    public void que_existem_pedidos_com_status_e(String status1, String status2) {
        MockitoAnnotations.openMocks(this);

        OrderStatusControl order1 = new OrderStatusControl("1");
        order1.setOrderStatus(OrderStatus.CREATED);

        OrderStatusControl order2 = new OrderStatusControl("2");
        order2.setOrderStatus(OrderStatus.FINISHED);

        orderStatusControls = List.of(order1, order2);

        when(orderStatusControlGateway.findAll()).thenReturn(orderStatusControls);
    }

    @When("eu recupero todos os status dos pedidos")
    public void eu_recupero_todos_os_status_dos_pedidos() {
        orderStatusControls = getAllOrderStatusCase.execute();
    }

    @Then("todos os pedidos devem ser retornados")
    public void todos_os_pedidos_devem_ser_retornados() {
        assertNotNull(orderStatusControls);
        assertEquals(2, orderStatusControls.size());

        assertEquals(OrderStatus.CREATED, orderStatusControls.get(0).getOrderStatus());
        assertEquals(OrderStatus.FINISHED, orderStatusControls.get(1).getOrderStatus());

        verify(orderStatusControlGateway, times(1)).findAll();
    }
}