Feature: Cancel Order Status

  Scenario: Successfully cancel an order
    Given an existing order with ID "123" and status "PENDING"
    When the cancel order status is executed
    Then the order status should be "CANCELLED"