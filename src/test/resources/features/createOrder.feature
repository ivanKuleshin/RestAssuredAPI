Feature: Validate Crate Order feature
  As a User I want to create a PayPal order
  to achieve some business goal

  Scenario Outline: Validate Create Order feature with valid detail
    Given User gets access token
    When User set currency code as "<currencyCode>" and value as "<currencyValue>"
    And User verify the status is "CREATED"

    Examples:
      | currencyCode | currencyValue |
      | USD          | 500.00        |