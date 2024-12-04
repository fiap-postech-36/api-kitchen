Feature: Get All Order Statuses

  Scenario: Successfully retrieve all order statuses
    Given the following order statuses exist:
      | id   | status    |
      | 123  | RECEIVED   |
      | 456  | FINISHED |
    When the get all order statuses is executed
    Then the system should return:
      | id   | status    |
      | 123  | RECEIVED   |
      | 456  | FINISHED |