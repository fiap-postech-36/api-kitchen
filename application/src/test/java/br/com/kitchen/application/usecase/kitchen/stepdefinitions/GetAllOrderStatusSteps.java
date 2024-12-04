package br.com.kitchen.application.usecase.kitchen.stepdefinitions;

import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.domain.gateway.OrderStatusControlGateway;
import br.com.kitchen.application.usecase.kitchen.GetAllOrderStatusCase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetAllOrderStatusSteps {

    private final OrderStatusControlGateway orderStatusControlGateway = Mockito.mock(OrderStatusControlGateway.class);
    private final GetAllOrderStatusCase getAllOrderStatusCase = new GetAllOrderStatusCase(orderStatusControlGateway);

    private List<OrderStatusControl> mockedOrderStatuses;
    private List<OrderStatusControl> result;

    @Given("the following order statuses exist:")
    public void theFollowingOrderStatusesExist(DataTable dataTable) {
        mockedOrderStatuses = new ArrayList<>();
        dataTable.asMaps().forEach(row -> {
            OrderStatusControl orderStatusControl = new OrderStatusControl(row.get("id"), OrderStatus.valueOf(row.get("status")));
            mockedOrderStatuses.add(orderStatusControl);
        });

        when(orderStatusControlGateway.findAll()).thenReturn(mockedOrderStatuses);
    }

    @When("the get all order statuses is executed")
    public void theGetAllOrderStatusesIsExecuted() {
        result = getAllOrderStatusCase.execute();
    }

    @Then("the system should return:")
    public void theSystemShouldReturn(DataTable expectedDataTable) {
        List<OrderStatusControl> expectedOrderStatuses = new ArrayList<>();
        expectedDataTable.asMaps().forEach(row -> {
            OrderStatusControl orderStatusControl = new OrderStatusControl(row.get("id"), OrderStatus.valueOf(row.get("status")));
            expectedOrderStatuses.add(orderStatusControl);
        });

        assertEquals(expectedOrderStatuses.size(), result.size());
        for (int i = 0; i < expectedOrderStatuses.size(); i++) {
            assertEquals(expectedOrderStatuses.get(i).getOrderId(), result.get(i).getOrderId());
            assertEquals(expectedOrderStatuses.get(i).getOrderStatus(), result.get(i).getOrderStatus());
        }

        verify(orderStatusControlGateway, times(1)).findAll();
    }
}