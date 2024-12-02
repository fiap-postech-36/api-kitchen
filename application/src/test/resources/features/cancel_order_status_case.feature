@CancelOrderStatusCase
Feature: Cancelar o status de um pedido

  Scenario: Cancelar um pedido com sucesso
    Given que eu tenha um pedido com ID "123"
    When eu cancelo o pedido com ID "123"
    Then o pedido deve estar com o status "CANCELED"


