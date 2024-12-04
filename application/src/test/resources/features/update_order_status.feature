Feature: Progress Order Status to the Next Step

  Scenario: Successfully progress an order status
    Given an order exists in the system with ID "789" and current status "READY"
    When the system processes the order to the next step
    Then the order status should transition to "FINISHED"