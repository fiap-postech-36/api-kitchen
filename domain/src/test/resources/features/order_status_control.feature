@OrderStatusControlTest
Feature: Controle de status de pedidos

  Scenario: Criar um pedido com status inicial
    Given um pedido com ID "12345" é criado
    Then o status do pedido deve ser "CREATED"
    And a última atualização deve ser registrada na criação

  Scenario: Avançar o status do pedido
    Given um pedido com ID "12345" é criado
    When o status do pedido é avançado
    Then o status do pedido deve ser "RECEIVED"
    And a última atualização deve ser registrada

  Scenario: Cancelar o pedido
    Given um pedido com ID "12345" é criado
    When o pedido é cancelado
    Then o status do pedido deve ser "CANCELED"
    And a última atualização deve ser registrada
