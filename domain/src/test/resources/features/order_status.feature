@OrderStatusTest
Feature: OrderStatus Enum Behavior

  Scenario: Retrieve OrderStatus by order
    Given the order value is 0
    When I call getFromOrder
    Then the OrderStatus should be CREATED

  Scenario: Retrieve the next OrderStatus
    Given the current OrderStatus is CREATED
    When I call getNext
    Then the next OrderStatus should be RECEIVED

  Scenario: Retrieve OrderStatus for a non-existent order
    Given the order value is 99
    When I call getFromOrder
    Then the OrderStatus should be null

