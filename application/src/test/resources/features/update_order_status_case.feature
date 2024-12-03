@UpdateOrderStatusCase
Feature: Atualizar o status do pedido

  Scenario: Atualizar o status do pedido de "Criado" para "Recebido"
    Given que o pedido com ID "1" tem o status "CREATED"
    When eu atualizo o status do pedido
    Then o status do pedido deve ser "RECEIVED"
