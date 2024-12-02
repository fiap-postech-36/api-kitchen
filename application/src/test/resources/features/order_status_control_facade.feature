@OrderStatusControlFacade
Feature: Facade para gerenciar o status do pedido

  Scenario: Criar um novo status de pedido
    Given que o pedido com ID "1" é criado com status "CREATED"
    When eu crio o pedido
    Then o pedido deve ser criado com sucesso

  Scenario: Atualizar o status do pedido para "Finalizado" e enviar para o RabbitMQ
    Given que o pedido com o ID "2" tem o status "FINISHED"
    When eu atualizo o status do pedido
    Then o status do pedido deve ser chamado
    And a mensagem deve ser enviada para o RabbitMQ

  Scenario: Atualizar o status do pedido para "Recebido" e nao enviar para o RabbitMQ
    Given que o pedido de ID "2" tem o status "RECEIVED"
    When eu atualizo o status desse pedido
    Then a atualização do status do pedido deve ser chamado
    And a mensagem deve não ser enviada para o RabbitMQ

  Scenario: Cancelar o pedido com ID "3"
    Given que o pedido com ID "3" tem o status "RECEIVED"
    When eu cancelo o pedido
    Then o pedido deve ser cancelado com sucesso

  Scenario: Obter todos os pedidos
    When eu obtenho todos os pedidos
    Then eu devo obter a lista de pedidos
