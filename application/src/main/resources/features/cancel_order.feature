Feature: Cancelar status do pedido

  Scenario: Cancelamento do pedido com ID válido
    Given que eu tenho um pedido com o status "PENDENTE"
    When eu cancelo o pedido com o ID "12345"
    Then o status do pedido deve ser "CANCELADO"

  Scenario: Tentar cancelar um pedido com ID inválido
    Given que não existe nenhum pedido com o ID "99999"
    When eu tento cancelar o pedido com o ID "99999"
    Then uma exceção deve ser lançada informando que o pedido não foi encontrado
