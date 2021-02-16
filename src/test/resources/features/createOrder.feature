Feature: Validate Crate Order feature
  As a User I want to create a PayPal order
  to achieve some business goal

  Scenario Outline: Validate Create Order feature with valid detail
    Given User creates order with currency code "<currencyCode>", value "<currencyValue>" and intent "<intent>"

    Examples:
      | currencyCode | currencyValue | intent  |
      | USD          | 500.00        | CAPTURE |