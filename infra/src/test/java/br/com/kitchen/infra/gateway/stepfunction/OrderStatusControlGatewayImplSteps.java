package br.com.kitchen.infra.gateway.stepfunction;

import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import br.com.kitchen.infra.entity.OrderStatusControlEntity;
import br.com.kitchen.infra.gateways.OrderStatusControlGatewayImpl;
import br.com.kitchen.infra.mapper.OrderStatusControlMapper;
import br.com.kitchen.infra.repository.OrderStatusControlRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class OrderStatusControlGatewayImplSteps {

    private static String ORDER_ID = "123";

    private OrderStatusControlRepository mockRepository;
    private OrderStatusControlGatewayImpl gateway;
    private OrderStatusControlMapper mockMapper;

    private OrderStatusControl inputOrderStatusControl;
    private Optional<OrderStatusControl> resultOrderStatusControl;
    private Collection<OrderStatusControl> resultCollection;

    @Given("a valid OrderStatusControl object for saving")
    public void aValidOrderStatusControlObjectForSaving() {
        mockRepository = Mockito.mock(OrderStatusControlRepository.class);
        mockMapper = Mockito.mock(OrderStatusControlMapper.class);

        gateway = new OrderStatusControlGatewayImpl(mockRepository);

        inputOrderStatusControl = new OrderStatusControl(ORDER_ID);

        OrderStatusControlEntity mockEntity = new OrderStatusControlEntity(inputOrderStatusControl.getOrderId(), inputOrderStatusControl.getOrderStatus(), LocalDate.now());
        Mockito.when(mockMapper.orderStatusControlToOrderStatusControlEntity(inputOrderStatusControl)).thenReturn(mockEntity);
        Mockito.when(mockMapper.orderStatusControlEntityToOrderStatusControl(mockEntity)).thenReturn(inputOrderStatusControl);
        Mockito.when(mockRepository.save(any())).thenReturn(mockEntity);
    }

    @When("I call save on the OrderStatusControlGateway")
    public void iCallSaveOnTheOrderStatusControlGateway() {
        resultOrderStatusControl = gateway.save(inputOrderStatusControl);
    }

    @Then("the OrderStatusControl should be saved successfully")
    public void theOrderStatusControlShouldBeSavedSuccessfully() {
        Assertions.assertTrue(resultOrderStatusControl.isPresent());
        Assertions.assertEquals(inputOrderStatusControl.getOrderId(), resultOrderStatusControl.get().getOrderId());
    }

    @Given("an existing OrderStatusControl ID for retrieval")
    public void anExistingOrderStatusControlIDForRetrieval() {
        mockRepository = Mockito.mock(OrderStatusControlRepository.class);
        mockMapper = Mockito.mock(OrderStatusControlMapper.class);

        gateway = new OrderStatusControlGatewayImpl(mockRepository);

        inputOrderStatusControl = new OrderStatusControl(ORDER_ID);

        OrderStatusControlEntity mockEntity = new OrderStatusControlEntity(inputOrderStatusControl.getOrderId(), inputOrderStatusControl.getOrderStatus(), LocalDate.now());
        Mockito.when(mockRepository.findById(ORDER_ID)).thenReturn(Optional.of(mockEntity));
        Mockito.when(mockMapper.orderStatusControlEntityToOrderStatusControl(any())).thenReturn(inputOrderStatusControl);
    }

    @When("I call findById on the OrderStatusControlGateway")
    public void iCallFindByIdOnTheOrderStatusControlGateway() {
        resultOrderStatusControl = gateway.findById(ORDER_ID);
    }

    @Then("the correct OrderStatusControl should be returned")
    public void theCorrectOrderStatusControlShouldBeReturned() {
        Assertions.assertTrue(resultOrderStatusControl.isPresent());
        Assertions.assertEquals(ORDER_ID, resultOrderStatusControl.get().getOrderId());
    }

    @When("I call findAll on the OrderStatusControlGateway")
    public void iCallFindAllOnTheOrderStatusControlGateway() {
        mockRepository = Mockito.mock(OrderStatusControlRepository.class);
        mockMapper = Mockito.mock(OrderStatusControlMapper.class);

        gateway = new OrderStatusControlGatewayImpl(mockRepository);

        OrderStatusControlEntity mockEntity1 = new OrderStatusControlEntity();
        OrderStatusControlEntity mockEntity2 = new OrderStatusControlEntity();

        OrderStatusControl mockControl1 = new OrderStatusControl(ORDER_ID);
        OrderStatusControl mockControl2 = new OrderStatusControl("12");

        Mockito.when(mockRepository.findAll()).thenReturn(List.of(mockEntity1, mockEntity2));
        Mockito.when(mockMapper.orderStatusControlEntityToOrderStatusControl(mockEntity1)).thenReturn(mockControl1);
        Mockito.when(mockMapper.orderStatusControlEntityToOrderStatusControl(mockEntity2)).thenReturn(mockControl2);

        resultCollection = gateway.findAll();
    }

    @Then("the returned collection should contain all mocked OrderStatusControls")
    public void theReturnedCollectionShouldContainAllMockedOrderStatusControls() {
        Assertions.assertNotNull(resultCollection);
        Assertions.assertEquals(2, resultCollection.size());
    }
}