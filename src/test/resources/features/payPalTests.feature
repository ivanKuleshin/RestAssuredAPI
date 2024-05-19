@PayPalTest
Feature: PayPal tests
  In order to verify the functionality of PayPal API
  As a QA engineer
  I want to run these tests

  Scenario: Create and get order
    Given I have the test data for creating an order
    When I send a request to create an order
    Then The order is created successfully
    And I send a request to get the order
    Then The order is retrieved successfully

  Scenario: Create and get order with invalid data
    Given I have the test data for creating an order with invalid data
    When I send a request to create an invalid order
    Then The order is not created successfully