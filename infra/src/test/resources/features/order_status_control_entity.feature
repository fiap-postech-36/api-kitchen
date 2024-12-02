@OrderStatusControlEntityTest
Feature: Testando a criação e manipulação da OrderStatusControlEntity

  Scenario: Criar OrderStatusControlEntity com todos os atributos
    Given que eu criei uma OrderStatusControlEntity com o ID "123", status "CREATED" e data de atualização "2024-12-01"
    Then a OrderStatusControlEntity deve ter o ID "123"
    And a OrderStatusControlEntity deve ter o status "CREATED"
    And a OrderStatusControlEntity deve ter a data de atualização "2024-12-01"

  Scenario: Atualizar os dados de OrderStatusControlEntity
    Given que eu criei uma OrderStatusControlEntity com o ID "456", status "CREATED" e data de atualização "2024-12-01"
    When eu atualizo a OrderStatusControlEntity para ter o status "RECEIVED" e data de atualização "2024-12-02"
    Then a OrderStatusControlEntity deve ter o status "RECEIVED"
    And a OrderStatusControlEntity deve ter a data de atualização "2024-12-02"

