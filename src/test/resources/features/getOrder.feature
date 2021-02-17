Feature: Validate Get Order feature
  As a User I want to get a PayPal order
  to achieve some business goal

  Scenario Outline: Validate Get Order feature with valid ID
    Given User gets order by ID "<orderId>"

    Examples:
    | orderId           |
    | 8KX99565TC966531G |