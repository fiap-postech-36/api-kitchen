Feature: Create Order Status

  Scenario: Successfully create an order status
    Given a new order with ID "456"
    When the create order status is executed
    Then the order status should be saved successfully