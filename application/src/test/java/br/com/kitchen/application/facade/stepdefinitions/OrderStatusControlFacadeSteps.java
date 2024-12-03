package br.com.kitchen.application.facade.stepdefinitions;

import br.com.kitchen.application.facade.OrderStatusControlFacade;
import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.application.usecase.kitchen.CancelOrderStatusCase;
import br.com.kitchen.application.usecase.kitchen.CreateOrderStatusCase;
import br.com.kitchen.application.usecase.kitchen.GetAllOrderStatusCase;
import br.com.kitchen.application.usecase.kitchen.UpdateOrderStatusCase;
import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

import static org.mockito.Mockito.*;

public class OrderStatusControlFacadeSteps {

    @Mock
    private CreateOrderStatusCase createOrderStatusCase;
    @Mock
    private UpdateOrderStatusCase updateOrderStatusCase;
    @Mock
    private CancelOrderStatusCase cancelOrderStatusCase;
    @Mock
    private GetAllOrderStatusCase getAllOrderStatusCase;
    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private OrderStatusControlFacade orderStatusControlFacade;

    private OrderStatusControlInput orderStatusControlInput;
    private OrderStatusControl orderStatusControl;
    private List<OrderStatusControl> orderStatusControls;

    @Given("que o pedido com ID {string} é criado com status {string}")
    public void que_o_pedido_com_id_e_criado_com_status(String orderId, String status) {
        MockitoAnnotations.openMocks(this);

        orderStatusControl = new OrderStatusControl(orderId);
        orderStatusControl.setOrderStatus(OrderStatus.valueOf(status));

        orderStatusControlInput = new OrderStatusControlInput(orderId);
    }

    @When("eu crio o pedido")
    public void eu_crio_o_pedido() {
        orderStatusControlFacade.createOrder(orderStatusControlInput);
    }

    @Then("o pedido deve ser criado com sucesso")
    public void o_pedido_deve_ser_criado_com_sucesso() {
        verify(createOrderStatusCase, times(1)).execute(orderStatusControlInput);
    }

    @Given("que o pedido com o ID {string} tem o status {string}")
    public void que_o_pedido_com_id_tem_o_status(String orderId, String status) {
        MockitoAnnotations.openMocks(this);
        orderStatusControl = new OrderStatusControl(orderId);
        orderStatusControlInput = new OrderStatusControlInput(orderId);
        when(updateOrderStatusCase.execute(orderStatusControlInput)).thenReturn(OrderStatus.valueOf(status));
    }

    @When("eu atualizo o status do pedido")
    public void eu_atualizo_o_status_do_pedido() {
        orderStatusControlFacade.updateOrder(orderStatusControlInput);
    }

    @Then("o status do pedido deve ser chamado")
    public void o_status_do_pedido_deve_ser() {
        verify(updateOrderStatusCase, times(1)).execute(orderStatusControlInput);
    }

    @Then("a mensagem deve ser enviada para o RabbitMQ")
    public void a_mensagem_deve_ser_enviada_para_o_rabbitmq() {
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), anyString());
    }

    @Given("que o pedido de ID {string} tem o status {string}")
    public void que_o_pedido_de_id_tem_o_status(String orderId, String status) {
        MockitoAnnotations.openMocks(this);
        orderStatusControl = new OrderStatusControl(orderId);
        orderStatusControlInput = new OrderStatusControlInput(orderId);
        when(updateOrderStatusCase.execute(orderStatusControlInput)).thenReturn(OrderStatus.valueOf(status));
    }

    @When("eu atualizo o status desse pedido")
    public void eu_atualizo_o_status_desse_pedido() {
        orderStatusControlFacade.updateOrder(orderStatusControlInput);
    }

    @Then("a atualização do status do pedido deve ser chamado")
    public void a_atualizacao_status_do_pedido_deve_ser() {
        verify(updateOrderStatusCase, times(1)).execute(orderStatusControlInput);
    }

    @Then("a mensagem deve não ser enviada para o RabbitMQ")
    public void a_mensagem_nao_deve_ser_enviada_para_o_rabbitmq() {
        verify(rabbitTemplate, times(0)).convertAndSend(anyString(), anyString(), anyString());
    }

    @Given("que o pedido com ID {string} tem o status {string}")
    public void que_o_pedido_com_id_tem_o_status_para_cancelar(String orderId, String status) {
        MockitoAnnotations.openMocks(this);
        orderStatusControl = new OrderStatusControl(orderId);
        orderStatusControl.setOrderStatus(OrderStatus.valueOf(status));
        orderStatusControlInput = new OrderStatusControlInput(orderId);
    }

    @When("eu cancelo o pedido")
    public void eu_cancelo_o_pedido() {
        orderStatusControlFacade.cancelOrder(orderStatusControlInput);
    }

    @Then("o pedido deve ser cancelado com sucesso")
    public void o_pedido_deve_ser_cancelado_com_sucesso() {
        verify(cancelOrderStatusCase, times(1)).execute(orderStatusControlInput);
    }

    @When("eu obtenho todos os pedidos")
    public void eu_obtenho_todos_os_pedidos() {
        MockitoAnnotations.openMocks(this);
        orderStatusControls = List.of(new OrderStatusControl("123"));
        when(getAllOrderStatusCase.execute()).thenReturn(orderStatusControls);
        orderStatusControlFacade.getAllOrder();
    }

    @Then("eu devo obter a lista de pedidos")
    public void eu_devo_obter_a_lista_de_pedidos() {
        verify(getAllOrderStatusCase, times(1)).execute();
    }
}