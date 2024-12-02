@GetAllOrderStatusCase
Feature: Obter todos os status dos pedidos

  Scenario: Obter todos os pedidos com sucesso
    Given que existam pedidos com status "Criado" e "Finalizado"
    When eu recupero todos os status dos pedidos
    Then todos os pedidos devem ser retornados
