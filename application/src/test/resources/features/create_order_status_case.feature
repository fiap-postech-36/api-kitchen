@CreateOrderStatusCase
Feature: Criar status de um pedido

  Scenario: Criar um pedido com status CREATED
    Given que eu tenha um pedido sem ID
    When eu crio o pedido
    Then o pedido deve ser salvo com o status "CREATED"
