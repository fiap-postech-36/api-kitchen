@OrderStatusControlGatewayImpTest
Feature: OrderStatusControlGateway with Mocked Repository

  Scenario: Save an OrderStatusControl
    Given a valid OrderStatusControl object for saving
    When I call save on the OrderStatusControlGateway
    Then the OrderStatusControl should be saved successfully

  Scenario: Find an OrderStatusControl by ID
    Given an existing OrderStatusControl ID for retrieval
    When I call findById on the OrderStatusControlGateway
    Then the correct OrderStatusControl should be returned

  Scenario: Find all OrderStatusControls
    When I call findAll on the OrderStatusControlGateway
    Then the returned collection should contain all mocked OrderStatusControls
